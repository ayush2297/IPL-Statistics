package cricketleague.analyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    public static final String BATTING_CSV = "./src/test/resources/battingSample.csv";
    public static final String BATTING_CSV_WITH_DELIMITER_ERROR = "./src/test/resources/battingSamplewithDelimiterErr.csv";
    public static final String BATTING_CSV_WITH_HEADER_ERROR = "./src/test/resources/battingSampleWithHeaderErr.csv";
    public static final String INCORRECT_FILE = "./src/test/resources/battingSample1.csv";

    @Test
    public void givenCricketLeagueCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(BATTING_CSV);
            Assert.assertEquals(8, numberOfRecords);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_IfHasDelimiterErrorsInIt_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(BATTING_CSV_WITH_DELIMITER_ERROR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR, e.type);
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_IfHasHeaderErrorsInIt_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(BATTING_CSV_WITH_HEADER_ERROR);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR, e.type);
        }
    }

    @Test
    public void givenWrongFile_AsInputToTheFileLoader_ShouldThrowException() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            int numberOfRecords = leagueAnalyser.loadDataFromCsv(INCORRECT_FILE);
        } catch (CricketLeagueAnalyserException e) {
            Assert.assertEquals(CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR, e.type);
        }
    }

    @Test
    public void givenCricketLeagueCsvFile_IfReturnsCorrectBatsman_WithHighestBattingAverage_ShouldReturnTrue() {
        try {
            CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
            leagueAnalyser.loadDataFromCsv(BATTING_CSV);
            String sortBasedOnAvg = leagueAnalyser.sortBasedOnAvg();
            IplBatsmanData[] batsmenArray = new Gson().fromJson(sortBasedOnAvg,IplBatsmanData[].class);
            Assert.assertEquals(83.2,batsmenArray[0].averageScore);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAcsvFile_WithDashInDataFields_AndReplacesThemWithZero_ShouldReturnTrue() {
        CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
        leagueAnalyser.prepareFile(BATTING_CSV);
    }
}

