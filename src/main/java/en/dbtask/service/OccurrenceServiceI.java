package en.dbtask.service;

import en.dbtask.dto.OccurrenceDto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface OccurrenceServiceI {

    public Map<String, OccurrenceDto> readLogsFromFile() throws IOException;
}
