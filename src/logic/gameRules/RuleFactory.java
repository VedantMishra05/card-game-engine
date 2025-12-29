package logic.gameRules;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import logic.gameFlow.RoundRule;
import logic.gameRules.rules.HigherCardRule;
import logic.gameRules.rules.LowerCardRule;

public class RuleFactory {

    // Handle list of rules - naye waale yaha daal denge
    public static Map<Integer, String> ruleOptions = new HashMap<>();
    static {
        ruleOptions.put(1, "Higher Card Wins");
        ruleOptions.put(2, "Lower Card Wins");
    }

    // print for user's choice
    public static void displayRules() {
        System.out.println("\n\n        Available Game modes            \n");
        ruleOptions.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public static int getUserChoice(Scanner sc) {
        int choice;
        while(true) {
            System.out.print("\nEnter Game mode number: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            if (!ruleOptions.containsKey(choice)) {
                System.out.println("Please enter a valid game mode number.");
                continue;
            }
            break;
        }

        System.out.println("\n!!!        LET'S PLAY A GAME OF " + ruleOptions.get(choice).toUpperCase() + "        !!!!");
        return choice;
    }

    // helper method to create rules
    public static RoundRule createRule(int choice) {
        return switch(choice) {
            case 1 -> new HigherCardRule();
            case 2 -> new LowerCardRule();
            default -> throw new IllegalArgumentException("");
        };
    }
}
