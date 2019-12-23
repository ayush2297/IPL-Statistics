package cricketleague.analyser.POJOs;

import com.opencsv.bean.CsvBindByName;

public class IplBatsmanData {

    @CsvBindByName (column = "\uFEFFPOS", required = true)
    public int position;

    @CsvBindByName (column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName (column = "Mat", required = true)
    public int matchesPlayed;

    @CsvBindByName (column = "Inns", required = true)
    public int inningsPlayed;

    @CsvBindByName (column = "NO", required = true)
    public int notOuts;

    @CsvBindByName (column = "Runs", required = true)
    public int runsScored;

    @CsvBindByName (column = "HS", required = true)
    public String highScore;

    @CsvBindByName (column = "Avg", required = true)
    public Double averageScore;

    @CsvBindByName (column = "BF", required = true)
    public int ballsFaced;

    @CsvBindByName (column = "SR", required = true)
    public Double strikeRate;

    @CsvBindByName (column = "100", required = true)
    public int hundreds;

    @CsvBindByName (column = "50", required = true)
    public int fifties;

    @CsvBindByName (column = "4s", required = true)
    public int fours;

    @CsvBindByName (column = "6s", required = true)
    public int sixes;


}
