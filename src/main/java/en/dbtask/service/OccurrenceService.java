package en.dbtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import en.dbtask.dto.OccurrenceDto;
import en.dbtask.entity.Occurrence;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OccurrenceService implements OccurrenceServiceI{
    private String finished = "FINISHED";
    private String started = "STARTED";


    @Override
    public Map<String, OccurrenceDto> readLogsFromFile() throws IOException {
        Map<String, OccurrenceDto> occurrenceDtoMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("logs.json"));
        String line = bufferedReader.readLine();
        while(line != null){
            Occurrence occurrence = objectMapper.readValue(line,Occurrence.class);
            OccurrenceDto occurrenceDto = occurrenceDtoMap.get(occurrence.getId());
            if(occurrenceDto == null) {
                occurrenceDto = new OccurrenceDto(occurrence.getId(),occurrence.getType(), occurrence.getHost());
                occurrenceDtoMap.put(occurrence.getId(),occurrenceDto);
            }
            if (finished.equals(occurrence.getState())) {
                occurrenceDto.setFinish(occurrence);
            } else if (started.equals(occurrence.getState())) {
                occurrenceDto.setStart(occurrence);
            } else{
                log.info("no such event");
            }

            line = bufferedReader.readLine();
        }
        return occurrenceDtoMap;
    }
}
