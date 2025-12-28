package engine.enums;

public enum Suit {
    HEARTS("Red"), DIAMONDS("Red"), CLUBS("Black"), SPADES("Black");

    private String color;
    Suit(String color) { this.color = color; }

    public boolean isRed() { return color.equals("Red"); }
    public boolean isBlack() { return color.equals("Black"); }
}