package net.minecraft.server.frazionz.blocks;

import java.util.Random;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.AxisAlignedBB;
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
import net.minecraft.server.INamable;
import net.minecraft.server.InventoryUtils;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.TileEntity;
import net.minecraft.server.frazionz.tileentity.TileEntityTrophyForge;
import net.minecraft.server.World;

public class BlockTrophyForge extends BlockTileEntity
{
	public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
	public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
    public static final BlockStateEnum<EnumTrophyForgeHalf> PARTS = BlockStateEnum.<EnumTrophyForgeHalf>of("parts", EnumTrophyForgeHalf.class);

    
    public BlockTrophyForge()
    {
        super(Material.ORE);
        this.w(this.blockStateList.getBlockData().set(BlockTrophyForge.FACING, EnumDirection.NORTH).set(BlockTrophyForge.PARTS, BlockTrophyForge.EnumTrophyForgeHalf.BASE));
        this.a(CreativeModeTab.c);
    }

    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {

        return FULL_BLOCK;
    }

    public boolean c(IBlockData iblockdata)
    {
        return false;
    }

    public boolean b(IBlockData iblockdata)
    {
        return false;
    }

    public EnumRenderType a(IBlockData state)
    {
        return EnumRenderType.ENTITYBLOCK_ANIMATED;
    }

    public EnumPistonReaction h(IBlockData iblockdata)
    {
        return EnumPistonReaction.IGNORE;
    }
    
    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        this.setDefaultFacing(world, blockposition, iblockdata);
    }
    
    private void setDefaultFacing(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        if (!world.isClientSide)
        {
            IBlockData iblockdata1 = world.getType(blockposition.north());
            IBlockData iblockdata2 = world.getType(blockposition.south());
            IBlockData iblockdata3 = world.getType(blockposition.west());
            IBlockData iblockdata4 = world.getType(blockposition.east());
            EnumDirection enumdirection = iblockdata.get(BlockTrophyForge.FACING);

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

            world.setTypeAndData(blockposition, iblockdata.set(FACING, enumdirection), 2);
        }
    }

    public TileEntity a(World worldIn, int meta)
    {
        if(meta != 0)
            return new TileEntityTrophyForge();
        return null;
    }

    public Item getDropType(IBlockData state, Random rand, int fortune)
    {
        return Item.getItemOf(Blocks.TROPHY_FORGE);
    }

    public boolean canPlace(World world, BlockPosition blockposition)
    {
        return blockposition.getY() < 255 && super.canPlace(world, blockposition) && super.canPlace(world, blockposition.up());
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

            if(iblockdata.get(PARTS) == EnumTrophyForgeHalf.OTHER && world.getType(pos).getBlock() == this)
            {
            	tileentity = world.getTileEntity(pos);
            }
            else if(iblockdata.get(PARTS) == EnumTrophyForgeHalf.BASE && world.getType(blockposition).getBlock() == this)
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
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityTrophyForge)
        {
            InventoryUtils.dropInventory(world, blockposition, (TileEntityTrophyForge) tileentity);
        }

        super.remove(world, blockposition, iblockdata);
    }
    
    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman)
    {
        BlockPosition blockposition1 = blockposition.down();
        BlockPosition blockposition2 = blockposition.up();

        if (iblockdata.get(PARTS) == EnumTrophyForgeHalf.OTHER && world.getType(blockposition1).getBlock() == this)
        {
            if (entityhuman.abilities.canInstantlyBuild)
            {
                world.setAir(blockposition);
            }
            world.setAir(blockposition1);
        }
        else if (iblockdata.get(PARTS) == EnumTrophyForgeHalf.BASE && world.getType(blockposition2).getBlock() == this)
        {
            if (entityhuman.abilities.canInstantlyBuild) 
            {
                world.setAir(blockposition);
            }
            world.setAir(blockposition2);
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
    
    @Override
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack)
    {
        this.placeTrophyForge(world, blockposition, entityliving.getDirection().opposite());
    }
    
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockData fromLegacyData(int meta)
    {
        if(meta == 0)
            return this.getBlockData();

        EnumDirection enumfacing = EnumDirection.fromType1(meta-1);

        if (enumfacing.k() == EnumDirection.EnumAxis.Y)
        {
        	enumfacing = EnumDirection.NORTH;
        }

        return this.getBlockData().set(FACING, enumfacing);
    }
    
    /**
     * Convert the BlockState into the correct metadata value
     */
    public int toLegacyData(IBlockData iblockdata)
    {
        if (iblockdata.get(BlockTrophyForge.PARTS) == EnumTrophyForgeHalf.BASE)
            return iblockdata.get(FACING).a() + 1;
        return 0;
    }
    
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) 
    {
        return iblockdata.set(FACING, enumblockrotation.a(iblockdata.get(FACING)));
    }
    
    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) 
    {
        return iblockdata.a(enumblockmirror.a(iblockdata.get(FACING)));
    }

    protected BlockStateList getStateList() 
    {
        return new BlockStateList(this, PARTS, FACING);
    }
    
    private void placeTrophyForge(World world, BlockPosition pos, EnumDirection enumdirection)
    {
        BlockPosition posUp = pos.up();
        IBlockData iblockdata = this.getBlockData().set(FACING, enumdirection);
        world.setTypeAndData(pos, iblockdata.set(PARTS, EnumTrophyForgeHalf.BASE), 2);
        world.setTypeAndData(posUp, iblockdata.set(PARTS, EnumTrophyForgeHalf.OTHER), 2);
        world.applyPhysics(pos, this, false);
        world.applyPhysics(posUp, this, false);
    }
    
    public enum EnumTrophyForgeHalf implements INamable
    {
        BASE,
        OTHER;

        public String toString() 
        {
            return this.getName();
        }

        public String getName() 
        {
            return this == OTHER ? "base" : "top";
        }
    }
}