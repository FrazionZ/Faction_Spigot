package net.minecraft.server;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockCristal extends BlockHalfTransparent {

    public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    protected static final AxisAlignedBB X_AXIS_AABB = new AxisAlignedBB(0.22D, 0.0D, 0.4D, 0.78D, 1.3D, 0.6D);
    protected static final AxisAlignedBB Z_AXIS_AABB = new AxisAlignedBB(0.4D, 0.0D, 0.22D, 0.6D, 1.3D, 0.78D);
    protected static final Logger e = LogManager.getLogger();
	
    public BlockCristal(Material material, boolean flag) {
        super(material, flag);
        this.w(this.blockStateList.getBlockData().set(BlockCristal.FACING, EnumDirection.NORTH));
        this.e(0);
        this.a(CreativeModeTab.b);
    }

    public int a(Random random) {
        return 0;
    }

    public boolean c(IBlockData iblockdata) {
        return false;
    }

    protected boolean n() {
        return true;
    }
    
    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        EnumDirection enumdirection1 = entityliving.getDirection().e();

        try {
            return super.getPlacedState(world, blockposition, enumdirection, f, f1, f2, i, entityliving).set(BlockCristal.FACING, enumdirection1);
        } catch (IllegalArgumentException illegalargumentexception) {
            if (!world.isClientSide) {
                BlockCristal.e.warn(String.format("Invalid damage property for anvil at %s. Found %d, must be in [0, 1, 2]", new Object[] { blockposition, Integer.valueOf(i >> 2)}));
                if (entityliving instanceof EntityHuman) {
                    entityliving.sendMessage(new ChatMessage("Invalid damage property. Please pick in [0, 1, 2]", new Object[0]));
                }
            }

            return super.getPlacedState(world, blockposition, enumdirection, f, f1, f2, 0, entityliving).set(BlockCristal.FACING, enumdirection1);
        }
    }
    
    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockCristal.FACING);

        return enumdirection.k() == EnumDirection.EnumAxis.X ? BlockCristal.X_AXIS_AABB : BlockCristal.Z_AXIS_AABB;
    }
    
    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockCristal.FACING, EnumDirection.fromType2(i & 3));
    }
    
    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumDirection) iblockdata.get(BlockCristal.FACING)).get2DRotationValue();

        return i;
    }
    
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return iblockdata.getBlock() != this ? iblockdata : iblockdata.set(BlockCristal.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockCristal.FACING)));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockCristal.FACING});
    }
    
}
