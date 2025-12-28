package engine;

import java.util.*;


public class Player {
    private final String name;
    private Hand hand = new Hand();
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }
    public Player(String name, Deck deck, int numberOfCards) {
        this.name = name;

    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public Card playCard(Card card) {
        hand.removeCard(card);
        return card;
    }

    public boolean isHandFull() {
        return hand.isFull();
    }

    public List<Card> showHands() {
        return Collections.unmodifiableList(hand.getCards());
    }

    public void increaseScore() {
        score++;
    }

    public Hand getHand() { return hand; }
    public String getName() { return name; }
    public int getScore() { return score; }
    
}
