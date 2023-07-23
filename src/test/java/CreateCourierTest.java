
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Can create courier")
    public void createValidCourier() {
        final String login = ApiHelper.appendUniqueSuffix("galochka");
        final String password = "12345";
        final String firstName = "Galina";

        ApiHelper.createCourier(login, password, firstName)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

        ApiHelper.deleteCourier(login, password);
    }

    @Test
    @DisplayName("Can not create courier with missing fields")
    public void canNotCreateCourierWithMissingFields() {
        final String login = ApiHelper.appendUniqueSuffix("galochka");
        final String password = "12345";
        final String firstName = "Galina";

        createCourierWithMissingFieldsAndCheckError(null, null, null);
        createCourierWithMissingFieldsAndCheckError(null, null, firstName);
        createCourierWithMissingFieldsAndCheckError(null, password, null);
        createCourierWithMissingFieldsAndCheckError(null, password, firstName);
        createCourierWithMissingFieldsAndCheckError(login, null, null);
        createCourierWithMissingFieldsAndCheckError(login, null, firstName);

        // возможно создать курьера без firstName
        //createCourierWithMissingFieldsAndCheckError(login, password, null);
    }

    @Test
    @DisplayName("Can not create two identical couriers")
    public void canNotCreateDuplicatedCourier() {
        final String login = ApiHelper.appendUniqueSuffix("galochka");
        final String password = "12345";
        final String firstName = "Galina";

        ApiHelper.createCourier(login, password, firstName)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);

        ApiHelper.createCourier(login, password, null)
                .then()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);

        ApiHelper.deleteCourier(login, password);
    }

    private void createCourierWithMissingFieldsAndCheckError(String login, String password, String firstName) {
        ApiHelper.createCourier(login, password, firstName)
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

}
