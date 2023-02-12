package HttpMethods;
import model.Courier;
import model.LoginRespounse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static model.Constants.*;



public class CourierMethods {


    public static Response CourierCorrectCreate() {
        Courier courier = new Courier(LOGIN, PASSWORD, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
    public static Response CourierCreateWithoutPass() {
        Courier courier = new Courier(LOGIN, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }
    public static Response AuthCourier() {
        CourierCorrectCreate();
        Courier courier = new Courier(LOGIN, PASSWORD, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }

    public static Response AuthCourierWithOutLogin() {
        CourierCorrectCreate();
        Courier courier = new Courier(LOGIN);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }
    public static Response AuthCourierForDefunctLogin(){
        CourierCorrectCreate();
        Courier courier = new Courier(LOGIN+PASSWORD, PASSWORD, FIRSTNAME);
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post("/api/v1/courier/login");
    }


    public static Response DeleteCourier(){
        Response response = AuthCourier();
        LoginRespounse loginRespounse =  response.body().as(LoginRespounse.class);
        return
                given()
                .header("Content-type", "application/json")
                .delete("/api/v1/courier/" + loginRespounse.getId() );
    }
}
