package net.minecraft.server.frazionz.players.stats;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemArmor;
import net.minecraft.server.ItemStack;
import net.minecraft.server.frazionz.players.stats.modifier.StatValueCappingModifier;
import net.minecraft.server.frazionz.players.stats.modifier.StatModifier;

import java.util.HashMap;
import java.util.Map;

public class PlayerStats {

    private Map<EnumStats, SimpleStat> stats = new HashMap<>();
    private final EntityHuman player;
    private Map<EnumStats, Integer> minCapping = new HashMap<>();
    private Map<EnumStats, Integer> maxCapping = new HashMap<>();
    public boolean hasFallDamage = true;
    public boolean hasFireDamage = true;
    public boolean hasDrowningDamage = true;
    public boolean hasExplosionDamage = true;

    public PlayerStats(EntityHuman player) {
        this.player = player;

        for(EnumStats stat : EnumStats.values()) {
            stats.put(stat, new SimpleStat(stat));
        }
    }

    public void update() {
        resetStat();
        applyAllItemStat();
        applyFullArmorSet();
        applyStatCapping();
    }

    private void resetStat() {
        for(EnumStats stat : EnumStats.values()) {
            stats.get(stat).setValue(stat.getBaseValue());
            minCapping.put(stat, stat.getMinValue());
            maxCapping.put(stat, stat.getMaxValue());
        }
        hasFallDamage = true;
        hasFireDamage = true;
        hasDrowningDamage = true;
        hasExplosionDamage = true;
    }

    private void applyAllItemStat() {
        for(ItemStack stack : player.inventory.armor) {
            updateStats(stack);
        }
        for(ItemStack stack : player.inventory.trophyInventory) {
            updateStats(stack);
        }
        updateStats(player.getItemInMainHand());
        updateStats(player.getItemInOffHand());
    }

    private void applyStatCapping() {
        for(SimpleStat stat : stats.values()) {
            if(stat.getValue() < minCapping.get(stat.getStat())) {
                stat.setValue(minCapping.get(stat.getStat()));
            }
            else if(stat.getValue() > maxCapping.get(stat.getStat())) {
                stat.setValue(maxCapping.get(stat.getStat()));
            }
        }
    }

    private void updateStats(ItemStack stack) {
        if(stack.isStatItem()) {
            for(EnumStats stat : EnumStats.values()) {
                if(StatHelper.hasStat(stack, stat)) {
                    stats.get(stat).setValue(stats.get(stat).getValue() + StatHelper.getStatValue(stack, stat));
                }
            }
        }
    }

    private void applyFullArmorSet() {
        if(player.isWearingFullArmorSet()) {
            ItemArmor.EnumArmorMaterial material = player.getFullArmorMaterial();
            for(Map.Entry<EnumStats, Integer> entry : material.getStats().entrySet()) {
                SimpleStat simpleStat = stats.get(entry.getKey());
                simpleStat.setValue(simpleStat.getValue() + entry.getValue());
            }
            if(!material.getModifiers().isEmpty()) {
                for(StatModifier modifier : material.getModifiers()) {
                    applyModifier(modifier);
                }
            }
        }
    }

    private void applyModifier(StatModifier modifier) {
        if(modifier instanceof StatValueCappingModifier) {
            StatValueCappingModifier capModifier = (StatValueCappingModifier) modifier;
            switch(capModifier.getType()) {
                case MIN:
                    if(minCapping.containsKey(capModifier.getStat()) && minCapping.get(capModifier.getStat()) > capModifier.getValue()) {
                        minCapping.put(capModifier.getStat(), capModifier.getValue());
                    }
                    break;
                case MAX:
                    if(maxCapping.containsKey(capModifier.getStat()) && maxCapping.get(capModifier.getStat()) < capModifier.getValue()) {
                        maxCapping.put(capModifier.getStat(), capModifier.getValue());
                    }
                    break;
            }
        }
        else {
            modifier.applyModifier(this);
        }
    }

    public int getStat(EnumStats stat) {
        return stats.get(stat).getValue();
    }

    public void setStat(EnumStats stat, int value) {
        stats.get(stat).setValue(value);
    }

    public Map<EnumStats, SimpleStat> getStatsMap() {
        return stats;
    }
}
