package net.minecraft.server.frazionz.items;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.server.AttributeModifier;
import net.minecraft.server.GenericAttributes;
import net.minecraft.server.IAttribute;
import net.minecraft.server.MathUtils;
import net.minecraft.server.frazionz.players.stats.EnumStats;

public class ItemShulkerTrophy extends ItemTrophy {
	
	public ItemShulkerTrophy() {
		super();
	}

	@Override
	public int getRandomStatModifier() {
		return 0;
	}

	public EnumStats getBaseStat()
	{
		return EnumStats.RESISTANCE;
	}
	
}
