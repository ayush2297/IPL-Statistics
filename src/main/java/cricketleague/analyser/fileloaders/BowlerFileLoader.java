package cricketleague.analyser.fileloaders;

import cricketleague.analyser.CricketLeagueAnalyser;
import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.POJOs.IplBowlerData;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;

import java.util.Map;
import java.util.stream.StreamSupport;

public class BowlerFileLoader extends CsvFileLoader{

    CricketLeagueAnalyser analyser = new CricketLeagueAnalyser();
    Map<String, IplPlayerDAO> bowlerList = null;

    public BowlerFileLoader() {
        this.bowlerList = analyser.playersList;
    }

    @Override
    public Map<String, IplPlayerDAO> loadCsv(String csvFilePath, String replaceMissingValuesWith) throws CricketLeagueAnalyserException {
        try {
            Iterable<IplBowlerData> csvIterable = super.getCsvIterable(IplBowlerData.class, csvFilePath, replaceMissingValuesWith);
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplBowlerData -> mergeData(iplBowlerData));
            return bowlerList;
        } catch (CricketLeagueAnalyserException e) {
            throw e;
        }
    }

    private void mergeData(IplBowlerData iplBowlerData) {
        IplPlayerDAO iplPlayerDAO = this.bowlerList.get(iplBowlerData.playerName);
        if(iplPlayerDAO == null) {
            this.bowlerList.put(iplBowlerData.playerName,new IplPlayerDAO(iplBowlerData));
            return;
        }
        iplPlayerDAO.bowlingAverage = iplBowlerData.averageRunsGiven;
        iplPlayerDAO.bowlingStrikeRate = iplBowlerData.strikeRate;
        iplPlayerDAO.bowlerDto = iplBowlerData;
    }
}
