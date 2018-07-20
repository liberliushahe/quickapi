# quickapi
系统框架
spring+springmvc+mybatis+redis+cxf+oracle
系统功能
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