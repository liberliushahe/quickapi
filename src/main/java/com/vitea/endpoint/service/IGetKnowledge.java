package com.vitea.endpoint.service;

import javax.jws.WebService;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.vitea.domain.Knowledge;
/**
 * 查询知识库
 * @author liushahe
 *
 */
@WebService
public interface IGetKnowledge {
    /**
     * 知识库restful接口
     * @param id
     * @return
     */
	@GET
    @Path("/knowledge/{id}")
	@Produces("application/xml")
	public Knowledge  queryKnowledge(@PathParam("id") String id);
}
