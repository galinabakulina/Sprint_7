import data.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.UUID;

public class ApiHelper {
    public static final String WRONG_ID = "0";
    public static final String MISSING_ID = "";
    public static Response createCourier(String login, String password, String firstName) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(new Courier(login, password, firstName))
                .when()
                .post("/api/v1/courier");
    }

    public static String appendUniqueSuffix(String myText) {
        return myText + "_" + UUID.randomUUID();
    }

    public static Response login(String login, String password){
        return given()
                .header("Content-type", "application/json")
                .body(new LoginRequest(login, password))
                .post("/api/v1/courier/login");
    }

    public static Response createOrder(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color){
       return given()
               .header("Content-type", "application/json")
               .body(new OrderRequest(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color))
               .post("/api/v1/orders");
    }

    public static void deleteCourier(String login, String password) {
        CourierId id = login(login, password)
                .as(CourierId.class);

        given()
                .when()
                .delete("/api/v1/courier/" + id.getId());
    }

    public static Response deleteCourier(CourierId id) {
        return given()
                .delete("/api/v1/courier/" + id.getId());
    }

    public static Response cancelOrder(String trackId) {
        return given().put("/api/v1/orders/cancel?track=" + trackId);
    }

    public static Response getAllOrders(){
        return given().get("/api/v1/orders");
    }

    public static Response getOrder(String track){
        return given()
                .queryParam("t", track)
                .get("/api/v1/orders/track");
    }
    public static Response acceptOrder(String courierId, String orderId){
        return given()
                .queryParam("courierId", courierId)
                .put("/api/v1/orders/accept/" + orderId);
    }



}
