package net.minecraft.server.frazionz.items;

import java.util.List;

import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumInteractionResult;
import net.minecraft.server.InteractionResultWrapper;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.World;

public class ItemBottleXP extends Item {

	private int minLevel = 30;
	private int maxLevel = 1000;
	
	public ItemBottleXP() {
		this.b(CreativeModeTab.f);
	}
	
	@Override
	public InteractionResultWrapper<ItemStack> a(World world, EntityHuman player, EnumHand hand) {
		
		ItemStack itemstack = player.b(hand);
		if(hand.equals(EnumHand.MAIN_HAND)) {
		
			if(itemstack.getTag() != null) {
				
				int level = itemstack.getTag().getInt("bottleLevel");
				
		        player.a(SoundEffects.fI, 0.5F, 1.0F);
		        player.addExperienceLevel(level, true);
		        
		        if (!player.abilities.canInstantlyBuild)
		        {
		            itemstack.subtract(1);
		        }
		        return new InteractionResultWrapper<ItemStack>(EnumInteractionResult.SUCCESS, itemstack);
			}
			return new InteractionResultWrapper<ItemStack>(EnumInteractionResult.FAIL, itemstack);
		}
		return new InteractionResultWrapper<ItemStack>(EnumInteractionResult.FAIL, itemstack);
	}
	
    public int getMinLevel() {
		return minLevel;
	}
    
    public int getMaxLevel() {
		return maxLevel;
	}
	
}
