package logic.gameFlow;
import engine.Card;
import engine.Player;

import java.util.List;
import java.util.Map;

public interface RoundRule {
    List<Player> determineWinner(Map<Player, Card> playedCards);
}
