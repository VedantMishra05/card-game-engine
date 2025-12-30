package logic.gameRules;

import logic.config.GameConfig;
import logic.config.RoundRuleType;
import logic.gameFlow.RoundRule;
import logic.gameRules.rules.HigherCardRule;
import logic.gameRules.rules.LowerCardRule;
import logic.gameRules.rules.WarRule;

public class RuleResolver {
    public RoundRule resolve(GameConfig config) {
        RoundRuleType type = config.getRoundRuleType();

        return switch(type) {
            case HIGHER_CARD -> new HigherCardRule(); 
            case LOWER_CARD -> new LowerCardRule();
            case WAR -> new WarRule();
            default -> throw new IllegalArgumentException();
        };
    }
}
