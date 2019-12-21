package cricketleague.analyser;

import com.google.gson.Gson;
import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    public enum CompareBasedOn {
        AVERAGE, STRIKE_RATE, SIX_AND_FOURS_HIT, STRIKE_RATE_WITH_6sn4s,
        AVG_THEN_STRIKERATE, RUNS_THEN_AVG
    }
    public static Map<String, IplBatsmanDAO> playersList;

    public CricketLeagueAnalyser() {
        this.playersList = new HashMap<>();
    }

    public int loadDataFromCsv(PlayerType playerType,String csvFilePath, String replaceWrongValuesWith) throws CricketLeagueAnalyserException {
        CsvFileLoader csvFileLoader = FileLoaderFactory.getAdapter(playerType);
        this.playersList = csvFileLoader.loadCsv(csvFilePath, replaceWrongValuesWith);
        return playersList.size();
    }

    public String sortBasedOn(CompareBasedOn comparingField) {
        MyComparators compareWith = new MyComparators();
        ArrayList<IplBatsmanDAO> sortedList = this.playersList
                        .values().stream()
                        .sorted(compareWith.comparators.get(comparingField))
                        .collect(toCollection(ArrayList::new));
        ArrayList<IplBatsmanData> sortedDtoList = new ArrayList<>();
        for (IplBatsmanDAO batsmanDAO: sortedList) {
            sortedDtoList.add(batsmanDAO.getBatsmanData());
        }
        String sortedString = new Gson().toJson(sortedDtoList);
        return sortedString;
    }

}
