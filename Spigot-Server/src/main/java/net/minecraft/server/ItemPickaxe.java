package net.minecraft.server;

import java.util.Set;

import com.google.common.collect.Sets;

public class ItemPickaxe extends ItemTool {

    private static final Set<Block> e = Sets.newHashSet(new Block[] { Blocks.ACTIVATOR_RAIL,
    		Blocks.YELLITE_CHEST,
    		Blocks.FRAZION_CHEST,
    		Blocks.BAUXITE_CHEST,
    		Blocks.ONYX_CHEST,
    		Blocks.DIRT_CHEST,
    		Blocks.YELLITE_BLOCK,
    		Blocks.YELLITE_ORE,
    		Blocks.BAUXITE_BLOCK,
    		Blocks.BAUXITE_ORE,
    		Blocks.ONYX_BLOCK,
    		Blocks.OBSIDIAN_YELLITE,
    		Blocks.OBSIDIAN_BAUXITE,
    		Blocks.OBSIDIAN_ONYX,
    		Blocks.ONYX_ORE,
    		Blocks.FRAZION_BLOCK,
    		Blocks.FRAZION_ORE,
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
    		Blocks.SANDSTONE2,
    		Blocks.STONE_ANDESITE,
    		Blocks.STONE_ANDESITE_SMOOTH,
    		Blocks.STONE_DIORITE,
    		Blocks.STONE_DIORITE_SMOOTH,
    		Blocks.STONE_GRANITE,
    		Blocks.STONE_GRANITE_SMOOTH,
    		Blocks.BAUXITE_LADDER,
    		Blocks.YELLITE_LADDER,
    		Blocks.ONYX_LADDER,
    		Blocks.FRAZION_LADDER,
    		Blocks.RANDOM_ORE,
    		Blocks.AMELIORATOR,
    		Blocks.TROPHY_FORGE
    		});

    private static final Set<Block> HARVEST_LEVEL_1 = Sets.newHashSet(Blocks.IRON_ORE, Blocks.IRON_BLOCK, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE);
    private static final Set<Block> HARVEST_LEVEL_2 = Sets.newHashSet(Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.EMERALD_BLOCK, Blocks.EMERALD_ORE, Blocks.REDSTONE_BLOCK, Blocks.REDSTONE_ORE, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE);
    private static final Set<Block> HARVEST_LEVEL_3 = Sets.newHashSet(Blocks.OBSIDIAN, Blocks.YELLITE_ORE, Blocks.YELLITE_BLOCK, Blocks.YELLITE_FURNACE, Blocks.LIT_YELLITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_4 = Sets.newHashSet(Blocks.OBSIDIAN_YELLITE, Blocks.BAUXITE_ORE, Blocks.BAUXITE_BLOCK, Blocks.BAUXITE_FURNACE, Blocks.LIT_BAUXITE_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_5 = Sets.newHashSet(Blocks.OBSIDIAN_BAUXITE, Blocks.RANDOM_ORE, Blocks.ONYX_ORE, Blocks.ONYX_BLOCK, Blocks.ONYX_FURNACE, Blocks.LIT_ONYX_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_6 = Sets.newHashSet(Blocks.OBSIDIAN_ONYX, Blocks.TROPHY_FORGE, Blocks.AMELIORATOR, Blocks.FRAZION_ORE, Blocks.FRAZION_BLOCK, Blocks.FRAZION_FURNACE, Blocks.LIT_FRAZION_FURNACE);
    private static final Set<Block> HARVEST_LEVEL_7 = Sets.newHashSet(Blocks.OBSIDIAN_FRAZION);
    
    
    public ItemPickaxe(Item.EnumToolMaterial item_enumtoolmaterial) {
        super(1.25F, item_enumtoolmaterial, ItemPickaxe.e);
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
            
            if (block == Blocks.OBSIDIAN || block == Blocks.OBSIDIAN_YELLITE || block == Blocks.OBSIDIAN_BAUXITE)
            	return 80.0F;
            else if(block == Blocks.OBSIDIAN_ONYX || block == Blocks.OBSIDIAN_FRAZION)
            	return 50.0F;
            else
                return material != Material.ORE && material != Material.HEAVY && material != Material.STONE ? super.getDestroySpeed(itemstack, iblockdata) : this.a;

        }
        return material != Material.ORE && material != Material.HEAVY && material != Material.STONE ? super.getDestroySpeed(itemstack, iblockdata) : this.a;
    }
}
