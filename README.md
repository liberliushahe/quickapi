# quickapi
系统框架
spring+springmvc+mybatis+redis+cxf+oracle
一,系统功能
1.接口访问
支持http socket socketchannel客户端访问
2.接口监控
监控接口调用状态
3.实时告警
通过邮件 短信微信公众平台告警
4.网络监控
使用sigar实现系统监控
Windows操作系统下Sigar.jar 依赖sigar-amd64-winnt.dll或sigar-x86-winnt.dll，C:\Windows\System32中。linux 操作系统下则依赖libsigar-amd64-linux.so或libsigar-x86-linux.so
Linux下配置：将libsigar-amd64-linux.so或libsigar-x86-linux.so拷贝到/usr/lib64或/lib64或/lib或/usr/lib目录下，如果不起作用，还需要sudochmod 744修改libsigar-amd64-linux.so文件权限
5.提供大屏监控界面
6.提供对外api
二,开发包详情
com.mongo.dao	         处理mongodb数据库底层操作接口
com.mongo.dao.impl	处理mongodb数据库底层操作接口实现类
com.mongo.domain	处理mongodb数据库实体
com.vitea.api	         对外提供api
com.vitea.apimodel	提供api实体类
com.vitea.config	swagger生成接口文档配置类
com.vitea.controller	应用控制类
com.vitea.dao	mybatis接口负责数据库交互接口
com.vitea.domain	数据库实体
com.vitea.endpoint.dto	webservice接口实体类
com.vitea.endpoint.service	 webservice接口
com.vitea.endpoint.service.impl	webservice实现类
com.vitea.handler	netty客户端处理类
com.vitea.mapping	mybatis映射文件
com.vitea.model	页面实体
com.vitea.quartz	定时器实现类
com.vitea.service	服务接口
com.vitea.service.impl	服务接口实现类
com.vitea.util	工具类
com.vitea.wechat	微信服务实现类
com.vitea.wechat.dto	微信消息实体类






