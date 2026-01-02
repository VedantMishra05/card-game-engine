package logic.gameMode;

public enum GameMode {
    HIGH_CARD("Higher Card Wins"),
    LOW_CARD("Lower Card Wins"),
    WAR("War");

    private final String displayName;
    GameMode(String s) {
        this.displayName = s;
    }

    public String getDisplayName() { return displayName; }
}
