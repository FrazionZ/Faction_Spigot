package net.minecraft.server.frazionz.items;

import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.server.AttributeModifier;
import net.minecraft.server.GenericAttributes;
import net.minecraft.server.IAttribute;
import net.minecraft.server.MathUtils;

public class ItemShulkerTrophy extends ItemTrophy {
	
	public ItemShulkerTrophy() {
		super();
	}
    
    public AttributeModifier getRandomAttributeModifier() {
    	Random rand = new Random();
    	float randFloat = rand.nextFloat();
    	
    	if(randFloat <= 0.5)
    	{
    		return new AttributeModifier(UUID.fromString("aa038f92-2b22-41c3-805d-556668182ed0"), "Resistance", 0.01 + MathUtils.roundAvoid(rand.nextFloat() * 0.04, 2), 2);
    	}
    	else if(randFloat <= 0.80) 
    	{
    		return new AttributeModifier(UUID.fromString("aa038f92-2b22-41c3-805d-556668182ed0"), "Resistance", 0.05 + MathUtils.roundAvoid(rand.nextFloat() * 0.04, 2), 2);
    	}
    	else if(randFloat <= 0.95) 
    	{
    		return new AttributeModifier(UUID.fromString("aa038f92-2b22-41c3-805d-556668182ed0"), "Resistance", 0.09 + MathUtils.roundAvoid(rand.nextFloat() * 0.04, 2), 2);
    	}
    	else
    	{
    		return new AttributeModifier(UUID.fromString("aa038f92-2b22-41c3-805d-556668182ed0"), "Resistance", 0.13 + MathUtils.roundAvoid(rand.nextFloat() * 0.02, 2), 2);
    	}
    }
    
    public IAttribute getMonsterAttributes()
    {
    	return GenericAttributes.RESISTANCE;
    }
	
}
