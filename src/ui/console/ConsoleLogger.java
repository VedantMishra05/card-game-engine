package ui.console;

import engine.util.Sleep;
import logic.events.EventBus;
import logic.events.eventTypes.CardPlayedEvent;
import logic.events.eventTypes.GameEndedEvent;
import logic.events.eventTypes.RoundEndedEvent;
import logic.events.eventTypes.RoundStartedEvent;

public class ConsoleLogger {
    public ConsoleLogger(EventBus eventBus) {
        eventBus.subscribe(CardPlayedEvent.class, event -> {
            Sleep.sleep(500);
            System.out.println(event.player().getName() + " played " + event.card());
        });

        eventBus.subscribe(RoundStartedEvent.class, event -> {
            System.out.println("\n------------  Round Starts ------------\n");
        });
        
        eventBus.subscribe(RoundEndedEvent.class, event -> {
            if(event.winners().isEmpty()) {
                System.out.println("There is no clear winner of this round.");
            }
            else if(event.winners().size() == 1) {
                System.out.print("\nRound winner is : ");
                System.out.print(event.winners().get(0).getName() + " ");
                System.out.println();
            } else {
                System.out.print("\nRound winners are : ");
                event.winners().forEach(p -> System.out.print(p.getName() + " "));
                System.out.println();
            }
            System.out.println("\n------------  Round Ended ------------");
            System.out.println();
            Sleep.sleep(1000);
        });

        eventBus.subscribe(GameEndedEvent.class, event -> {
            System.out.println("------------ GAME OVER ------------");
            System.out.print("\nWinner : ");
            event.finalWinnerList().forEach(p ->
                System.out.print(p.getName() + " ")
            );
            System.out.println("\n");
        });
    }
}
