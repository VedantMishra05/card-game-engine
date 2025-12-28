package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int HAND_SIZE_LIMIT = 5;
    private final List<Card> cardsInHand = new ArrayList<>();

    public void addCard(Card card) {
        if(cardsInHand.size() >= HAND_SIZE_LIMIT) {throw new IllegalStateException("Hand is full!");}
        cardsInHand.add(card);
    }

    public void drawFrom(Deck deck) {
        if(cardsInHand.size() >= HAND_SIZE_LIMIT) {throw new IllegalStateException("Hand is full!");}
        cardsInHand.add(deck.draw());
    }

    public boolean contains(Card card) {
        return cardsInHand.contains(card);
    }

    public boolean isFull() {
        return cardsInHand.size() == HAND_SIZE_LIMIT;
    }

    public void removeCard(Card card) {
        if(!(cardsInHand.remove(card))) {
            throw new IllegalStateException("Card not in hand");
        }
    }

    // public void getDetails() {
    //     for(Card card: cardsInHand) {
    //         System.out.println(card.toString());
    //         System.out.println();
    //     }
    // }



    public List<Card> getCards() {
        return Collections.unmodifiableList(cardsInHand);
    }
}
