package fun.reallyisnt.oms.minigamecore.games.rules;

public enum GameRules {

    DAMAGE(true),
    PVP_DAMAGE(true),
    PVE_DAMAGE(true),
    EVP_DAMAGE(true),
    FALL_DAMAGE(true),
    TEAM_DAMAGE(false),

    BREAK_BLOCK(false),
    BREAK_PLAYER_BLOCK(false),
    PLACE_BLOCK(false),

    WEATHER(false),
    FIRE_SPREAD(false);

    private final boolean defaultValue;

    GameRules(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
}
