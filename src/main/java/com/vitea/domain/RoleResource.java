package com.vitea.domain;
/**
 * 角色资源
 * @author liushahe
 *
 */
public class RoleResource {
    /**
     *
     * 资源角色编码
     */
    private Short rrid;

    /**
     *
     * 资源编号
     */
    private Short resid;

    /**
     *
     * 角色编号
     */
    private Short rid;

    
    public Short getRrid() {
        return rrid;
    }

   
    public void setRrid(Short rrid) {
        this.rrid = rrid;
    }

    public Short getResid() {
        return resid;
    }

    public void setResid(Short resid) {
        this.resid = resid;
    }

 
    public Short getRid() {
        return rid;
    }
    public void setRid(Short rid) {
        this.rid = rid;
    }
}