import data.CourierId;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierTest {
    private CourierId id;

    @Before
    public void setUp() {
        RestAssured.baseURI = ApiHelper.URL;
    }

    @Test
    @DisplayName("Can delete courier with valid id")
    @Description("")
    public void deleteValidCourier() {
        ApiHelper.createCourier(ApiHelper.LOGIN,ApiHelper.PASSWORD, null);
        id = ApiHelper.login(ApiHelper.LOGIN,ApiHelper.PASSWORD)
                .as(CourierId.class);
        ApiHelper.deleteCourier(id)
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Can not delete courier without id")
    public void canNotDeleteCourierWithoutId() {
        ApiHelper.createCourier(ApiHelper.LOGIN,ApiHelper.PASSWORD, null);
        id = new CourierId(ApiHelper.MISSING_ID);
        ApiHelper.deleteCourier(id)
                .then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Not Found."));
        ApiHelper.deleteCourier(ApiHelper.LOGIN,ApiHelper.PASSWORD);
    }

    @Test
    @DisplayName("Can not delete courier with wrong id")
    public void canNotDeleteCourierWithWrongId() {
        ApiHelper.createCourier(ApiHelper.LOGIN,ApiHelper.PASSWORD, null);
        CourierId id = new CourierId(ApiHelper.WRONG_ID);
        ApiHelper.deleteCourier(id)
                .then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Курьера с таким id нет."));
        ApiHelper.deleteCourier(ApiHelper.LOGIN,ApiHelper.PASSWORD);
    }
}
