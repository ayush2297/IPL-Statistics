package cricketleague.analyser.POJOs;

public class AllRounderDTO {

    public String playerName;
    public int runsScored;
    public Double battingAverage;
    public Double battingStrikeRate;
    public int ballsFaced;
    public int fours;
    public int sixes;
    public double bowlingAverage;
    public double bowlingStrikeRate;
    public double bowlerEconomy;
    public double bowler5Wickets;
    public int wicketsTaken;
    public double bowler4Wickets;
    public int ballsBowled;

    public AllRounderDTO(IplPlayerDAO iplPlayerDAO) {
        this.playerName = iplPlayerDAO.playerName;
        this.runsScored = iplPlayerDAO.runsScored;
        this.battingAverage = iplPlayerDAO.battingAverage;
        this.ballsFaced = iplPlayerDAO.ballsFaced;
        this.battingStrikeRate = iplPlayerDAO.battingStrikeRate;
        this.fours = iplPlayerDAO.fours;
        this.sixes = iplPlayerDAO.sixes;
        this.wicketsTaken = iplPlayerDAO.wicketsTaken;
        this.bowlingStrikeRate = iplPlayerDAO.bowlingStrikeRate;
        this.bowlingAverage = iplPlayerDAO.bowlingAverage;
        this.bowlerEconomy = iplPlayerDAO.bowlerEconomy;
        this.bowler5Wickets = iplPlayerDAO.bowler5Wickets;
        this.bowler4Wickets = iplPlayerDAO.bowler4Wickets;
        this.ballsBowled = iplPlayerDAO.ballsBowled;
    }
}
