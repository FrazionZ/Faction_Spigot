package net.minecraft.server.frazionz.items;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.Blocks;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import net.minecraft.server.Item.EnumToolMaterial;



public class ItemHammer extends ItemTool {

    private static final Set<Block> e = Sets.newHashSet(new Block[] { Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE});

    private static final Set<Block> No = Sets.newHashSet(new Block[] {
    		Blocks.OBSIDIAN,
    		Blocks.OBSIDIAN_BAUXITE,
    		Blocks.OBSIDIAN_FRAZION,
    		Blocks.OBSIDIAN_ONYX,
    		Blocks.OBSIDIAN_YELLITE,
    		Blocks.BEDROCK,
    		Blocks.WATER,
    		Blocks.LAVA,
    		Blocks.FLOWING_LAVA, 
    		Blocks.FLOWING_WATER,
    		Blocks.CHEST, 
    		Blocks.YELLITE_CHEST,
    		Blocks.BAUXITE_CHEST,
    		Blocks.ONYX_CHEST,
    		Blocks.FRAZION_CHEST,
    		Blocks.FURNACE,
    		Blocks.LIT_FURNACE, 
    		Blocks.BAUXITE_FURNACE,
    		Blocks.LIT_BAUXITE_FURNACE,
    		Blocks.YELLITE_FURNACE,
    		Blocks.LIT_YELLITE_FURNACE,
    		Blocks.FRAZION_FURNACE, 
    		Blocks.LIT_FRAZION_FURNACE,
    		Blocks.ONYX_FURNACE,
    		Blocks.LIT_ONYX_FURNACE
    });

    
    public ItemHammer(Item.EnumToolMaterial item_enumtoolmaterial) {
        super(1.0F, item_enumtoolmaterial, ItemHammer.e);
    }

    public boolean canDestroySpecialBlock(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        if (block == Blocks.OBSIDIAN) {
            return this.d.d() == 3;
        } else if (block != Blocks.DIAMOND_BLOCK && block != Blocks.DIAMOND_ORE) {
            if (block != Blocks.EMERALD_ORE && block != Blocks.EMERALD_BLOCK) {
                if (block != Blocks.GOLD_BLOCK && block != Blocks.GOLD_ORE) {
                    if (block != Blocks.IRON_BLOCK && block != Blocks.IRON_ORE) {
                        if (block != Blocks.LAPIS_BLOCK && block != Blocks.LAPIS_ORE) {
                            if (block != Blocks.REDSTONE_ORE && block != Blocks.LIT_REDSTONE_ORE) {
                                Material material = iblockdata.getMaterial();

                                return material == Material.STONE ? true : (material == Material.ORE ? true : material == Material.HEAVY);
                            } else {
                                return this.d.d() >= 2;
                            }
                        } else {
                            return this.d.d() >= 1;
                        }
                    } else {
                        return this.d.d() >= 1;
                    }
                } else {
                    return this.d.d() >= 2;
                }
            } else {
                return this.d.d() >= 2;
            }
        } else {
            return this.d.d() >= 2;
        }
    }

    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        Material material = iblockdata.getMaterial();

        return material != Material.ORE && material != Material.HEAVY && material != Material.STONE ? super.getDestroySpeed(itemstack, iblockdata) : this.a;
    }
    
    public boolean a(ItemStack breaker, World w, IBlockData state, BlockPosition pos, EntityLiving e)
    {
    	
    	if(state.getBlock().a(state, w, pos) <= 0.2F) {
    		return false;
    	}
    	
    	else if (e instanceof EntityPlayer && !w.isClientSide)
        {
            EntityPlayer p = (EntityPlayer) e;
            breaker.damage(1, e);
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                
                if( (-321 <= x && x < 321) && ( -321 < z && z < 321) ) {
                    this.destroyAndDropBlock(w, p, breaker, x , y , z , state);
                }
                
                else {
                	
                    for(int x1 = -1; x1 < 2; x1++)
                    {
                        for(int y1 = -1; y1 < 2; y1++)
                        {
                            for(int z1 = -1; z1 < 2; z1++)
                            {
                                this.destroyAndDropBlock(w, p, breaker, x + x1, y + y1, z + z1 , state);
                            }
                        }
                    }
                	
                }
                
                
                return true;
        }
        return super.a(breaker, w, state, pos, e);
    }
    private void destroyAndDropBlock(World w, EntityPlayer p, ItemStack breaker, int x, int y, int z, IBlockData state)
    {
        BlockPosition pos = new BlockPosition(x, y, z);
        
        
        float f = state.b(w, pos);
        if(f >= 0.0D) {
        	if(!No.contains(w.getType(pos).getBlock()))
        	{
        		
                w.getType(pos).getBlock().a(w, p, pos, w.getType(pos), w.getTileEntity(pos), breaker);
                w.setAir(pos);
        		
        	}

        	
        }
        
    }
    
}
