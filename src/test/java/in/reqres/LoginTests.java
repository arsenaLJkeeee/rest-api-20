package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class LoginTests {

    @Test
    void successfulGetListOfUsers() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", everyItem(notNullValue()));
    }

    @Test
    void successfulGetTokenAfterRegistration() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", notNullValue());
    }

    @Test
    void successfulCreatingOfUser() {
        String authData = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    void negativeUserNotFoundTest() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void successfulDeleteOfUser204() {

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

}