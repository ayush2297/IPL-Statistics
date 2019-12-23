package cricketleague.analyser.fileloaders;

import com.myopencsv.CsvBuilderFactory;
import com.myopencsv.ICsvBuilder;
import com.myopencsv.OpenCsvException;
import cricketleague.analyser.POJOs.IplPlayerDAO;
import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CsvFileLoader {

    public abstract Map<String, IplPlayerDAO> loadCsv(String... csvFilePath) throws CricketLeagueAnalyserException;
    Reader reader = null;
    public <E> Iterable<E> getCsvIterable(Class<E> clazz, String csvFilePath, String replaceWrongValuesWith) throws CricketLeagueAnalyserException {
        try {
            this.prepareFile(csvFilePath,replaceWrongValuesWith);
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR);
        }

        try {
            this.reader = Files.newBufferedReader(Paths.get("./src/test/resources/readableCsv.csv"));
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<E> iplIterator = csvBuilder.getCsvFileIterator(reader,clazz);
            Iterable<E> iplIterable = () -> iplIterator;
            return iplIterable;
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.FILE_INPUT_ERROR);
        } catch (OpenCsvException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR);
        }
    }

    public void closeReader() throws IOException {
        this.reader.close();
    }

    private void prepareFile(String filePath, String replaceWrongValuesWith) throws IOException {
        String searchFor = "-";
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            List<String> replaced = lines
                    .map(line-> line.replaceAll(searchFor, replaceWrongValuesWith))
                    .collect(Collectors.toList());
            Files.write(Paths.get("./src/test/resources/readableCsv.csv"), replaced);
        } catch (NoSuchFileException e) {
            throw e;
        }
    }


}
