package net.minecraft.server.frazionz.players.stats.modifier;

import net.minecraft.server.frazionz.players.stats.PlayerStats;

public class StatExplosionDamageModifier extends StatModifier {

    private boolean hasExplosionDamage;

    public StatExplosionDamageModifier(boolean hasExplosionDamage) {
        this.hasExplosionDamage = hasExplosionDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasExplosionDamage = hasExplosionDamage;
        return true;
    }
}
