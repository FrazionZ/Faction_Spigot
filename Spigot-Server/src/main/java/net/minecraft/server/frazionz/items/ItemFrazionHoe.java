package net.minecraft.server.frazionz.items;

import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import net.minecraft.server.AttributeModifier;
import net.minecraft.server.Block;
import net.minecraft.server.BlockDirt;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.Blocks;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumInteractionResult;
import net.minecraft.server.EnumItemSlot;
import net.minecraft.server.GenericAttributes;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.SoundCategory;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.World;

public class ItemFrazionHoe extends ItemTool {

    protected Item.EnumToolMaterial a;

    private static final Set<Block> e = Sets.newHashSet(new Block[] { 
    		Blocks.HAY_BLOCK,
    		Blocks.NETHER_WART_BLOCK2,
    		Blocks.SPONGE,
    		Blocks.dg
    		
    });
    
    public ItemFrazionHoe(Item.EnumToolMaterial item_enumtoolmaterial) {
    	
    	super(0.0F, item_enumtoolmaterial, ItemFrazionHoe.e);
    	
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (!entityhuman.a(blockposition.shift(enumdirection), enumdirection, itemstack)) {
            return EnumInteractionResult.FAIL;
        } else {
            IBlockData iblockdata = world.getType(blockposition);
            Block block = iblockdata.getBlock();

            if (enumdirection != EnumDirection.DOWN && world.getType(blockposition.up()).getMaterial() == Material.AIR) {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
                    this.a(itemstack, entityhuman, world, blockposition, Blocks.FARMLAND.getBlockData());
                    return EnumInteractionResult.SUCCESS;
                }

                if (block == Blocks.DIRT) {
                    switch ((BlockDirt.EnumDirtVariant) iblockdata.get(BlockDirt.VARIANT)) {
                    case DIRT:
                        this.a(itemstack, entityhuman, world, blockposition, Blocks.FARMLAND.getBlockData());
                        return EnumInteractionResult.SUCCESS;

                    case COARSE_DIRT:
                        this.a(itemstack, entityhuman, world, blockposition, Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT));
                        return EnumInteractionResult.SUCCESS;
                    }
                }
            }

            return EnumInteractionResult.PASS;
        }
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        itemstack.damage(1, entityliving1);
        return true;
    }

    protected void a(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, IBlockData iblockdata) {
        world.a(entityhuman, blockposition, SoundEffects.cE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (!world.isClientSide) {
            world.setTypeAndData(blockposition, iblockdata, 11);
            itemstack.damage(1, entityhuman);
        }
    }

    public String g() {
        return this.a.toString();
    }
    
    
    @Override
    public boolean a(ItemStack breaker, World w, IBlockData state, BlockPosition pos, EntityLiving e)
    {
    	
    	if(state.getBlock() == Blocks.CARROTS || state.getBlock() == Blocks.POTATOES || state.getBlock() == Blocks.WHEAT || state.getBlock() == Blocks.NETHER_WART || state.getBlock() == Blocks.BEETROOT) 
    	{
    		
        	if (e instanceof EntityHuman && !w.isClientSide)
            {
                EntityHuman p = (EntityHuman) e;
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();

                if(state.getBlock() == Blocks.CARROTS && p.inventory.h(new ItemStack(Items.CARROT)))
                {
                	w.setTypeUpdate(pos, Blocks.CARROTS.getBlockData());
                    breaker.damage(1, e);
                	removeItems(p, new ItemStack(Items.CARROT), 1);
                	
                	return true;
                }
                
                else if(state.getBlock() == Blocks.POTATOES && p.inventory.h(new ItemStack(Items.POTATO)))
                {
                	w.setTypeUpdate(pos, Blocks.POTATOES.getBlockData());
                    breaker.damage(1, e);
                	removeItems(p, new ItemStack(Items.POTATO), 1);
                	return true;
                }
                
                else if(state.getBlock() == Blocks.NETHER_WART && p.inventory.h(new ItemStack(Items.NETHER_WART)))
                {
                	w.setTypeUpdate(pos, Blocks.NETHER_WART.getBlockData());
                    breaker.damage(1, e);
                	removeItems(p, new ItemStack(Items.NETHER_WART), 1);
                	
                	return true;
                }
                
                else if(state.getBlock() == Blocks.WHEAT && p.inventory.h(new ItemStack(Items.WHEAT_SEEDS)))
                {
                	w.setTypeUpdate(pos, Blocks.WHEAT.getBlockData());
                    breaker.damage(1, e);
                	removeItems(p, new ItemStack(Items.WHEAT_SEEDS), 1);
                	
                	return true;
                }
                
                else if(state.getBlock() == Blocks.BEETROOT && p.inventory.h(new ItemStack(Items.BEETROOT_SEEDS)))
                {
                	w.setTypeUpdate(pos, Blocks.BEETROOT.getBlockData());
                    breaker.damage(1, e);
                	removeItems(p, new ItemStack(Items.BEETROOT_SEEDS), 1);
                	
                	return true;
                }
                
                else {
                	return super.a(breaker, w, state, pos, e);
                }
            }
        	
    		
    	}
    	
        return super.a(breaker, w, state, pos, e);
    }
    
    
    
    public void removeItems(EntityHuman player , ItemStack stack, int amount) {
    	
    	
        int size = player.inventory.getSize();
        
        for (int slot = 0; slot < size; slot++) 
        {
            ItemStack is = player.inventory.getItem(slot);        
            
            if (is.getItem() == stack.getItem()) 
            {
                int newAmount = is.getCount() - amount;
                
                if (newAmount > 0)
                {
                    is.setCount(newAmount);
                    break;
                }
                else 
                {
                    player.inventory.splitWithoutUpdate(slot);
                    amount = -newAmount;
                    if (amount == 0) 
                    {
                    	break;
                    }
                }
            }
            
            else {
            	
            	continue;
            	
            }
        }
    }
    

    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        Multimap multimap = super.a(enumitemslot);

        if (enumitemslot == EnumItemSlot.MAINHAND) {
            multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ItemFrazionHoe.h, "Weapon modifier", 0.0D, 0));
        }

        return multimap;
    }
}
