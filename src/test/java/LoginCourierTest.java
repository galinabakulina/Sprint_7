import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {
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
    @DisplayName("Valid courier can login")
    public void loginWithValidFields() {
        ApiHelper.login(login, password)
                .then()
                .assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Courier without login can not login")
    public void canNotLoginWithMissingFields() {
        ApiHelper.login(null, password)
                .then()
                .assertThat().body("message",  equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
/*
        ApiHelper.login(login, null)
                .then()
                .assertThat()
                .statusCode(504);

        ApiHelper.login(null, null)
                .then()
                .assertThat()
                .statusCode(504);
*/
    }

    @Test
    @DisplayName("Courier with wrong password can not login")
    public void canNotLoginWithWrongPassword() {
        ApiHelper.login(login, "wrong_password")
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @Test
    @DisplayName("Courier with nonexistent login can not login")
    public void canNotLoginWithWrongLogin() {
        ApiHelper.login("wrong_login", password)
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }

    @After
    public void tearDown(){
        ApiHelper.deleteCourier(login, password);
    }
}
