package com.vitea.endpoint.service;

import javax.jws.WebService;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	 * 客户详细信息查询
	 * @param id
	 * @return
	 */
	@GET
    @Path("/custinfo")
	//@Consumes("application/json")  
	@Produces({"application/json"}) 
	public CustInfo queryDetailCustInfoGet(String paramString);
	
	@POST
    @Path("/custinfo")  
	@Produces({"application/json"}) 
	public CustInfo queryDetailCustInfoPost(String paramString);
	
}
