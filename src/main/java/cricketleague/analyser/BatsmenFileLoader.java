package cricketleague.analyser;

import java.io.IOException;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BatsmenFileLoader extends CsvFileLoader {

    Map<String,IplBatsmanDAO> batsmenList = null;

    public BatsmenFileLoader() {
        this.batsmenList = CricketLeagueAnalyser.playersList;
    }

    @Override
    public Map<String, IplBatsmanDAO> loadCsv(String csvFilePath, String replaceMissingValuesWith) throws CricketLeagueAnalyserException {
        try {
            Iterable<IplBatsmanData> csvIterable = super.getCsvIterable(IplBatsmanData.class, csvFilePath, replaceMissingValuesWith);
            StreamSupport.stream(csvIterable.spliterator(),false)
                        .forEach(batsmanData -> batsmenList.put(batsmanData.playerName,new IplBatsmanDAO(batsmanData)));
            super.closeReader();
            return batsmenList;
        } catch (CricketLeagueAnalyserException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new CricketLeagueAnalyserException(e.getMessage(),
                    CricketLeagueAnalyserException.ExceptionType.CSV_TO_OBJECT_ERROR);
        } catch (IOException e) {
            throw new CricketLeagueAnalyserException("unable to close the reader",CricketLeagueAnalyserException.ExceptionType.READER_PROBLEM);
        }
    }
}
