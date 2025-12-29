package engine;

import engine.enums.Suit;
import engine.enums.Rank;
import java.util.*;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck(int shuffleCount) {
        for(Suit suit: Suit.values()) {
            for(Rank rank: Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        for(int i = 0; i < shuffleCount; i++) {
            Collections.shuffle(cards);
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if(cards.isEmpty()) throw new IllegalStateException("Deck is empty!");
        return cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }
    
    public int remainingCards() {
        return cards.size();
    }

    // public static void main(String[] args) {
        // Deck deck1 = new Deck();
        // Card firstDraw = deck1.draw();
        // Card secDraw = deck1.draw();
        // Card thirdDraw = deck1.draw();
        // Card fourthDraw = deck1.draw();
        // System.out.println(deck1.size());
    //     System.out.println(firstDraw.getCardDetails());
    //     System.out.println(secDraw.getCardDetails());
    //     System.out.println(thirdDraw.getCardDetails());
    //     System.out.println(fourthDraw.getCardDetails());
    // }
}
