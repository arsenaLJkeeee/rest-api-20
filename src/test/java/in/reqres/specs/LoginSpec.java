package in.reqres.specs;

import in.reqres.helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class LoginSpec {

    public static RequestSpecification getRequestSpecification() {
        return with()
                .log().uri()
                .log().method()
                .log().body()
                .filter(CustomAllureListener.withCustomTemplates())
                .contentType(JSON);
    }

    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification getResponseSpecificationFor201() {
        return new ResponseSpecBuilder()
                .log(STATUS)
                .log(BODY)
                .expectStatusCode(201)
                .build();
    }
}