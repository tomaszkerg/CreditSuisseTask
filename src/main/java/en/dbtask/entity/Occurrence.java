package en.dbtask.entity;

import java.util.Objects;

public class Occurrence {

    private String id;
    private String state;
    private String type;
    private String host;
    private Long duration;
    private Boolean alert;

    public Occurrence() {
    }

    public Occurrence(String id, String state, String type, String host, Long duration, Boolean alert) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.duration = duration;
        this.alert = alert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occurrence occurrence = (Occurrence) o;
        return Objects.equals(id, occurrence.id) &&
                Objects.equals(state, occurrence.state) &&
                Objects.equals(type, occurrence.type) &&
                Objects.equals(host, occurrence.host) &&
                Objects.equals(duration, occurrence.duration) &&
                Objects.equals(alert, occurrence.alert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, type, host, duration, alert);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }
}
