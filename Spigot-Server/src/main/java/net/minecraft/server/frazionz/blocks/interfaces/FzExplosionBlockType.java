package net.minecraft.server.frazionz.blocks.interfaces;

import org.bukkit.event.frazionz.FzBlockExplodeEvent.ExplosiveType;
import org.bukkit.frazionz.enums.ExplosionBlockType;

public interface FzExplosionBlockType {

	public ExplosionBlockType getExplosionBlockType();
	
	public default float getExplosionChance(ExplosiveType explosiveType) {
		return 1.0f;
	}
	
	public default float getExplosionChanceBonusForLevel(int level) {
		return 0.0f;
	}
	
}
