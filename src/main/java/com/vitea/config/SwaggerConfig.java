package com.vitea.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * swagger配置类
 * @author liushahe
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig{
	
    @Bean
    public Docket api() {
    	//自定义异常信息
        @SuppressWarnings({ "unused", "serial" })
		ArrayList<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>() {{
               add(new ResponseMessageBuilder().code(200).message("成功").build());
               add(new ResponseMessageBuilder().code(400).message("请求参数错误").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(401).message("权限认证失败").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(403).message("请求资源不可用").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(404).message("请求资源不存在").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(409).message("请求资源冲突").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(415).message("请求格式错误").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(423).message("请求资源被锁定").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(501).message("请求方法不存在").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(503).message("服务暂时不可用").responseModel(new ModelRef("Error")).build());
               add(new ResponseMessageBuilder().code(-1).message("未知异常").responseModel(new ModelRef("Error")).build());
           }};
        return new Docket(DocumentationType.SWAGGER_2)
        		.apiInfo(apiInfo()) 
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("com.vitea.api")) 
        		.paths(PathSelectors.any()) 
        		.build(); 
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("预处理系统接口文档")
                .description("预处理工单操作接口")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                
                .licenseUrl("")
                .build();
    }

}