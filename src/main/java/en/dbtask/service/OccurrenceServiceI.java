package en.dbtask.service;

import en.dbtask.dto.OccurrenceDto;
import en.dbtask.entity.Occurrence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface OccurrenceServiceI {

    public Map<String, OccurrenceDto> readLogsFromFile() throws IOException;
    public Occurrence mapperFromDto(OccurrenceDto occurrenceDto);
    public Occurrence countOccurrenceTime(Occurrence occurrence, OccurrenceDto occurrenceDto);
}
