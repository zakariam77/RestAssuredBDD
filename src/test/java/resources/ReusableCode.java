package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;


import java.io.*;

import static io.restassured.RestAssured.given;

public class ReusableCode {

    public static RequestSpecification requestBase;

    public RequestSpecification requestBase() throws FileNotFoundException {
        String BaseURI = ConfigReader.getProperty("BaseURI");

        RestAssured.filters(new AllureRestAssured(),  new RequestLoggingFilter(),
                new ResponseLoggingFilter());

        if(requestBase == null){
            PrintStream log  = new PrintStream(new FileOutputStream("logs.log"));
            requestBase =  new RequestSpecBuilder().setBaseUri(BaseURI)
                    .setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .addQueryParam("key", "qaclick123")
                    .build();
            return requestBase;
        }

        return requestBase;
    }
}