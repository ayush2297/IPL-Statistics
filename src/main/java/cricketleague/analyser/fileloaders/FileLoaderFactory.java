package cricketleague.analyser.fileloaders;

import cricketleague.analyser.analyseressentials.CricketLeagueAnalyserException;
import cricketleague.analyser.analyseressentials.PlayerType;

public class FileLoaderFactory {
    public static CsvFileLoader getAdapter(PlayerType playerType) throws CricketLeagueAnalyserException {
        switch (playerType) {
            case BATSMAN:
                return new BatsmenFileLoader();
            case BOWLER:
                return new BowlerFileLoader();
            case BOTH:
                return new LoadBowlingAndBattingData();
            default:
                throw new CricketLeagueAnalyserException("Incorrect Country entered",
                        CricketLeagueAnalyserException.ExceptionType.INCORRECT_PLAYER_TYPE);
        }
    }
}
