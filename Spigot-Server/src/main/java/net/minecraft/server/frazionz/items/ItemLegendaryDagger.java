package net.minecraft.server.frazionz.items;

import net.minecraft.server.EffectItem;
import net.minecraft.server.MobEffects;

import java.util.Arrays;

public class ItemLegendaryDagger extends ItemWeaponEffects {

	public ItemLegendaryDagger(EnumToolMaterial paramEnumToolMaterial) {
		super(paramEnumToolMaterial, Arrays.asList(new EffectItem(MobEffects.FASTER_DIG, 200000, 0),new EffectItem(MobEffects.FASTER_MOVEMENT, 200000, 1)));
	}

}
