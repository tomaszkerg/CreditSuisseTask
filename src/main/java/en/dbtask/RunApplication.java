package en.dbtask;

import en.dbtask.dao.OccurrenceDao;
import en.dbtask.entity.Occurrence;
import en.dbtask.service.OccurrenceService;
import en.dbtask.service.OccurrenceServiceI;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class RunApplication {

    private final OccurrenceServiceI occurrenceService = new OccurrenceService();

    public static void main(String[] args) {
        RunApplication runApplication = new RunApplication();
        runApplication.runApp();
    }
    public void runApp() {
        try {
            OccurrenceDao occurrenceDao = new OccurrenceDao();
            log.info("Reading logs from file");
            Map<String, Occurrence> occurrenceFromFile = occurrenceService.readLogsFromFile();
            List<Occurrence> occurrences = occurrenceService.calculateLogsTime(occurrenceFromFile);
            for (Occurrence o : occurrences) {
                Runnable runnable = () -> occurrenceDao.create(o);
                runnable.run();
            }
            occurrenceDao.closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
