package net.minecraft.server.frazionz.items;

import com.google.common.collect.Sets;

import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ItemTool;
import net.minecraft.server.Material;
import net.minecraft.server.Item.EnumToolMaterial;

import java.util.Set;

public class ItemSpawnerPickaxe extends ItemTool {

    private static final Set<Block> e = Sets.newHashSet(new Block[] {
    		Blocks.MOB_SPAWNER
    		});

    public ItemSpawnerPickaxe() {
        super(0.8F, Item.EnumToolMaterial.SPAWNER_PICKAXE, ItemSpawnerPickaxe.e);
    }

    public boolean canDestroySpecialBlock(IBlockData iblockdata) {
            return this.d.d() >= 3;
    }

    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        Material material = iblockdata.getMaterial();

        return material != Material.ORE && material != Material.HEAVY && material != Material.STONE ? super.getDestroySpeed(itemstack, iblockdata) : this.a;
    }
    
}
