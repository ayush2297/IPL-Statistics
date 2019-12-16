package cricketleague.analyser;

import com.google.gson.Gson;
import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    public enum CompareBasedOn {
        AVERAGE, STRIKE_RATE, SIX_AND_FOURS_HIT
    }
    List<IplBatsmanDAO> playersList;

    public CricketLeagueAnalyser() {
        this.playersList = new ArrayList<>();
    }

    public int loadDataFromCsv(String csvFilePath) throws CricketLeagueAnalyserException {
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        List<IplBatsmanData> tempList = csvFileLoader.loadCsvAsList(csvFilePath);
        for (IplBatsmanData batsmanData: tempList) {
            this.playersList.add(new IplBatsmanDAO(batsmanData));
        }
        return playersList.size();
    }

    public String sortBasedOn(CompareBasedOn comparingField) {
        ArrayList<IplBatsmanDAO> sortedList = this.playersList
                        .stream()
                        .sorted(MyComparators.comparators.get(comparingField))
                        .collect(toCollection(ArrayList::new));
        ArrayList<IplBatsmanData> sortedDtoList = new ArrayList<>();
        for (IplBatsmanDAO batsmanDAO: sortedList) {
            sortedDtoList.add(batsmanDAO.getBatsmanData());
        }
        String sortedString = new Gson().toJson(sortedDtoList);
        return sortedString;
    }

}
