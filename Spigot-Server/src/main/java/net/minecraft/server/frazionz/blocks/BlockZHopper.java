package net.minecraft.server.frazionz.blocks;

import com.google.common.base.Predicate;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateBoolean;
import net.minecraft.server.BlockStateDirection;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.BlockTileEntity;
import net.minecraft.server.Container;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumBlockFaceShape;
import net.minecraft.server.EnumBlockMirror;
import net.minecraft.server.EnumBlockRotation;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumRenderType;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.InventoryUtils;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.MaterialMapColor;
import net.minecraft.server.StatisticList;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityZHopper;
import net.minecraft.server.World;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionBlockType;

import java.util.List;
import javax.annotation.Nullable;

import org.bukkit.frazionz.enums.ExplosionBlockType;

public class BlockZHopper extends BlockTileEntity implements FzExplosionBlockType {

    public static final BlockStateDirection FACING = BlockStateDirection.of("facing", new Predicate() {
        public boolean a(@Nullable EnumDirection enumdirection) {
            return enumdirection != EnumDirection.UP;
        }

        public boolean apply(@Nullable Object object) {
            return this.a((EnumDirection) object);
        }
    });
    public static final BlockStateBoolean ENABLED = BlockStateBoolean.of("enabled");
    protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D);
    protected static final AxisAlignedBB d = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
    protected static final AxisAlignedBB e = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB f = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    protected static final AxisAlignedBB g = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

    public BlockZHopper() {
        super(Material.ORE, MaterialMapColor.n);
        this.w(this.blockStateList.getBlockData().set(BlockZHopper.FACING, EnumDirection.DOWN).set(BlockZHopper.ENABLED, Boolean.valueOf(true)));
        this.a(CreativeModeTab.d);
    }

    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return BlockZHopper.j;
    }

    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable Entity entity, boolean flag) {
        a(blockposition, axisalignedbb, list, BlockZHopper.c);
        a(blockposition, axisalignedbb, list, BlockZHopper.g);
        a(blockposition, axisalignedbb, list, BlockZHopper.f);
        a(blockposition, axisalignedbb, list, BlockZHopper.d);
        a(blockposition, axisalignedbb, list, BlockZHopper.e);
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        EnumDirection enumdirection1 = enumdirection.opposite();

        if (enumdirection1 == EnumDirection.UP) {
            enumdirection1 = EnumDirection.DOWN;
        }

        return this.getBlockData().set(BlockZHopper.FACING, enumdirection1).set(BlockZHopper.ENABLED, Boolean.valueOf(true));
    }

    public TileEntity a(World world, int i) {
        return new TileEntityZHopper();
    }

    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        super.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
        if (itemstack.hasName()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityZHopper) {
                ((TileEntityZHopper) tileentity).setCustomName(itemstack.getName());
            }
        }

    }

    public boolean k(IBlockData iblockdata) {
        return true;
    }

    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.e(world, blockposition, iblockdata);
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isClientSide) {
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityZHopper) {
                entityhuman.openContainer((TileEntityZHopper) tileentity);
                entityhuman.b(StatisticList.P);
            }

            return true;
        }
    }

    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
        this.e(world, blockposition, iblockdata);
    }

    private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
        boolean flag = !world.isBlockIndirectlyPowered(blockposition);

        if (flag != ((Boolean) iblockdata.get(BlockZHopper.ENABLED)).booleanValue()) {
            world.setTypeAndData(blockposition, iblockdata.set(BlockZHopper.ENABLED, Boolean.valueOf(flag)), 4);
        }

    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityZHopper) {
            InventoryUtils.dropInventory(world, blockposition, (TileEntityZHopper) tileentity);
            world.updateAdjacentComparators(blockposition, this);
        }

        super.remove(world, blockposition, iblockdata);
    }

    public EnumRenderType a(IBlockData iblockdata) {
        return EnumRenderType.MODEL;
    }

    public boolean c(IBlockData iblockdata) {
        return false;
    }

    public boolean b(IBlockData iblockdata) {
        return false;
    }

    public static EnumDirection b(int i) {
        return EnumDirection.fromType1(i & 7);
    }

    public static boolean f(int i) {
        return (i & 8) != 8;
    }

    public boolean isComplexRedstone(IBlockData iblockdata) {
        return true;
    }

    public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return Container.a(world.getTileEntity(blockposition));
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockZHopper.FACING, b(i)).set(BlockZHopper.ENABLED, Boolean.valueOf(f(i)));
    }

    public int toLegacyData(IBlockData iblockdata) {
        byte b0 = 0;
        int i = b0 | ((EnumDirection) iblockdata.get(BlockZHopper.FACING)).a();

        if (!((Boolean) iblockdata.get(BlockZHopper.ENABLED)).booleanValue()) {
            i |= 8;
        }

        return i;
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return iblockdata.set(BlockZHopper.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockZHopper.FACING)));
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockZHopper.FACING)));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockZHopper.FACING, BlockZHopper.ENABLED});
    }

    public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
        return enumdirection == EnumDirection.UP ? EnumBlockFaceShape.BOWL : EnumBlockFaceShape.UNDEFINED;
    }

	@Override
	public ExplosionBlockType getExplosionBlockType() {
		return ExplosionBlockType.Z_HOPPER;
	}
}
