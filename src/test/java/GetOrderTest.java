import data.OrderTrackId;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderTest {
    private OrderTrackId track;
    @Before
    public void setUp() {
        RestAssured.baseURI = ApiHelper.URL;

        String firstName = "Галина";
        String lastName = "Бакулина";
        String address = "Ходынский бульвар 13-219";
        Integer metroStation = 122;
        String phone = "+79031111111";
        Integer rentTime = 2;
        String deliveryDate = "2023-08-29";
        String comment = "";
        List<String> color = List.of("GREY");

        track = ApiHelper.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color)
                .as(OrderTrackId.class);
    }

    @Test
    @DisplayName("Can get valid order")
    public void getValidOrder() {
        ApiHelper.getOrder(track.getTrack())
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("order.id", notNullValue());

    }

    @Test
    @DisplayName("Can not get order without trackId")
    public void canNotGetOrderWithoutTrackId() {
        ApiHelper.getOrder(ApiHelper.MISSING_ID)
                .then()
                .statusCode(400)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Can not get order with wrong trackId")
    public void canNotGetOrderWithWrongTrackId() {
        ApiHelper.getOrder(ApiHelper.WRONG_ID)
                .then()
                .statusCode(404)
                .and()
                .assertThat()
                .body("message", equalTo("Заказ не найден"));
    }

    @After
    public void tearDown(){
        ApiHelper.cancelOrder(track.getTrack());
    }
}
