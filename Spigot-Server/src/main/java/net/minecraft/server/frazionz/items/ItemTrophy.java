package net.minecraft.server.frazionz.items;

import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.players.stats.EnumStats;

public abstract class ItemTrophy extends Item {

	public ItemTrophy() {
		this.b(CreativeModeTab.f);
		this.maxStackSize = 1;
	}

	public abstract int getRandomStatModifier();

	public abstract EnumStats getBaseStat();

	public void randomBaseStat(ItemStack stack) {
		if(stack.getTag() == null) {
			stack.load(new NBTTagCompound());
		}
		setBaseStatValue(stack, getRandomStatModifier());
	}
	public int getBaseStatValue(ItemStack stack) {
		return getOtherStatValue(stack, getBaseStat());
	}

	public void setBaseStatValue(ItemStack stack, int statValue) {
		setOtherStatValue(stack, getBaseStat(), statValue);
	}

	public void setOtherStatValue(ItemStack stack, EnumStats stat, int value) {
		NBTTagCompound nbt = stack.getTag();
		if(nbt == null) {
			nbt = new NBTTagCompound();
		}
		nbt.setInt(stat.name(), value);
		stack.load(nbt);
	}

	public int getOtherStatValue(ItemStack stack, EnumStats stat) {
		NBTTagCompound nbt = stack.getTag();
		if(nbt != null) {
			if(nbt.hasKey(stat.name())) {
				return nbt.getInt(stat.name());
			}
		}
		return 0;
	}
	
}
