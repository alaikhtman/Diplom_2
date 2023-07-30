package testSteps.steps;

import dataProvider.User;
import io.restassured.response.Response;
import testSteps.apiRequests.CreateUserRequest;

public class CreateUserSteps {

    CreateUserRequest createUserRequest = new CreateUserRequest();

    public Response createUser(User user) {
        Response response = createUserRequest.sendPostRequestUser(user);
        return response;
    }
}
