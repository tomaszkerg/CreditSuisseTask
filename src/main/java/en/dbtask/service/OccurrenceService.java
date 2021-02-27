package en.dbtask.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import en.dbtask.entity.Occurrence;
import lombok.extern.slf4j.Slf4j;


import java.io.*;

import java.util.*;

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
        File file = new File(fileName);

        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Occurrence occurrence = objectMapper.readValue(line,Occurrence.class);
                log.info("read log with id: "+occurrence.getId());
                occurrenceMap = setTime(occurrenceMap,occurrence);
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
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
