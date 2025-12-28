package engine;
import engine.enums.Suit;
import engine.enums.Rank;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() { return suit; }
    public Rank getRank() { return rank; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    
    // PART SUGGESTED BY GPT : SEARCH LOGIC..
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Card)) return false;
        Card other = (Card) obj;
        return this.rank == other.rank && this.suit == other.suit;
    }

    @Override
    public int hashCode() {
        return rank.hashCode() * 31 + suit.hashCode();
    }
}
