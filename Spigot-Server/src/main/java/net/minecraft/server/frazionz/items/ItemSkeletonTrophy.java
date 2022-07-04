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

public class ItemSkeletonTrophy extends ItemTrophy {
	
	public ItemSkeletonTrophy() {
		super();
	}
    
    public AttributeModifier getRandomAttributeModifier() {
    	Random rand = new Random();
    	float randFloat = rand.nextFloat();
    	if(randFloat <= 0.5)
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Speed", 0.01 + MathUtils.roundAvoid(rand.nextDouble() * 0.10, 2), 2);
    	}
    	else if(randFloat <= 0.80) 
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Speed", 0.11 + MathUtils.roundAvoid(rand.nextDouble() * 0.10, 2), 2);
    	}
    	else if(randFloat <= 0.95) 
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Speed", 0.21 + MathUtils.roundAvoid(rand.nextDouble() * 0.10, 2), 2);
    	}
    	else
    	{
    		return new AttributeModifier(UUID.fromString("81ef1173-6068-4c9a-88db-5df409734476"), "Speed", 0.31 + MathUtils.roundAvoid(rand.nextDouble() * 0.04, 2), 2);
    	}
    }
    
    public IAttribute getMonsterAttributes()
    {
    	return GenericAttributes.MOVEMENT_SPEED;
    }
	
}
