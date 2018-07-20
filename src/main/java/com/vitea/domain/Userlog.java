package com.vitea.domain;

import java.util.Date;
/**
 * 
 * @author liushahe
 *
 */
public class Userlog {
    
    private Integer id;

    
    private String username;

    private Date latesttime;
    private String ip;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    public Date getLatesttime() {
        return latesttime;
    }

    public void setLatesttime(Date latesttime) {
        this.latesttime = latesttime;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
	@Override
	public String toString() {
		return "Userlog [id=" + id + ", username=" + username + ", latesttime=" + latesttime + ", ip=" + ip + "]";
	}
    
}