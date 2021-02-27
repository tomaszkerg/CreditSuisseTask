package en.dbtask.service;

import en.dbtask.entity.Occurrence;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OccurrenceServiceI {

    public Map<String, Occurrence> readLogsFromFile() throws IOException;
    public Occurrence checkStateAndSetFirstTime(Occurrence occurrence);
    public List<Occurrence> calculateLogsTime(Map<String, Occurrence> logsFromFile);
    public Map<String, Occurrence> setTime(Map<String, Occurrence> occurrenceMap, Occurrence occurrence);
}
