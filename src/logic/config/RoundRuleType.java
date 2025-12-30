package logic.config;

public enum RoundRuleType {
    HIGHER_CARD(1, "Higher Card"),
    LOWER_CARD(2, "Lower Card"),
    WAR(3, "War");

    private final int code;
    private final String displayName;
    RoundRuleType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public static RoundRuleType getType(int code) {
        for(RoundRuleType type: values()) {
            if(type.code == code) return type;
        }
        throw new IllegalArgumentException("Invalid Game mode choice : " + code);
    }
}
