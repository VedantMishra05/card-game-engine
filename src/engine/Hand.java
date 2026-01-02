package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    // private static final int HAND_SIZE_LIMIT = 5;
    // private final int handSize;
    private final List<Card> cardsInHand = new ArrayList<>();

    public Hand (int handSize) {
        // this.handSize = handSize;
    }

    public void addCard(Card card) {
        // if(cardsInHand.size() >= HAND_SIZE_LIMIT) {throw new IllegalStateException("Hand is full!");}
        cardsInHand.add(card);
    }

    public void drawFrom(Deck deck) {
        // if(cardsInHand.size() >= HAND_SIZE_LIMIT) {throw new IllegalStateException("Hand is full!");}
        cardsInHand.add(deck.draw());
    }

    public boolean contains(Card card) {
        return cardsInHand.contains(card);
    }

    public boolean isEmpty() {
        return cardsInHand.size() == 0;
    }
    
    // public boolean isFull() {
    //     return cardsInHand.size() == HAND_SIZE_LIMIT;
    // }

    public void removeCard(Card card) {
        if(!(cardsInHand.remove(card))) {
            throw new IllegalStateException("Card not in hand");
        }
    }

    public int size() { return cardsInHand.size(); }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cardsInHand);
    }
}
