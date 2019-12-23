package cricketleague.analyser.fileloaders;

import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.POJOs.IplBowlerData;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BowlerFileLoader extends CsvFileLoader{

    Map<String, IplPlayerDAO> bowlerList = null;

    public BowlerFileLoader() {
        this.bowlerList = new HashMap<>();
    }

    public BowlerFileLoader(Map<String, IplPlayerDAO> allRounderMap) {
        this.bowlerList = allRounderMap;
    }

    @Override
    public Map<String, IplPlayerDAO> loadCsv(String... csvFilePath) throws CricketLeagueAnalyserException {
        try {
            Iterable<IplBowlerData> csvIterable = super.getCsvIterable(IplBowlerData.class, csvFilePath[0], "99");
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplBowlerData -> mergeData(iplBowlerData));
            return this.bowlerList;
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
        iplPlayerDAO.bowlingStrikeRate = iplBowlerData.bowlingStrikeRate;
        iplPlayerDAO.bowlerEconomy = iplBowlerData.economyRate;
        iplPlayerDAO.bowler4Wickets = iplBowlerData.fourWickets;
        iplPlayerDAO.bowler5Wickets = iplBowlerData.fiveWickets;
        iplPlayerDAO.wicketsTaken = iplBowlerData.wicketsTaken;
        iplPlayerDAO.ballsBowled = (int) (Math.round(iplBowlerData.oversBowled)*6 + (iplBowlerData.oversBowled%6));
        iplPlayerDAO.bowlerData = iplBowlerData;
    }
}
