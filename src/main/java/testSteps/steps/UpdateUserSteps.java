package testSteps.steps;

import dataProvider.ChangeUser;
import dataProvider.User;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import testSteps.apiRequests.ChangeUserRequest;

public class UpdateUserSteps {

    ChangeUserRequest changeUserRequest = new ChangeUserRequest();

    public Response changeUserName(User user, String newName, String token) {
        ChangeUser changeUser = new ChangeUser(user.getEmail(), newName);
        Response response = changeUserRequest.sendPatchRequestUser(changeUser, token);
        return response;
    }

    public Response changeUserEmail(User user, String newEmail, String token) {
        ChangeUser changeUser = new ChangeUser(newEmail, user.getName());
        Response response = changeUserRequest.sendPatchRequestUser(changeUser, token);
        return response;
    }

    public Response changeUserWithoutAuth(User user, String newEmail) {
        ChangeUser changeUser = new ChangeUser(newEmail, user.getName());
        Response response = changeUserRequest.sendPatchRequestUserWithoutAuth(changeUser);
        return response;
    }

}
