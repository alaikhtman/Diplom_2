package testSteps.apiRequests;

import dataProvider.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteUserRequest {

    @Step("Send DELETE request to /api/auth/user")
    public Response sendDeleteRequestUser(String token) {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .when()
                .patch("/api/auth/user");
        return response;
    }
}
