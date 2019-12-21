package cricketleague.analyser;

public class FileLoaderFactory {
    public static CsvFileLoader getAdapter(PlayerType playerType) throws CricketLeagueAnalyserException {
        switch (playerType) {
            case BATSMAN:
                return new BatsmenFileLoader();
            case BOWLER:
                return new BowlerFileLoader();
            default:
                throw new CricketLeagueAnalyserException("Incorrect Country entered",
                        CricketLeagueAnalyserException.ExceptionType.INCORRECT_PLAYER_TYPE);
        }
    }
}
