package ui.console;

import java.util.Map;
import java.util.Scanner;

import logic.config.GameConfig;
import logic.gameMode.GameMode;
import logic.gameMode.GameModeCatalog;

public class ConsoleGameModeSelector {
    
    public static GameConfig selectGameConfig(Scanner sc) {
        System.out.println("\n----- Available Game Modes -----\n");
        for (Map.Entry<Integer, String> entry : GameModeCatalog.displayOptions().entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println();

        int choice;
        while (true) {
            System.out.print("Enter game mode number: ");
            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            choice = sc.nextInt();
            try {
                GameMode mode = GameModeCatalog.fromChoice(choice);
                System.out.println("\n----- LET'S PLAY A GAME OF " + mode.getDisplayName() + " -----\n");
                return GameModeCatalog.createConfig(mode);
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid game mode number.");
            }
        }
    }
}
