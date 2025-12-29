package logic.gameRules.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Player;
import logic.gameFlow.RoundRule;

public class LowerCardRule implements RoundRule {

    @Override
    public List<Player> determineWinner(Map<Player, Card> playedCards) {
        List<Player> listOfWinners = new ArrayList<>();
        System.out.println("Lower Card rule");

        return listOfWinners;
    }
    
}
