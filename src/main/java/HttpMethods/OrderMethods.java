package HttpMethods;

import model.Courier;
import model.LoginRespounse;
import io.restassured.response.Response;
import model.Order;
import model.OrderRespounse;

import static io.restassured.RestAssured.given;
import static model.Constants.*;


public class OrderMethods {
    public static Response OrderCorrectCreate(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }
    public static Response OrderGetList(){
        return  given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders");
    }


}
