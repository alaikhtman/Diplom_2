import assertions.CoreAssertions;
import dataProvider.HashIngredients;
import dataProvider.Order;
import dataProvider.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import testSteps.BaseUserTest;
import testSteps.steps.CreateOrderSteps;
import testSteps.steps.CreateUserSteps;
import testSteps.steps.GetOrderForSpecificUserSteps;

import java.util.ArrayList;
import java.util.List;

public class GetOrderTest extends BaseUserTest {

    CreateUserSteps createUserSteps = new CreateUserSteps();
    CoreAssertions assertions = new CoreAssertions();
    CreateOrderSteps createOrderSteps = new CreateOrderSteps();
    HashIngredients hashIngredients = new HashIngredients();
    GetOrderForSpecificUserSteps getOrderForSpecificUserSteps = new GetOrderForSpecificUserSteps();

    User newUser = new User(RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
            RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10));

    Order order;

    @Test
    @DisplayName("Get order: positive  get order flow")
    @Description("Get order: positive flow for successfully  get order for specific user")
    public void getOrderForSpecificUserTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        List<String> ingredients = new ArrayList<>();
        ingredients.add(hashIngredients.HASH_KRATORNAYBULKA);
        ingredients.add(hashIngredients.HASH_GOVIAGIYMETEORIT);
        ingredients.add(hashIngredients.HASH_SPICYX);

        order = new Order(ingredients);
        createOrderSteps.createOrder(order, token);

        Response response = getOrderForSpecificUserSteps.getSpecificOrder(token);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldNotNull(response, "orders.id");

    }

    @Test
    @DisplayName("Get order: get order without auth")
    @Description("Get order: negative flow for get order for specific user without auth")
    public void getOrderForSpecificUserWithoutAuthTest() {

        Response response = getOrderForSpecificUserSteps.getSpecificOrderWithoutAuth();
        assertions.checkResponseStatus(response, 401)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "You should be authorised");

    }



}
