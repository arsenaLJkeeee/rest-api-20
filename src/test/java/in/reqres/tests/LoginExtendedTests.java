package in.reqres.tests;

import in.reqres.models.UserData;
import in.reqres.specs.LoginSpec;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class LoginExtendedTests extends TestBase {

    @Test
    void successfulGetListOfUsers() {
        step("Get list of users", () -> {
        given()
                .spec(LoginSpec.getRequestSpecification())
                .contentType(JSON)
                .when()
                .get("/users?page=2")
                .then()
                .spec(LoginSpec.getResponseSpecification())
                .body(matchesJsonSchemaInClasspath("schemas/users-schema.json"));
    });
    }

    @Test
    void successfulGetTokenAfterRegistration() {
        UserData authData = new UserData();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("pistol");

        step("Register and get token", () -> {
            given()
                    .spec(LoginSpec.getRequestSpecification())
                    .contentType(JSON)
                    .body(authData)
                    .when()
                    .post("/register")
                    .then()
                    .spec(LoginSpec.getResponseSpecification())
                    .body("token", notNullValue())
                    .body(matchesJsonSchemaInClasspath("schemas/get-token-after-registration-schema.json"));
        });
    }

    @Test
    void successfulCreatingOfUser() {
        UserData authData = new UserData();
        authData.setName("morpheus");
        authData.setJob("leader");

        step("Create a new user", () -> {
            given()
                    .spec(LoginSpec.getRequestSpecification()) // Используем общую спецификацию для запроса
                    .body(authData)
                    .when()
                    .post("/users")
                    .then()
                    .spec(LoginSpec.getResponseSpecificationFor201()) // Используем отдельную спецификацию для ответа с кодом 201
                    .body("id", notNullValue());
        });
    }

    @Test
    void negativeUserNotFoundTest() {
        step("Check user not found", () -> {
            given()
                    .spec(LoginSpec.getRequestSpecification())
                    .contentType(JSON)
                    .when()
                    .get("/unknown/23")
                    .then()
                    .statusCode(404);
        });
    }

    @Test
    void successfulDeleteOfUser204() {
        step("Delete user", () -> {
            given()
                    .spec(LoginSpec.getRequestSpecification())
                    .contentType(JSON)
                    .when()
                    .delete("/users/2")
                    .then()
                    .statusCode(204);
        });
    }
}