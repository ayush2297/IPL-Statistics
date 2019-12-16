package cricketleague.analyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparators {

    Comparator<IplBatsmanDAO> strikeRateComparator =
            Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.strikeRate, Comparator.reverseOrder());

    Comparator<IplBatsmanDAO> runsAverageComparator =
            Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.averageScore, Comparator.reverseOrder());

    Comparator<IplBatsmanDAO> sixesAndFoursComparator =
            Comparator.comparing(iplBatsmanDAO -> (iplBatsmanDAO.sixes*6)+(iplBatsmanDAO.fours*4)
                    ,Comparator.reverseOrder());

    Comparator<IplBatsmanDAO> strikeRateWith6n4sComparator =
            Comparator.comparing(iplBatsmanDAO ->
                    ((((iplBatsmanDAO.batsmanData.fours*4)+(iplBatsmanDAO.batsmanData.sixes*6))*100)
                            /iplBatsmanDAO.batsmanData.ballsFaced), Comparator.reverseOrder());

    public Map<Enum, Comparator<IplBatsmanDAO>> comparators = new HashMap<>();

    public MyComparators() {
        this.comparators.put(CricketLeagueAnalyser.CompareBasedOn.STRIKE_RATE,
                this.strikeRateComparator);
        this.comparators.put(CricketLeagueAnalyser.CompareBasedOn.AVERAGE,
                this.runsAverageComparator);
        this.comparators.put(CricketLeagueAnalyser.CompareBasedOn.SIX_AND_FOURS_HIT,
                this.sixesAndFoursComparator);
        this.comparators.put(CricketLeagueAnalyser.CompareBasedOn.STRIKE_RATE_WITH_6sn4s,
                this.strikeRateWith6n4sComparator);
    }
}
