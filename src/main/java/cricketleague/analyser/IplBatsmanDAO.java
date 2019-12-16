package cricketleague.analyser;

public class IplBatsmanDAO {

    public String playerName;
    public Double averageScore;
    public Double strikeRate;
    public int ballsFaced;
    public int fours;
    public int sixes;
    public IplBatsmanData batsmanData;

    public IplBatsmanDAO() {
    }

    public IplBatsmanDAO(IplBatsmanData batsmanData) {
        this.playerName = batsmanData.playerName;
        this.averageScore = batsmanData.averageScore;
        this.strikeRate = batsmanData.strikeRate;
        this.ballsFaced = batsmanData.ballsFaced;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
        this.batsmanData = batsmanData;
    }

    public IplBatsmanData getBatsmanData() {
        return batsmanData;
    }

}
