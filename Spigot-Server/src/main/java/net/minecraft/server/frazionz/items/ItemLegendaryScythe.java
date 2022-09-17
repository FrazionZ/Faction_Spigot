package net.minecraft.server.frazionz.items;

import net.minecraft.server.EffectItem;
import net.minecraft.server.MobEffects;

import java.util.Arrays;

public class ItemLegendaryScythe extends ItemWeaponEffects {

	public ItemLegendaryScythe(EnumToolMaterial paramEnumToolMaterial) {
		super(paramEnumToolMaterial, Arrays.asList(new EffectItem(MobEffects.SLOWER_DIG, 200000, 1),new EffectItem(MobEffects.INCREASE_DAMAGE, 200000, 1)));
	}

}
