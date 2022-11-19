package net.minecraft.server.frazionz.items.trophies;

import java.util.Random;

import net.minecraft.server.frazionz.players.stats.EnumStats;

public class ItemShulkerTrophy extends ItemTrophy {
	
	public ItemShulkerTrophy() {
		super();
	}

	@Override
	public int getRandomStatModifier() {
		Random rand = new Random();
		float randFloat = rand.nextFloat();
		if(randFloat <= 0.5)
		{
			return 1 + rand.nextInt(7);
		}
		else if(randFloat <= 0.80)
		{
			return 9 + rand.nextInt(6);
		}
		else if(randFloat <= 0.95)
		{
			return 15 + rand.nextInt(5);
		}
		else
		{
			return 21 + rand.nextInt(4);
		}
	}

	public EnumStats getBaseStat()
	{
		return EnumStats.RESISTANCE;
	}
}
