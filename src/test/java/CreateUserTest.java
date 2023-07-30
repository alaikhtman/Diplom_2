import assertions.CoreAssertions;
import dataProvider.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import testSteps.BaseUserTest;
import testSteps.steps.CreateUserSteps;


public class CreateUserTest extends BaseUserTest {

    CreateUserSteps createUserSteps = new CreateUserSteps();
    CoreAssertions assertions = new CoreAssertions();
    User newUser;


    @Test
    @DisplayName("Create user: positive flow")
    @Description("Create user: positive flow for unique user creation")
    public void createUserSuccessfullyTest() {
        newUser = new User(
                RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        Response response = createUserSteps.createUser(newUser);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldNotNull(response, "accessToken");
        token = response.jsonPath().getString("accessToken").substring(7);

    }

    @Test
    @DisplayName("Create user: existed user flow")
    @Description("Create user: negative flow for non-unique user creation")
    public void createExistedUserUnsuccessfullyTest() {
        newUser = new User(
                RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
        createUserSteps.createUser(newUser);
        Response response = createUserSteps.createUser(newUser);
        assertions.checkResponseStatus(response, 403)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "User already exists");

    }

    @Test
    @DisplayName("Create user: without name user flow")
    @Description("Create user: negative flow for user creation without name")
    public void createUserWithoutNameUnsuccessfullyTest() {
        newUser = new User(
                RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
                RandomStringUtils.randomAlphabetic(10),
                null);
        Response response = createUserSteps.createUser(newUser);
        assertions.checkResponseStatus(response, 403)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "Email, password and name are required fields");

    }

    @Test
    @DisplayName("Create user: without password user flow")
    @Description("Create user: negative flow for user creation without password")
    public void createUserWithoutPasswordUnsuccessfullyTest() {
        newUser = new User(
                RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
                null,
                RandomStringUtils.randomAlphabetic(10)
        );
        Response response = createUserSteps.createUser(newUser);
        assertions.checkResponseStatus(response, 403)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "Email, password and name are required fields");

    }

    @Test
    @DisplayName("Create user: without email user flow")
    @Description("Create user: negative flow for user creation without email")
    public void createUserWithoutEmailUnsuccessfullyTest() {
        newUser = new User(
                null,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)
        );
        Response response = createUserSteps.createUser(newUser);
        assertions.checkResponseStatus(response, 403)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "Email, password and name are required fields");

    }
}
