import data.OrderTrackId;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final Integer metroStation;
    private final String phone;
    private final Integer rentTime;
    private final String deliveryDate;
    private final String comment;
    private final List<String> color;

    public CreateOrderTest(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] orderDetails() {

        return new Object[][] {
                {"Галина", "Бакулина", "Ходынский бульвар, 13-219", 122, "+79031111111", 5, "2023-08-20",  "Не звоните, напишите смс, пожалуйста", List.of("BLACK")},
                {"Василий", "Кравцов", "Пресненская набережная, 12", 143, "+79261111111", 3, "2023-07-01", "Жду!", List.of("GRAY")},
                {"Василий", "Кравцов", "Пресненская набережная, 12", 143, "+79261111111", 3, "2023-07-01", "Жду!", List.of("GRAY", "BLACK")},
                {"Артем", "Бакулин", "Ходынский бульвар, 13-219", 122, "+79031111111", 5, "2023-08-20", "Не звоните, напишите смс, пожалуйста", List.of()}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Can create an order")
    public void createValidOrder() {
        Response response = ApiHelper.createOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        response.then().assertThat().body("track", notNullValue());

        OrderTrackId track = response.as(OrderTrackId.class);
        ApiHelper.cancelOrder(track.getTrack())
                .then()
                .statusCode(200);
    }

}
