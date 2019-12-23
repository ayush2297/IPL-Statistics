package cricketleague.analyser.POJOs;

import com.opencsv.bean.CsvBindByName;

public class IplBowlerData {
    @CsvBindByName (column = "POS", required = true)
    public int position;

    @CsvBindByName (column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName (column = "Mat", required = true)
    public int matchesPlayed;

    @CsvBindByName (column = "Inns", required = true)
    public int inningsPlayed;

    @CsvBindByName (column = "Ov", required = true)
    public double oversBowled;

    @CsvBindByName (column = "Runs", required = true)
    public int runsGiven;

    @CsvBindByName (column = "Wkts", required = true)
    public int wicketsTaken;

    @CsvBindByName (column = "BBI", required = true)
    public int bestBowlingInnings;

    @CsvBindByName (column = "Avg", required = true)
    public double averageRunsGiven;

    @CsvBindByName (column = "Econ", required = true)
    public double economyRate;

    @CsvBindByName (column = "SR", required = true)
    public double bowlingStrikeRate;

    @CsvBindByName (column = "4w", required = true)
    public double fourWickets;

    @CsvBindByName (column = "5w", required = true)
    public double fiveWickets;

}
