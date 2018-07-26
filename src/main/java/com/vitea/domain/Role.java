package com.vitea.domain;
/**
 * 角色
 * @author liushahe
 *
 */
public class Role {
    /**
     *
     * 角色编码
     */
    private Short rid;

    /**
     *
     * 角色
     */
    private String rname;

    /**
     *描述
     */
    private String rdescription;

   
    public Short getRid() {
        return rid;
    }

   
    public void setRid(Short rid) {
        this.rid = rid;
    }

   
    public String getRname() {
        return rname;
    }

   
    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription == null ? null : rdescription.trim();
    }
}