import HttpMethods.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierLoginTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    @Test
    @DisplayName("Курьер может авторизоваться")
    public void LoginCourier() {
        Response response = CourierMethods.AuthCourier();
        response.then().statusCode(200).and().body("id", notNullValue());
    }

    @Test
    @DisplayName("Для авторизации нужно передать все обязательные поля")
    public void LoginCourierWithoutPass() {
        Response response = CourierMethods.AuthCourierWithOutLogin();
        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void LoginCourierForDefunctLogin() {
        Response response = CourierMethods.AuthCourierForDefunctLogin();
        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }
    @After
    public void setDown(){
        CourierMethods.DeleteCourier();
    }
}
