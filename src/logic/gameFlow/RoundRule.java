package logic.gameFlow;
import engine.Card;
import engine.Player;
import java.util.Map;

public interface RoundRule {
    Player determineWinner(Map<Player, Card> playedCards);
}
