import assertions.CoreAssertions;
import dataProvider.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import testSteps.BaseUserTest;
import testSteps.steps.CreateUserSteps;
import testSteps.steps.UpdateUserSteps;

import java.util.Locale;

public class ChangeUserTest extends BaseUserTest {

    CreateUserSteps createUserSteps = new CreateUserSteps();
    CoreAssertions assertions = new CoreAssertions();
    UpdateUserSteps updateUserSteps = new UpdateUserSteps();

    User newUser = new User(RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
            RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10));

    @Test
    @DisplayName("Update user: positive update name flow")
    @Description("Update user: positive flow for successfully user update name")
    public void updateUserNameTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);
        String newName = RandomStringUtils.randomAlphabetic(10);

        Response response = updateUserSteps.changeUserName(newUser, newName, token);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldValue(response, "user.name", newName);

    }

    @Test
    @DisplayName("Update user: positive update email flow")
    @Description("Update user: positive flow for successfully user update email")
    public void updateUserPasswordTest() {

        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);
        String newEmail = (RandomStringUtils.randomAlphabetic(10) + "@mail.ru").toLowerCase(Locale.ROOT);

        Response response = updateUserSteps.changeUserEmail(newUser, newEmail, token);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldValue(response, "user.email", newEmail);

    }

    @Test
    @DisplayName("Update user: update without auth")
    @Description("Update user: negative update flow without auth")
    public void updateUserWithoutAuthTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);
        String newEmail = (RandomStringUtils.randomAlphabetic(10) + "@mail.ru").toLowerCase(Locale.ROOT);

        Response response = updateUserSteps.changeUserWithoutAuth(newUser, newEmail);
        assertions.checkResponseStatus(response, 401)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "You should be authorised");

    }

}
