package logic.actions.actionTypes;

import engine.Card;
import engine.Player;
import logic.actions.Action;

public class PlayCardAction implements Action {

    public final Player player;
    private final Card card;

    public PlayCardAction(Player player, Card card) {
        this.player = player;
        this.card = card;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Card getCard() {
        return card;
    }
}
