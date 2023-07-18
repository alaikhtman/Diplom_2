package testSteps.apiRequests;

import dataProvider.AuthUser;
import dataProvider.ChangeUser;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ChangeUserRequest {

    @Step("Send PATCH request to /api/auth/user")
    public Response sendPatchRequestUser (ChangeUser changeUser, String token) {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .body(changeUser)
                .when()
                .patch("/api/auth/user");
        return response;

    }

    @Step("Send PATCH request to /api/auth/user")
    public Response sendPatchRequestUserWithoutAuth (ChangeUser changeUser) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(changeUser)
                .when()
                .patch("/api/auth/user");
        return response;

    }
}
