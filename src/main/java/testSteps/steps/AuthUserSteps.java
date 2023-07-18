package testSteps.steps;

import dataProvider.AuthUser;
import dataProvider.User;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import testSteps.apiRequests.AuthUserRequest;

public class AuthUserSteps {

    AuthUserRequest authUserRequest = new AuthUserRequest();



    public Response authUser(User user) {
        AuthUser authUser = new AuthUser(user.getEmail(), user.getPassword());
        Response response = authUserRequest.sendPostAuthRequestUser(authUser);
        return response;
    }

    public Response authUserWithIncorrectPassword (User user) {
        AuthUser authUser = new AuthUser(user.getEmail(), RandomStringUtils.randomAlphabetic(10));
        Response response = authUserRequest.sendPostAuthRequestUser(authUser);
        return response;
    }

    public Response authUserWithIncorrectEmail (User user) {
        AuthUser authUser = new AuthUser(RandomStringUtils.randomAlphabetic(10) + "@mail.ru", user.getPassword());
        Response response = authUserRequest.sendPostAuthRequestUser(authUser);
        return response;
    }
}