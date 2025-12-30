package logic.config.userInput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import logic.config.RoundRuleType;

public class RuleFactory {

    // Handle list of rules - naye waale yaha daal denge
    public static Map<Integer, String> ruleOptions = new HashMap<>();
    static {
        ruleOptions.put(1, "Higher Card Wins");
        ruleOptions.put(2, "Lower Card Wins");
        ruleOptions.put(3, "War");
    }

    // print for user's choice
    public static void displayRules() {
        System.out.println("\n\n-----  -----  -----  -----  ----- -----  -----\n\n        Available Game modes            \n");
        ruleOptions.forEach((key, value) -> System.out.println(key + ". " + value));
        System.out.println("\n-----  -----  -----  -----  ----- -----  -----\n\n");
    }

    public static RoundRuleType getUserChoice(Scanner sc) {
        int choice;
        while(true) {
            System.out.print("Enter Game mode number: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter a number.");
                sc.next();
                continue;
            }
            choice = sc.nextInt();
            try {
                return RoundRuleType.getType(choice);
            } catch(IllegalArgumentException e) {
                System.out.println("Please enter a valid game mode number.");
            }
            break;
        }

        System.out.println("\n-----  -----  -----  -----  ----- -----  -----\n\n");
        System.out.println("\n!!!        LET'S PLAY A GAME OF " + ruleOptions.get(choice).toUpperCase() + "        !!!!");
        System.out.println("\n\n-----  -----  -----  -----  ----- -----  -----\n\n");
        return RoundRuleType.HIGHER_CARD;
    }
}
