package cricketleague.analyser;

import java.util.Map;
import java.util.stream.StreamSupport;

public class BowlerFileLoader extends CsvFileLoader{

    Map<String,IplBatsmanDAO> bowlerList = null;

    public BowlerFileLoader() {
        this.bowlerList = CricketLeagueAnalyser.playersList;
    }

    @Override
    public Map<String, IplBatsmanDAO> loadCsv(String csvFilePath, String replaceMissingValuesWith) throws CricketLeagueAnalyserException {
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
        IplBatsmanDAO iplBatsmanDAO = this.bowlerList.get(iplBowlerData.playerName);
        if(iplBatsmanDAO == null) {
            this.bowlerList.put(iplBowlerData.playerName,new IplBatsmanDAO(iplBowlerData));
            return;
        }
        iplBatsmanDAO.bowlingAverage = iplBowlerData.averageRunsGiven;
    }
}
