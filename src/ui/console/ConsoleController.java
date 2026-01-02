package ui.console;

import java.util.List;
import java.util.Scanner;

import engine.Card;
import engine.Player;
import logic.actions.actionTypes.PlayCardAction;
import logic.events.EventBus;
import logic.events.eventTypes.RoundStartedEvent;
import logic.gameFlow.Game;

public class ConsoleController {
    private final Game game;
    private final Scanner sc;

    public ConsoleController(Game game, EventBus eventBus) {
        this.game = game;
        this.sc = new Scanner(System.in);

        eventBus.subscribe(RoundStartedEvent.class, this::onRoundStarted);
    }

    public void onRoundStarted(RoundStartedEvent event) {
        // System.out.println("----- Manual Mode -----");
        System.out.println("- Play your cards -");
        
        for(Player players: game.getRound().getPlayers()) {
            promptPlayer(players);
        }
    }
    
    private void promptPlayer(Player player) {
        List<Card> hand = player.showHands();
        
        System.out.println("\n" + player.getName() + "'s turn: ");
        for(int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
        
        int choice;
        while (true) {
            System.out.print("Choose card to play : ");
            if(!sc.hasNextInt()) {
                System.out.print("Invalid choice.");
                sc.next();
                continue;
            }

            choice = sc.nextInt(); choice--;
            if(choice >= 0 && choice < hand.size()) break;
        }

        Card selected = hand.get(choice);

        game.getActionExecutor().execute(new PlayCardAction(player, selected));
    }
}
