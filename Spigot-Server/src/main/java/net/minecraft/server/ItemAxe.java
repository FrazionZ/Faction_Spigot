package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Set;

public class ItemAxe extends ItemTool {

    private static final Set<Block> e = Sets.newHashSet(new Block[] { Blocks.crimson_log, Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE});

    protected ItemAxe(Item.EnumToolMaterial item_enumtoolmaterial) {
    	super(2.0F, item_enumtoolmaterial, ItemAxe.e);
    }

    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        Material material = iblockdata.getMaterial();

        return material != Material.WOOD && material != Material.PLANT && material != Material.REPLACEABLE_PLANT ? super.getDestroySpeed(itemstack, iblockdata) : this.a;
    }
}
