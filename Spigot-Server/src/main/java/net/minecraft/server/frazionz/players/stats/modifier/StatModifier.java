package net.minecraft.server.frazionz.players.stats.modifier;

import net.minecraft.server.frazionz.players.stats.EnumStats;

public class StatModifier {

    protected EnumStats stat;
    protected int value;

    public StatModifier(EnumStats stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public EnumStats getStat() {
        return stat;
    }

    public int getValue() {
        return value;
    }
}