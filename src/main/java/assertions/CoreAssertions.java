package assertions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;

public class CoreAssertions {

    @Step("Check response status ")
    public CoreAssertions checkResponseStatus(Response response, int statusCode) {
        response.then().statusCode(statusCode);
        return this;

    }

    @Step("Check response body")
    public CoreAssertions checkResponseBody(Response response, String expectedMessage) {
        Assert.assertEquals(expectedMessage, response.asString());
        return this;
    }

    @Step("Check response body field value")
    public CoreAssertions checkResponseBodyFieldValue(Response response, String field, String expectedValue) {
        Assert.assertEquals(expectedValue, response.jsonPath().getString(field));
        return this;

    }

    @Step("Check response body field")
    public CoreAssertions checkResponseBodyField( Response response, String fieldName) {
        response.then().assertThat().body("$", hasKey(fieldName));
        return this;
    }

    @Step("Check response body field not null")
    public CoreAssertions checkResponseBodyFieldNotNull( Response response, String fieldName) {
        response.then().assertThat().body(fieldName, notNullValue());
        return this;
    }
}
