package net.minecraft.server.frazionz.players.stats.modifier;

import net.minecraft.server.frazionz.players.stats.EnumStats;

public class StatValueCappingModifier extends StatValueModifier {

    private StatCapType type;

    public StatValueCappingModifier(StatCapType type, EnumStats stat, int value) {
        super(stat, value);
        this.type = type;
    }

    public StatCapType getType() {
        return type;
    }
    public enum StatCapType {
        MAX,
        MIN
    }
}