package net.minecraft.server.frazionz.players.stats.modifier;

import net.minecraft.server.frazionz.players.stats.EnumStats;

public class StatCapModifier extends StatModifier {

    private StatCapType type;

    public StatCapModifier(StatCapType type, EnumStats stat, int value) {
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