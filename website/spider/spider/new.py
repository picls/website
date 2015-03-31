'''
Created on 2015-1-12

@author: anzhixiang
'''
#encoding=gbk

import urllib
import urllib.request
import re


headers = {'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
           'Accept-Encoding': 'gzip, deflate, compress',
           'Accept-Language': 'en-us;q=0.5,en;q=0.3',
           'Cache-Control': 'max-age=0',
           'Connection': 'keep-alive',
           'X-Requested-With': 'XMLHttpRequest',
           'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36' }


def getHtml(url):
    #data = urllib.parse.urlencode(headers)
    page = urllib.request.urlopen(url)
    html = str(page.read().decode('GBK'))
    return html

    
html = getHtml("http://bbs.byr.cn/article/Python/2735")

print(html)

pattern1 = re.compile(r'<td class="title_9\\sbg-odd">.*?</td>')
pattern2 = re.compile(r'<a.*?</a>')
pattern3 = re.compile(r'type')
match = pattern3.match(html)
print(match)
if(match):
    print(match.group())
