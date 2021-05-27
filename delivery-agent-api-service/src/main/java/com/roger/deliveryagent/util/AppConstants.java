package com.stackroute.deliveryagent.util;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class AppConstants {

    //Swagger - Constants
    public static final Contact SWAGGER_DEFAULT_CONTACT = new Contact("Roger D Dhas", "http://rogerdhas.com/",
            "rogerdhas@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
            .title("Shop partner Api Service")
            .description("Shop partner Api Service ")
            .version("V1.0.0")
            .license("Roger 1.0")
            .licenseUrl("http://rogerdhas.com/licenses")
            .contact(SWAGGER_DEFAULT_CONTACT)
            .termsOfServiceUrl("http://rogerdhas.com/terms")
            .build();

    public static String JWTTOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0dXNlciIsInVzZXJJZCI6Ijg4OCIsImFkZHJlc3MiOiJjaGVubmFpLHRhbWlsbmFkdSIsInJvbGUiOiJiYXNpYyJ9.JXFS9j8TNzMJ7WFcNMXsrj__GX0MGRtxaXAvicIwf5N3UkkYP1SO1dvkwb37hRbXogbhxOPqkFRBB1SmLS_SZQ";
}