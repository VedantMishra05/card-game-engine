package logic.gameFlow;

import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import engine.Card;
import engine.Deck;
import engine.Player;
import logic.GameState;

public class Game {
    private final List<Player> players;
    private final Deck deck;
    private final RoundRule roundRule;

    private GameState state = GameState.SETUP;

    public Game(List<Player> players, Deck deck, RoundRule roundRule) {
        this.players = players;
        this.deck = deck;
        this.roundRule = roundRule;
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
        announceFinalWinner();
    }

    public boolean canPlayRound() {
        return deck.remainingCards() >= players.size();
    }

    
    private void playRound() {
        if(state != GameState.IN_PROGRESS) throw new IllegalStateException("Game not in progress.");

        Map<Player, Card> playedCards = new LinkedHashMap<>();

        for(Player player: players) {
            Card drawn = deck.draw();
            player.receiveCard(drawn);

            Card played = player.playCard(drawn);
            playedCards.put(player, played);
        }

        List<Player> roundWinners = roundRule.determineWinner(playedCards);
        roundWinners.forEach((p) -> p.increaseScore());

        System.out.println("Played Cards: ");
        playedCards.forEach((p, c) ->  System.out.println(p.getName() + " played " + c));

        System.out.print("\nRound winner: ");
        roundWinners.forEach((p) -> System.out.print(p.getName() + "  "));
        System.out.println();
        System.out.println();
    }
    
    private void announceFinalWinner() {
        Player winner = Collections.max(players, Comparator.comparingInt(Player::getScore));

        System.out.println("GAME OVER");
        System.out.println("The final winner is ->  " + winner.getName());
        System.out.println();
    }
}
