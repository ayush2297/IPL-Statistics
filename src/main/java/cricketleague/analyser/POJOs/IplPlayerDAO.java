package cricketleague.analyser.POJOs;

public class IplPlayerDAO {

    public String playerName;
    public int runsScored=0;
    public Double battingAverage =0.0;
    public Double battingStrikeRate=0.0;
    public int ballsFaced=0;
    public int fours=0;
    public int sixes=0;
    public double bowlingAverage=99;
    public double bowlingStrikeRate=99;
    public double bowlerEconomy=99;
    public double bowler5Wickets=0;
    public int wicketsTaken=0;
    public double bowler4Wickets=0;
    public int ballsBowled=999;
    public IplBatsmanData batsmanData;
    public IplBowlerData bowlerData;

    public IplPlayerDAO() {
    }

    public IplPlayerDAO(IplBatsmanData batsmanData) {
        this.playerName = batsmanData.playerName;
        this.runsScored = batsmanData.runsScored;
        this.battingAverage = batsmanData.battingAverage;
        this.battingStrikeRate = batsmanData.battingStrikeRate;
        this.ballsFaced = batsmanData.ballsFaced;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
        this.batsmanData = batsmanData;
    }

    public IplPlayerDAO(IplBowlerData iplBowlerData) {
        this.playerName = iplBowlerData.playerName;
        this.bowlingAverage = iplBowlerData.averageRunsGiven;
        this.bowlingStrikeRate = iplBowlerData.bowlingStrikeRate;
        this.bowlerEconomy = iplBowlerData.economyRate;
        this.bowler4Wickets = iplBowlerData.fourWickets;
        this.bowler5Wickets = iplBowlerData.fiveWickets;
        this.wicketsTaken = iplBowlerData.wicketsTaken;
        this.ballsBowled = (int) (Math.round(iplBowlerData.oversBowled)*6 + (iplBowlerData.oversBowled%6));
        this.bowlerData = iplBowlerData;
    }

    public IplBatsmanData getBatsmanData() {
        return batsmanData;
    }

    public IplBowlerData getBowlerData() {
         return bowlerData;
    }
}
