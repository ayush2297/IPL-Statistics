package cricketleague.analyser;

import com.opencsv.bean.CsvBindByName;

public class IplBatsmanData {

    @CsvBindByName (column = "PLAYER", required = true)
    public String playerName;

    @CsvBindByName (column = "Runs", required = true)
    public String runsScored;

    @CsvBindByName (column = "HS", required = true)
    public String highScore;

    @CsvBindByName (column = "Avg", required = true)
    public String averageScore;

    @CsvBindByName (column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName (column = "100", required = true)
    public String hundreds;

    @CsvBindByName (column = "6s", required = true)
    public String sixes;

}
