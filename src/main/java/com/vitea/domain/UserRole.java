package com.vitea.domain;

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
    private Short usid;

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

   
    public Short getUsid() {
        return usid;
    }

   
    public void setUsid(Short usid) {
        this.usid = usid;
    }

    public Short getRid() {
        return rid;
    }

    public void setRid(Short rid) {
        this.rid = rid;
    }
}