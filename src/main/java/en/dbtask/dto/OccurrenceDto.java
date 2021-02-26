package en.dbtask.dto;

import en.dbtask.entity.Occurrence;

public class OccurrenceDto {
    private String id;
    private String type;
    private String host;
    private Occurrence start;
    private Occurrence finish;


    public OccurrenceDto(String id, String type, String host, Occurrence start) {
        this.id = id;
        this.type = type;
        this.host = host;
        this.start = start;
    }

    public OccurrenceDto(String id, String type, String host) {
        this.id = id;
        this.type = type;
        this.host = host;
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

    public Occurrence getStart() {
        return start;
    }

    public void setStart(Occurrence start) {
        this.start = start;
    }

    public Occurrence getFinish() {
        return finish;
    }

    public void setFinish(Occurrence finish) {
        this.finish = finish;
    }
}
