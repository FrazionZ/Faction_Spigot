package net.minecraft.server.frazionz.players.stats.modifier;

import net.minecraft.server.frazionz.players.stats.PlayerStats;

public class StatDrowningDamageModifier extends StatModifier {

    private boolean hasDrowningDamage;

    public StatDrowningDamageModifier(boolean hasDrowningDamage) {
        this.hasDrowningDamage = hasDrowningDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasDrowningDamage = hasDrowningDamage;
        return true;
    }
}
