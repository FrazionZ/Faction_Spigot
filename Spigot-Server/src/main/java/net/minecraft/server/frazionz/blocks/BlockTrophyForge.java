package net.minecraft.server.frazionz.blocks;

import java.util.Random;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.BlockDoor;
import net.minecraft.server.BlockFacingHorizontal;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateDirection;
import net.minecraft.server.BlockStateEnum;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.BlockTileEntity;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumBlockMirror;
import net.minecraft.server.EnumBlockRotation;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumPistonReaction;
import net.minecraft.server.EnumRenderType;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.INamable;
import net.minecraft.server.InventoryUtils;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityTrophyForge;
import net.minecraft.server.World;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionBlockType;

public class BlockTrophyForge extends BlockTileEntity implements FzExplosionBlockType
{
	public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
	public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
    public static final BlockStateEnum<BlockTrophyForge.EnumTrophyForgeHalf> HALF = BlockStateEnum.of("half", BlockTrophyForge.EnumTrophyForgeHalf.class);
    
    private static boolean keepInventory;
    
    public BlockTrophyForge()
    {
        super(Material.ORE);
        this.w(this.blockStateList.getBlockData().set(BlockTrophyForge.FACING, EnumDirection.NORTH).set(BlockTrophyForge.HALF, BlockTrophyForge.EnumTrophyForgeHalf.LOWER));
        this.a(CreativeModeTab.c);
    }
    
    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        this.e(world, blockposition, iblockdata);
    }
    
    private void e(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        if (!world.isClientSide)
        {
            IBlockData iblockdata1 = world.getType(blockposition.north());
            IBlockData iblockdata2 = world.getType(blockposition.south());
            IBlockData iblockdata3 = world.getType(blockposition.west());
            IBlockData iblockdata4 = world.getType(blockposition.east());
            EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockTrophyForge.FACING);

            if (enumdirection == EnumDirection.NORTH && iblockdata1.b() && !iblockdata2.b())
            {
                enumdirection = EnumDirection.SOUTH;
            } 
            else if (enumdirection == EnumDirection.SOUTH && iblockdata2.b() && !iblockdata1.b()) 
            {
                enumdirection = EnumDirection.NORTH;
            }
            else if (enumdirection == EnumDirection.WEST && iblockdata3.b() && !iblockdata4.b())
            {
                enumdirection = EnumDirection.EAST;
            } 
            else if (enumdirection == EnumDirection.EAST && iblockdata4.b() && !iblockdata3.b()) 
            {
                enumdirection = EnumDirection.WEST;
            }

            world.setTypeAndData(blockposition, iblockdata.set(BlockTrophyForge.FACING, enumdirection), 2);
        }
    }
    
    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {

        return FULL_BLOCK;
    }
    
    public boolean c(IBlockData iblockdata)
    {
        return false;
    }
    
	public TileEntity a(World worldIn, int meta)
	{
		return new TileEntityTrophyForge();
	}
	
    public boolean b(IBlockData iblockdata) 
    {
        return false;
    }
	
	public EnumRenderType a(IBlockState state)
    {
        return EnumRenderType.MODEL;
    }
	
    public EnumPistonReaction h(IBlockData iblockdata) 
    {
        return EnumPistonReaction.IGNORE;
    }
	
    public Item getDropType(IBlockData state, Random rand, int fortune)
    {
        return Item.getItemOf(Blocks.TROPHY_FORGE);
    }
    
    public boolean canPlace(World world, BlockPosition blockposition)
    {
        return blockposition.getY() >= 255 ? false : super.canPlace(world, blockposition) && super.canPlace(world, blockposition.up());
    }
    
    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2)
    {
        if (world.isClientSide)
        {
            return true;
        }
        else
        {
        	BlockPosition pos = blockposition.down();
            TileEntity tileentity = null;

            if(iblockdata.get(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.UPPER && world.getType(pos).getBlock() == this)
            {
            	tileentity = world.getTileEntity(pos);
            }
            else if(iblockdata.get(HALF) == BlockTrophyForge.EnumTrophyForgeHalf.LOWER && world.getType(blockposition).getBlock() == this) 
            {
            	tileentity = world.getTileEntity(blockposition);
            }
            
            if (tileentity instanceof TileEntityTrophyForge) 
            {
                entityhuman.openContainer((TileEntityTrophyForge) tileentity);
            }

            return true;
        }
    }
    
    public void remove(World world, BlockPosition blockposition, IBlockData iblockdata) 
    {
        if (!keepInventory)
        {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityTrophyForge) 
            {
                InventoryUtils.dropInventory(world, blockposition, (TileEntityTrophyForge) tileentity);
            }
        }

        super.remove(world, blockposition, iblockdata);
    }
    
    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman)
    {
        BlockPosition blockposition1 = blockposition.down();
        BlockPosition blockposition2 = blockposition.up();

        if (iblockdata.get(BlockTrophyForge.HALF) == BlockTrophyForge.EnumTrophyForgeHalf.UPPER && world.getType(blockposition1).getBlock() == this)
        {
            if (entityhuman.abilities.canInstantlyBuild)
            {
                world.setAir(blockposition);
            }
            world.setAir(blockposition1);
        }
        else if (iblockdata.get(BlockTrophyForge.HALF) == BlockTrophyForge.EnumTrophyForgeHalf.LOWER && world.getType(blockposition2).getBlock() == this) 
        {
            if (entityhuman.abilities.canInstantlyBuild) 
            {
                world.setAir(blockposition);
            }
            world.setAir(blockposition2);
        }
    }
    
    public static void setState(boolean flag, World world, BlockPosition blockposition)
    {
        IBlockData iblockdata = world.getType(blockposition);
        TileEntity tileentity = world.getTileEntity(blockposition);
        keepInventory = true;
        
        world.setTypeAndData(blockposition, Blocks.TROPHY_FORGE.getBlockData().set(FACING, iblockdata.get(FACING)).set(HALF, iblockdata.get(HALF)), 3);

        keepInventory = false;
        
        if (tileentity != null) 
        {
            tileentity.A();
            world.setTileEntity(blockposition, tileentity);
        }
    }
    
    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving)
    {
    	return this.getBlockData().set(FACING, entityliving.getDirection().opposite());
    }
    
    public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition)
    {
        if (iblockdata.get(BlockDoor.HALF) == BlockDoor.EnumDoorHalf.LOWER) {
        	IBlockData iblockdata1 = iblockaccess.getType(blockposition.up());
        }
        else
        {
        	IBlockData iblockdata1 = iblockaccess.getType(blockposition.down());
            if (iblockdata1.getBlock() == this)
            {
                iblockdata = iblockdata.set(BlockDoor.FACING, iblockdata1.get(BlockDoor.FACING));
            }
        }

        return iblockdata;
    }
    
    @Override
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        this.placeTrophyForge(world, blockposition, entityliving.getDirection().opposite());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockData fromLegacyData(int i)
    {
        EnumDirection enumfacing = EnumDirection.fromType1(i);

        if (enumfacing.k() == EnumDirection.EnumAxis.Y)
        {
        	enumfacing = EnumDirection.NORTH;
        }
        
        return (i & 8) > 0 ? (this.getBlockData().set(FACING, enumfacing).set(HALF, BlockTrophyForge.EnumTrophyForgeHalf.UPPER)) : (this.getBlockData().set(FACING, enumfacing).set(HALF, BlockTrophyForge.EnumTrophyForgeHalf.LOWER));
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int toLegacyData(IBlockData iblockdata)
    {
        int i = 0;

        if (iblockdata.get(BlockTrophyForge.HALF) == BlockTrophyForge.EnumTrophyForgeHalf.UPPER)
        {
            i = i | 8;
        }
        else
        {
            i = i | ((EnumDirection) iblockdata.get(BlockTrophyForge.FACING)).e().get2DRotationValue();
        }

        return i;
    }
    
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) 
    {
        return iblockdata.get(BlockTrophyForge.HALF) != BlockTrophyForge.EnumTrophyForgeHalf.LOWER ? iblockdata : iblockdata.set(BlockTrophyForge.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockTrophyForge.FACING)));
    }
    
    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) 
    {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockTrophyForge.FACING)));
    }

    protected BlockStateList getStateList() 
    {
        return new BlockStateList(this, new IBlockState[] {BlockTrophyForge.HALF, BlockTrophyForge.FACING});
    }
    
    private void placeTrophyForge(World world, BlockPosition blockposition, EnumDirection enumdirection) 
    {
        BlockPosition blockposition3 = blockposition.up();
        IBlockData iblockdata = this.getBlockData().set(BlockTrophyForge.FACING, enumdirection);
        world.setTypeAndData(blockposition, iblockdata.set(BlockTrophyForge.HALF, BlockTrophyForge.EnumTrophyForgeHalf.LOWER), 2);
        world.setTypeAndData(blockposition3, iblockdata.set(BlockTrophyForge.HALF, BlockTrophyForge.EnumTrophyForgeHalf.UPPER), 2);
        world.applyPhysics(blockposition, this, false);
        world.applyPhysics(blockposition3, this, false);
    }
    
    protected static boolean isTop(int i)
    {
        return (i & 8) != 0;
    }
    
    public static enum EnumTrophyForgeHalf implements INamable 
    {
        UPPER,
        LOWER;

        public String toString() 
        {
            return this.getName();
        }

        public String getName() 
        {
            return this == BlockTrophyForge.EnumTrophyForgeHalf.UPPER ? "upper" : "lower";
        }
    }

	@Override
	public ExplosionBlockType getExplosionBlockType() {
		return ExplosionBlockType.TROPHY_FORGE;
	}
}