package com.bms.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Properties file for any Swagger configuration properties.
 *
 * @since v0.1
 * @author PAR
 */

@Data
@ConfigurationProperties("swagger.api")
@Component
/*
 * This class was originally intended to be named "SwaggerProperties", but there
 * is a bean in the Springfox code "SwaggerCommonConfiguration.java" that
 * reserves the name, causing a failure on startup. The only alternative was to
 * create a qualifier or choose a new name. Keep that in mind if you decide to
 * rename this back to SwaggerProperties.
 */
public class BmsSwaggerProperties {
    
    private String contactName;
    
    private String contactUrl;
    
    private String contactEmail;
    
    private String title;
    
    private String description;
    
    private String version;
    
    private String license;
    
    private String licenseUrl;
    
    private String termsOfServiceUrl;
    
}