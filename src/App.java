import java.util.List;

import engine.Deck;
import engine.Player;
import logic.gameFlow.Game;
import logic.rule.HigherCardRule;

public class App {
    public static void main(String[] args) throws Exception {
        Deck deck = new Deck();
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
    }
}
