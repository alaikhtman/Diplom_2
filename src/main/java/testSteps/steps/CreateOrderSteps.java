package testSteps.steps;

import dataProvider.Order;
import dataProvider.User;
import io.restassured.response.Response;
import testSteps.apiRequests.CreateOrderRequest;


public class CreateOrderSteps {

    CreateOrderRequest createOrderRequest = new CreateOrderRequest();

    public Response createOrder(Order order, String token) {
        Response response = createOrderRequest.sendPostRequestOrder(order, token);
        return response;
    }

    public Response createOrderWithoutAuth(Order order) {
        Response response = createOrderRequest.sendPostRequestWithoutAuthOrder(order);
        return response;
    }
}
