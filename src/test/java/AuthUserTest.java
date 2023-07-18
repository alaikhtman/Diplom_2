import assertions.CoreAssertions;
import dataProvider.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import org.junit.Test;
import testSteps.BaseUserTest;
import testSteps.steps.AuthUserSteps;
import testSteps.steps.CreateUserSteps;
import testSteps.steps.DeleteUserSteps;

public class AuthUserTest extends BaseUserTest {

    AuthUserSteps authUserSteps = new AuthUserSteps();
    CreateUserSteps createUserSteps = new CreateUserSteps();
    CoreAssertions assertions = new CoreAssertions();


    User newUser = new User(RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
            RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10));




    @Test
    @DisplayName("Login user: positive login flow")
    @Description("Login user: positive flow for successfully user login")
    public void loginUserSuccessfullyTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        Response response = authUserSteps.authUser(newUser);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldNotNull(response, "accessToken");


    }

    @Test
    @DisplayName("Login user: login with incorrect password")
    @Description("Login user: negative flow for user login with incorrect password")
    public void loginUserWithIncorrectPasswordUnsuccessfullyTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        Response response = authUserSteps.authUserWithIncorrectPassword(newUser);
        assertions.checkResponseStatus(response, 401)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "email or password are incorrect");

    }

    @Test
    @DisplayName("Login user: login with incorrect email")
    @Description("Login user: negative flow for user login with incorrect email")
    public void loginUserWithIncorrectEmailUnsuccessfullyTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        Response response = authUserSteps.authUserWithIncorrectEmail(newUser);
        assertions.checkResponseStatus(response, 401)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "email or password are incorrect");

    }
}
