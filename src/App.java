import java.util.List;
import java.util.Scanner;

import engine.Player;
import engine.util.Sleep;
import logic.config.GameConfig;
import logic.config.RoundRuleType;
import logic.config.userInput.RuleFactory;
import logic.events.EventBus;
import logic.gameFlow.Game;
import ui.console.ConsoleLogger;

public class App {

    private static int shuffleCount(Scanner sc) {
        final int MIN_SHUFFLES = 5;
        final int MAX_SHUFFLES = 20;

        int shuffleCount;
        while(true) {
            System.out.println("\n-----  -----  -----  -----  ----- -----  -----\n\n");
            System.out.print("How many times do you want to shuffle ( " + MIN_SHUFFLES + " - " + MAX_SHUFFLES +" ) : ");
            if(!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }
            
            shuffleCount = sc.nextInt();
            
            if(shuffleCount < MIN_SHUFFLES || shuffleCount > MAX_SHUFFLES) {
                System.out.println("Please enter a number between " + MIN_SHUFFLES + " - " + MAX_SHUFFLES +". ");
                continue;
            }
            
            break;
        }
        
        System.out.println("\n-----  -----  -----  -----  ----- -----  -----\n\n");
        System.out.println("........Preparing to shuffle the deck........");
        System.out.println();
        Sleep.sleep(500);
        System.out.println("Shuffling deck " + shuffleCount + " times...");
        System.out.println("\n-----  -----  -----  -----  ----- -----  -----\n\n");
        Sleep.sleep(1000);

        return shuffleCount;
    }
    
    
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Invoking infrastructure...
        EventBus eventBus = new EventBus();

        // Event Listners...
        new ConsoleLogger(eventBus);

        // Input for which game mode..
        RuleFactory.displayRules();
        RoundRuleType roundRuleType = RuleFactory.getUserChoice(sc);
        // RoundRule roundRule = RuleFactory.createRule(ruleChoice);

        // config
        GameConfig config = GameConfig.builder().
            roundRuleType(roundRuleType).
            build();

        // User input for number of shuffles
        int shuffleCount = shuffleCount(sc);

        List<Player> players = List.of(
            new Player("Vedant"),
            new Player("Sakshi"),
            new Player("Ashish"),
            new Player("Kappa"),
            new Player("Ritwik"), 
            new Player("Abhay"),
            new Player("Harsh")
        );
        
        Game game = new Game(
            players,
            shuffleCount,
            config, 
            eventBus
        );
        game.start();
        
        sc.close();
    }
}
