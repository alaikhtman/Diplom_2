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
                .post("/api/orders");
        return response;
    }

    @Step("Send not-authorized POST request to /api/orders")
    public Response sendPostRequestWithoutAuthOrder(Order order) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post("/api/orders");
        return response;
    }

}
