'''
Created on 2015-1-12

@author: anzhixiang
'''
#encoding='GB2312'
import requests
import sys
import os



print('���ű����԰��������ҳ�ĳ��������ʷ��ĳ��id����������ӣ��������ڱ��ز鿴���밴������ʾ����'+'\n')

userid = input('��ֱ�����������ѯ���û�id�� ')
board = input('��ֱ����������ѡ���ѯ�İ����Ӣ���������ƣ����磺"Python","StudyShare","WorkLife": ')
PAGE = int(input('�����������ѯ��ҳ����һҳ�������µ�30ƪ���ӣ�Ϊ�˰�����̳�ķ�������ǿ�ҽ�������Ҫ�������� 10ҳ(300ƪ��): '))


bourl = "http://bbs.byr.cn/board/" + board +"?p="
filepath= 'bbs.byr.cn/id_list/' + userid+'/' + board +'/'

headers = {'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
           'Accept-Encoding': 'gzip, deflate, compress',
           'Accept-Language': 'en-us;q=0.5,en;q=0.3',
           'Cache-Control': 'max-age=0',
           'Connection': 'keep-alive',
        
           'X-Requested-With': 'XMLHttpRequest',
           'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36' }

if os.path.exists(filepath):
    pass
else:
    os.makedirs(filepath)

if(PAGE>=50):
    PAGE=50
                

i=1
ROW=30

while (i <= PAGE):
    bbourl = bourl + str(i)
    print(bbourl)
    print('--------------------------')
    bbocont = str(requests.get(bbourl,headers=headers).content.decode('GBK'))
    

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
       

               
        if(1):
            particleurlh = bbocont.find(r'<a target="_blank" href="',ptitle_8h )
            #print particleurlh
            particleurlt = bbocont.find(r'" title', particleurlh)
            #print particleurlt
            articleurl[j] = bbocont[ particleurlh + len('<a target="_blank" href="')  : particleurlt ]
 
            ptitlehtmp = bbocont.find(r'<a href="', psampt)
            ptitleh = bbocont.find(r'">', ptitlehtmp)
            ptitlet = bbocont.find(r'</a>', ptitleh)
            titlelist[j]= bbocont[ ptitleh + len('">')  : ptitlet ]

           
 
            pauthorhtmp1 = bbocont.find(r'<td class="title_12', psampt)
            pauthorhtmp2 = bbocont.find(r'<a href', pauthorhtmp1)
            pauthorh = bbocont.find(r'">', pauthorhtmp2)
            pauthort = bbocont.find(r'</a>', pauthorh)
            authorlist[j]= bbocont[ pauthorh + len('">')  : pauthort ]
            #print authorlist[j]
            
            replylist[j]=replyamnt
            artipages = int(round((int(replylist[j])+1+5)/10))
            #print artipages
            artipaurltmp = 'http://bbs.byr.cn'+articleurl[j]+'?p='
            n=1
            flag=0
            while(flag == 0 and n <= artipages):
                
                artipaurl= artipaurltmp +str(n)
                #print artipaurl
                articont = str(requests.get(artipaurl,headers=headers).content)
                
                puidhtmp1 = articont.find(r'<span class="a-u-name"><a href="')
                puidhtmp2 = articont.find(r'<a href=',puidhtmp1)
                puidh = articont.find(r'">',puidhtmp2)
                puidt = articont.find(r'</a>',puidh)
                uidcont = articont[puidh + len('">') : puidt]
                #print uidcont
                
                NANU=10
                k=0
                
                while(puidh!=-1 and puidt!=-1 and uidcont!=-1 and k<NANU):
                    #print uidcont
                    if(uidcont==userid):
                        #print flag
                        flag = 1
                        #print flag
                        n=0
                        print('Downloading page: '+userid+'-'+artipaurltmp+'1')
                        break

                    puidhtmp1 = articont.find(r'<span class="a-u-name"><a href="',puidt)
                    puidhtmp2 = articont.find(r'<a href=',puidhtmp1)
                    puidh = articont.find(r'">',puidhtmp2)
                    puidt = articont.find(r'</a>',puidh)
                    uidcont = articont[puidh + len('">') : puidt]
                    
                    k +=1
                    
                n +=1
                        
            while(flag==1 and n<=artipages):
                
                artipaurl= artipaurltmp +str(n)
                articont = str(requests.get(artipaurl,headers=headers).content)
                try:
                    open(filepath+replylist[j]+ '_'+'id'+' '+authorlist[j]+'_' + titlelist[j] + '.html','a').write(artipaurl+'<br><br>')
                except IOError:
                    pass
                #print 'Downloading page: '+userid+'-'+artipaurl
                pcwh = articont.find(r'<div class="a-content-wrap">')
                pcwt = articont.find(r'<font class=',pcwh)

                NANU=10
                k=0
                namecont = articont[pcwh + len('<div class="a-content-wrap">') : pcwt]
                while(pcwh!=-1 and pcwt!=-1 and namecont!=-1 and k<NANU):
                    try:
                        open(filepath+replylist[j]+ '_'+'id'+' '+authorlist[j]+'_' + titlelist[j] + '.html','a').write(namecont+'<br><br>')
                    except IOError:
                        pass
                    
                    #print 'Downloaded page: '+userid+' '+artipaurl
                    pcwh = articont.find(r'<div class="a-content-wrap">',pcwt)
                    pcwt = articont.find(r'<font',pcwh)
   
                    namecont = articont[pcwh + len('<div class="a-content-wrap">') : pcwt]

                    k +=1


                n +=1
            




           
           
        ptitle_8h = bbocont.find('<td class="title_8',ptitle_8t)
        ptitle_8t = bbocont.find(r'</samp>',ptitle_8h)
        
        j +=1
        
    i +=1
print('Completed!')
