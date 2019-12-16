package cricketleague.analyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyComparators {

    public static Map<Enum, Comparator<IplBatsmanData>> comparators = new HashMap<Enum, Comparator<IplBatsmanData>>() {{
        put(CricketLeagueAnalyser.CompareBasedOn.STRIKE_RATE, Comparator.comparing(iplBatsmanData -> iplBatsmanData.strikeRate,Comparator.reverseOrder()));
        put(CricketLeagueAnalyser.CompareBasedOn.AVERAGE, Comparator.comparing(iplBatsmanData -> iplBatsmanData.averageScore,Comparator.reverseOrder()));
    }};

}
