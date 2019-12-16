package cricketleague.analyser;

public class IplBatsmanDAO {

    protected String playerName;
    protected Double averageScore;
    protected Double strikeRate;
    protected int fours;
    protected int sixes;
    protected IplBatsmanData batsmanData;

    public IplBatsmanDAO() {
    }

    public IplBatsmanDAO(IplBatsmanData batsmanData) {
        this.playerName = batsmanData.playerName;
        this.averageScore = batsmanData.averageScore;
        this.strikeRate = batsmanData.strikeRate;
        this.fours = batsmanData.fours;
        this.sixes = batsmanData.sixes;
        this.batsmanData = batsmanData;
    }

    public IplBatsmanData getBatsmanData() {
        return batsmanData;
    }
}
