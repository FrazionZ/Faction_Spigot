package net.minecraft.server.frazionz.blocks;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockFacingHorizontal;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateDirection;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.BlockTileEntity;
import net.minecraft.server.Container;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityOcelot;
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
import net.minecraft.server.TileEntityDirtChest;
import net.minecraft.server.World;
import net.minecraft.server.EnumDirection.EnumAxis;
import net.minecraft.server.EnumDirection.EnumDirectionLimit;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionBlockType;

public class BlockDirtChest extends BlockTileEntity implements FzExplosionBlockType {

    public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    protected static final AxisAlignedBB NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

    public BlockDirtChest() {
    	
        super(Material.WOOD);
        this.w(this.blockStateList.getBlockData().set(BlockDirtChest.FACING, EnumDirection.NORTH));
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
        EnumDirection enumdirection = EnumDirection.fromType2(MathHelper.floor((double) (entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3).opposite();

        iblockdata = iblockdata.set(BlockDirtChest.FACING, enumdirection);
        BlockPosition blockposition1 = blockposition.north();
        BlockPosition blockposition2 = blockposition.south();
        BlockPosition blockposition3 = blockposition.west();
        BlockPosition blockposition4 = blockposition.east();
        boolean flag = this == world.getType(blockposition1).getBlock();
        boolean flag1 = this == world.getType(blockposition2).getBlock();
        boolean flag2 = this == world.getType(blockposition3).getBlock();
        boolean flag3 = this == world.getType(blockposition4).getBlock();

        if (!flag && !flag1 && !flag2 && !flag3)
        {
            world.setTypeAndData(blockposition, iblockdata, 3);
        }
        else if (enumdirection.k() == EnumDirection.EnumAxis.X && (flag || flag1))
        {
            if (flag)
            {
                world.setTypeAndData(blockposition1, iblockdata, 3);
            } else {
                world.setTypeAndData(blockposition2, iblockdata, 3);
            }

            world.setTypeAndData(blockposition, iblockdata, 3);
        }
        else if (enumdirection.k() == EnumDirection.EnumAxis.Z && (flag2 || flag3)) 
        {
            if (flag2) 
            {
                world.setTypeAndData(blockposition3, iblockdata, 3);
            } else {
                world.setTypeAndData(blockposition4, iblockdata, 3);
            }

            world.setTypeAndData(blockposition, iblockdata, 3);
        }

        if (itemstack.hasName()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityDirtChest) {
                ((TileEntityDirtChest) tileentity).setCustomName(itemstack.getName());
            }
        }

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
            return iblockdata.set(BlockDirtChest.FACING, enumdirection.opposite());
        } else {
            EnumDirection enumdirection2 = (EnumDirection) iblockdata.get(BlockDirtChest.FACING);

            if (world.getType(blockposition.shift(enumdirection2)).b()) {
                enumdirection2 = enumdirection2.opposite();
            }

            if (world.getType(blockposition.shift(enumdirection2)).b()) {
                enumdirection2 = enumdirection2.e();
            }

            if (world.getType(blockposition.shift(enumdirection2)).b()) {
                enumdirection2 = enumdirection2.opposite();
            }

            return iblockdata.set(BlockDirtChest.FACING, enumdirection2);
        }
    }

    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1)
    {
        super.a(iblockdata, world, blockposition, block, blockposition1);
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityDirtChest)
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

        if (!(tileentity instanceof TileEntityDirtChest))
        {
            return null;
        }
        else
        {
            Object object = (TileEntityDirtChest) tileentity;

            if (!flag && this.e(world, blockposition))
            {
                return null;
            }
            else
            {
                Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

                while (iterator.hasNext())
                {
                    EnumDirection enumdirection = (EnumDirection) iterator.next();
                    BlockPosition blockposition1 = blockposition.shift(enumdirection);
                    Block block = world.getType(blockposition1).getBlock();

                    if (block == this)
                    {
                        if (this.e(world, blockposition1))
                        {
                            return null;
                        }

                        TileEntity tileentity1 = world.getTileEntity(blockposition1);

                    }
                }

                return (ITileInventory) object;
            }
        }
    }

    public TileEntity a(World world, int i) {
        return new TileEntityDirtChest();
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

            if (tileentity instanceof TileEntityDirtChest)
            {
                i = ((TileEntityDirtChest) tileentity).numPlayersUsing;
            }

            return MathHelper.clamp(i, 0, 15);
        }
    }

    public int c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
        return enumdirection == EnumDirection.UP ? iblockdata.a(iblockaccess, blockposition, enumdirection) : 0;
    }

    private boolean e(World world, BlockPosition blockposition) {
        return this.i(world, blockposition) || this.j(world, blockposition);
    }

    private boolean i(World world, BlockPosition blockposition) {
        return world.getType(blockposition.up()).l();
    }

    private boolean j(World world, BlockPosition blockposition)
    {
        Iterator iterator = world.a(EntityOcelot.class, new AxisAlignedBB((double) blockposition.getX(), (double) (blockposition.getY() + 1), (double) blockposition.getZ(), (double) (blockposition.getX() + 1), (double) (blockposition.getY() + 2), (double) (blockposition.getZ() + 1))).iterator();

        EntityOcelot entityocelot;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            Entity entity = (Entity) iterator.next();

            entityocelot = (EntityOcelot) entity;
        } while (!entityocelot.isSitting());

        return true;
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

        return this.getBlockData().set(BlockDirtChest.FACING, enumdirection);
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumDirection) iblockdata.get(BlockDirtChest.FACING)).a();
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return iblockdata.set(BlockDirtChest.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockDirtChest.FACING)));
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockDirtChest.FACING)));
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockDirtChest.FACING});
    }

    public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
        return EnumBlockFaceShape.UNDEFINED;
    }


	public org.bukkit.frazionz.enums.ExplosionBlockType getExplosionBlockType() {
		return ExplosionBlockType.CHEST_DIRT;
	}

}
