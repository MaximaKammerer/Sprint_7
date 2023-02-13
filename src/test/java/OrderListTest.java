import HttpMethods.OrderMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static model.Constants.BASE_URL;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("В тело ответа возвращается список заказов")
    public void orderListTest() {
        Response response = OrderMethods.orderGetList();
        response.then().body("orders", notNullValue());
    }
}
