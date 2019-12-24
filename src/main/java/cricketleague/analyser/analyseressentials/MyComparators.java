package cricketleague.analyser.analyseressentials;

import cricketleague.analyser.POJOs.IplPlayerDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparators {

    public enum CompareBasedOn {
        AVERAGE, STRIKE_RATE, SIX_AND_FOURS_HIT, STRIKE_RATE_WITH_6sn4s,
        AVG_THEN_STRIKERATE, RUNS_THEN_AVG, BOWLING_AVG, BOWLING_SR,
        BOWLING_ECONOMY, BOWLING_SR_WITH4WAND5W, BOWLING_SR_WITH_AVG,
        BOWLING_MOSTWKTS_WITH_AVG, BEST_BATTING_AND_BOWLING_AVG, BEST_ALL_ROUNDER
    }

    Comparator<IplPlayerDAO> strikeRateComparator =
            Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.battingStrikeRate, Comparator.reverseOrder());

    Comparator<IplPlayerDAO> runsAverageComparator =
            Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.battingAverage, Comparator.reverseOrder());

    Comparator<IplPlayerDAO> sixesAndFoursComparator =
            Comparator.comparing(iplBatsmanDAO -> (iplBatsmanDAO.sixes * 6) + (iplBatsmanDAO.fours * 4)
                    , Comparator.reverseOrder());

    Comparator<IplPlayerDAO> strikeRateWith6n4sComparator =
            Comparator.comparing(iplBatsmanDAO ->
                    ((((iplBatsmanDAO.batsmanData.fours * 4) + (iplBatsmanDAO.batsmanData.sixes * 6)) * 100)
                            / iplBatsmanDAO.batsmanData.ballsFaced), Comparator.reverseOrder());

    Comparator<IplPlayerDAO> avg = Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.battingAverage, Comparator.reverseOrder());
    Comparator<IplPlayerDAO> avgWithStrikeRateComparator = avg.thenComparing(iplBatsmanDAO -> iplBatsmanDAO.battingStrikeRate, Comparator.reverseOrder());

    Comparator<IplPlayerDAO> runs = Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.runsScored, Comparator.reverseOrder());
    Comparator<IplPlayerDAO> runsThenAverageComparator = runs.thenComparing(iplBatsmanDAO -> iplBatsmanDAO.battingAverage, Comparator.reverseOrder());

    Comparator<IplPlayerDAO> bowlerAvg = Comparator.comparing(iplPlayerDAO -> iplPlayerDAO.bowlingAverage);

    Comparator<IplPlayerDAO> bowlingSR = Comparator.comparing(iplPlayerDAO -> iplPlayerDAO.bowlingStrikeRate);

    Comparator<IplPlayerDAO> bowlingEconomy = Comparator.comparing(iplPlayerDAO -> iplPlayerDAO.bowlerEconomy);

    Comparator<IplPlayerDAO> bowlingSRWith4n5W = Comparator.comparing(iplPlayerDAO -> ((
            (iplPlayerDAO.bowler4Wickets * 4) + (iplPlayerDAO.bowler5Wickets * 5)) / iplPlayerDAO.ballsBowled), Comparator.reverseOrder());

    Comparator<IplPlayerDAO> bowlingSRWithAvg = Comparator.comparing(iplPlayerDAO ->
            (iplPlayerDAO.bowlingAverage + iplPlayerDAO.bowlingStrikeRate));

    Comparator<IplPlayerDAO> bowlingWktsWithAvg = Comparator.comparing(iplPlayerDAO ->
            (iplPlayerDAO.wicketsTaken / iplPlayerDAO.bowlingAverage), Comparator.reverseOrder());

    Comparator<IplPlayerDAO> bestAllRounderAvg = Comparator.comparing(iplPlayerDAO ->
            (iplPlayerDAO.battingAverage / iplPlayerDAO.bowlingAverage), Comparator.reverseOrder());

    Comparator<IplPlayerDAO> bestAllRounder = Comparator.comparing(iplPlayerDAO ->
            (iplPlayerDAO.runsScored * iplPlayerDAO.wicketsTaken), Comparator.reverseOrder());

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
        this.comparators.put(CompareBasedOn.BOWLING_SR,
                this.bowlingSR);
        this.comparators.put(CompareBasedOn.BOWLING_ECONOMY,
                this.bowlingEconomy);
        this.comparators.put(CompareBasedOn.BOWLING_SR_WITH4WAND5W,
                this.bowlingSRWith4n5W);
        this.comparators.put(CompareBasedOn.BOWLING_SR_WITH_AVG,
                this.bowlingSRWithAvg);
        this.comparators.put(CompareBasedOn.BOWLING_MOSTWKTS_WITH_AVG,
                this.bowlingWktsWithAvg);
        this.comparators.put(CompareBasedOn.BEST_BATTING_AND_BOWLING_AVG,
                this.bestAllRounderAvg);
        this.comparators.put(CompareBasedOn.BEST_ALL_ROUNDER,
                this.bestAllRounder);
    }

    public Comparator<IplPlayerDAO> getComparator(CompareBasedOn type){
        return this.comparators.get(type);
    }
}
