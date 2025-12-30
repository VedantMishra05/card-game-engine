package logic.gameFlow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Player;
import logic.events.EventBus;
import logic.events.eventTypes.CardPlayedEvent;
import logic.events.eventTypes.RoundEndedEvent;
import logic.events.eventTypes.RoundStartedEvent;

public class Round {
    private final int roundNumber;
    private final List<Player> players;
    private final RoundRule roundRule;
    private final EventBus eventBus;

    private final Map<Player, Card> playedCards = new HashMap<>();
    private boolean finished = false;

    public Round(int roundNumber, List<Player> players, RoundRule roundRule, EventBus eventBus) {
        this.roundNumber = roundNumber;
        this.players = players;
        this.roundRule = roundRule;
        this.eventBus = eventBus;
    }

    public void start() {
        eventBus.publish(new RoundStartedEvent(roundNumber));
    }
    
   public void playCard(Player player, Card card) {
        if(finished) throw new IllegalStateException("Round already finished.");

        if(!players.contains(player)) throw new IllegalArgumentException("Player not part of the game.");

        if(playedCards.containsKey(player)) throw new IllegalStateException("Player has already played this round");

        playedCards.put(player, card);
        eventBus.publish(new CardPlayedEvent(player, card));

        if(playedCards.size() == players.size()) {
            endRound();
        }
   }
    
    private void endRound() {
        finished = true;

        List<Player> roundWinners = roundRule.determineWinner(playedCards);
        roundWinners.forEach(Player::increaseScore);

        eventBus.publish(new RoundEndedEvent(roundNumber, playedCards, roundWinners));
    }

    public boolean isFinished() {
        return finished;
    }

    public int getRoundNumber() {
        return roundNumber;
    }
}
