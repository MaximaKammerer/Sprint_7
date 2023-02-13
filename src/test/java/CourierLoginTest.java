import HttpMethods.CourierMethods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static model.Constants.BASE_URL;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    public void LoginCourier() {
        Response response = CourierMethods.authCourier();
        response.then().statusCode(200).and().body("id", notNullValue());
    }

    @Test
    @DisplayName("Для авторизации нужно передать все обязательные поля")
    public void loginCourierWithoutPass() {
        Response response = CourierMethods.authCourierWithOutLogin();
        response.then().statusCode(400).and().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void loginCourierForDefunctLogin() {
        Response response = CourierMethods.authCourierForDefunctLogin();
        response.then().statusCode(404).and().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void setDown() {
        CourierMethods.deleteCourier();
    }
}
