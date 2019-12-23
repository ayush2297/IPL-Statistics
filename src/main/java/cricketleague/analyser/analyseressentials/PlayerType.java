package cricketleague.analyser.analyseressentials;

import cricketleague.analyser.POJOs.AllRounderDTO;
import cricketleague.analyser.POJOs.IplBatsmanData;
import cricketleague.analyser.POJOs.IplBowlerData;
import cricketleague.analyser.POJOs.IplPlayerDAO;

public enum PlayerType {
    BATSMAN {
        @Override
        public IplBatsmanData getDto(IplPlayerDAO iplPlayerDAO) {
            return iplPlayerDAO.batsmanData;
        }
    }, BOWLER {
        @Override
        public IplBowlerData getDto(IplPlayerDAO iplPlayerDAO) {
            return iplPlayerDAO.bowlerData;
        }
    }, BOTH {
        @Override
        public AllRounderDTO getDto(IplPlayerDAO iplPlayerDAO) {
            return new AllRounderDTO(iplPlayerDAO);
        }
    }, OTHER {
        @Override
        public Object getDto(IplPlayerDAO iplPlayerDAO) {
            return null;
        }
    };

    public abstract Object getDto(IplPlayerDAO iplPlayerDAO);

}
