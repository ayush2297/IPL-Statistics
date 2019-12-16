package cricketleague.analyser;

import com.google.gson.Gson;
import java.util.*;

import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    public enum CompareBasedOn {
        AVERAGE, STRIKE_RATE
    }
    CompareBasedOn compareType;

    Map<Enum,Comparator<IplBatsmanData>> comparators = new HashMap<Enum, Comparator<IplBatsmanData>>() {{
        put(CompareBasedOn.STRIKE_RATE, Comparator.comparing(iplBatsmanData -> iplBatsmanData.strikeRate,Comparator.reverseOrder()));
        put(CompareBasedOn.AVERAGE, Comparator.comparing(iplBatsmanData -> iplBatsmanData.averageScore,Comparator.reverseOrder()));
    }};

    List<IplBatsmanData> playersList = null;

    public int loadDataFromCsv(String csvFilePath) throws CricketLeagueAnalyserException {
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        this.playersList = csvFileLoader.loadCsvAsList(csvFilePath);
        return playersList.size();
    }

    public String sortBasedOn(CompareBasedOn comparingField) {
        ArrayList<IplBatsmanData> sortedList = this.playersList
                        .stream()
                        .sorted(comparators.get(comparingField))
                        .collect(toCollection(ArrayList::new));
        String sortedString = new Gson().toJson(sortedList);
        return sortedString;
    }

}
