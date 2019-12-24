package cricketleague.analyser;

import com.google.gson.Gson;
import cricketleague.analyser.fileloaders.CsvFileLoader;
import cricketleague.analyser.fileloaders.FileLoaderFactory;
import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;
import cricketleague.analyser.analyseressentials.MyComparators;
import cricketleague.analyser.analyseressentials.PlayerType;

import java.util.*;
import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {

    private PlayerType playerType;
    public Map<String, IplPlayerDAO> playersList;
    private CsvFileLoader mockedLoader;

    public CricketLeagueAnalyser() {
        this.playersList = new HashMap<>();
    }

    public int loadDataFromCsv(PlayerType playerType, String... csvFilePath) throws CricketLeagueAnalyserException {
        this.playerType = playerType;
//        CsvFileLoader csvFileLoader = FileLoaderFactory.getAdapter(playerType);
        this.mockedLoader = this.getFactoryObject(playerType);
        this.playersList = this.mockedLoader.loadCsv(csvFilePath);
        return playersList.size();
    }

    public String sortBasedOn(MyComparators.CompareBasedOn comparingField) {
        MyComparators comparator = new MyComparators();
        ArrayList<IplPlayerDAO> sortedList = this.playersList
                        .values().stream()
                        .sorted(comparator.getComparator(comparingField))
                        .collect(toCollection(ArrayList::new));
        ArrayList sortedDtoList = new ArrayList<>();
        for (IplPlayerDAO playerDAO : sortedList) {
            sortedDtoList.add(this.playerType.getDto(playerDAO));
        }
        String sortedString = new Gson().toJson(sortedDtoList);
        return sortedString;
    }

    public CsvFileLoader getFactoryObject(PlayerType playerType) {
        try {
            return FileLoaderFactory.getAdapter(playerType);
        } catch (CricketLeagueAnalyserException e) {
            return null;
        }
    }
}
