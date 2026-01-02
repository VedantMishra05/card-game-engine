package logic.gameMode;

import java.util.LinkedHashMap;
import java.util.Map;

import logic.config.GameConfig;
import logic.config.PlayMode;
import logic.config.RoundRuleType;
import logic.strategy.deal.types.DealAllAtStart;

public class GameModeCatalog {
    
    private static final Map<Integer, GameMode> MODES = new LinkedHashMap<>();
    static {
        MODES.put(1, GameMode.HIGH_CARD);
        MODES.put(2, GameMode.LOW_CARD);
        MODES.put(3, GameMode.WAR);
    }

    public static Map<Integer, String> displayOptions() {
        Map<Integer, String> options = new LinkedHashMap<>();
        MODES.forEach((k, v) -> options.put(k, v.getDisplayName()));
        return options;
    }

    public static GameMode fromChoice(int choice) {
        if(!MODES.containsKey(choice)) throw new IllegalArgumentException("Invalid game mode choice: " + choice);
        return MODES.get(choice);
    }

    public static GameConfig createConfig(GameMode mode) {
        return switch(mode) {
            case GameMode.HIGH_CARD -> GameConfig.builder().
                                    roundRuleType(RoundRuleType.HIGHER_CARD).
                                    playMode(PlayMode.MANUAL).
                                    dealingStrategy(new DealAllAtStart()).
                                    build();
            
            case GameMode.LOW_CARD -> GameConfig.builder().
                                    roundRuleType(RoundRuleType.LOWER_CARD).
                                    playMode(PlayMode.MANUAL).
                                    dealingStrategy(new DealAllAtStart()).
                                    build();
                    
            case GameMode.WAR -> GameConfig.builder().
                                    roundRuleType(RoundRuleType.WAR).
                                    playMode(PlayMode.AUTO).
                                    dealingStrategy(new DealAllAtStart()).
                                    build();
                            
            default -> throw new UnsupportedOperationException();
        };
    }
}
