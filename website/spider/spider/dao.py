'''
Created on 2015-3-2

@author: anzhixiang
'''

import mysql.connector
import sys, os

class db:
    user = ''
    pwd  = ''
    host = ''
    db   = ''
    
    def __init__(self , user = 'root' , pwd = 'fifa2007', host = '127.0.0.1', db = 'web'):
        self.user = user
        self.pwd  = pwd
        self.host = host
        self.db   = db

    def create_conn(self):
        conn = mysql.connector.connect(user=self.user,password=self.pwd,host=self.host,database=self.db)
        cursor = conn.cursor()
        
        sql = ''' insert into s_board values(null,'Feeling') '''
        cursor.execute(sql)
        conn.commit()
        exit(0)
        for row in cursor:
            print(row)
        exit(0)
        
class connection_pool:
    x = ''