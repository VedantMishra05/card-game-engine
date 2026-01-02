package logic.gameRules.War;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Player;
import engine.enums.Rank;
import logic.gameFlow.RoundRule;

public class WarRule implements RoundRule {

    private static final int WAR_FACE_DOWN = 3;

    @Override
    public List<Player> determineWinner(Map<Player, Card> playedCards) {
        Rank highest = null;
        List<Player> tied = new ArrayList<>();

        for(var entry: playedCards.entrySet()) {
            Rank rank = entry.getValue().getRank();
            if(highest == null || rank.getValue() > highest.getValue()) {
                highest = rank;
                tied.clear();
                tied.add(entry.getKey());
            } else if(rank.getValue() == highest.getValue()) {
                tied.add(entry.getKey());
            }
        }
        
        if(tied.size() == 1) return tied;

        return resolveWar(tied);
    }

    private List<Player> resolveWar(List<Player> tiedPlayers) {
        WarContext context = new WarContext();
        Map<Player, Card> faceUp = new HashMap<>();

        for (Player player : tiedPlayers) {

            // Not enough cards → player loses war
            if (player.getHand().size() < WAR_FACE_DOWN + 1) {
                continue;
            }

            // Face-down cards
            for (int i = 0; i < WAR_FACE_DOWN; i++) {
                context.addCard(player, player.playTopCard());
            }

            // Face-up card
            Card faceUpCard = player.playTopCard();
            context.addCard(player, faceUpCard);
            faceUp.put(player, faceUpCard);
        }

        // Recursive resolution
        return determineWinner(faceUp);
    }
    
}
