package logic.gameRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Player;
import engine.enums.Rank;
import logic.gameFlow.RoundRule;

public class LowerCardRule implements RoundRule {

    @Override
    public List<Player> determineWinner(Map<Player, Card> playedCards) {
        List<Player> listOfWinners = new ArrayList<>();
        Rank lowestRank = null;
        
        for(Map.Entry<Player, Card> entry: playedCards.entrySet()) {
            Rank currentRank = entry.getValue().getRank();
            if(lowestRank == null || currentRank.getValue() < lowestRank.getValue()) {
                lowestRank = currentRank;
                listOfWinners.clear();
                listOfWinners.add(entry.getKey());
            } else if(currentRank.getValue() == lowestRank.getValue()) {
                listOfWinners.add(entry.getKey());
            }
        }

        return listOfWinners;
    }
    
}
