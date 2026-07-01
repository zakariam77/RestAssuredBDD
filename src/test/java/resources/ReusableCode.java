package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ReusableCode {

    public static RequestSpecification requestBase;

    public RequestSpecification requestBase() throws FileNotFoundException {
        String BaseURI = ConfigReader.getProperty("BaseURI");


        if(requestBase == null){
            PrintStream log  = new PrintStream(new FileOutputStream("logs.log"));
            requestBase =  new RequestSpecBuilder().setBaseUri(BaseURI)
                    .setContentType(ContentType.JSON)
                    .addFilter(new AllureRestAssured())
                    .addFilter(new RequestLoggingFilter()).addFilter(new ResponseLoggingFilter())
                    .addQueryParam("key", "qaclick123")
                    .build();
            return requestBase;
        }

        return requestBase;
    }
}