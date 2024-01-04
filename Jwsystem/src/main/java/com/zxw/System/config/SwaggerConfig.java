package com.zxw.System.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zxw
 * @date 2019/8/27 19:14
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.zxw.swagger.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //  设置标题
                .title("使用swagger2构建短视频后端api接口文档")
                // 设置联系人
                .contact(new Contact("imooc-Nathan", "http://www.imooc.com", "502513206@qq.com"))
                // 设置描述
                .description("欢迎访问短视频接口文档")
                // 定义版本号
                .version("1.0").build();
    }
}
