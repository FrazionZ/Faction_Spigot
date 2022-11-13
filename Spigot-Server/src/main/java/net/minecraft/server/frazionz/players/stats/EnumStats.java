package net.minecraft.server.frazionz.players.stats;

public enum EnumStats {

    HEALTH(-100, 0, 100),
    REGENERATION(-50, 0, 50),
    SPEED(65, 100, 125),
    DAMAGE(50, 100, 125),
    RESISTANCE(50, 100, 125),
    MINING_SPEED(25, 100, 150),
    DUPLICATE_MINING_CHANCE(50, 100, 200),
    FARMING_SKILL_XP(50, 100, 200),
    MINING_SKILL_XP(50, 100, 200),
    COMBAT_SKILL_XP(50, 100, 200),
    PILLAGE_SKILL_XP(50, 100, 200);
    ;

    private int minValue;
    private int baseValue;
    private int maxValue;

    EnumStats(int minValue, int baseValue, int maxValue) {
        this.minValue = minValue;
        this.baseValue = baseValue;
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getBaseValue() {
        return baseValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
