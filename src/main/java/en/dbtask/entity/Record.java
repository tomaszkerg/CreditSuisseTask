package en.dbtask.entity;

import java.util.Objects;

public class Record {

    private String id;
    private String state;
    private String type;
    private String host;
    private Integer duration;
    private Boolean alert;

    public Record() {
    }

    public Record(String id, String state, String type, String host, Integer duration, Boolean alert) {
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
        Record record = (Record) o;
        return Objects.equals(id, record.id) &&
                Objects.equals(state, record.state) &&
                Objects.equals(type, record.type) &&
                Objects.equals(host, record.host) &&
                Objects.equals(duration, record.duration) &&
                Objects.equals(alert, record.alert);
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }
}
