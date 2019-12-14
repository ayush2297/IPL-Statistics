package cricketleague.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplBatsmanData {

    @CsvBindByName (column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName (column = "Runs", required = true)
    public int runsScored;

    @CsvBindByName (column = "HS", required = true)
    public String highScore;

    @CsvBindByName (column = "Avg", required = true)
    public double averageScore;

    @CsvBindByName (column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName (column = "100", required = true)
    public int hundreds;

    @CsvBindByName (column = "6s", required = true)
    public int sixes;

}
