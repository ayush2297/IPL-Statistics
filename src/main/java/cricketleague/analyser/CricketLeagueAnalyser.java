package cricketleague.analyser;

import com.google.gson.Gson;
import com.myopencsv.CsvBuilderFactory;
import com.myopencsv.ICsvBuilder;
import com.myopencsv.OpenCsvBuilder;
import com.myopencsv.OpenCsvException;
import org.omg.IOP.Encoding;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toCollection;

public class CricketLeagueAnalyser {
    List<IplBatsmanData> playersList = null;
    public int loadDataFromCsv(String csvFilePath) throws CricketLeagueAnalyserException {
        try {
            this.prepareFile(csvFilePath);
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get("./src/test/resources/readableCsv.csv"))) {
            OpenCsvBuilder csvBuilder = (OpenCsvBuilder) CsvBuilderFactory.createCsvBuilder();
            Iterator<IplBatsmanData> battingDataIterator = csvBuilder.getCsvFileIterator(reader,IplBatsmanData.class);
            Iterable<IplBatsmanData> censusDataIterable = () -> battingDataIterator;
            this.playersList = StreamSupport.stream(censusDataIterable.spliterator(), false)
                    .map(IplBatsmanData.class::cast)
                    .collect(Collectors.toList());
            return playersList.size();
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR);
        } catch (OpenCsvException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR);
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR);
        } finally {

        }
    }

    public void prepareFile(String filePath) throws IOException {
        String searchFor = "-";
        String replaceWith = "00";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> replaced = lines
                    .map(line-> line.replaceAll(searchFor, replaceWith))
                    .collect(Collectors.toList());
            Files.write(Paths.get("./src/test/resources/readableCsv.csv"), replaced);
        } catch (IOException e) {
            throw e;
        }
    }

    public String sortBasedOnAvg() {
        ArrayList<IplBatsmanData> sortedList = this.playersList
                        .stream()
                        .sorted(Comparator.comparing(iplBatsmanData -> iplBatsmanData.averageScore,Comparator.reverseOrder()))
                        .collect(toCollection(ArrayList::new));
        String sortedString = new Gson().toJson(sortedList);
        return sortedString;
    }

    public String sortBasedOnStrikeRate() {
        ArrayList<IplBatsmanData> sortedList = this.playersList
                .stream()
                .sorted(Comparator.comparing(iplBatsmanData -> iplBatsmanData.strikeRate,Comparator.reverseOrder()))
                .collect(toCollection(ArrayList::new));
        String sortedString = new Gson().toJson(sortedList);
        return sortedString;
    }
}
