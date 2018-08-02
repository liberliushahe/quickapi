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
  sysid       NUMBER,
  name        VARCHAR2(100),
  url         VARCHAR2(100),
  port        VARCHAR2(10),
  type        VARCHAR2(400),
  method      VARCHAR2(10),
  stat        NUMBER,
  timeout     NUMBER,
  input       VARCHAR2(4000),
  description VARCHAR2(100)
)
insert into qk_interface (ID, SYSID, NAME, URL, PORT, TYPE, METHOD, STAT, DESCRIPTION, TIMEOUT, INPUT)
values (201801, 1, '指令短信', 'http://135.149.33.87:7001/smsProxySender', '7001', 'application/x-www-form-urlencoded', 'http', 1, '发送特定指令短信', 3, null);

insert into qk_interface (ID, SYSID, NAME, URL, PORT, TYPE, METHOD, STAT, DESCRIPTION, TIMEOUT, INPUT)
values (201802, 1, '短信日志', 'http://135.149.33.87:7001/searchSendMessage', '7001', 'application/x-www-form-urlencoded', 'http', 1, '查询当月短信清单', 3, null);

insert into qk_interface (ID, SYSID, NAME, URL, PORT, TYPE, METHOD, STAT, DESCRIPTION, TIMEOUT, INPUT)
values (201803, 2, '计费清单', '135.149.16.170', '8080', 'socket', 'socket', 1, '查询计费清单', 3, null);

insert into qk_interface (ID, SYSID, NAME, URL, PORT, TYPE, METHOD, STAT, DESCRIPTION, TIMEOUT, INPUT)
values (201804, 3, 'vlote信息', 'http://135.148.76.179:53099/rest_json_gas', '53099', 'application/json', 'http', 1, '智能网管vlote', 3, null);
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
10,接口系统分类表
 create table QK_INTERFACE_SYSTEM(
  sysid       NUMBER,
  sysname     VARCHAR2(50)
)
insert into QK_INTERFACE_SYSTEM (SYSID, SYSNAME)
values (1, '省内短厅');
insert into QK_INTERFACE_SYSTEM (SYSID, SYSNAME)
values (2, '计费系统');
insert into QK_INTERFACE_SYSTEM (SYSID, SYSNAME)
values (3, '智能网管');
11,知识库表
 create table QK_KNOWLEDGE(
  id       NUMBER,
  title    VARCHAR2(100),
  author   VARCHAR2(50),
  copyfrom   VARCHAR2(50),
  time     date,
  content     clob,
  titledesc VARCHAR2(200),
  filepath   VARCHAR2(50),
  hits  NUMBER default 0
)




