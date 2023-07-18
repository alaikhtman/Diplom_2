package testSteps.apiRequests;

import dataProvider.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetOrderRequest {

    @Step("Send GET request to /api/orders")
    public Response sendGetRequestOrder( String token) {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .when()
                .get("/api/orders");
        return response;
    }
}
