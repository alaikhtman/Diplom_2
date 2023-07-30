package testSteps.steps;

import io.restassured.response.Response;
import testSteps.apiRequests.GetOrderRequest;

public class GetOrderForSpecificUserSteps {

    GetOrderRequest getOrderRequest = new GetOrderRequest();

    public Response getSpecificOrder(String token) {
        Response response = getOrderRequest.sendGetRequestOrder(token);
        return response;
    }

    public Response getSpecificOrderWithoutAuth() {
        Response response = getOrderRequest.sendGetRequestOrderWithoutAuth();
        return response;
    }
}