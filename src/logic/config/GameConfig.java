package logic.config;

public final class GameConfig {
    private final RoundRuleType roundRuleType;
    private final int cardsPerRound;
    private final PlayMode playMode;

    private GameConfig(Builder builder) {
        this.roundRuleType = builder.roundRuleType;
        this.cardsPerRound = builder.cardsPerRound;
        this.playMode = builder.playMode;
    }

    public RoundRuleType getRoundRuleType() {
        return roundRuleType;
    }

    public int getCardsPerRound() {
        return cardsPerRound;
    }

    public PlayMode getPlayMode() {
        return playMode;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private RoundRuleType roundRuleType;
        private int cardsPerRound = 1;
        private PlayMode playMode = PlayMode.AUTO;

        public Builder roundRuleType(RoundRuleType type) {
            this.roundRuleType = type;
            return this;
        }
        public Builder cardsPerRound(int count) {
            this.cardsPerRound = count;
            return this;
        }
        public Builder playMode(PlayMode mode) {
            this.playMode = mode;
            return this;
        }

        public GameConfig build() {
            if(roundRuleType == null) throw new IllegalStateException("RoundRuleType must be set");
            if(cardsPerRound <= 0) throw new IllegalStateException("Cards per round must be > 0");
            return new GameConfig(this);
        }
    }
}
// isme khudka class isliye shayad ki change na kar paaye.. to inner class me parameter leke main class ko de diye..