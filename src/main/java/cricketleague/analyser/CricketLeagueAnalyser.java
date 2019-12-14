package cricketleague.analyser;

import com.myopencsv.CsvBuilderFactory;
import com.myopencsv.ICsvBuilder;
import com.myopencsv.OpenCsvException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyser {
    public int loadDataFromCsv(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
            Iterator<IplBatsmanData> battingDataIterator = csvBuilder.getCsvFileIterator(reader,IplBatsmanData.class);
            Iterable<IplBatsmanData> censusDataIterable = () -> battingDataIterator;
            return (int) StreamSupport.stream(censusDataIterable.spliterator(), false).count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } catch (OpenCsvException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
