package net.minecraft.server.frazionz.players.stats.modifier;


import net.minecraft.server.frazionz.players.stats.PlayerStats;

public class StatFireDamageModifier extends StatModifier {

    private boolean hasFireDamage;

    public StatFireDamageModifier(boolean hasFireDamage) {
        this.hasFireDamage = hasFireDamage;
    }

    @Override
    public boolean applyModifier(PlayerStats stats) {
        stats.hasFireDamage = hasFireDamage;
        return true;
    }
}
