package HttpMethods;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Courier;
import model.LoginRespounse;
import static io.restassured.RestAssured.given;
import static model.Constants.*;

public class CourierMethods {

    @Step("Создание курьера")
    public static Response courierCorrectCreate() {
        Courier courier = new Courier(LOGIN, PASSWORD, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Создание курьера без пароля")
    public static Response courierCreateWithoutPass() {
        Courier courier = new Courier(LOGIN, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    @Step("Авторизация курьера")
    public static Response authCourier() {
        courierCorrectCreate();
        Courier courier = new Courier(LOGIN, PASSWORD, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }

    @Step("Авторизация курьера без логина")
    public static Response authCourierWithOutLogin() {
        courierCorrectCreate();
        Courier courier = new Courier(LOGIN);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }

    @Step("Авторизация курьера с неверным логином")
    public static Response authCourierForDefunctLogin() {
        courierCorrectCreate();
        Courier courier = new Courier(LOGIN + PASSWORD, PASSWORD, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }


    @Step("Удаление курьера")
    public static Response deleteCourier() {
        Response response = authCourier();
        LoginRespounse loginRespounse = response.body().as(LoginRespounse.class);
        return
                given()
                        .header("Content-type", "application/json")
                        .delete("/api/v1/courier/" + loginRespounse.getId());
    }
}
