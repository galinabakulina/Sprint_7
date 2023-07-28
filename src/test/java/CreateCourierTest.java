import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = ApiHelper.URL;
    }

    @Test
    @DisplayName("Can create courier")
    public void createValidCourier() {
        ApiHelper.createCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD, ApiHelper.FIRST_NAME)
                .then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Can not create two identical couriers")
    public void canNotCreateDuplicatedCourier() {
        ApiHelper.createCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD, ApiHelper.FIRST_NAME)
                .then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        ApiHelper.createCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD, ApiHelper.FIRST_NAME)
                .then()
                .statusCode(409)
                .and()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        ApiHelper.deleteCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD);
    }
}
