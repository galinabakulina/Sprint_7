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

        ApiHelper.deleteCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD);
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

        ApiHelper.deleteCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD);
    }

    @Test
    @DisplayName("Can not create courier with missing login and password")
    public void canNotCreateCourierWithMissingFields() {
        createCourierWithMissingFieldsAndCheckError(null, null, null);
        createCourierWithMissingFieldsAndCheckError(null, null, ApiHelper.FIRST_NAME);
    }

    @Test
    @DisplayName("Can not create courier without login")
    public void canNotCreateCourierWithoutLogin() {

        createCourierWithMissingFieldsAndCheckError(null, ApiHelper.PASSWORD, null);
        createCourierWithMissingFieldsAndCheckError(null, ApiHelper.PASSWORD, ApiHelper.FIRST_NAME);
    }
    @Test
    @DisplayName("Can not create courier without password")
    public void canNotCreateCourierWithoutPassword() {
        createCourierWithMissingFieldsAndCheckError(ApiHelper.LOGIN, null, null);
        createCourierWithMissingFieldsAndCheckError(ApiHelper.LOGIN, null, ApiHelper.FIRST_NAME);
    }

    private void createCourierWithMissingFieldsAndCheckError(String login, String password, String firstName) {
        ApiHelper.createCourier(login, password, firstName)
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
