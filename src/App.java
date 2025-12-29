import java.util.List;
import java.util.Scanner;

import engine.Deck;
import engine.Player;
import engine.util.Sleep;
import logic.gameFlow.Game;
import logic.rule.HigherCardRule;

public class App {

    private static int shuffleCount(Scanner sc) {
        final int MIN_SHUFFLES = 5;
        final int MAX_SHUFFLES = 20;

        int shuffleCount;
        while(true) {
            System.out.print("\nHow many times do you want to shuffle ( " + MIN_SHUFFLES + " - " + MAX_SHUFFLES +" ) : ");
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
        
        System.out.println();
        System.out.println("Preparing to shuffle the deck...");
        System.out.println();
        Sleep.sleep(500);
        System.out.println("Shuffling deck " + shuffleCount + " times...");
        System.out.println();
        Sleep.sleep(1000);

        return shuffleCount;
    }
    
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int shuffleCount = shuffleCount(sc);
        Deck deck = new Deck(shuffleCount);
        Player player1 = new Player("Vedant");
        Player player2 = new Player("Ashish");
        Player player3 = new Player("Sakshi");
        Player player4 = new Player("Kappa");
        Player player5 = new Player("Ritwik");
        Player player6 = new Player("Abhay");
        Player player7 = new Player("Harsh");
        
        Game game = new Game(
            List.of(player1, player2, player3, player4, player5, player6, player7),
            deck,
            new HigherCardRule()
        );
        
        game.play();
        
        sc.close();
    }
}
