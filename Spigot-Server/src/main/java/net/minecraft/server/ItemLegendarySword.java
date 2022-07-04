package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

public class ItemLegendarySword extends ItemWeaponEffects {

	public ItemLegendarySword(EnumToolMaterial paramEnumToolMaterial) {
		super(paramEnumToolMaterial, Arrays.asList(new EffectItem(MobEffects.FASTER_MOVEMENT, 200000, 1),new EffectItem(MobEffects.INCREASE_DAMAGE, 200000, 1)));
	}

}
