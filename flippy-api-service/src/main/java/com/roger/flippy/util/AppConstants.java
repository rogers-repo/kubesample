package com.roger.flippy.util;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class AppConstants {

    //Swagger - Constants
    public static final Contact SWAGGER_DEFAULT_CONTACT = new Contact("Roger D Dhas", "http://rogerdhas.com/",
            "rogerdhas@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
            .title("Flippy Api Service")
            .description("Flippy Api Service ")
            .version("V1.0.0")
            .license("Roger 1.0")
            .licenseUrl("http://rogerdhas.com/licenses")
            .contact(SWAGGER_DEFAULT_CONTACT)
            .termsOfServiceUrl("http://rogerdhas.com/terms")
            .build();

    public static String JWTTOKEN ="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2dlciIsInVzZXJJZCI6IjEyMyIsImFkZHJlc3MiOiJjaGVubmFpLHRhbWlsbmFkdSIsInJvbGUiOiJnb2xkIn0.vtiSV1y97ILur08F1O99Y1ELsFT4wk775M8G_BlWnRMxidJLx3jZDcrXFjhISNj81r7pd4ztCq7Oosh5y-Bd7Q";
//Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2dlciIsInVzZXJJZCI6IjEyMyIsImFkZHJlc3MiOiJjaGVubmFpLHRhbWlsbmFkdSIsInJvbGUiOiJnb2xkIn0.vtiSV1y97ILur08F1O99Y1ELsFT4wk775M8G_BlWnRMxidJLx3jZDcrXFjhISNj81r7pd4ztCq7Oosh5y-Bd7Q
}
