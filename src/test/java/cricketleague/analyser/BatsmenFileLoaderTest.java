package cricketleague.analyser;

import cricketleague.analyser.POJOs.IplBatsmanData;
import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;
import cricketleague.analyser.fileloaders.BatsmenFileLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BatsmenFileLoaderTest {
    public static final String BATTING_CSV = "./src/test/resources/battingSample.csv";
    private HashMap<String, IplPlayerDAO> expectedBattingMap;

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
    }

    @Test
    public void givenFilePath_WhenPassedToLoadCsv_ShouldReturnCorrectHashmap() {
        try {
            BatsmenFileLoader batsmenFileLoader = new BatsmenFileLoader();
            Map<String, IplPlayerDAO> batsmanMap = batsmenFileLoader.loadCsv(BATTING_CSV);
            Assert.assertEquals(this.expectedBattingMap,batsmanMap);
        } catch (CricketLeagueAnalyserException e) {
            e.printStackTrace();
        }
    }
}
