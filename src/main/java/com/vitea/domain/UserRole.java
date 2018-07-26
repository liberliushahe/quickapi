package com.vitea.domain;
/**
 * 用户角色
 * @author liushahe
 *
 */
public class UserRole {
    /**
     *
     * 用户角色编号
     */
    private Short urid;

    /**
     *
     *用户编号
     */
    private String usid;

    /**
     *角色编号
     */
    private Short rid;

   
    public Short getUrid() {
        return urid;
    }

   
    public void setUrid(Short urid) {
        this.urid = urid;
    }

   
    public String getUsid() {
        return usid;
    }

   
    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Short getRid() {
        return rid;
    }

    public void setRid(Short rid) {
        this.rid = rid;
    }
}