package cricketleague.analyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparators {

    public static Map<Enum, Comparator<IplBatsmanDAO>> comparators = new HashMap<Enum, Comparator<IplBatsmanDAO>>() {
        {
            put(CricketLeagueAnalyser.CompareBasedOn.STRIKE_RATE,
                    Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.strikeRate,Comparator.reverseOrder()));
            put(CricketLeagueAnalyser.CompareBasedOn.AVERAGE,
                    Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.averageScore,Comparator.reverseOrder()));
            put(CricketLeagueAnalyser.CompareBasedOn.SIX_AND_FOURS_HIT,
                    this.getSixAndFourComparator());
        }

        private Comparator<IplBatsmanDAO> getSixAndFourComparator() {
            return Comparator.comparing(iplBatsmanDAO -> (iplBatsmanDAO.fours*4)+(iplBatsmanDAO.sixes*6),
                    Comparator.reverseOrder());
        }
    };

}
