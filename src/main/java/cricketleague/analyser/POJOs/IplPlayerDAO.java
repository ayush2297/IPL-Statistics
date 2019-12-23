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
    public double bowlingStrikeRate;
    public double bowlerEconomy;
    public IplBatsmanData batsmanData;
    public IplBowlerData bowlerDto;
    public double bowler5Wickets;
    public double bowler4Wickets;
    public int ballsBowled;

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
        this.bowlingStrikeRate = iplBowlerData.strikeRate;
        this.bowlerEconomy = iplBowlerData.economyRate;
        this.bowler4Wickets = iplBowlerData.fourWickets;
        this.bowler5Wickets = iplBowlerData.fiveWickets;
        this.ballsBowled = (int) (Math.round(iplBowlerData.oversBowled)*6 + (iplBowlerData.oversBowled%6));
        this.bowlerDto = iplBowlerData;
    }

    public IplBatsmanData getBatsmanData() {
        return batsmanData;
    }

    public IplBowlerData getBowlerDto() {
         return bowlerDto;
    }
}
