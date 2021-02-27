package en.dbtask.service;

import en.dbtask.entity.Occurrence;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface OccurrenceServiceI {

    Map<String, Occurrence> readLogsFromFile() throws IOException;
    Occurrence checkStateAndSetFirstTime(Occurrence occurrence);
    List<Occurrence> calculateLogsTime(Map<String, Occurrence> logsFromFile);
    Map<String, Occurrence> setTime(Map<String, Occurrence> occurrenceMap, Occurrence occurrence);
}
