import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = ApiHelper.URL;
        ApiHelper.createCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD, null);
    }

    @Test
    @DisplayName("Valid courier can login")
    public void loginWithValidFields() {
        ApiHelper.login(ApiHelper.LOGIN, ApiHelper.PASSWORD)
                .then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Courier without login can not login")
    public void canNotLoginWithMissingFields() {
        ApiHelper.login(null, ApiHelper.PASSWORD)
                .then()
                .statusCode(400)
                .and()
                .assertThat().body("message",  equalTo("Недостаточно данных для входа"));
/*
        ApiHelper.login(ApiHelper.LOGIN, null)
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
        ApiHelper.login(ApiHelper.LOGIN, "wrong_password")
                .then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Courier with nonexistent login can not login")
    public void canNotLoginWithWrongLogin() {
        ApiHelper.login("wrong_login", ApiHelper.PASSWORD)
                .then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public  void  tearDown(){
        ApiHelper.deleteCourier(ApiHelper.LOGIN, ApiHelper.PASSWORD);
    }

}
