package com.springboot.demo.config;

import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author liaoyujian
 * 启用 Swagger
 */
@Getter
@Setter
@ToString
@Configuration
@EnableSwagger2Doc
@EnableSwagger2
//@ConditionalOnProperty(prefix = "swagger", value = "enabled", havingValue = "true")
//@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {

//    /**
//     * 大标题
//     */
//    private String title;
//
//    /**
//     * 简介
//     */
//    private String description;
//
//    /**
//     * 服务url
//     */
//    private String url;
//
//    /**
//     * 作者
//     */
//    public String author;
//
//    /**
//     * 版本
//     */
//    private String version;
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(title)
//                .description(description)
//                .termsOfServiceUrl(url)
//                .contact(new Contact(author, "", ""))
//                .version(version)
//                .build();
//    }

}
