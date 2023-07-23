package data;

public class CourierId {
    private String id;

    public CourierId() {}

    public CourierId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CourierId{" +
                "id='" + id + '\'' +
                '}';
    }
}
