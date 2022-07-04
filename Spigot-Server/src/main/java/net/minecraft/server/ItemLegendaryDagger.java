package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

public class ItemLegendaryDagger extends ItemWeaponEffects {

	public ItemLegendaryDagger(EnumToolMaterial paramEnumToolMaterial) {
		super(paramEnumToolMaterial, Arrays.asList(new EffectItem(MobEffects.FASTER_DIG, 200000, 0),new EffectItem(MobEffects.FASTER_MOVEMENT, 200000, 1)));
	}

}
