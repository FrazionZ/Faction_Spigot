package net.minecraft.server.frazionz.blocks;

import net.minecraft.server.*;

public class BlockStrawberries extends BlockCrops {

    private static final AxisAlignedBB[] STAGE_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.1875, 0.9D), new AxisAlignedBB(0.1D, 0.1D, 0.0D, 0.9D, 0.375D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.4375D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D), new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.625D, 0.9D)};

    public BlockStrawberries() {}

    protected Item h() {
        return Items.STRAWBERRY;
    }

    protected Item i() {
        return Items.STRAWBERRY;
    }

    @Override
    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        if(!world.isClientSide) {
            if (this.z(iblockdata)) {
                entityhuman.a(enumhand);
                world.setTypeAndData(blockposition, this.setAge(3), 2);
                this.a(world, entityhuman, blockposition, iblockdata, null, entityhuman.getItemInMainHand());
                return true;
            }
        }
        return false;
    }

    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return STAGE_AABB[((Integer) iblockdata.get(this.e())).intValue()];
    }
}
