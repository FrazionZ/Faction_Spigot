package net.minecraft.server.frazionz.players.stats.modifier;

import net.minecraft.server.frazionz.players.stats.PlayerStats;

public class StatFallDamageModifier extends StatModifier {

    private boolean hasFallDamage;

    public StatFallDamageModifier(boolean hasFallDamage) {
        this.hasFallDamage = hasFallDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasFallDamage = hasFallDamage;
        return true;
    }
}
