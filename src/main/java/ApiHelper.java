import data.*;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.UUID;

public class ApiHelper {
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    public static final String LOGIN = appendUniqueSuffix("galochka");
    public static final String PASSWORD = "12345";
    public static final String FIRST_NAME = "Мой курьер";
    public static final String WRONG_ID = "0";
    public static final String MISSING_ID = "";
    private static final String API_COURIER = "/api/v1/courier";
    private static final String API_COURIER_LOGIN = API_COURIER + "/login";
    private static final String API_ORDER = "/api/v1/orders";
    private static final String API_CANCEL_ORDER = API_ORDER  + "/cancel";
    private static final String API_GET_ORDER = API_ORDER  + "/track";
    private static final String API_ACCEPT_ORDER = API_ORDER + "/accept/";

    @Step("Send post request to " + API_COURIER)
    public static Response createCourier(String login, String password, String firstName) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(new Courier(login, password, firstName))
                .when()
                .post(API_COURIER);
    }

    @Step("Send post request to " + API_COURIER_LOGIN)
    public static Response login(String login, String password){
        return given()
                .header("Content-type", "application/json")
                .body(new LoginRequest(login, password))
                .post(API_COURIER_LOGIN);
    }
    @Step("Send post request to " + API_ORDER)
    public static Response createOrder(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color){
       return given()
               .header("Content-type", "application/json")
               .body(new OrderRequest(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color))
               .post(API_ORDER);
    }
    @Step("Send delete request to " + API_COURIER + "/:id")
    public static void deleteCourier(String login, String password) {
        CourierId id = login(login, password)
                .as(CourierId.class);

        given()
                .when()
                .delete(API_COURIER + "/" + id.getId());
    }
    @Step("Send delete request to " + API_COURIER + "/:id")
    public static Response deleteCourier(CourierId id) {
        return given()
                .delete(API_COURIER + "/" + id.getId());
    }
    @Step("Send put request to " + API_CANCEL_ORDER)
    public static Response cancelOrder(String trackId) {
        return given()
                .put(API_CANCEL_ORDER +"?track=" + trackId);
    }
    @Step("Send get request to " + API_ORDER)
    public static Response getAllOrders(){
        return given()
                .get(API_ORDER);
    }

    @Step("Send get request to " + API_GET_ORDER)
    public static Response getOrder(String track){
        return given()
                .queryParam("t", track)
                .get(API_GET_ORDER);
    }

    @Step("Send put request to " + API_ACCEPT_ORDER)
    public static Response acceptOrder(String courierId, String orderId){
        return given()
                .queryParam("courierId", courierId)
                .put(API_ACCEPT_ORDER + orderId);
    }

    private static String appendUniqueSuffix(String myText) {
        return myText + "_" + UUID.randomUUID();
    }
}
