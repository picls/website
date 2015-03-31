#coding=gbk
'''
Created on 2015-1-12

@author: anzhixiang
'''
import requests
import re
import os
import sys
import datetime
import bs4
from bs4 import BeautifulSoup
import db

conn = db.db()

#import mysql.connector



#print('本脚本可以帮助您查找出某个版面历史上某个id参与过的帖子，并保存在本地查看，请按下面提示操作'+'\n')

userid = 1 #input('请直接输入您想查询的用户id： ')
board_list = {1:'Feeling',2:'Python',3:'Job',4:'Friends',5:'Talking',6:'WorkLife',7:'AimGraduate',8:'Home',
        9:'DigiLife',10:'Food',11:'Basketball',12:'Football',13:'WWWTechnology',14:'ACM_ICPC',15:'JAVA',101:'jobinfo'}
boardx = 'Talking'  #input('请直接输入您所选择查询的版面的英文完整名称，例如："Python","StudyShare","WorkLife": ')
PAGE = 1  #int(input('请输入您想查询的页数，一页代表最新的30篇帖子，为了爱护论坛的服务器，强烈建议您不要经常超过 10页(300篇啊): '))


source_url = "http://bbs.byr.cn"
board_url = "http://bbs.byr.cn/board/" + boardx +"?p="
filepath= 'bbs.byr.cn/page/' + boardx +'/'

headers = {'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
           'Accept-Encoding': 'gzip, deflate, compress',
           'Accept-Language': 'en-us;q=0.5,en;q=0.3',
           'Cache-Control': 'max-age=0',
           'Connection': 'keep-alive',
        
           'X-Requested-With': 'XMLHttpRequest',
           'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36' }


def write_file(text , file_name = 'x.txt' , url = ''):
    file = open(file_name , 'w')
    if(url != ''):
        text = str(requests.get(url,headers=headers).content.decode('GBK'))
    file.write(text)
    

def messy_code(text):
    text = text.replace('|&ensp;','')
    text = text.replace('&ensp;','')
    text = text.replace('&ensp','')
    text = text.replace('&emsp;','')
    text = text.replace('&emsp','')        
    text = text.replace('&nbsp;','')
    text = text.replace('&nbsp','')      
    return text


def tag(text , tag , if_replace = False):
    pattern = re.compile('</?'+tag+'.*?/?>')
    brackets = pattern.findall(text)
    if(if_replace):
        for i in range(len(brackets)):
            text = text.replace(brackets[i],'')
        return text
    else: return brackets
    
    
def soup_board_page(url):
    bbocont = requests.get(url,headers=headers).content.decode('gbk')
    print(bbocont)
    bbocont = messy_code(bbocont)
    soup = BeautifulSoup(bbocont.encode(encoding='utf-8', errors='strict'),from_encoding = 'utf-8')
    #print(soup)

    pages = [] #0url地址，1标题，2页数，3发帖时间，4作者，5回复数量
    
    #置顶话题
    tr_top = soup.find_all('tr',{"class":"top"})
    for t in tr_top:
        pass#print(str(t))
        #write_file(str(t.encode('gbk')),'tr.txt')
        #print(str(t).encode('gbk'))

    #内容
    tr = soup.findAll('tr',{"class":""})
    for i,t in enumerate(tr):
        #print(t)
        if(t != None):
            td_8 = t.find('td',{"class":"title_8"}) #状态
            td_9 = t.find('td',{"class":"title_9"}) #主题
            td_10 = t.find('td',{"class":"title_10"}) #创建时间
            td_11 = t.find('td',{"class":"title_11"}) #回复数量
            td_12 = t.find('td',{"class":"title_12"}) #作者
            
            if(td_8 == None): td_8 = ''; relative_url = ''
            else: 
                relative_url = td_8.find('a')['href']
            if(td_9 == None): td_9 = ''; title = ''; page_num = 0
            else: 
                title = td_9.text
                x = title.rfind('[')
                y = title.rfind(']')
                z = title[x+1:y]
                if(z.isdigit()):
                    title = title[0:title.rfind('[')]
                page_num = len(td_9.findAll('a'))
            if(td_10 == None): td_10 = ''; time = ''
            else:
                time = td_10.text
            if(td_11 == None): td_11 = ''; reply_num = 0
            else:
                reply_num = td_11.text
            if(td_12 == None): td_12 = ''; author = ''
            else:
                author = td_12.text
            
            temp_page = []
            temp_page=[relative_url,title,page_num,time,reply_num,author]
            pages.append(temp_page)
        
    #for p in pages:
    #    print(p)
    return pages    

today = str(datetime.datetime.now().strftime("%Y-%m-%d"))
def store_board_page(page,board):
    sql = ''' select id from s_user where name = '%s' ''' %(page[5])
    result = conn.query(sql)
    if(len(result) == 0):
        sql = ''' insert into s_user values(null,'%s',null,null,null,null,null,null,null) ''' %(page[5])
        conn.execute(sql)
        
    #print(page)
    id = page[0][page[0].rfind('/')+1:]
    page[1] = page[1].replace("'","\\'")
    #print(id)
    sql = ''' select * from s_page where id = %s ''' %(id)
    result = conn.query(sql)
    if(len(page[3])==10): time = "str_to_date('%s','%%Y-%%m-%%d')" %(page[3])
    else: time = "str_to_date(concat('%s','%s'),'%%Y-%%m-%%d %%H:%%i:%%s')" %(today+' ',page[3])
    if(result != None)and(len(result) != 0)and(result[0][0] != None)and(result[0][0] != ''):
        sql = '''
            update s_page set title = '%s' , page_num = %s , time = %s , reply_num = %s , 
            creator = (select id from s_user where name = '%s')
            where id = %s
        ''' %(page[1],page[2],time,page[4],page[5],id)
        conn.execute(sql)  
    else:
        sql = ''' 
            insert into s_page values(%s,(select id from s_board where name = '%s'),'%s','%s',%s,%s,%s,
            (select id from s_user where name = '%s')) 
        ''' %(id,board,page[0],page[1],page[2],time,page[4],page[5])
        conn.execute(sql)
    
        
        
def soup_page(page):
    page_num = int(page[2])
    urls = []
    posts = [] #0{}发帖人信息，1内容，2时间，3来源
    for i in range(page_num):
        urls.append(source_url + page[0] + '?p=' + str(i+1))
    #for i in range(page_num):
        try:
            page_con = requests.get(urls[i],headers=headers).content.decode('gbk')
        except Exception:
            continue
            print()
        page_con = messy_code(page_con)
        
        soup = BeautifulSoup(page_con)
        b_content = soup.find('div',{"class":"b-content corner"})
        a_wrap = b_content.findAll('div',{"class":"a-wrap corner"})
        for j in range(len(a_wrap)):
            a_head = a_wrap[j].find('tr',{"class":"a-head"})
            a_body = a_wrap[j].find('tr',{"class":"a-body"})
            h_a_left = a_head.find('td',{"class":"a-left"})
            b_a_left = a_body.find('td',{"class":"a-left"})
            if(h_a_left.find('a') is None): continue;
            uname = h_a_left.find('a').string
            if(b_a_left.find('div',{"class":"a-u-uid"}) is None): continue;
            uid = b_a_left.find('div',{"class":"a-u-uid"}).string
            if(b_a_left.find('dl',{"class":"a-u-info"}) is None): continue;
            a_u_info = b_a_left.find('dl',{"class":"a-u-info"})
            level = a_u_info.contents[1].string
            article = a_u_info.contents[3].string
            integrate = a_u_info.contents[5].string
            if(len(a_u_info.contents)>=7): constellation = a_u_info.contents[7].string
            else: constellation = ''
            user_info = [uname,uid,level,article,integrate,constellation]
            
            b_a_content = a_body.find('td',{"class":"a-content"})
            contents = str(b_a_content.contents[0])
            b = contents.find('北邮人论坛')
            a = contents.find('站内')
            time = contents[b+7:a-3]
            b = contents.find('--')
            content = contents[a+2:b].lstrip()
            content = tag(content , 'br' , True)
            content = tag(content , 'font' , True)
            a = contents.find('※')
            b = contents.find(']',a)
            source = contents[a+6:b+1]

            posts.append([user_info,content,time,source])
            #print(posts[-1])
            
    return posts


month_dic = {'JAN':'01','FEB':'02','MAR':'03','APR':'04','MAY':'05','JUN':'06',
             'JUL':'07','AUG':'08','SEP':'09','OCT':'10','NOV':'11','DEC':'12'}
def change_time(post):
    day = 0
    if(post[2] == ''): return ''
    #print(post[2])
    k = post[2].count(' ')
    a = post[2].find(' ')
    c = post[2].rfind(' ')+1
    b = post[2].rfind(' ',0,c-1)
    year = post[2][c:]
    month = post[2][a+1:a+4]
    if(k == 3): day = post[2][a+4:b]
    elif(k == 4): day = post[2][a+5:b]
    hour = post[2][b+1:c]
    if(month.upper() in month_dic):
        month = month_dic[month.upper()]
    if(len(str(day)) == 1):
        day = '0' + str(day)
    time = str(year)+'-'+str(month)+'-'+str(day)+' '+str(hour)
    #print(time)
    return str(time).rstrip().strip()


def store_user(post):
    if(post[0][1] == None): post[0][1] = ''
    post[0][1] = post[0][1].replace("'","\\'")
    post[0][1] = post[0][1].rstrip("\\")
    sql = ''' select * from s_user where name = '%s' ''' %(post[0][0])
    result = conn.query(sql)
    if(result != None)and(len(result) != 0)and(result[0][0] != None)and(result[0][0] != ''):
        sql = ''' 
            update s_user set
            nickname='%s',level='%s',article=%s,integrate=%s,last_login_time=null where name = '%s' 
        ''' %(post[0][1],post[0][2],post[0][3],post[0][4],post[0][0])
        conn.execute(sql)
    else:
        sql = ''' 
            insert into s_user values(null,'%s','%s','%s',%s,%s,'%s',null,null) 
        ''' %(post[0][0],post[0][1],post[0][2],post[0][3],post[0][4],post[0][5])
        conn.execute(sql)


def judge_page(page,page_id):
    sql = ''' select count(*) from s_post where page_id = %s ''' %(page_id)
    result = conn.query(sql)
    if(result[0][0] == page[4]): return False
    else: return True

def store_page(posts,page_id):
    sql = ''' select count(*) from s_post where page_id = %s ''' %(page_id)
    #print(sql)
    result = conn.query(sql)
    if(result[0][0] == None)or(result[0][0] == 0):
        for post in posts:
            if(post == None): continue
            #print(post)
            try:
                post[1] = post[1].replace("'","\\'")
                if(len(post[1])>=100000): post[1] = post[1][0:100000]
                store_user(post)
        
                time = change_time(post)
                if(time == '')or(time == '--'):
                    sql = '''
                        insert into s_post values(null,%s,(select id from s_user where name = '%s'),
                        '%s',null,'%s')
                    ''' %(page_id , post[0][0] , post[1] , post[3])
                    conn.execute(sql)
                else:
                    sql = '''
                        insert into s_post values(null,%s,(select id from s_user where name = '%s'),
                        '%s',str_to_date('%s','%%Y-%%m-%%d %%H:%%i:%%s'),'%s')
                    ''' %(page_id , post[0][0] , post[1] , time , post[3])
                    conn.execute(sql)
            except Exception:
                pass
    elif(len(posts) > result[0][0]):
        sql = ''' select max(time) from s_post where page_id = %s ''' %(page_id)
        result = conn.query(sql)
        if(len(result)==0)or(result[0][0] is None):
            current_end = 0
        else:
            current_end = datetime.datetime.strptime(str(result[0][0]),'%Y-%m-%d %H:%M:%S')
        for post in posts:
            try:
                if(post == None): continue
                post[1] = post[1].replace("'","\\'")
                if(len(post[1])>=100000): post[1] = post[1][0:100000]
                store_user(post)
                
                time = change_time(post)
                if(time == '')or(time == '--'):
                    sql = '''
                        insert into s_post values(null,%s,(select id from s_user where name = '%s'),
                        '%s',null,'%s')
                    ''' %(page_id , post[0][0] , post[1] , post[3])
                    conn.execute(sql)
                else:
                    end = datetime.datetime.strptime(time,'%Y-%m-%d %H:%M:%S')
                    if(end > current_end):
                        sql = '''
                            insert into s_post values(null,%s,(select id from s_user where name = '%s'),
                            '%s',str_to_date('%s','%%Y-%%m-%%d %%H:%%i:%%s'),'%s')
                        ''' %(page_id , post[0][0] , post[1] , time , post[3])
                        conn.execute(sql)
            except Exception:
                pass



#soup = BeautifulSoup(''.join(doc))
#write_file(str(soup.prettify('gbk')),'prettify.txt')
#print(soup.prettify('gbk'))
#exit(0)
    
    
    

def main_process():
    for b in board_list:
        board = board_list[b]
        board_url = "http://bbs.byr.cn/board/" + board +"?p="
        for i in range(2):
            bbourl = board_url + str(i)
            pages = soup_board_page(bbourl)
            l = 0
            for i,p in enumerate(pages):
                if(p[0]!=''):
                    store_board_page(p,board)
                    pass
                #if(p[0].endswith('4773')):
                #continue
                if(p[0]!=''): 
                    if(judge_page(p,p[0][p[0].rfind('/')+1:])):
                        posts = soup_page(p)
                        store_page(posts,p[0][p[0].rfind('/')+1:])
        



if(__name__=="__main__"):
    main_process()
    exit(0)






'''
create table s_board(
id int primary key auto_increment,
name varchar(50) not null
);

create table s_page(
id int primary key auto_increment,
board_id int not null,
url varchar(100) not null,
title varchar(200) not null,
page_num int not null,
time datetime,
reply_num int not null,
creator int not null
);

create table s_post(
id int primary key auto_increment,
page_id int not null,
creator int not null,
content varchar(10000) not null,
time datetime,
source varchar(1000)
);

create table s_user(
id int primary key auto_increment,
name varchar(50) not null,
nickname varchar(100),
level varchar(10),
article int,
integrate int,
constellation varchar(10),
create_time datetime,
last_login_time timestamp
);
'''













def spide_page(url):
    bbocont = str(requests.get(url,headers=headers).content.decode('GBK'))
    pages = [] 
    cpages = [] #0页数，1url源地址，2发帖时间，3回复数量，4作者
    pagecont = str(requests.get('http://bbs.byr.cn/article/Python/4602',headers = headers).content.decode('GBK'))
    
    pattern_top = re.compile(r'<tr class="top">.*?</tr>') #top
    pattern_9 = re.compile(r'<td class="title_9">.*?</td>') #主题
    pattern_a = re.compile(r'<a.*?>') #a标签
    pattern_url = re.compile('".*?"') #url
    pattern_10 = re.compile(r'<td class="title_10">.*?</td>') #创建时间
    pattern_11 = re.compile(r'<td class="title_11">.*?</td>') #回复
    pattern_12 = re.compile(r'<td class="title_12">.*?</td>') #创建者
    
    
    
    brackets = pattern_top.findall(bbocont)
    for i,x in enumerate(brackets):
        print(x)
        #pages.append(pattern_a.findall(x))
        #print(x)
        #print(pattern2.findall(x))
    #print(brackets)
    exit(0)
    
    for page in pages:
        tempurl = pattern_url.findall(page[0])[0].replace('"','')
        tempurl = source_url + tempurl
        temp = [len(page),tempurl]
        cpages.append(temp)
       
    
    for x in cpages:
        print(x)
        
    page_con = str(requests.get(cpages[3][1],headers=headers).content.decode('GBK'))
    
    
    '''if(match):
        print(match.group())
    print('end')'''







if(os.path.exists(filepath)):
    pass
else:
    os.makedirs(filepath)

if(PAGE>=50):
    PAGE=50
                

i=1
ROW=30


while (i <= PAGE):
    bbourl = board_url + str(i)
    print(bbourl)
    print('--------------------------')
    bbocont = str(requests.get(bbourl,headers=headers).content.decode('GBK'))
    
#    soup_board_page(bbourl)
#    exit(0)
#    spide_page(bbourl)
#    exit(0)
    

    

    ptitle_8h = bbocont.find('<td class="title_8')
    ptitle_8t = bbocont.find(r'</samp>',ptitle_8h)


    articleurl = ['']*ROW
    titlelist = ['']*ROW
    replylist = ['']*ROW
    authorlist = ['']*ROW
    j=0
    
    while(ptitle_8h != -1 and ptitle_8t !=-1 and j<=ROW):
        psamph = bbocont.find(r'<samp class="tag',ptitle_8h)
        psampt = bbocont.find(r'"></samp>',psamph)
        tagcont = bbocont[ psamph + len(r'<samp class="tag ico-pos-article-')  : psampt ]
        #print tagcont

        ptitle_11htmp = bbocont.find(r'title_11 middle', ptitle_8h)
        ptitle_11h = bbocont.find(r'">',ptitle_11htmp)
        #print ptitle_11h
        ptitle_11t = bbocont.find(r'</td>', ptitle_11h)
        #print ptitle_11t
        replyamnt = bbocont[ ptitle_11h + len('">')  : ptitle_11t ]


               

        ptitle_8h = bbocont.find('<td class="title_8',ptitle_8t)
        ptitle_8t = bbocont.find(r'</samp>',ptitle_8h)

        j +=1
        
    i +=1
print('Completed!')
