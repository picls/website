'''
Created on 2015-3-2

@author: anzhixiang
'''

import mysql.connector
import sys, os
import copy

class db_config:
    user = ''
    pwd  = ''
    host = ''
    db   = ''
    
    def __init__(self , user , pwd , host , db):
        self.user = user
        self.pwd  = pwd
        self.host = host
        self.db   = db

class db:
    dc = ''
    cp = ''
    conn = ''
    
    def __init__(self , user = 'root' , pwd = 'fifa2007', host = '127.0.0.1', db = 'web'):
        self.dc = db_config(user , pwd , host , db)
        self.cp = connection_pool(self.dc)
        self.conn = self.get_conn()
        
    def get_conn(self):
        conn = mysql.connector.connect(user=self.dc.user,password=self.dc.pwd,host=self.dc.host,database=self.dc.db)
        return conn

    def create_conn(self):
        conn = mysql.connector.connect(user=self.dc.user,password=self.dc.pwd,host=self.dc.host,database=self.dc.db)
        cursor = conn.cursor()
        
        sql = ''' insert into s_board values(null,'Feeling') '''
        cursor.execute(sql)
        conn.commit()
        exit(0)
        for row in cursor:
            print(row)
        exit(0)
        
    def execute(self , sql , if_show = True):
        if(if_show):print(sql)
        cursor = self.conn.cursor()
        cursor.execute(sql)
        cursor.close()
        self.conn.commit()
        
    def query(self , sql , if_show = True):
        if(if_show):print(sql)
        cursor = self.conn.cursor()
        cursor.execute(sql)
        list = cursor.fetchall()
        cursor.close()
        self.conn.commit()
        return list
        
        
class connection_pool:
    pool_size = 0
    stable_size = 0
    current_size = 0
    conns = []
    flags = []
    dc = None
    
    def __init__(self , dc , pool_size = 3 , stable_size = 1):
        self.dc = dc
        self.pool_size = pool_size
        self.stable_size = stable_size
        for i in range(self.stable_size):
            conn = mysql.connector.connect(self.dc.user,self.dc.pwd,self.dc.host,self.dc.db)
            self.conns.append(conn)
        self.current_size = self.stable_size
        for i in range(self.stable_size,self.pool_size):
            self.conns.append(None)
        
    def get_conn(self):
        for i in range(self.stable_size):
            if(self.flags[i] == True):
                self.flags[i] == False
                return self.conns[i]
        for i in range(self.stable_size,self.pool_size):
            if(self.conns[i] == None):
                conn = mysql.connector.connect(self.dc.user,self.dc.pwd,self.dc.host,self.dc.db)
                self.conns[i] = conn
                self.current_size += 1
                return self.conns[i]
        return None
        
    def close(self):
        pass