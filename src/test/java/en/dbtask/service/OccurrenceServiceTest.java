package en.dbtask.service;

import en.dbtask.entity.Occurrence;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OccurrenceServiceTest {


    private List<Occurrence> occurrenceList;
    private Map<String, Occurrence> occurrenceMap;
    private OccurrenceServiceI occurrenceService = new OccurrenceService();

    @Before
    public void init() {
        occurrenceList = new ArrayList<>();
        occurrenceList.add(new Occurrence("scsmbstgra", "STARTED", 1491377495212L, "APPLICATION_LOG", "12345"));
        occurrenceList.add(new Occurrence("scsmbstgrb", "STARTED", 1491377495213L));
        occurrenceList.add(new Occurrence("scsmbstgrc", "FINISHED", 1491377495218L));
        occurrenceList.add(new Occurrence("scsmbstgrd", "FINISHED", 1491377495211L));
        occurrenceList.add(new Occurrence("scsmbstgra", "FINISHED", 1491377495217L, "APPLICATION_LOG", "12345"));
        occurrenceList.add(new Occurrence("scsmbstgrc", "STARTED", 1491377495210L));
        occurrenceList.add(new Occurrence("scsmbstgrb", "FINISHED", 1491377495216L));
        occurrenceList.add(new Occurrence("scsmbstgrd", "FINISHED", 1491377495216L));

        occurrenceMap = new HashMap<>();
        occurrenceMap.put("scsmbstgrb", new Occurrence("scsmbstgrb", "STARTED","app","1233", 1491377495213L,1491377495213L,1491377495218L));
    }


    @Test
    public void shouldSetTime(){
        Occurrence occurrence = new Occurrence("scsmbstgra","FINISHED", 1491377495217L);
        Map<String, Occurrence> occurrenceMapTest = occurrenceService.setTime(occurrenceMap, occurrence);
        Occurrence occurrenceTest =  new Occurrence("scsmbstgra","FINISHED", 1491377495217L);
        occurrenceTest.setFinishTime(1491377495217L);
        Assert.assertEquals(occurrenceMapTest.get("scsmbstgra"),occurrenceTest);
    }

    @Test
    public void shouldSetSecondTime(){
        Occurrence occurrence = new Occurrence("scsmbstgra","FINISHED", 1491377495217L);
        occurrence.setFinishTime(1491377495217L);
        occurrenceMap.put("scsmbstgra",occurrence);
        Occurrence occurrenceStart = new Occurrence("scsmbstgra","STARTED", 1491377495220L);
        Map<String, Occurrence> occurrenceMapTest = occurrenceService.setTime(occurrenceMap, occurrenceStart);
        occurrence.setStartTime(1491377495220L);
        Assert.assertEquals(occurrenceMapTest.get("scsmbstgra"), occurrence);
    }

    @Test
    public void shouldCountDuration(){
        List<Occurrence> occurrenceListTest = occurrenceService.calculateLogsTime(occurrenceMap);
        Occurrence occurrenceTest = new Occurrence("scsmbstgrb", "STARTED","app","1233", 1491377495213L,1491377495213L,1491377495218L);
        occurrenceTest.setDuration(5L);
        occurrenceTest.setAlert(true);
        Assert.assertEquals(occurrenceListTest.get(0),occurrenceTest);
    }
}
