package net.minecraft.server.frazionz.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.server.Block;
import net.minecraft.server.BlockDirt;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.Blocks;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumInteractionResult;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.SoundCategory;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.World;
import net.minecraft.server.BlockDirt.EnumDirtVariant;
import net.minecraft.server.Item.EnumToolMaterial;

public class ItemMultiTool extends ItemTool {

    private static final Set<Block> e = Sets.newHashSet(new Block[] { 
    		Blocks.PLANKS,
    		Blocks.BOOKSHELF,
    		Blocks.LOG,
    		Blocks.LOG2,
    		Blocks.CHEST,
    		Blocks.PUMPKIN,
    		Blocks.LIT_PUMPKIN,
    		Blocks.MELON_BLOCK,
    		Blocks.LADDER,
    		Blocks.WOODEN_BUTTON,
    		Blocks.WOODEN_PRESSURE_PLATE,
    		Blocks.crimson_log,
    		Blocks.YELLITE_CHEST,
    		Blocks.FRAZION_CHEST,
    		Blocks.BAUXITE_CHEST,
    		Blocks.ONYX_CHEST,
    		Blocks.dirt_chest,
    		Blocks.YELLITE_BLOCK,
    		Blocks.YELLITE_ORE,
    		Blocks.BAUXITE_BLOCK,
    		Blocks.BAUXITE_ORE,
    		Blocks.ONYX_BLOCK,
    		Blocks.ONYX_ORE,
    		Blocks.FRAZION_BLOCK,
    		Blocks.FRAZION_ORE,
    		Blocks.OBSIDIAN_YELLITE,
    		Blocks.OBSIDIAN_BAUXITE,
    		Blocks.OBSIDIAN_ONYX,
    		Blocks.ACTIVATOR_RAIL,
    		Blocks.COAL_ORE,
    		Blocks.COBBLESTONE,
    		Blocks.DETECTOR_RAIL,
    		Blocks.DIAMOND_BLOCK,
    		Blocks.DIAMOND_ORE,
    		Blocks.DOUBLE_STONE_SLAB,
    		Blocks.GOLDEN_RAIL,
    		Blocks.GOLD_BLOCK,
    		Blocks.GOLD_ORE,
    		Blocks.ICE,
    		Blocks.IRON_BLOCK,
    		Blocks.IRON_ORE,
    		Blocks.LAPIS_BLOCK,
    		Blocks.LAPIS_ORE,
    		Blocks.LIT_REDSTONE_ORE,
    		Blocks.MOSSY_COBBLESTONE,
    		Blocks.NETHERRACK,
    		Blocks.PACKED_ICE,
    		Blocks.RAIL,
    		Blocks.REDSTONE_ORE,
    		Blocks.SANDSTONE,
    		Blocks.RED_SANDSTONE,
    		Blocks.STONE,
    		Blocks.STONE_SLAB,
    		Blocks.STONE_BUTTON,
    		Blocks.OBSIDIAN,
    		Blocks.STONE_PRESSURE_PLATE,
    		Blocks.sandstone2,
    		Blocks.stone_andesite,
    		Blocks.stone_andesite_smooth,
    		Blocks.stone_diorite,
    		Blocks.stone_diorite_smooth,
    		Blocks.stone_granite,
    		Blocks.stone_granite_smooth,
    		Blocks.BAUXITE_LADDER,
    		Blocks.YELLITE_LADDER,
    		Blocks.ONYX_LADDER,
    		Blocks.FRAZION_LADDER,
    		Blocks.RANDOM_ORE,
    		Blocks.CLAY,
    		Blocks.DIRT,
    		Blocks.FARMLAND,
    		Blocks.GRASS,
    		Blocks.GRAVEL,
    		Blocks.MYCELIUM,
    		Blocks.SAND,
    		Blocks.SNOW,
    		Blocks.SNOW_LAYER,
    		Blocks.SOUL_SAND,
    		Blocks.GRASS_PATH,
    		Blocks.dS,
    		Blocks.HAY_BLOCK,
    		Blocks.nether_wart_block2,
    		Blocks.SPONGE,
    		Blocks.AMELIORATOR,
    		Blocks.WOODEN_SLAB,
    		Blocks.DOUBLE_WOODEN_SLAB,
    		Blocks.ACACIA_STAIRS,
    		Blocks.BIRCH_STAIRS,
    		Blocks.DARK_OAK_STAIRS,
    		Blocks.JUNGLE_STAIRS,
    		Blocks.OAK_STAIRS,
    		Blocks.SPRUCE_STAIRS,
    		Blocks.FENCE,
    		Blocks.FENCE_GATE,
    		Blocks.ACACIA_FENCE,
    		Blocks.ACACIA_FENCE_GATE,
    		Blocks.BIRCH_FENCE,
    		Blocks.BIRCH_FENCE_GATE,
    		Blocks.DARK_OAK_FENCE,
    		Blocks.DARK_OAK_FENCE_GATE,
    		Blocks.JUNGLE_FENCE,
    		Blocks.JUNGLE_FENCE_GATE,
    		Blocks.NETHER_BRICK_FENCE,
    		Blocks.SPRUCE_FENCE,
    		Blocks.SPRUCE_FENCE_GATE,
    		Blocks.CHEST,
    		Blocks.TRAPDOOR,
    		Blocks.TRAPPED_CHEST,
    		Blocks.WEB,
    		Blocks.ACACIA_DOOR,
    		Blocks.BIRCH_DOOR,
    		Blocks.DARK_OAK_DOOR,
    		Blocks.JUNGLE_DOOR,
    		Blocks.WOODEN_DOOR,
    		Blocks.SPRUCE_DOOR,
    		Blocks.STANDING_SIGN,
    		Blocks.WALL_SIGN,
    		Blocks.RENFORCED_SAND,
    		Blocks.HOPPER,
    		Blocks.Z_HOPPER,
    		Blocks.CRAFTING_TABLE,
    		Blocks.TROPHY_FORGE,
    		Blocks.dg
    		
    });

    private static final Set<Block> HARVEST_LEVEL_1 = Sets.newHashSet(Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE);
    private static final Set<Block> HARVEST_LEVEL_2 = Sets.newHashSet(Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.EMERALD_BLOCK, Blocks.EMERALD_ORE, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE);
    private static final Set<Block> HARVEST_LEVEL_3 = Sets.newHashSet(Blocks.OBSIDIAN, Blocks.YELLITE_ORE, Blocks.YELLITE_BLOCK, Blocks.YELLITE_FURNACE, Blocks.LIT_YELLITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_4 = Sets.newHashSet(Blocks.OBSIDIAN_YELLITE, Blocks.BAUXITE_ORE, Blocks.BAUXITE_BLOCK, Blocks.BAUXITE_FURNACE, Blocks.LIT_BAUXITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_5 = Sets.newHashSet(Blocks.OBSIDIAN_BAUXITE, Blocks.RANDOM_ORE, Blocks.ONYX_ORE, Blocks.ONYX_BLOCK, Blocks.ONYX_FURNACE, Blocks.LIT_ONYX_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_6 = Sets.newHashSet(Blocks.OBSIDIAN_ONYX, Blocks.TROPHY_FORGE, Blocks.AMELIORATOR, Blocks.FRAZION_ORE, Blocks.FRAZION_BLOCK, Blocks.FRAZION_FURNACE, Blocks.LIT_FRAZION_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_7 = Sets.newHashSet(Blocks.OBSIDIAN_FRAZION);
    
    
    public ItemMultiTool(Item.EnumToolMaterial item_enumtoolmaterial) {
        super(1.00F, item_enumtoolmaterial, ItemMultiTool.e);
    }

    public boolean canDestroySpecialBlock(IBlockData iblockdata) {

        Block block = iblockdata.getBlock();
        
        if(HARVEST_LEVEL_1.contains(block)) {
            return this.d.d() >= 1;
        }
        else if(HARVEST_LEVEL_2.contains(block)) {
            return this.d.d() >= 2;
        }
        else if(HARVEST_LEVEL_3.contains(block)) {
            return this.d.d() >= 3;
        }
        else if(HARVEST_LEVEL_4.contains(block)) {
            return this.d.d() >= 4;
        }
        else if(HARVEST_LEVEL_5.contains(block)) {
            return this.d.d() >= 5;
        }
        else if(HARVEST_LEVEL_6.contains(block)) {
            return this.d.d() >= 6;
        }
        else if(HARVEST_LEVEL_7.contains(block)) {
            return this.d.d() >= 7;
        }
        else {
            Material material = iblockdata.getMaterial();
            return material == Material.STONE ? true : (material == Material.ORE ? true : material == Material.HEAVY);
        }
    }

    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        Material material = iblockdata.getMaterial();

        if(this.d.d() >= 7) {
            Block block = iblockdata.getBlock();
            
            if (block == Blocks.OBSIDIAN || block == Blocks.OBSIDIAN_YELLITE || block == Blocks.OBSIDIAN_BAUXITE || block == Blocks.OBSIDIAN_ONYX || block == Blocks.OBSIDIAN_FRAZION)
            	return 90.0F;
            else
                return material != Material.ORE && material != Material.HEAVY && material != Material.STONE ? super.getDestroySpeed(itemstack, iblockdata) : this.a;

        }
        return material != Material.ORE && material != Material.HEAVY && material != Material.STONE ? super.getDestroySpeed(itemstack, iblockdata) : this.a;
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
    
    protected void a(ItemStack itemstack, EntityHuman entityhuman, World world, BlockPosition blockposition, IBlockData iblockdata) {
        world.a(entityhuman, blockposition, SoundEffects.cE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        if (!world.isClientSide) {
            world.setTypeAndData(blockposition, iblockdata, 11);
            itemstack.damage(1, entityhuman);
        }
    }
    
    @Override
    public boolean a(ItemStack breaker, World w, IBlockData state, BlockPosition pos, EntityLiving e)
    {
    	
    	if(this.d.d() >= 7) {
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
                    	removeItems(p, new ItemStack(Items.CARROT), 1);
                    	
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.POTATOES && p.inventory.h(new ItemStack(Items.POTATO)))
                    {
                    	w.setTypeUpdate(pos, Blocks.POTATOES.getBlockData());
                    	removeItems(p, new ItemStack(Items.POTATO), 1);
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.NETHER_WART && p.inventory.h(new ItemStack(Items.NETHER_WART)))
                    {
                    	w.setTypeUpdate(pos, Blocks.NETHER_WART.getBlockData());
                    	removeItems(p, new ItemStack(Items.NETHER_WART), 1);
                    	
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.WHEAT && p.inventory.h(new ItemStack(Items.WHEAT_SEEDS)))
                    {
                    	w.setTypeUpdate(pos, Blocks.WHEAT.getBlockData());
                    	removeItems(p, new ItemStack(Items.WHEAT_SEEDS), 1);
                    	
                    	return true;
                    }
                    
                    else if(state.getBlock() == Blocks.BEETROOT && p.inventory.h(new ItemStack(Items.BEETROOT_SEEDS)))
                    {
                    	w.setTypeUpdate(pos, Blocks.BEETROOT.getBlockData());
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
            else
            	continue;
        }
    }
    
}
