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
 * 查询客户信息接口
 * @author liushahe
 *
 */
@WebService
public interface IGetCustInfo {
    /**
     * 客户部分信息restful接口
     * 接口地址:http://localhost:8888/quickapi/ws/rest/custinfo/1
     * @param id
     * @return
     */
	@GET
    @Path("/custinfo/{id}")
	@Produces({"application/xml"}) 
	public CustInfo  queryCustInfo(@PathParam("id") String id);
	
	/**
	 * POST获取客户信息
	 * @param paramString
	 * @return
	 */
	@POST
    @Path("/custinfo")  
	@Produces({"application/json"}) 
	public JSONObject queryDetailCustInfoPost(String paramString);
	
}
