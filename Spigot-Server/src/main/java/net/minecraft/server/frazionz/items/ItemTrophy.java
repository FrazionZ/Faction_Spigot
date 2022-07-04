package net.minecraft.server.frazionz.items;

import java.util.List;
import java.util.Map.Entry;

import com.google.common.collect.Multimap;

import net.minecraft.server.AttributeModifier;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.IAttribute;
import net.minecraft.server.Item;
public abstract class ItemTrophy extends Item {

	public ItemTrophy() {
		this.b(CreativeModeTab.f);
		this.maxStackSize = 1;
	}
    
    public abstract AttributeModifier getRandomAttributeModifier();
    
    public abstract IAttribute getMonsterAttributes();
	
}
