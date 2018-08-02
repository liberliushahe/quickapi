package com.vitea.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vitea.apimodel.Sheet;
import com.vitea.domain.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 对外提供API服务
 * API文档服务地址：http://localhost:8080/quickapi/api/swagger-ui.html
 * 具体服务地址为：http://localhost:8080/quickapi/api/v1/sheet/2
 * 具体服务地址为：https://localhost:8080/quickapi/api/v1/sheet/2
 * swagger接口文档注解说明
 * - @Api()用于类； 
 *   表示标识这个类是swagger的资源 
 * - @ApiOperation()用于方法； 
 *   表示一个http请求的操作   
 * - @ApiParam()用于方法，参数，字段说明； 
 *   表示对参数的添加元数据（说明或是否必填等） 
 * - @ApiModel()用于类 
 *   表示对类进行说明，用于参数用实体类接收 
 * - @ApiModelProperty()用于方法，字段 
 *   表示对model属性的说明或者数据操作更改 
 * - @ApiIgnore()用于类，方法，方法参数 
 *   表示这个方法或者类被忽略 
 * - @ApiImplicitParam() 用于方法 
 *   表示单独的请求参数 
 * - @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam 
 * 
 *   @author liushahe
 *
 */
@Api(value = "/sheet", tags = "通过工单号获取工单接口")
@RestController
@RequestMapping("/v1")
public class RestFulServer {
    @ApiOperation(value = "获取工单接口", notes = "通过编号获取工单", response = User.class)
    @RequestMapping(value="/sheet/{id}",method= RequestMethod.GET)
    public @ResponseBody Sheet sayHello( @ApiParam(value = "sheetid" ,required=true ) @PathVariable(value="sheetid") String sheetid) {
    	Sheet sheet=new Sheet();
    	sheet.setSheetid("GS201809871267");
    	sheet.setBusivalue("180XXXXXXX");
    	sheet.setAreacode("937");
    	sheet.setCreateoperator("1098");
    	sheet.setRemark("获取工单号");
        return sheet;
    }


}
