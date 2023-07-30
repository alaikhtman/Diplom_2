package testSteps.steps;

import dataProvider.User;
import io.restassured.response.Response;
import testSteps.apiRequests.DeleteUserRequest;

public class DeleteUserSteps {

    DeleteUserRequest deleteUserRequest = new DeleteUserRequest();

    public Response deleteUser(String token) {
        Response response = deleteUserRequest.sendDeleteRequestUser(token);
        return response;
    }

}
