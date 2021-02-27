package com.jacques.sensitive.config;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


/**
 * <p>Swagger的初始化配置</p>
 *
 * @Author bob
 * @Date 2020/9/21 12:51
 */
@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enable:true}")
    private boolean enableSwagger;


    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @PostConstruct
    public void printText(){
        String swaggerUrl="http://localhost:"+serverPort+contextPath+"/swagger-ui.html";
        System.out.println("\nSwagger访问路径: "+swaggerUrl+"\n");
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + swaggerUrl);
        } catch (IOException e) {
            log.error("打开swagger失败",e);
        }
    }

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("示例文档")
                .description("API文档")
                .termsOfServiceUrl("")
                //.license("证书")
                //.licenseUrl("证书地址")
                .version("1.0.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("Authorization", "token", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }
}