-----------------------------------------
               oracle建表语句
-----------------------------------------
由于oracle没有主键自增操作 所以采用sequence
-- Create sequence 使用在用户日志表
create sequence SEQ_QUICK                 --Sequence实例名
minvalue 1                                --最小值，可以设置为0
maxvalue 2147483647                       --最大值
start with 1                              --从1开始计数
increment by 1                            --每次加几个
cache 20;                                 --设置缓存cache个序列，如果系统down掉了或者其它情况将会导致序列不连续，也可以设置为---------NOCACHE

-- Create sequence 使用在接口日志表
create sequence SEQ_QUICK_INTERFACE_LOG              
minvalue 1                                
maxvalue 2147483647                       
start with 1                              
increment by 1                            
cache 20;                                 


1.接口表
create table QK_INTERFACE
(
  id          NUMBER,
  url         VARCHAR2(100),
  port        VARCHAR2(10),
  type        VARCHAR2(10),
  method      VARCHAR2(10),
  description VARCHAR2(100)
)
INSERT INTO interface VALUES ('1', 'http://135.149.33.87:7001/smsProxySender', '7001', 'POST', 'http', '网厅发送短信接口');
INSERT INTO interface VALUES ('2', 'http://135.149.33.87:7001/searchSendMessage', '7001', 'POST', 'http', '网厅短信日志查询');
INSERT INTO interface VALUES ('3', '135.149.16.170', '8080', 'SOCKET', 'SOCKET', '计费清单查询');
2.用户表
create table QK_USERS
(
  username VARCHAR2(20) not null primary key,
  password VARCHAR2(50),
  email    VARCHAR2(30),
  phone    VARCHAR2(15),
  enabled   NUMBER,
  islocked  Number,
  register DATE,
  url      VARCHAR2(50)
)
insert into qk_users (USERNAME, PASSWORD, EMAIL, PHONE, ENABLED, ISLOCKED, REGISTER, URL)
values ('admin', '21232f297a57a5a743894a0e4a801fc3', '1540077031@qq.com', '18919927184', 1, 0, to_date('19-07-2018', 'dd-mm-yyyy'), null,);

3.登录日志表
create table QK_USERS_LOG
(
 id number not null primary key,
 username VARCHAR2(20),
 latesttime DATE,
 ip VARCHAR2(20)
)
4.接口调用日志
create table QK_INTERFACE_LOG
(
  id          NUMBER not null primary key,
  interface_id VARCHAR2(30),
  url         VARCHAR2(100),
  in_param    VARCHAR2(4000),
  out_param   VARCHAR2(4000),
  transfer_time     date,
  used_time         VARCHAR2(10)
)
5.角色表
create table QK_ROLE(
rid number not null primary key,
rname VARCHAR2(100),
rdescription VARCHAR2(100)
)
6.用户角色表
create table QK_USER_ROLE(
urid number not null primary key,
usid number,
rid number
)
7.资源表
create table QK_RESOURCE(
resid number not null primary key,
resurl VARCHAR2(100),
resdesc VARCHAR2(100)
)
8.资源角色表
create table QK_ROLE_RESOURCE(
rrid number not null primary key,
resid number,
rid number
)
9.开放接口用户表
create table QK_API_USER(
type VARCHAR2(10) not null primary key,
appid VARCHAR2(100),
appsecret VARCHAR2(100),
whitelist VARCHAR2(100),
name VARCHAR2(30),
description VARCHAR2(100),
time    date
)





