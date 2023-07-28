package data;

public class OrderTrackId {
    private String track;

    public OrderTrackId() {
    }

    public OrderTrackId(String track) {
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "OrderTrackId{" +
                "track='" + track + '\'' +
                '}';
    }
}
