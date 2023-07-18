package testSteps.apiRequests;

import dataProvider.ChangeUser;
import dataProvider.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateOrderRequest {

    @Step("Send POST request to /api/orders")
    public Response sendPostRequestOrder(Order order, String token) {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .and()
                .body(order)
                .when()
                .patch("/api/orders");
        return response;
    }

}
