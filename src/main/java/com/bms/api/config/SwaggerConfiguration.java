package com.bms.api.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bms.api.config.properties.BmsSwaggerProperties;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class responsible for housing any configuration items for Swagger.
 *
 *
 * @author PAR
 * @since v0.1
 * @see {@link BmsSwaggerProperties} for the list of externalized
 *      properties.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    
    private final BmsSwaggerProperties swaggerProperties;
    
    @Autowired
    public SwaggerConfiguration(BmsSwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }
    
    @SuppressWarnings("rawtypes")
    private ApiInfo apiInfo() {
        
        final Contact contact = new Contact(swaggerProperties.getContactName(), swaggerProperties.getContactUrl(),
                swaggerProperties.getContactEmail());
        
        final ApiInfo apiInfo = new ApiInfo(swaggerProperties.getTitle(),
                swaggerProperties.getDescription(),
                swaggerProperties.getVersion(), swaggerProperties.getTermsOfServiceUrl(),
                contact,
                swaggerProperties.getLicense(), swaggerProperties.getLicenseUrl(), new ArrayList<VendorExtension>());
        return apiInfo;
    }
    
    @Bean
    @Profile({ "preprod", "prod" })
    public UiConfiguration uiConfiguration() {
        UiConfiguration uiConfiguration = UiConfigurationBuilder.builder().validatorUrl(null)
                .supportedSubmitMethods(UiConfiguration.Constants.NO_SUBMIT_METHODS).build();
        return uiConfiguration;
    }
    
    @Bean
    public Docket configureSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                // Allows swagger to render a decent looking Model when using
                // OffsetDatetime. Swagger has trouble
                // rendering it properly
                .directModelSubstitute(java.time.OffsetDateTime.class,
                        java.util.Date.class)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors
                        .basePackage("org.springframework.boot")))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    
}

