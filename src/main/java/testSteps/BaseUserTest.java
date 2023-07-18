package testSteps;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import testSteps.steps.DeleteUserSteps;

public class BaseUserTest {

    DeleteUserSteps deleteUserSteps = new DeleteUserSteps();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

    public String token;

    @After
    public void tearDown() {
        if (token != null) {
            deleteUserSteps.deleteUser(token);
        }
    }
}
