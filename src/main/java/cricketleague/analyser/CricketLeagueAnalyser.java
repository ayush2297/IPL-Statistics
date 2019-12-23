package cricketleague.analyser;

import com.google.gson.Gson;
import cricketleague.analyser.fileloaders.CsvFileLoader;
import cricketleague.analyser.fileloaders.FileLoaderFactory;
import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.POJOs.IplBatsmanData;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;
import cricketleague.analyser.analyseressentials.MyComparators;
import cricketleague.analyser.analyseressentials.PlayerType;

import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    private PlayerType playerType;
    public Map<String, IplPlayerDAO> playersList;


    public CricketLeagueAnalyser() {
        this.playersList = new HashMap<>();
    }

    public int loadDataFromCsv(PlayerType playerType, String csvFilePath, String replaceWrongValuesWith) throws CricketLeagueAnalyserException {
        this.playerType = playerType;
        CsvFileLoader csvFileLoader = FileLoaderFactory.getAdapter(playerType);
        this.playersList = csvFileLoader.loadCsv(csvFilePath, replaceWrongValuesWith);
        return playersList.size();
    }

    public String sortBasedOn(MyComparators.CompareBasedOn comparingField) {
        MyComparators compareWith = new MyComparators();
        ArrayList<IplPlayerDAO> sortedList = this.playersList
                        .values().stream()
                        .sorted(compareWith.comparators.get(comparingField))
                        .collect(toCollection(ArrayList::new));
        ArrayList sortedDtoList = new ArrayList<>();
        if (this.playerType.equals(PlayerType.BATSMAN)) {
            for (IplPlayerDAO batsmanDAO : sortedList) {
                sortedDtoList.add(batsmanDAO.getBatsmanData());
            }
        }
        if (this.playerType.equals(PlayerType.BOWLER)) {
            for (IplPlayerDAO bowlerDao : sortedList) {
                  sortedDtoList.add(bowlerDao.getBowlerDto());
            }
        }
        String sortedString = new Gson().toJson(sortedDtoList);
        return sortedString;
    }

}
