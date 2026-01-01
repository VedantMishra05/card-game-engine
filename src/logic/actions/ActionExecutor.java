package logic.actions;

import engine.Player;
import logic.actions.actionTypes.PlayCardAction;
import logic.gameFlow.Game;
import logic.gameFlow.Round;

public class ActionExecutor {
    private final Game game;

    public ActionExecutor(Game game) {
        this.game = game;
    }

    public void execute(Action action) {
        if(action instanceof PlayCardAction playCardAction) {
            handlePlayCard(playCardAction);
        }
        else throw new IllegalArgumentException("Illegal action type");
    }

    private void handlePlayCard(PlayCardAction action) {
        Round round = game.getRound();

        if(round == null || round.isFinished()) throw new IllegalStateException("No active round");
        
        Player expectedPlayer = game.getCurrentTurnPlayer();
        Player actingPlayer = action.getPlayer();
        if(!actingPlayer.equals(expectedPlayer)) throw new IllegalStateException("It is not " + actingPlayer + "'s turn.");
        if(!actingPlayer.showHands().contains(action.getCard())) throw new IllegalStateException("Player does not have this card");

        // Remove card from hand..
        actingPlayer.playCard(action.getCard());

        // Update round
        round.playCard(actingPlayer, action.getCard());

        // advance turn
        game.advanceTurn();
    }
}
