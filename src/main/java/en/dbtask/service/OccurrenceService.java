package en.dbtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import en.dbtask.entity.Occurrence;
import lombok.extern.slf4j.Slf4j;


import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class OccurrenceService implements OccurrenceServiceI{


    @Override
    public Map<String, Occurrence> readLogsFromFile() throws IOException {
        Map<String, Occurrence> occurrenceMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String fileName = "logs.json";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = bufferedReader.readLine();
        while(line != null){
            Occurrence occurrence = objectMapper.readValue(line,Occurrence.class);
            log.info("read log with id: "+occurrence.getId());
            occurrenceMap = setTime(occurrenceMap,occurrence);
            line = bufferedReader.readLine();
        }
        return occurrenceMap;
    }



    @Override
    public Occurrence checkStateAndSetFirstTime(Occurrence occurrence) {
        String finished = "FINISHED";
        String started = "STARTED";
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
            long duration = o.getFinishTime()-o.getStartTime();
            o.setDuration(duration);
            long maxTime = 4L;
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
