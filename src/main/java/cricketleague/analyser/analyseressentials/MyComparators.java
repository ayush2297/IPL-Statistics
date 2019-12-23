package cricketleague.analyser.analyseressentials;

import cricketleague.analyser.CricketLeagueAnalyser;
import cricketleague.analyser.POJOs.IplPlayerDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparators {

    public enum CompareBasedOn {
        AVERAGE, STRIKE_RATE, SIX_AND_FOURS_HIT, STRIKE_RATE_WITH_6sn4s,
        AVG_THEN_STRIKERATE, RUNS_THEN_AVG, BOWLING_AVG
    }
    
    Comparator<IplPlayerDAO> strikeRateComparator =
            Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.battingStrikeRate, Comparator.reverseOrder());

    Comparator<IplPlayerDAO> runsAverageComparator =
            Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.averageScore, Comparator.reverseOrder());

    Comparator<IplPlayerDAO> sixesAndFoursComparator =
            Comparator.comparing(iplBatsmanDAO -> (iplBatsmanDAO.sixes*6)+(iplBatsmanDAO.fours*4)
                    ,Comparator.reverseOrder());

    Comparator<IplPlayerDAO> strikeRateWith6n4sComparator =
            Comparator.comparing(iplBatsmanDAO ->
                    ((((iplBatsmanDAO.batsmanData.fours*4)+(iplBatsmanDAO.batsmanData.sixes*6))*100)
                            /iplBatsmanDAO.batsmanData.ballsFaced), Comparator.reverseOrder());

    Comparator<IplPlayerDAO> avg =Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.averageScore,Comparator.reverseOrder());
    Comparator<IplPlayerDAO> avgWithStrikeRateComparator = avg.thenComparing(iplBatsmanDAO -> iplBatsmanDAO.battingStrikeRate,Comparator.reverseOrder());

    Comparator<IplPlayerDAO> runs =Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.runsScored,Comparator.reverseOrder());
    Comparator<IplPlayerDAO> runsThenAverageComparator = runs.thenComparing(iplBatsmanDAO -> iplBatsmanDAO.averageScore,Comparator.reverseOrder());

    Comparator<IplPlayerDAO> bowlerAvg = Comparator.comparing(iplPlayerDAO -> iplPlayerDAO.bowlingAverage);

    public Map<Enum, Comparator<IplPlayerDAO>> comparators = new HashMap<>();

    public MyComparators() {
        this.comparators.put(CompareBasedOn.STRIKE_RATE,
                this.strikeRateComparator);
        this.comparators.put(CompareBasedOn.AVERAGE,
                this.runsAverageComparator);
        this.comparators.put(CompareBasedOn.SIX_AND_FOURS_HIT,
                this.sixesAndFoursComparator);
        this.comparators.put(CompareBasedOn.STRIKE_RATE_WITH_6sn4s,
                this.strikeRateWith6n4sComparator);
        this.comparators.put(CompareBasedOn.AVG_THEN_STRIKERATE,
                this.avgWithStrikeRateComparator);
        this.comparators.put(CompareBasedOn.RUNS_THEN_AVG,
                this.runsThenAverageComparator);
        this.comparators.put(CompareBasedOn.BOWLING_AVG,
                this.bowlerAvg);
    }
}
