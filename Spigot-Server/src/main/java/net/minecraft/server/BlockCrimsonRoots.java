package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockCrimsonRoots extends BlockPlant {

    protected static final AxisAlignedBB a = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    public BlockCrimsonRoots() {
        super(Material.REPLACEABLE_PLANT);
    }

    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return BlockCrimsonRoots.a;
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return MaterialMapColor.p;
    }

    protected boolean x(IBlockData iblockdata) {
        return iblockdata.getBlock() == Blocks.NETHERRACK;
    }

    public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition)
    {
        return true;
    }

    public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
        if (!world.isClientSide && itemstack.getItem() == Items.SHEARS) {
            entityhuman.b(StatisticList.a((Block) this));
            a(world, blockposition, new ItemStack(Blocks.CRIMSON_ROOTS, 1, 0));
        } else {
            super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
        }

    }
}
