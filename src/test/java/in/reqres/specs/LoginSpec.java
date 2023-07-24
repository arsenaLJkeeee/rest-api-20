package in.reqres.specs;

import in.reqres.models.UserData;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsNull.notNullValue;

public class LoginSpec {

    public static RequestSpecification getRequestSpecification() {
        return with()
                .log().uri()
                .log().method()
                .log().body()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .baseUri("https://reqres.in")
                .basePath("/api");
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
