import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CreateCourierWithMissingFieldsTest {
    private final String login;
    private final String password;
    private final String firstName;

    public CreateCourierWithMissingFieldsTest(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters
    public static Object[][] orderDetails() {

        return new Object[][]{
                {null, null, null},
                {null, null, ApiHelper.FIRST_NAME},
                {null, ApiHelper.PASSWORD, null},
                {null, ApiHelper.PASSWORD, ApiHelper.FIRST_NAME},
                {ApiHelper.LOGIN, null, null},
                {ApiHelper.LOGIN, null, ApiHelper.FIRST_NAME}
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = ApiHelper.URL;
    }

    @Test
    @DisplayName("Can not create courier with missing fields")
    public void canNotCreateCourierWithMissingFields() {
        ApiHelper.createCourier(login, password, firstName)
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
