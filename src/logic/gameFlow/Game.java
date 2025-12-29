package logic.gameFlow;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Deck;
import engine.Player;
import logic.GameState;
import logic.events.EventBus;
import logic.events.eventTypes.CardPlayedEvent;
import logic.events.eventTypes.GameEndedEvent;
import logic.events.eventTypes.RoundEndedEvent;
import logic.events.eventTypes.RoundStartedEvent;

public class Game {

    private final List<Player> players;
    private final Deck deck;
    private final RoundRule roundRule;
    private final EventBus eventBus;

    private GameState state = GameState.SETUP;

    public Game(List<Player> players, int shuffleCount, RoundRule roundRule, EventBus eventBus) {
        this.players = players;
        this.deck = new Deck(shuffleCount);
        this.roundRule = roundRule;
        this.eventBus = eventBus;
    }

    public void setup() {
        if(state != GameState.SETUP) throw new IllegalStateException("Game already started!");
        state = GameState.IN_PROGRESS;
    }

    // Main flow of the game yahi hai.. saare games pe chalega..
    public void play() {
        setup();

        while(canPlayRound()) {
            playRound();
        }

        state = GameState.FINISHED;
        endRound();
    }

    public boolean canPlayRound() {
        return deck.remainingCards() >= players.size();
    }

    
    private void playRound() {
        if(state != GameState.IN_PROGRESS) throw new IllegalStateException("Game not in progress.");

        eventBus.publish(new RoundStartedEvent());

        Map<Player, Card> playedCards = new LinkedHashMap<>();

        for(Player player: players) {
            Card drawn = deck.draw();
            player.receiveCard(drawn);

            Card played = player.playCard(drawn);
            playedCards.put(player, played);

            eventBus.publish(new CardPlayedEvent(player, played));
        }

        List<Player> roundWinners = roundRule.determineWinner(playedCards);
        roundWinners.forEach(Player::increaseScore);

        eventBus.publish(new RoundEndedEvent(playedCards, roundWinners));
    }
    
    private void endRound() {
        int highestScore = players.stream().mapToInt(Player::getScore).max().orElse(0);
        List<Player> finalWinners = players.stream().filter(p -> p.getScore() == highestScore).toList();

        eventBus.publish(new GameEndedEvent(finalWinners));
    }
}
