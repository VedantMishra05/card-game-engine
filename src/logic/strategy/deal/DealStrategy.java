package logic.strategy.deal;

import java.util.List;

import engine.Deck;
import engine.Player;

public interface DealStrategy {
    void deal(Deck deck, List<Player> player);
}
