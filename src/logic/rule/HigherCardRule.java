package logic.rule;

import java.util.Map;

import engine.Card;
import engine.Player;
import engine.enums.Rank;
import logic.gameFlow.RoundRule;

public class HigherCardRule implements RoundRule{

    @Override
    public Player determineWinner(Map<Player, Card> playedCards) {
        Player winner = null;
        Rank highestRank = null;

        for(Map.Entry<Player, Card> entry: playedCards.entrySet()) {
            Rank currentRank = entry.getValue().getRank();
            if(highestRank == null || currentRank.ordinal() > highestRank.ordinal()) {
                highestRank = currentRank;
                winner = entry.getKey();
            }
        }

        return winner;
    }

    
}
