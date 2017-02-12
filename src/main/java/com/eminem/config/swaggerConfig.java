package com.eminem.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ysen6 on 2017/2/7.
 */

@Configuration
@EnableAutoConfiguration
@EnableSwagger
public class swaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .includePatterns(".*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "南京兆米",
                "南京兆米api接口文档(rest)",
                "api.njzhaomi.com",
                "server@njzhaomi.com",
                "MIT License",
                "/rest");
    }
}
