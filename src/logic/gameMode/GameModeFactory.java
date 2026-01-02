package logic.gameMode;

import logic.config.GameConfig;

public class GameModeFactory {
    
    public static GameConfig create(GameMode mode) {
        return GameModeCatalog.createConfig(mode);
    }
}
