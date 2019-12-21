package cricketleague.analyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    public static final String BATTING_CSV = "./src/test/resources/battingSample.csv";
    public static final String BATTING_CSV_WITH_DELIMITER_ERROR = "./src/test/resources/battingSamplewithDelimiterErr.csv";
    public static final String BATTING_CSV_WITH_HEADER_ERROR = "./src/test/resources/battingSampleWithHeaderErr.csv";
    public static final String BOWLING_CSV = "./src/test/resources/bowling_sample.csv";
    public static final String INCORRECT_FILE = "./src/test/resources/battingSample1.csv";

    @Test
    public void givenCricketLeagueCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            Assert.assertEquals(8, numberOfRecords);
        } catch (CricketLeagueAnalyserException e) {
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_IfHasDelimiterErrorsInIt_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV_WITH_DELIMITER_ERROR, "00");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR, e.type);
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_IfHasHeaderErrorsInIt_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV_WITH_HEADER_ERROR, "00");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR, e.type);
        }
    }

    @Test
    public void givenWrongFile_AsInputToTheFileLoader_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,INCORRECT_FILE, "00");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR, e.type);
        }
    }

    @Test
    public void givenAcsvFile_WithDashInDataFields_AndReplacesThemWithZero_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAWrongCsvFile_ToProcessTheReplacementFunction_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,INCORRECT_FILE, "00");
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR, e.type);
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_AfterSortingBasedOnBattingAverage_IfReturnsCorrectBatsman_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(CricketLeagueAnalyser.CompareBasedOn.AVERAGE);
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
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(CricketLeagueAnalyser.CompareBasedOn.AVERAGE);
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
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(CricketLeagueAnalyser.CompareBasedOn.SIX_AND_FOURS_HIT);
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
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(CricketLeagueAnalyser.CompareBasedOn.STRIKE_RATE_WITH_6sn4s);
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
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(CricketLeagueAnalyser.CompareBasedOn.AVG_THEN_STRIKERATE);
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
            leagueAnalyser.loadDataFromCsv(PlayerType.BATSMAN,BATTING_CSV, "00");
            String sortBasedOnAvg = leagueAnalyser.sortBasedOn(CricketLeagueAnalyser.CompareBasedOn.RUNS_THEN_AVG);
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
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(PlayerType.BOWLER,BOWLING_CSV, "99");
            Assert.assertEquals(8, numberOfRecords);
        } catch (CricketLeagueAnalyserException e) {
        }
    }
}

