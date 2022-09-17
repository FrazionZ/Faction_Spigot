package net.minecraft.server.frazionz.items;

import net.minecraft.server.EffectItem;
import net.minecraft.server.MobEffects;

import java.util.Arrays;

public class ItemLegendarySword extends ItemWeaponEffects {

	public ItemLegendarySword(EnumToolMaterial paramEnumToolMaterial) {
		super(paramEnumToolMaterial, Arrays.asList(new EffectItem(MobEffects.FASTER_MOVEMENT, 200000, 1),new EffectItem(MobEffects.INCREASE_DAMAGE, 200000, 1)));
	}

}
