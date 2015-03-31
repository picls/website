'''
Created on 2015-1-14

@author: anzhixiang
'''

import urllib
import urllib.request
import urllib.parse
import requests


if(__name__=='__main__'):
    url = 'http://bbs.byr.cn/user/login'
    #urllib.parse.urlencode(query, doseq, safe, encoding, errors)
    values = {'name':'picls','password':'wlkwlk123','b_login':'submit'}
    url_values = urllib.parse.urlencode(values).encode('utf_8')
    req = urllib.request.Request(url+'?')
    response = urllib.request.urlopen(req,url_values)
    html = response.read().decode('gbk')
    print(html)
    print(response.info())
    print(response.getcode())
    print(response.geturl())    
    
    #x = requests.post(url,values) 
    #print(x.content.decode('gbk'))
    
    
    
'''
Cookie: _ga=GA1.2.412906060.1419235033; 
__utma=217424581.1842967981.1407749025.1420520143.1420525539.601; 
__utmz=217424581.1409017495.54.17.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=byr%20%E7%94%9F%E5%91%BD%E5%8A%9B; 
login-user=picls; 
nforum[UTMPUSERID]=picls; 
nforum[PASSWORD]=ebeodW2vvhwt%2Fc9GDcTGLA%3D%3D; 
nforum[UTMPKEY]=34805989; 
nforum[UTMPNUM]=2113; 
nforum[BMODE]=6; 
nforum[XWJOKE]=hoho; 
Hm_lvt_38b0e830a659ea9a05888b924f641842=1420535472,1420617387,1421026443; 
Hm_lpvt_38b0e830a659ea9a05888b924f641842=1421213527
'''
    
'''
_ga=GA1.2.412906060.1419235033;
 __utma=217424581.1842967981.1407749025.1420520143.1420525539.601; 
 __utmz=217424581.1409017495.54.17.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=byr%20%E7%94%9F%E5%91%BD%E5%8A%9B; 
 nforum[BMODE]=6; 
 nforum[XWJOKE]=hoho; 
 Hm_lvt_38b0e830a659ea9a05888b924f641842=1420535472,1420617387,1421026443; 
 Hm_lpvt_38b0e830a659ea9a05888b924f641842=1421213535; 
 login-user=picls; 
 nforum[UTMPUSERID]=guest; 
 nforum[UTMPKEY]=8905616; 
 nforum[UTMPNUM]=14626
'''
    
    
    