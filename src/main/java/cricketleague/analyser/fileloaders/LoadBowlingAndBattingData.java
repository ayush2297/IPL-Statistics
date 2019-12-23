package cricketleague.analyser.fileloaders;

import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;

import java.util.HashMap;
import java.util.Map;

public class LoadBowlingAndBattingData extends CsvFileLoader {
    Map<String,IplPlayerDAO> allRounderMap = null;

    public LoadBowlingAndBattingData() {
        this.allRounderMap = new HashMap<>();
    }

    @Override
    public Map<String, IplPlayerDAO> loadCsv(String... csvFilePath) throws CricketLeagueAnalyserException {
        this.allRounderMap = new BatsmenFileLoader().loadCsv(csvFilePath[0]);
        this.allRounderMap = new BowlerFileLoader(this.allRounderMap).loadCsv( csvFilePath[1]);
        return this.allRounderMap;
    }
}
