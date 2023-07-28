package data;

import java.util.List;

public class OrderList {

    List<Order> orders;

    public OrderList(){}

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "orders=" + orders +
                '}';
    }
}
