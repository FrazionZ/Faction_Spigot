package net.minecraft.server.frazionz.items;

import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.items.interfaces.IStatItem;
import net.minecraft.server.frazionz.players.stats.EnumStats;
import net.minecraft.server.frazionz.players.stats.StatHelper;

public abstract class ItemTrophy extends Item implements IStatItem {

	public ItemTrophy() {
		this.b(CreativeModeTab.f);
		this.maxStackSize = 1;
	}

	public abstract int getRandomStatModifier();

	public abstract EnumStats getBaseStat();

	public void randomBaseStat(ItemStack stack) {
		setBaseStatValue(stack, getRandomStatModifier());
	}
	public int getBaseStatValue(ItemStack stack) {
		return getStatValue(stack, getBaseStat());
	}

	public void setBaseStatValue(ItemStack stack, int statValue) {
		setStatValue(stack, getBaseStat(), statValue);
	}

	public void setStatValue(ItemStack stack, EnumStats stat, int value) {
		StatHelper.applyStats(stack, stat, value);
	}

	public int getStatValue(ItemStack stack, EnumStats stat) {
		return StatHelper.getStatValue(stack, stat);
	}
	
}
