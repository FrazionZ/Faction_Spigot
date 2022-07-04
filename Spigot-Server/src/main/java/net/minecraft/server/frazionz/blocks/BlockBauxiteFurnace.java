package net.minecraft.server.frazionz.blocks;

import java.util.Random;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.BlockFacingHorizontal;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateDirection;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.BlockTileEntity;
import net.minecraft.server.Blocks;
import net.minecraft.server.Container;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumBlockMirror;
import net.minecraft.server.EnumBlockRotation;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumRenderType;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.InventoryUtils;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.StatisticList;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityBauxiteFurnace;
import net.minecraft.server.World;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionBlockType;

public class BlockBauxiteFurnace extends BlockTileEntity implements FzExplosionBlockType {

    public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    private final boolean b;
    private static boolean c;

    public BlockBauxiteFurnace(boolean flag) {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockBauxiteFurnace.FACING, EnumDirection.NORTH));
        this.b = flag;
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
        return Item.getItemOf(Blocks.BAUXITE_FURNACE);
    }

    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.e(world, blockposition, iblockdata);
    }

    private void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (!world.isClientSide) {
            IBlockData iblockdata1 = world.getType(blockposition.north());
            IBlockData iblockdata2 = world.getType(blockposition.south());
            IBlockData iblockdata3 = world.getType(blockposition.west());
            IBlockData iblockdata4 = world.getType(blockposition.east());
            EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockBauxiteFurnace.FACING);

            if (enumdirection == EnumDirection.NORTH && iblockdata1.b() && !iblockdata2.b()) {
                enumdirection = EnumDirection.SOUTH;
            } else if (enumdirection == EnumDirection.SOUTH && iblockdata2.b() && !iblockdata1.b()) {
                enumdirection = EnumDirection.NORTH;
            } else if (enumdirection == EnumDirection.WEST && iblockdata3.b() && !iblockdata4.b()) {
                enumdirection = EnumDirection.EAST;
            } else if (enumdirection == EnumDirection.EAST && iblockdata4.b() && !iblockdata3.b()) {
                enumdirection = EnumDirection.WEST;
            }

            world.setTypeAndData(blockposition, iblockdata.set(BlockBauxiteFurnace.FACING, enumdirection), 2);
        }
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isClientSide) {
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityBauxiteFurnace) {
                entityhuman.openContainer((TileEntityBauxiteFurnace) tileentity);
                entityhuman.b(StatisticList.Y);
            }

            return true;
        }
    }

    public static void a(boolean flag, World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);
        TileEntity tileentity = world.getTileEntity(blockposition);

        BlockBauxiteFurnace.c = true;
        if (flag) {
            world.setTypeAndData(blockposition, Blocks.LIT_BAUXITE_FURNACE.getBlockData().set(BlockBauxiteFurnace.FACING, iblockdata.get(BlockBauxiteFurnace.FACING)), 3);
            world.setTypeAndData(blockposition, Blocks.LIT_BAUXITE_FURNACE.getBlockData().set(BlockBauxiteFurnace.FACING, iblockdata.get(BlockBauxiteFurnace.FACING)), 3);
        } else {
            world.setTypeAndData(blockposition, Blocks.BAUXITE_FURNACE.getBlockData().set(BlockBauxiteFurnace.FACING, iblockdata.get(BlockBauxiteFurnace.FACING)), 3);
            world.setTypeAndData(blockposition, Blocks.BAUXITE_FURNACE.getBlockData().set(BlockBauxiteFurnace.FACING, iblockdata.get(BlockBauxiteFurnace.FACING)), 3);
        }

        BlockBauxiteFurnace.c = false;
        if (tileentity != null) {
            tileentity.A();
            world.setTileEntity(blockposition, tileentity);
        }

    }

    public TileEntity a(World world, int i) {
        return new TileEntityBauxiteFurnace();
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        return this.getBlockData().set(BlockBauxiteFurnace.FACING, entityliving.getDirection().opposite());
    }

    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        world.setTypeAndData(blockposition, iblockdata.set(BlockBauxiteFurnace.FACING, entityliving.getDirection().opposite()), 2);
        if (itemstack.hasName()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityBauxiteFurnace) {
                ((TileEntityBauxiteFurnace) tileentity).setCustomName(itemstack.getName());
            }
        }

    }

    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) {
        if (!BlockBauxiteFurnace.c) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityBauxiteFurnace) {
                InventoryUtils.dropInventory(world, blockposition, (TileEntityBauxiteFurnace) tileentity);
                world.updateAdjacentComparators(blockposition, this);
            }
        }

        super.remove(world, blockposition, iblockdata);
    }

    public boolean isComplexRedstone(IBlockData iblockdata) {
        return true;
    }

    public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return Container.a(world.getTileEntity(blockposition));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return new ItemStack(Blocks.BAUXITE_FURNACE);
    }

    public EnumRenderType a(IBlockData iblockdata) {
        return EnumRenderType.MODEL;
    }

    public IBlockData fromLegacyData(int i) {
        EnumDirection enumdirection = EnumDirection.fromType1(i);

        if (enumdirection.k() == EnumDirection.EnumAxis.Y) {
            enumdirection = EnumDirection.NORTH;
        }

        return this.getBlockData().set(BlockBauxiteFurnace.FACING, enumdirection);
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumDirection) iblockdata.get(BlockBauxiteFurnace.FACING)).a();
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return iblockdata.set(BlockBauxiteFurnace.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockBauxiteFurnace.FACING)));
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockBauxiteFurnace.FACING)));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockBauxiteFurnace.FACING});
    }

	@Override
	public ExplosionBlockType getExplosionBlockType() {
		return ExplosionBlockType.FURNACE_BAUXITE;
	}
}
