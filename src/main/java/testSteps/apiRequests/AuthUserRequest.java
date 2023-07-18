package testSteps.apiRequests;

import dataProvider.AuthUser;
import dataProvider.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthUserRequest {

    @Step("Send POST request to api/auth/login")
    public Response sendPostAuthRequestUser (AuthUser authUser) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(authUser)
                .when()
                .post("/api/auth/login");
        return response;

    }
}
