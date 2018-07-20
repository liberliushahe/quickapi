package com.vitea.domain;

public class Resource {
    /**
     *
     * 资源编码
     */
    private Short resid;

    /**
     *
     * 资源
     */
    private String resurl;

    /**
     *
     * 资源描述
     */
    private String resdesc;

   
    public Short getResid() {
        return resid;
    }

   
    public void setResid(Short resid) {
        this.resid = resid;
    }

   
    public String getResurl() {
        return resurl;
    }

    public void setResurl(String resurl) {
        this.resurl = resurl == null ? null : resurl.trim();
    }

   
    public String getResdesc() {
        return resdesc;
    }

   
    public void setResdesc(String resdesc) {
        this.resdesc = resdesc == null ? null : resdesc.trim();
    }
}