package en.dbtask.entity;

import java.util.Objects;

public class Occurrence {

    private String id;
    private String state;
    private String type;
    private String host;
    private Long duration;
    private Long timestamp;
    private Long startTime;
    private Long finishTime;
    private Boolean alert;



    public Occurrence() {
    }

    public Occurrence(String id, String state, Long timestamp, String type, String host) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }

    public Occurrence(String id, String state, String type, String host, Long timestamp, Long startTime, Long finishTime) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Occurrence(String id, String state, Long timestamp) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
    }

    public Occurrence(String id, String state, String type, String host, Long duration, Long timestamp, Long startTime, Long finishTime, Boolean alert) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.duration = duration;
        this.timestamp = timestamp;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.alert = alert;
    }
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Occurrence that = (Occurrence) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(state, that.state) &&
                Objects.equals(type, that.type) &&
                Objects.equals(host, that.host) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(finishTime, that.finishTime) &&
                Objects.equals(alert, that.alert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, type, host, duration, startTime, finishTime, alert);
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
