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

import java.util.ArrayList;
import java.util.List;

public class CreateOrderTest extends BaseUserTest {

    CreateUserSteps createUserSteps = new CreateUserSteps();
    CoreAssertions assertions = new CoreAssertions();
    CreateOrderSteps createOrderSteps = new CreateOrderSteps();
    HashIngredients hashIngredients = new HashIngredients();

    User newUser = new User(RandomStringUtils.randomAlphabetic(10) + "@mail.ru",
            RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10));

    Order order;

    @Test
    @DisplayName("Create order: positive order flow")
    @Description("Create order: positive flow for successfully order creation")
    public void createOrderSuccessfullyTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        List<String> ingredients = new ArrayList<>();
        ingredients.add(hashIngredients.HASH_KRATORNAYBULKA);
        ingredients.add(hashIngredients.HASH_GOVIAGIYMETEORIT);
        ingredients.add(hashIngredients.HASH_SPICYX);

        order = new Order(ingredients);

        Response response = createOrderSteps.createOrder(order, token);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldNotNull(response, "order.number");


    }

    @Test
    @DisplayName("Create order: create order without auth")
    @Description("Create order: negative flow for order creation without auth")
    public void createOrderWithoutAuthUnsuccessfullyTest() {

        List<String> ingredients = new ArrayList<>();
        ingredients.add(hashIngredients.HASH_KRATORNAYBULKA);
        ingredients.add(hashIngredients.HASH_GOVIAGIYMETEORIT);
        ingredients.add(hashIngredients.HASH_SPICYX);

        order = new Order(ingredients);

        Response response = createOrderSteps.createOrderWithoutAuth(order);
        assertions.checkResponseStatus(response, 200)
                .checkResponseBodyFieldValue(response, "success", "true")
                .checkResponseBodyFieldNotNull(response, "order.number");


    }

    @Test
    @DisplayName("Create order: create order without ingredients")
    @Description("Create order: negative flow for order creation without ingredients")
    public void createOrderWithoutIngredientsUnsuccessfullyTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        List<String> ingredients = new ArrayList<>();
        order = new Order(ingredients);

        Response response = createOrderSteps.createOrder(order, token);
        assertions.checkResponseStatus(response, 400)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "Ingredient ids must be provided" );


    }

    @Test
    @DisplayName("Create order: create order with incorrect ingredients")
    @Description("Create order: negative flow for order creation with incorrect ingredients")
    public void createOrderWithIncorrectIngredientsUnsuccessfullyTest() {
        Response createUserResponse = createUserSteps.createUser(newUser);
        token = createUserResponse.jsonPath().getString("accessToken").substring(7);

        List<String> ingredients = new ArrayList<>();
        ingredients.add(hashIngredients.HASH_INVALID_INGREDIENT);
        order = new Order(ingredients);

        Response response = createOrderSteps.createOrder(order, token);
        assertions.checkResponseStatus(response, 400)
                .checkResponseBodyFieldValue(response, "success", "false")
                .checkResponseBodyFieldValue(response, "message", "One or more ids provided are incorrect");


    }

}
