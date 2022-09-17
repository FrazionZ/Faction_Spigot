package net.minecraft.server.frazionz.items;

import java.util.List;

import com.google.common.collect.Multimap;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EffectItem;
import net.minecraft.server.ItemSword;

public class ItemWeaponEffects extends ItemSword
{
	private final float a;
	  private final EnumToolMaterial b;
	private List<EffectItem> effectList;
	  
	  public ItemWeaponEffects(EnumToolMaterial paramEnumToolMaterial, List<EffectItem> effectList)
	  {
		  super(paramEnumToolMaterial);
		  this.b = paramEnumToolMaterial;
		  this.maxStackSize = 1;
		  this.setMaxDurability(paramEnumToolMaterial.a());
		  this.b(CreativeModeTab.j);
	      this.a = (3.0F + paramEnumToolMaterial.c());
	      this.effectList = effectList;
	  }
	  
	  public List<EffectItem> getEffectList() {
		return effectList;
	  }
}
