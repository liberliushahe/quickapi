package com.vitea.endpoint.service;

import javax.jws.WebService;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.alibaba.fastjson.JSONObject;
import com.vitea.endpoint.dto.CustInfo;

/**
 * /**
 * 此接口主要负责将核心接口转为webservice服务返回xml格式数据
 * 1.查询客户信息
 * 2.查询用户信息
 * 3.查询用户订购的套餐信息
 * 4.查询用户订购主产品信息
 * 5.查询用户订购的附属产品信息
 * @author liushahe
 *
 */
@WebService
public interface IQueryInfoRest {
    /**
     * GET获取客户部分信息restful接口
     * 接口地址:http://localhost:8888/quickapi/ws/info/custinfo/1
     * @param id
     * @return
     */
	@GET
    @Path("/custinfo/{id}")
	@Produces({"application/xml"}) 
	public CustInfo  queryCustInfo(@PathParam("id") String id);
	
	/**
	 * POST获取详细客户信息
	 * @param paramString
	 * @return
	 */
	@POST
    @Path("/custinfo")  
	@Produces({"application/json"}) 
	public JSONObject queryDetailCustInfo(String paramString);
	
	/**
	 * POST获取用户信息
	 * @param paramString
	 * @return
	 */
	@POST
    @Path("/userinfo")  
	@Produces({"application/json"}) 
	public JSONObject queryDetailUserInfo(String paramString);
	
	/**
	 * POST获取用户套餐信息
	 * @param paramString
	 * @return
	 */
	@POST
    @Path("/userpkgs")  
	@Produces({"application/json"}) 
	public JSONObject queryDetailUserPackages(String paramString);
	/**
	 * POST获取主产品信息
	 * @param paramString
	 * @return
	 */
	@POST
    @Path("/prodlist")  
	@Produces({"application/json"}) 
	public JSONObject queryDetailProdList(String paramString);
	
	/**
	 * POST获取附属产品信息
	 * @param paramString
	 * @return
	 */
	@POST
    @Path("/subproduct")  
	@Produces({"application/json"})
	public JSONObject queryDetailSubProducts(String paramString);
}
