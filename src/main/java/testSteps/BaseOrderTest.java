package testSteps;

import io.restassured.RestAssured;
import org.junit.Before;

public class BaseOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }
}
