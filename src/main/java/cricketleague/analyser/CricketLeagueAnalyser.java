package cricketleague.analyser;

import com.google.gson.Gson;
import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    public enum CompareBasedOn {
        AVERAGE, STRIKE_RATE, SIX_AND_FOURS_HIT, STRIKE_RATE_WITH_6sn4s,
        AVG_THEN_STRIKERATE, RUNS_THEN_AVG
    }
    List<IplBatsmanDAO> playersList;

    public CricketLeagueAnalyser() {
        this.playersList = new ArrayList<>();
    }

    public int loadDataFromCsv(String csvFilePath) throws CricketLeagueAnalyserException {
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        playersList = csvFileLoader.loadCsvAsList(csvFilePath);
        return playersList.size();
    }

    public String sortBasedOn(CompareBasedOn comparingField) {
        MyComparators compareWith = new MyComparators();
        ArrayList<IplBatsmanDAO> sortedList = this.playersList
                        .stream()
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
