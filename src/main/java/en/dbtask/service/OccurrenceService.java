package en.dbtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import en.dbtask.entity.Occurrence;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class OccurrenceService implements OccurrenceServiceI{
    private String finished = "FINISHED";
    private String started = "STARTED";
    private Long maxTime = 4L;
    private String fileName = "logs.json";


    @Override
    public Map<String, Occurrence> readLogsFromFile() throws IOException {
        Map<String, Occurrence> occurrenceMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = bufferedReader.readLine();
        while(line != null){
            Occurrence occurrence = objectMapper.readValue(line,Occurrence.class);
            log.info("read log: "+occurrence);
            occurrenceMap = setTime(occurrenceMap,occurrence);
            line = bufferedReader.readLine();
        }
        return occurrenceMap;
    }



    @Override
    public Occurrence checkStateAndSetFirstTime(Occurrence occurrence) {
        if (finished.equals(occurrence.getState())) {
            occurrence.setFinishTime(occurrence.getTimestamp());
        } else if (started.equals(occurrence.getState())) {
            occurrence.setStartTime(occurrence.getTimestamp());
        } else{
            log.info("no such state");
        }
        return occurrence;
    }

    @Override
    public List<Occurrence> calculateLogsTime(Map<String, Occurrence> logsFromFile) {
        List<Occurrence> occurrenceList = new ArrayList<>();
        for(Occurrence o:logsFromFile.values()){
            log.info("calculating log time with id: "+o.getId());
            Long duration = o.getFinishTime()-o.getStartTime();
            o.setDuration(duration);
            o.setAlert(duration > maxTime);
            occurrenceList.add(o);
        }
        return occurrenceList;
    }

    @Override
    public Map<String, Occurrence> setTime(Map<String, Occurrence> occurrenceMap, Occurrence occurrence) {
        Occurrence occurrenceFromMap = occurrenceMap.get(occurrence.getId());
        log.info("setting time for log with id: "+occurrence.getId());
        if(occurrenceFromMap==null) {
            occurrence = checkStateAndSetFirstTime(occurrence);
            occurrenceMap.put(occurrence.getId(),occurrence);
        }else{
            if(occurrenceFromMap.getStartTime()!=null) {
                occurrenceFromMap.setFinishTime(occurrence.getTimestamp());
            }else if(occurrenceFromMap.getFinishTime()!=null){
                occurrenceFromMap.setStartTime(occurrence.getTimestamp());
            }
        }
        return occurrenceMap;
    }
}
