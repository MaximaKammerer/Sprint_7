import HttpMethods.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CourierCreateTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Курьера можно создать")
    public void CreateCourier() {
        Response response = CourierMethods.CourierCorrectCreate();
        response.then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("ok", is(true));
    }
    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void CreateSameCourier() {
        CourierMethods.CourierCorrectCreate();
        Response response  =  CourierMethods.CourierCorrectCreate();
        response.then().statusCode(409).and().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой.")) ;
    }
    @Test
    @DisplayName("Чтобы создать курьера, нужно передать в ручку все обязательные поля")
    public void createWithoutRequiredField() {
        Response response = CourierMethods.CourierCreateWithoutPass();
        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void setDown(){
    CourierMethods.DeleteCourier();
    }

}
