package cricketleague.analyser;

import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueAnalyserTest {
    public static final String BATTING_CSV = "./src/test/resources/battingSample.csv";

    @Test
    public void givenCricketLeagueCsvFile_IfHasCorrectNumberOfRecords_ShouldReturnTrue() {
        CricketLeagueAnalyser leagueAnalyser = new CricketLeagueAnalyser();
        int numberOfRecords = leagueAnalyser.loadDataFromCsv(BATTING_CSV);
        Assert.assertEquals(8,numberOfRecords);
    }

}
