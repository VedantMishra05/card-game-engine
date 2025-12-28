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

        while(!deck.isEmpty()) {
            playRound();
        }

        state = GameState.FINISHED;
        announceFinalWinner();
    }

    
    private void playRound() {
        if(state != GameState.IN_PROGRESS) throw new IllegalStateException("Game not in progress.");

        Map<Player, Card> playedCards = new LinkedHashMap<>();

        for(Player player: players) {
            if(deck.isEmpty()) break;

            Card drawn = deck.draw();
            player.receiveCard(drawn);

            Card played = player.playCard(drawn);
            playedCards.put(player, played);
        }

        Player roundWinner = roundRule.determineWinner(playedCards);
        roundWinner.increaseScore();

        System.out.println("Played Cards: ");
        playedCards.forEach((p, c) ->  System.out.println(p.getName() + " played " + c));

        System.out.println("\nRound winner: " + roundWinner.getName());
        System.out.println();
    }
    
    private void announceFinalWinner() {
        Player winner = Collections.max(players, Comparator.comparingInt(Player::getScore));

        System.out.println("GAME OVER");
        System.out.println("Winner: " + winner.getName());
        System.out.println();
    }
}
