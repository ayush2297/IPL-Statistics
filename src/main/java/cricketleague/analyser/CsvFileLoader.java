package cricketleague.analyser;

import com.myopencsv.CsvBuilderFactory;
import com.myopencsv.ICsvBuilder;
import com.myopencsv.OpenCsvBuilder;
import com.myopencsv.OpenCsvException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CsvFileLoader {

    public List loadCsvAsList (String csvFilePath) throws CricketLeagueAnalyserException {
        try {
            this.prepareFile(csvFilePath);
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get("./src/test/resources/readableCsv.csv"))) {
            List<IplBatsmanData> playersList = new ArrayList<>();
            ICsvBuilder csvBuilder = (OpenCsvBuilder) CsvBuilderFactory.createCsvBuilder();
            Iterator<IplBatsmanData> battingDataIterator = csvBuilder.getCsvFileIterator(reader,IplBatsmanData.class);
            Iterable<IplBatsmanData> censusDataIterable = () -> battingDataIterator;
            playersList = StreamSupport.stream(censusDataIterable.spliterator(), false)
                    .map(IplBatsmanData.class::cast)
                    .collect(Collectors.toList());
            return playersList;
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

    private void prepareFile(String filePath) throws IOException {
        String searchFor = "-";
        String replaceWith = "00";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> replaced = lines
                    .map(line-> line.replaceAll(searchFor, replaceWith))
                    .collect(Collectors.toList());
            Files.write(Paths.get("./src/test/resources/readableCsv.csv"), replaced);
        } catch (NoSuchFileException e) {
            throw e;
        }
    }


}
