package cricketleague.analyser;

public class IplBatsmanDAO {

    public String playerName;
    public int runsScored=0;
    public Double averageScore=0.0;
    public Double battingStrikeRate=0.0;
    public int ballsFaced=0;
    public int fours=0;
    public int sixes=0;
    public double bowlingAverage=99;
    public IplBatsmanData batsmanData;

    public IplBatsmanDAO() {
    }

    public IplBatsmanDAO(IplBatsmanData batsmanData) {
        this.playerName = batsmanData.playerName;
        this.runsScored = batsmanData.runsScored;
        this.averageScore = batsmanData.averageScore;
        this.battingStrikeRate = batsmanData.strikeRate;
        this.ballsFaced = batsmanData.ballsFaced;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
        this.batsmanData = batsmanData;
    }

    public IplBatsmanDAO(IplBowlerData iplBowlerData) {

    }

    public IplBatsmanData getBatsmanData() {
        return batsmanData;
    }

}
