import data.OrderList;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GetAllOrdersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Can get all orders")
    public void getAllOrders(){
        OrderList orders = ApiHelper.getAllOrders().as(OrderList.class);
        Assert.assertTrue(orders.getOrders().size()>0);
    }
}
