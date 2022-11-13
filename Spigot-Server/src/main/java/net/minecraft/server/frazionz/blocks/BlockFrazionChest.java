package net.minecraft.server.frazionz.blocks;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockEnderChest;
import net.minecraft.server.BlockFacingHorizontal;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateDirection;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.BlockTileEntity;
import net.minecraft.server.Container;
import net.minecraft.server.CreativeModeTab;
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
import net.minecraft.server.IInventory;
import net.minecraft.server.ITileInventory;
import net.minecraft.server.InventoryUtils;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.MathHelper;
import net.minecraft.server.StatisticList;
import net.minecraft.server.TileEntity;
import net.minecraft.server.frazionz.tileentity.TileEntityFrazionChest;
import net.minecraft.server.World;

public class BlockFrazionChest extends BlockTileEntity {

    public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    protected static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    public BlockFrazionChest() {
    	
        super(Material.WOOD);
        this.w(this.blockStateList.getBlockData().set(BlockFrazionChest.FACING, EnumDirection.NORTH));
        this.a(CreativeModeTab.c);
    }

    public boolean b(IBlockData iblockdata) {
        return false;
    }
    
    public boolean c(IBlockData iblockdata) {
        return false;
    }
    
    
    
    
    
    
    
    
    
    

    public EnumRenderType a(IBlockData state)
    {
        return EnumRenderType.ENTITYBLOCK_ANIMATED;
    }
    
    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition)
    {
        return NOT_CONNECTED_AABB;
    }

    
    
    
    
    
    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
    }

    
    
    
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack)
    {
    	world.setTypeAndData(blockposition, iblockdata.set(BlockEnderChest.FACING, entityliving.getDirection().opposite()), 2);
    }

    public IBlockData f(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        EnumDirection enumdirection = null;
        
        Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

        while (iterator.hasNext()) {
            EnumDirection enumdirection1 = (EnumDirection) iterator.next();
            IBlockData iblockdata1 = world.getType(blockposition.shift(enumdirection1));

            if (iblockdata1.getBlock() == this) {
                return iblockdata;
            }

            if (iblockdata1.b()) {
                if (enumdirection != null) {
                    enumdirection = null;
                    break;
                }

                enumdirection = enumdirection1;
            }
        }

        if (enumdirection != null) {
            return iblockdata.set(BlockFrazionChest.FACING, enumdirection.opposite());
        } else {
            EnumDirection enumdirection2 = (EnumDirection) iblockdata.get(BlockFrazionChest.FACING);

            if (world.getType(blockposition.shift(enumdirection2)).b()) {
                enumdirection2 = enumdirection2.opposite();
            }

            if (world.getType(blockposition.shift(enumdirection2)).b()) {
                enumdirection2 = enumdirection2.e();
            }

            if (world.getType(blockposition.shift(enumdirection2)).b()) {
                enumdirection2 = enumdirection2.opposite();
            }

            return iblockdata.set(BlockFrazionChest.FACING, enumdirection2);
        }
    }

    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1)
    {
        super.a(iblockdata, world, blockposition, block, blockposition1);
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityFrazionChest)
        {
            tileentity.invalidateBlockCache();
        }

    }

    
    
    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof IInventory)
        {
            InventoryUtils.dropInventory(world, blockposition, (IInventory) tileentity);
            world.updateAdjacentComparators(blockposition, this);
        }

        super.remove(world, blockposition, iblockdata);
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        if (world.isClientSide)
        {
            return true;
        } 
        else
        {
            ITileInventory itileinventory = this.getInventory(world, blockposition);

            if (itileinventory != null)
            {
                entityhuman.openContainer(itileinventory);
                
                entityhuman.b(StatisticList.aa);
            }

            return true;
        }
    }

    @Nullable
    public ITileInventory getInventory(World world, BlockPosition blockposition)
    {
        return this.a(world, blockposition, false);
    }

    @Nullable
    public ITileInventory a(World world, BlockPosition blockposition, boolean flag)
    {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (!(tileentity instanceof TileEntityFrazionChest))
        {
            return null;
        }
        else
        {
            Object object = (TileEntityFrazionChest) tileentity;

            if (!flag && this.e(world, blockposition))
            {
                return null;
            }
            else
            {
                return (ITileInventory) object;
            }
        }
    }

    public TileEntity a(World world, int i) {
        return new TileEntityFrazionChest();
    }


    public int b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection)
    {
        if (!iblockdata.m())
        {
            return 0;
        }
        else
        {
            int i = 0;
            TileEntity tileentity = iblockaccess.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityFrazionChest)
            {
                i = ((TileEntityFrazionChest) tileentity).numPlayersUsing;
            }

            return MathHelper.clamp(i, 0, 15);
        }
    }

    public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
        return enumdirection == EnumDirection.UP ? iblockdata.a(iblockaccess, blockposition, enumdirection) : 0;
    }

    private boolean e(World world, BlockPosition blockposition) {
        return this.i(world, blockposition);
    }

    private boolean i(World world, BlockPosition blockposition) {
        return world.getType(blockposition.up()).l();
    }

    public boolean isComplexRedstone(IBlockData iblockdata) {
        return true;
    }

    public int c(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return Container.b((IInventory) this.getInventory(world, blockposition));
    }

    public IBlockData fromLegacyData(int i) {
        EnumDirection enumdirection = EnumDirection.fromType1(i);

        if (enumdirection.k() == EnumDirection.EnumAxis.Y) {
            enumdirection = EnumDirection.NORTH;
        }

        return this.getBlockData().set(BlockFrazionChest.FACING, enumdirection);
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumDirection) iblockdata.get(BlockFrazionChest.FACING)).a();
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return iblockdata.set(BlockFrazionChest.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockFrazionChest.FACING)));
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockFrazionChest.FACING)));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockFrazionChest.FACING});
    }

    public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
        return EnumBlockFaceShape.UNDEFINED;
    }
}
