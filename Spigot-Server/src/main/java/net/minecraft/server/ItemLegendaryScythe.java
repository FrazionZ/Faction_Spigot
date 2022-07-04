package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

public class ItemLegendaryScythe extends ItemWeaponEffects {

	public ItemLegendaryScythe(EnumToolMaterial paramEnumToolMaterial) {
		super(paramEnumToolMaterial, Arrays.asList(new EffectItem(MobEffects.SLOWER_DIG, 200000, 1),new EffectItem(MobEffects.INCREASE_DAMAGE, 200000, 1)));
	}

}
