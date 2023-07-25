import data.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderTest {
    private CourierId courierId;
    private OrderTrackId track;
    private OrderProfileContainer order;

    @Before
    public void setUp() {
        RestAssured.baseURI = ApiHelper.URL;

        ApiHelper.createCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD, ApiHelper.FIRST_NAME);
        courierId = ApiHelper.login(ApiHelper.LOGIN, ApiHelper.PASSWORD).as(CourierId.class);

        String firstName = "Галина";
        String lastName = "Бакулина";
        String address = "Ходынский бульвар 13-219";
        Integer metroStation = 122;
        String phone = "+79031111111";
        Integer rentTime = 6;
        String deliveryDate = "2023-07-29";
        String comment = "";
        List<String> color = List.of();

        track = ApiHelper.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color)
                .as(OrderTrackId.class);

        order = ApiHelper.getOrder(track.getTrack()).as(OrderProfileContainer.class);
    }

    @Test
    @DisplayName("Can accept valid order")
    public void acceptValidOrder() {
        ApiHelper.acceptOrder(courierId.getId(), order.getOrder().getId())
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("ok", equalTo(true));

        ApiHelper.getOrder(track.getTrack())
                .then()
                .assertThat()
                .body("order.courierFirstName", equalTo(ApiHelper.FIRST_NAME));


    }

    @Test
    @DisplayName("Can not accept order without courierId")
    public void canNotAcceptOrderWithoutCourierId() {
        ApiHelper.acceptOrder(ApiHelper.MISSING_ID, order.getOrder().getId())
                .then()
                .statusCode(400)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для поиска"));
    }

    @Test
    @DisplayName("Can not accept order with wrong courierId")
    public void canNotAcceptOrderWithWrongCourierId() {
        ApiHelper.acceptOrder(ApiHelper.WRONG_ID, order.getOrder().getId())
                .then()
                .statusCode(404)
                .and()
                .assertThat()
                .body("message", equalTo("Курьера с таким id не существует"));
    }

    @Test
    @DisplayName("Can not accept order with wrong orderId")
    public void canNotAcceptOrderWithWrongOrderId() {
        ApiHelper.acceptOrder(courierId.getId(), ApiHelper.WRONG_ID)
                .then()
                .statusCode(404)
                .and()
                .assertThat()
                .body("message", equalTo("Заказа с таким id не существует"));

    }

    @After
    public void tearDown(){
        ApiHelper.deleteCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD);
        ApiHelper.cancelOrder(track.getTrack());
    }
}
