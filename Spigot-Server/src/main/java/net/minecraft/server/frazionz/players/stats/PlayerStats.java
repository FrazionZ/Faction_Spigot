package net.minecraft.server.frazionz.players.stats;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.frazionz.items.interfaces.IStatItem;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {

    private Map<EnumStats, SimpleStat> stats = new HashMap<>();
    private final EntityHuman player;

    public PlayerStats(EntityHuman player) {
        this.player = player;

        for(EnumStats stat : EnumStats.values()) {
            stats.put(stat, new SimpleStat(stat));
        }
    }

    public void update() {
        for(EnumStats stat : EnumStats.values()) {
            stats.get(stat).setValue(stat.getBaseValue());
        }
        for(ItemStack stack : player.inventory.armor) {
            updateStats(stack);
        }
        for(ItemStack stack : player.inventory.trophyInventory) {
            updateStats(stack);
        }
        updateStats(player.getItemInMainHand());
        updateStats(player.getItemInOffHand());
    }

    private void updateStats(ItemStack stack) {
        if(stack.getItem() instanceof IStatItem) {
            NBTTagCompound tag = stack.getTag();
            if(tag != null) {
                for(EnumStats stat : EnumStats.values()) {
                    SimpleStat simpleStat = stats.get(stat);
                    if(tag.hasKey(stat.name())) {
                        simpleStat.setValue(simpleStat.getValue() + tag.getInt(stat.name()));
                    }
                }
            }
        }
    }

    public int getStat(EnumStats stat) {
        return stats.get(stat).getValue();
    }

    public void setStat(EnumStats stat, int value) {
        stats.get(stat).setValue(value);
    }
}
