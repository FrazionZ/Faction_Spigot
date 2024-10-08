package net.minecraft.server;

import java.util.Random;

import org.bukkit.craftbukkit.event.CraftEventFactory; // CraftBukkit

public class BlockCrops extends BlockPlant implements IBlockFragilePlantElement {

    public static final BlockStateInteger AGE = BlockStateInteger.of("age", 0, 7);
    private static final AxisAlignedBB[] a = new AxisAlignedBB[] { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};

    protected BlockCrops() {
        this.w(this.blockStateList.getBlockData().set(this.e(), Integer.valueOf(0)));
        this.a(true);
        this.a((CreativeModeTab) null);
        this.c(0.0F);
        this.a(SoundEffectType.c);
        this.p();
    }

    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return BlockCrops.a[((Integer) iblockdata.get(this.e())).intValue()];
    }

    protected boolean x(IBlockData iblockdata) {
        return iblockdata.getBlock() == Blocks.FARMLAND;
    }

    protected BlockStateInteger e() {
        return BlockCrops.AGE;
    }

    public int g() {
        return 7;
    }

    protected int y(IBlockData iblockdata) {
        return ((Integer) iblockdata.get(this.e())).intValue();
    }

    public IBlockData setAge(int i) {
        return this.getBlockData().set(this.e(), Integer.valueOf(i));
    }

    public boolean z(IBlockData iblockdata) {
        return ((Integer) iblockdata.get(this.e())).intValue() >= this.g();
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        super.b(world, blockposition, iblockdata, random);
        if (world.getLightLevel(blockposition.up()) >= 9) {
            int i = this.y(iblockdata);

            if (i < this.g()) {
                float f = a((Block) this, world, blockposition);

                if (random.nextInt((int) ((100.0F / world.spigotConfig.wheatModifier) * (25.0F / f)) + 1) == 0) { // Spigot
                    // CraftBukkit start
                    IBlockData data = this.setAge(i + 1);
                    CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(data));
                    // CraftBukkit end
                }
            }
        }

    }

    public void g(World world, BlockPosition blockposition, IBlockData iblockdata, boolean isWithered) {
    	if(isWithered) {
    	
            for (int i1 = 0; i1 < 128; ++i1)
            {
            	BlockPosition blockpos1 = blockposition;
                int j1 = 0;
        		Random rand = new Random();
        		
        		while(true) {
                    if (j1 >= i1 / 16)
                    {
                        if (world.getType(blockpos1).getBlock() instanceof BlockCrops)
                        {
                            int i = this.y(world.getType(blockpos1)) + this.b(world);
                            int j = this.g();
                            
                            if (i > j)
                            {
                                i = j;
                            }
                            world.setTypeAndData(blockpos1, ((BlockCrops) world.getType(blockpos1).getBlock()).setAge(i), 2);
                        }

                        break;
                    }
                    
                    blockpos1 = blockpos1.a(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                    if (!(world.getType(blockpos1).getBlock() instanceof BlockCrops))
                    {
                        break;
                    }
                    
                    ++j1;
        		}
        		
            }
    		
    	}
    	else {
	        int i = this.y(iblockdata) + this.b(world);
	        int j = this.g();
	
	        if (i > j) {
	            i = j;
	        }
	        // CraftBukkit start
	        IBlockData data = this.setAge(i);
	        CraftEventFactory.handleBlockGrowEvent(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this, toLegacyData(data));
	        // CraftBukkit end
    	}
    }

    protected int b(World world) {
        return MathHelper.nextInt(world.random, 2, 5);
    }

    protected static float a(Block block, World world, BlockPosition blockposition) {
        float f = 1.0F;
        BlockPosition blockposition1 = blockposition.down();

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                IBlockData iblockdata = world.getType(blockposition1.a(i, 0, j));

                if (iblockdata.getBlock() == Blocks.FARMLAND) {
                    f1 = 1.0F;
                    if (((Integer) iblockdata.get(BlockSoil.MOISTURE)).intValue() > 0) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPosition blockposition2 = blockposition.north();
        BlockPosition blockposition3 = blockposition.south();
        BlockPosition blockposition4 = blockposition.west();
        BlockPosition blockposition5 = blockposition.east();
        boolean flag = block == world.getType(blockposition4).getBlock() || block == world.getType(blockposition5).getBlock();
        boolean flag1 = block == world.getType(blockposition2).getBlock() || block == world.getType(blockposition3).getBlock();

        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = block == world.getType(blockposition4.north()).getBlock() || block == world.getType(blockposition5.north()).getBlock() || block == world.getType(blockposition5.south()).getBlock() || block == world.getType(blockposition4.south()).getBlock();

            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return (world.j(blockposition) >= 8 || world.h(blockposition)) && this.x(world.getType(blockposition.down()));
    }

    protected Item h() {
        return Items.WHEAT_SEEDS;
    }

    protected Item i() {
        return Items.WHEAT;
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        super.dropNaturally(world, blockposition, iblockdata, f, 0);
        if (!world.isClientSide) {
            int j = this.y(iblockdata);

            if (j >= this.g()) {
                int k = 3 + i;

                for (int l = 0; l < k; ++l) {
                    if (world.random.nextInt(2 * this.g()) <= j) {
                        a(world, blockposition, new ItemStack(this.h()));
                    }
                }
            }

        }
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return this.z(iblockdata) ? this.i() : this.h();
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return new ItemStack(this.h());
    }

    public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
        return !this.z(iblockdata);
    }

    public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
        return true;
    }

    public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata, boolean isWithered) {
        this.g(world, blockposition, iblockdata, isWithered);
    }

    public IBlockData fromLegacyData(int i) {
        return this.setAge(i);
    }

    public int toLegacyData(IBlockData iblockdata) {
        return this.y(iblockdata);
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockCrops.AGE});
    }
}
