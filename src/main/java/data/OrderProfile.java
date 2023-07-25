package data;

import java.util.List;

public class OrderProfile {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String track;
    private String status;
    private List<String> color;
    private String comment;
    private boolean isCancelled;
    private boolean isFinished;
    private boolean isInDelivery;
    private String courierFirstName;
    private String createdAt;
    private String updatedAt;

    public OrderProfile() {
    }

    public OrderProfile(String id, String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String track, String status, List<String> color, String comment, boolean isCancelled, boolean isFinished, boolean isInDelivery, String courierFirstName, String createdAt, String updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.track = track;
        this.status = status;
        this.color = color;
        this.comment = comment;
        this.isCancelled = isCancelled;
        this.isFinished = isFinished;
        this.isInDelivery = isInDelivery;
        this.courierFirstName = courierFirstName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(Integer metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isInDelivery() {
        return isInDelivery;
    }

    public void setInDelivery(boolean inDelivery) {
        isInDelivery = inDelivery;
    }

    public String getCourierFirstName() {
        return courierFirstName;
    }

    public void setCourierFirstName(String courierFirstName) {
        this.courierFirstName = courierFirstName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "OrderProfile{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", metroStation='" + metroStation + '\'' +
                ", phone='" + phone + '\'' +
                ", rentTime='" + rentTime + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", track='" + track + '\'' +
                ", status='" + status + '\'' +
                ", color=" + color +
                ", comment='" + comment + '\'' +
                ", isCancelled=" + isCancelled +
                ", isFinished=" + isFinished +
                ", isInDelivery=" + isInDelivery +
                ", courierFirstName='" + courierFirstName + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
