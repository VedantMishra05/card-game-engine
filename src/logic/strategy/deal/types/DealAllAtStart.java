package logic.strategy.deal.types;

import java.util.List;

import engine.Deck;
import engine.Player;
import logic.strategy.deal.DealStrategy;

public class DealAllAtStart implements DealStrategy {

    @Override
    public void deal(Deck deck, List<Player> players) {
        int cardsPerPlayer = deck.size() / players.size();
        int cardsToDeal = cardsPerPlayer * players.size();
        
        int index = 0;
        for(int i = 0; i < cardsToDeal; i++) {
            players.get(index % players.size()).receiveCard(deck.draw());
            index++;
        }
    }
}