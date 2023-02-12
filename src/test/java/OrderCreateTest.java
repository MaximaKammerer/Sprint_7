import HttpMethods.*;
import model.Order;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class OrderCreateTest  {
    private final String firstNameValue;
    private final String lastNameValue;
    private final String addressValue;
    private final String metroStationValue;
    private final String phoneValue;
    private final int rentTimeValue;
    private final String deliveryDateValue;
    private final String commentValue;
    private final List<String> colorValue;

    public OrderCreateTest(String firstNameValue, String lastNameValue, String addressValue, String metroStationValue, String phoneValue, int rentTimeValue, String deliveryDateValue, String commentValue, List<String> colorValue) {
        this.firstNameValue = firstNameValue;
        this.lastNameValue = lastNameValue;
        this.addressValue = addressValue;
        this.metroStationValue = metroStationValue;
        this.phoneValue = phoneValue;
        this.rentTimeValue = rentTimeValue;
        this.deliveryDateValue = deliveryDateValue;
        this.commentValue = commentValue;
        this.colorValue = colorValue;
    }

    @Parameterized.Parameters
    public static Object[][] getTestDataCreateOrder() {
        return new Object[][]{
                {"Sophia", "St", "Pushkina, 174 apt.", "2", "+7 920 333 33 33", 3, "2023-01-23", "Go home", null},
                {"Sophia", "St", "Pushkina, 174 apt.", "2", "+7 920 333 33 33", 3, "2023-01-23", "Go home", List.of("BLACK")},
                {"Sophia", "St", "Pushkina, 174 apt.", "2", "+7 920 333 33 33", 3, "2023-01-23", "Go home", List.of("GREY")},
                {"Sophia", "St", "Pushkina, 174 apt.", "2", "+7 920 333 33 33", 3, "2023-01-23", "Go home", List.of("BLACK", "GREY")},
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @DisplayName("можно указать один из цветов — BLACK или GREY/можно указать оба цвета/можно совсем не указывать цвет")
    @Test
    public void testTrackFieldInOrder(){
       Response response  = OrderMethods.OrderCorrectCreate(new Order(
                firstNameValue, lastNameValue, addressValue,
                metroStationValue, phoneValue, rentTimeValue, deliveryDateValue, commentValue, colorValue));
       response.then().statusCode(201).and().body("track", notNullValue());
        System.out.println(response.body().asString());
    }
}