import data.CourierId;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierTest {
    private String login;
    private String password;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        login = ApiHelper.appendUniqueSuffix("galochka");
        password = "12345";
        ApiHelper.createCourier(login, password, null);
    }

    @Test
    @DisplayName("Can delete courier with valid id")
    @Description("")
    public void deleteValidCourier() {
        CourierId id = ApiHelper.login(login, password)
                .as(CourierId.class);

        ApiHelper.deleteCourier(id)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Can not delete courier without id")
    public void canNotDeleteCourierWithoutId() {
        CourierId id = new CourierId(ApiHelper.MISSING_ID);
        ApiHelper.deleteCourier(id)
                .then()
                .assertThat().body("message", equalTo("Not Found."))
                .and()
                .statusCode(404);
    }

    @Test
    @DisplayName("Can not delete courier with wrong id")
    public void canNotDeleteCourierWithWrongId() {
        CourierId id = new CourierId(ApiHelper.WRONG_ID);
        ApiHelper.deleteCourier(id)
                .then()
                .assertThat().body("message", equalTo("Курьера с таким id нет."))
                .and()
                .statusCode(404);
    }

    @After
    public void tearDown(){
        ApiHelper.deleteCourier(login, password);
    }
}
