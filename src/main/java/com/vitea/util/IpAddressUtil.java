package com.vitea.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
/**
 * 获取ip地址工具类
 * @author liushahe
 *
 */
public class IpAddressUtil {
	private static final String UNKNOWN="unknown";
	/**
	 * 获取客户端访问ip
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request){  
	    String ip = request.getHeader("x-forwarded-for");  
	    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}
	/**
	 * 获取本机ip地址
	 * loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
	 * @return
	 */
	public static String getIpRealAddress() {
		
		try{
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()){
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (ip != null 
							&& ip instanceof Inet4Address
                    		&& !ip.isLoopbackAddress() 
                    		&& ip.getHostAddress().indexOf(":")==-1){
						return ip.getHostAddress();
					} 
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

}
