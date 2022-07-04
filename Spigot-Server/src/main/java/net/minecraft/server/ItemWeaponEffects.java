package net.minecraft.server;

import java.util.List;

import com.google.common.collect.Multimap;

public class ItemWeaponEffects extends ItemSword
{
	private final float a;
	  private final Item.EnumToolMaterial b;
	private List<EffectItem> effectList;
	  
	  public ItemWeaponEffects(Item.EnumToolMaterial paramEnumToolMaterial, List<EffectItem> effectList)
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
