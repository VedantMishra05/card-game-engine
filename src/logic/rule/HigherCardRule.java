package logic.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Player;
import engine.enums.Rank;
import logic.gameFlow.RoundRule;

public class HigherCardRule implements RoundRule{

    @Override
    public List<Player> determineWinner(Map<Player, Card> playedCards) {
        List<Player> listOfWinners = new ArrayList<>();
        Rank highestRank = null;

        for(Map.Entry<Player, Card> entry: playedCards.entrySet()) {
            Rank currentRank = entry.getValue().getRank();
            if(highestRank == null || currentRank.ordinal() > highestRank.ordinal()) {
                highestRank = currentRank;
                listOfWinners.clear();
                listOfWinners.add(entry.getKey());
            } else if(currentRank.ordinal() == highestRank.ordinal()) {
                listOfWinners.add(entry.getKey());
            }
        }

        return listOfWinners;
    }

    
}
