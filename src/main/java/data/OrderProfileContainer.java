package data;

public class OrderProfileContainer {
    private OrderProfile order;

    public OrderProfileContainer() {
    }
    public OrderProfileContainer(OrderProfile order) {
        this.order = order;
    }

    public OrderProfile getOrder() {
        return order;
    }

    public void setOrder(OrderProfile order) {
        this.order = order;
    }
}
