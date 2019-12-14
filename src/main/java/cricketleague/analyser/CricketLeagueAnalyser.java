package cricketleague.analyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CricketLeagueAnalyser {
    public int loadDataFromCsv(String csvFilePath) {
        try (Stream<String> stream = Files.lines(Paths.get(csvFilePath))) {
            return (int) stream.count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
