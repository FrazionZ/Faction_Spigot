package net.minecraft.server.frazionz.blocks;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.MaterialMapColor;
import net.minecraft.server.MathHelper;
import net.minecraft.server.World;

public class BlockBauxiteOre extends Block {

    public BlockBauxiteOre() {
        this(Material.STONE.r());
    }

    public BlockBauxiteOre(MaterialMapColor materialmapcolor) {
        super(Material.STONE, materialmapcolor);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Items.BAUXITE;
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        super.dropNaturally(world, blockposition, iblockdata, f, i);
        
        if (this.getDropType(iblockdata, world.random, i) != Item.getItemOf(this)) {
            int j = 0;
            
                j = MathHelper.nextInt(world.random, 2, 5);

            this.dropExperience(world, blockposition, j);
        }

    }

    @Override
    public int getExpDrop(World world, IBlockData iblockdata, int i) {
        if (this.getDropType(iblockdata, world.random, i) != Item.getItemOf(this)) {
            int j = 0;
            
                j = MathHelper.nextInt(world.random, 2, 5);

            return j;
        }

        return 0;
    }
}
