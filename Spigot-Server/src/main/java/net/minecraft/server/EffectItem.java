package net.minecraft.server;

import java.util.ArrayList;
import java.util.List;

public class EffectItem {

	private MobEffectList effect;
	private int duration;
	private int level;
	
	public EffectItem(MobEffectList fireResistance, int duration, int level) {
		this.effect = fireResistance;
		this.duration = duration;
		this.level = level;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public MobEffectList getEffect() {
		return effect;
	}
	
	public int getLevel() {
		return level;
	}
	
}
