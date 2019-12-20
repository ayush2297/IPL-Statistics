package cricketleague.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplBowlerData {
    @CsvBindByName (column = "\uFEFFPOS", required = true)
    public int position;

    @CsvBindByName (column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName (column = "Mat", required = true)
    public int matchesPlayed;

    @CsvBindByName (column = "Inns", required = true)
    public int inningsPlayed;

    @CsvBindByName (column = "Ov", required = true)
    public int oversBowled;

    @CsvBindByName (column = "Runs", required = true)
    public int runsScored;

    @CsvBindByName (column = "Wkts", required = true)
    public int wicketsTaken;

    @CsvBindByName (column = "BBI", required = true)
    public int bestBowlingInnings;

    @CsvBindByName (column = "Avg", required = true)
    public int averageRunsGiven;

    @CsvBindByName (column = "Econ", required = true)
    public int economyRate;

    @CsvBindByName (column = "SR", required = true)
    public int strikeRate;

    @CsvBindByName (column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName (column = "5w", required = true)
    public int fiveWickets;

}
