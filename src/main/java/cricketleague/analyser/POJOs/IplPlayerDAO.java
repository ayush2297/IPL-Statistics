package cricketleague.analyser.POJOs;

public class IplPlayerDAO {

    public String playerName;
    public int runsScored=0;
    public Double averageScore=0.0;
    public Double battingStrikeRate=0.0;
    public int ballsFaced=0;
    public int fours=0;
    public int sixes=0;
    public double bowlingAverage=99;
    public IplBatsmanData batsmanData;
    public IplBowlerData bowlerDto;

    public IplPlayerDAO() {
    }

    public IplPlayerDAO(IplBatsmanData batsmanData) {
        this.playerName = batsmanData.playerName;
        this.runsScored = batsmanData.runsScored;
        this.averageScore = batsmanData.averageScore;
        this.battingStrikeRate = batsmanData.strikeRate;
        this.ballsFaced = batsmanData.ballsFaced;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
        this.batsmanData = batsmanData;
    }

    public IplPlayerDAO(IplBowlerData iplBowlerData) {
        this.playerName = iplBowlerData.playerName;
        this.bowlingAverage = iplBowlerData.averageRunsGiven;
        this.bowlerDto = iplBowlerData;
    }

    public IplBatsmanData getBatsmanData() {
        return batsmanData;
    }

    public IplBowlerData getBowlerDto() {
         return bowlerDto;
    }
}
