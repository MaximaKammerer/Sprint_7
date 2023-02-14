package HttpMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;
import static io.restassured.RestAssured.given;


public class OrderMethods {
    @Step("Создание заказа")
    public static Response orderCorrectCreate(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    @Step("Получение списка заказов")
    public static Response orderGetList() {
        return given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders");
    }


}
