package net.minecraft.server;

import java.util.Random;


public class BlockRandomOre extends Block {

	private Random random = new Random();
	
    public BlockRandomOre() {
        this(Material.STONE.r());
    }

    public BlockRandomOre(MaterialMapColor materialmapcolor) {
        super(Material.STONE, materialmapcolor);
        this.a(CreativeModeTab.b);
    }

    public Item getDropType(IBlockData iblockdata, Random rand, int i) {
    	
    	float i1 = random.nextFloat();
    	
    	if(i1 < 0.00010F) { // 0.01%
    		return Items.COSMIC_NUGGET;
    	}
    	else if (i1 < 0.0060F) // 0.5%
        {
            return Items.COSMIC_POWDER;
        }
        else if (i1 < 0.016F) // 1%
        {
            return Items.FRAZION_POWDER;
        }
        else if (i1 < 0.116F) // 10%
        {
            return Items.ONYX; 
        }
        else if (i1 < 0.466F) // 35%
        {
            return Items.BAUXITE;
        }
        else if (i1 < 1.0F) // 53,4%
        {
            return Items.YELLITE;
        }
        else
        {
            return Items.YELLITE;
        }
    }
    
    public int getDropCount(int i, Random random) {
        if (i > 0 && Item.getItemOf(this) != this.getDropType((IBlockData) this.s().a().iterator().next(), random, i)) {
            int j = random.nextInt(i + 2) - 1;

            if (j < 0) {
                j = 0;
            }

            return this.a(random) * (j + 1);
        } else {
            return this.a(random);
        }
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        super.dropNaturally(world, blockposition, iblockdata, f, i);
        /*CraftBukkit start - Delegated to getExpDrop*/
        if (this.getDropType(iblockdata, world.random, i) != Item.getItemOf(this)) {
            int j = 0;

                j = MathHelper.nextInt(world.random, 3, 7);


            this.dropExperience(world, blockposition, j);
        }

    }

    @Override
    public int getExpDrop(World world, IBlockData iblockdata, int i) {
        if (this.getDropType(iblockdata, world.random, i) != Item.getItemOf(this)) {
            int j = 0;

                j = MathHelper.nextInt(world.random, 3, 7);

            return j;
        }

        return 0;
        // CraftBukkit end
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return new ItemStack(this);
    }
}
