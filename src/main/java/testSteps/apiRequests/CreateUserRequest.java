package testSteps.apiRequests;

import dataProvider.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CreateUserRequest {


        @Step("Send POST request to /api/auth/register")
        public Response sendPostRequestUser (User user) {
            Response response = given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(user)
                    .when()
                    .post("/api/auth/register");
            return response;

        }


}
