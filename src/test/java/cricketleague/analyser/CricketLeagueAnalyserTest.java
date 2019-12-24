package cricketleague.analyser;

import com.google.gson.Gson;
import cricketleague.analyser.POJOs.AllRounderDTO;
import cricketleague.analyser.POJOs.IplBatsmanData;
import cricketleague.analyser.POJOs.IplBowlerData;
import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;
import cricketleague.analyser.analyseressentials.MyComparators;
import cricketleague.analyser.analyseressentials.PlayerType;
import cricketleague.analyser.fileloaders.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CricketLeagueAnalyserTest {
    public static final String BATTING_CSV = "./src/test/resources/battingSample.csv";
    public static final String BATTING_CSV_WITH_DELIMITER_ERROR = "./src/test/resources/battingSamplewithDelimiterErr.csv";
    public static final String BATTING_CSV_WITH_HEADER_ERROR = "./src/test/resources/battingSampleWithHeaderErr.csv";
    public static final String BOWLING_CSV = "./src/test/resources/bowling_sample.csv";
    public static final String INCORRECT_FILE = "./src/test/resources/battingSample1.csv";
    private HashMap<String, IplPlayerDAO> expectedBattingMap;
    private HashMap<String, IplPlayerDAO> expectedBowlingMap;
    private HashMap<String,IplPlayerDAO> expectedAllRounderMap;

    @Before
    public void setup() {
        IplBatsmanData hp = new IplBatsmanData("Hardik Pandya",402,44.06,191.42,210,28,29);
        IplBatsmanData ar = new IplBatsmanData("Andre Russell",510,56.66,204.81,249,31,52);
        IplBatsmanData msd = new IplBatsmanData("MS Dhoni",416,83.2,134.62,309,22,23);
        IplBatsmanData is = new IplBatsmanData("Ishant Sharma",10,0.0,333.33,3,1,1);
        IplBatsmanData dw = new IplBatsmanData("David Warner",692,69.2,143.86,481,57,21);
        this.expectedBattingMap = new HashMap<String, IplPlayerDAO>() {{
            put("Hardik Pandya",new IplPlayerDAO(hp));
            put("Andre Russell",new IplPlayerDAO(ar));
            put("MS Dhoni",new IplPlayerDAO(msd));
            put("Ishant Sharma",new IplPlayerDAO(is));
            put("David Warner",new IplPlayerDAO(dw));
        }};
        IplBowlerData it = new IplBowlerData("Imran Tahir",16.57,14.84,6.69,2.0,0.0,26,64.2);
        IplBowlerData hpb = new IplBowlerData("Hardik Pandya",27.85,18.21,9.17,0.0,0.0,14,42.3);
        IplBowlerData kr = new IplBowlerData("Kagiso Rabada",14.72,11.28,7.82,2.0,0.0,25,47);
        IplBowlerData aj = new IplBowlerData("Alzarri Joseph",14.5,8.66,10.03,0.0,1.0,6,8.4);
        IplBowlerData arb = new IplBowlerData("Anukul Roy",11.0,12.0,5.5,0.0,0.0,1,2.0);
        IplBowlerData sd = new IplBowlerData("Shivam Dube",99.0,99.0,4.8,0.0,0.0,1,1.4);
        this.expectedBowlingMap = new HashMap<String, IplPlayerDAO>() {{
           put("Imran Tahir",new IplPlayerDAO(it));
           put("Hardik Pandya",new IplPlayerDAO(hpb));
           put("Kagiso Rabada",new IplPlayerDAO(kr));
           put("Alzarri Joseph",new IplPlayerDAO(aj));
           put("Anukul Roy",new IplPlayerDAO(arb));
           put("Shivam Dube",new IplPlayerDAO(sd));
        }};
        this.expectedAllRounderMap = new HashMap<String, IplPlayerDAO>() {{
            put("Hardik Pandya",new IplPlayerDAO(hp,hpb));
            put("Andre Russell",new IplPlayerDAO(ar));
            put("MS Dhoni",new IplPlayerDAO(msd));
            put("Ishant Sharma",new IplPlayerDAO(is));
            put("David Warner",new IplPlayerDAO(dw));
            put("Imran Tahir",new IplPlayerDAO(it));
            put("Kagiso Rabada",new IplPlayerDAO(kr));
            put("Alzarri Joseph",new IplPlayerDAO(aj));
            put("Anukul Roy",new IplPlayerDAO(arb));
            put("Shivam Dubey",new IplPlayerDAO(sd));
        }};
    }

    @Test
    public void givenCricketLeagueCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            Assert.assertEquals(5, numberOfRecords);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnBattingAverage_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.AVERAGE);
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals("MS Dhoni",batsmenArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnBoundariesHit_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.AVERAGE);
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals("MS Dhoni",batsmenArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnStrikeRate_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.SIX_AND_FOURS_HIT);
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals("Andre Russell",batsmenArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnStrikeRate_WithRespectTo6sAnd4s_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.STRIKE_RATE_WITH_6sn4s);
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals("Ishant Sharma",batsmenArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnAvg_AndThenStrikeRate_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.AVG_THEN_STRIKERATE);
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals("MS Dhoni",batsmenArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnRuns_AndThenAvg_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BATSMAN).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV)).thenReturn(this.expectedBattingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN, BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.RUNS_THEN_AVG);
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals("David Warner",batsmenArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueBowlingCsvFile_WhenLoaded_ShouldReturnCorrectNumberOfRecords() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            Assert.assertEquals(6, numberOfRecords);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueBowlingCsvFile_AfterSortingBasedBowlingAvg_IfReturnsCorrectBowler_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BOWLING_AVG);
            IplBowlerData[] bowlerArray = new Gson().fromJson(sortBasedOnAvg,IplBowlerData[].class);
            Assert.assertEquals("Anukul Roy",bowlerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueBowlingCsvFile_AfterSortingBasedBowlingStrikeRate_IfReturnsCorrectBowler_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BOWLING_SR);
            IplBowlerData[] bowlerArray = new Gson().fromJson(sortBasedOnAvg,IplBowlerData[].class);
            Assert.assertEquals("Alzarri Joseph",bowlerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueBowlingCsvFile_AfterSortingBasedBowlingEconomy_IfReturnsCorrectBowler_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BOWLING_ECONOMY);
            IplBowlerData[] bowlerArray = new Gson().fromJson(sortBasedOnAvg,IplBowlerData[].class);
            Assert.assertEquals("Shivam Dube",bowlerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueBowlingCsvFile_AfterSortingBasedOnBowlingSRWith4wAnd5w_IfReturnsCorrectBowler_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BOWLING_SR_WITH4WAND5W);
            IplBowlerData[] bowlerArray = new Gson().fromJson(sortBasedOnAvg,IplBowlerData[].class);
            Assert.assertEquals("Alzarri Joseph",bowlerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueBowlingCsvFile_AfterSortingBasedOnBowlingSRWithAvg_IfReturnsCorrectBowler_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BOWLING_SR_WITH_AVG);
            IplBowlerData[] bowlerArray = new Gson().fromJson(sortBasedOnAvg,IplBowlerData[].class);
            Assert.assertEquals("Anukul Roy",bowlerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenCricketLeagueBowlingCsvFile_AfterSortingBasedOnMostWicketsWithBestBowlingAvg_IfReturnsCorrectBowler_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOWLER).getClass());
            when(csvFileLoader.loadCsv(BOWLING_CSV)).thenReturn(this.expectedBowlingMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BOWLING_MOSTWKTS_WITH_AVG);
            IplBowlerData[] bowlerArray = new Gson().fromJson(sortBasedOnAvg,IplBowlerData[].class);
            Assert.assertEquals("Kagiso Rabada",bowlerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFiles_AfterSortingBasedOnBestBattingAvgWithBestBowlingAvg_IfReturnsCorrectCricketer_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOTH).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV,BOWLING_CSV)).thenReturn(this.expectedAllRounderMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOTH, BATTING_CSV, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BEST_BATTING_AND_BOWLING_AVG);
            AllRounderDTO[] playerArray = new Gson().fromJson(sortBasedOnAvg,AllRounderDTO[].class);
            Assert.assertEquals("Hardik Pandya",playerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFiles_AfterSortingBasedOnMostRunsAndMostWickets_IfReturnsCorrectCricketer_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            CsvFileLoader csvFileLoader = mock(leagueAnalyser.getFactoryObject(PlayerType.BOTH).getClass());
            when(csvFileLoader.loadCsv(BATTING_CSV,BOWLING_CSV)).thenReturn(this.expectedAllRounderMap);
            leagueAnalyser.setAdapter(csvFileLoader);
            leagueAnalyser.loadDataFromCsv(PlayerType.BOTH, BATTING_CSV, BOWLING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(MyComparators.CompareBasedOn.BEST_ALL_ROUNDER);
            AllRounderDTO[] playerArray = new Gson().fromJson(sortBasedOnAvg,AllRounderDTO[].class);
            Assert.assertEquals("Hardik Pandya",playerArray[0].playerName);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

}

