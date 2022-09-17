package net.minecraft.server.frazionz.blocks;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.tileentity.TileEntityGrimoirePedestal;

public class BlockGrimoirePedestal extends Block implements ITileEntity {

    protected static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    protected static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

    public BlockGrimoirePedestal()
    {
        super(Material.STONE);
        this.e(0);
        this.w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH));
        this.a(CreativeModeTab.c);
        this.isTileEntity = true;
    }

    public AxisAlignedBB b(IBlockData state, IBlockAccess source, BlockPosition pos)
    {
        return FULL_BLOCK;
    }

    public boolean c(IBlockData state)
    {
        return false;
    }

    public boolean b(IBlockData state)
    {
        return false;
    }

    public EnumRenderType a(IBlockData iblockdata)
    {
        return EnumRenderType.MODEL;
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onPlace(World worldIn, BlockPosition pos, IBlockData state)
    {
        this.e(worldIn, pos, state);
    }

    private void e(World worldIn, BlockPosition pos, IBlockData state)
    {
        if (!worldIn.isClientSide)
        {
            IBlockData iblockstate = worldIn.getType(pos.north());
            IBlockData iblockstate1 = worldIn.getType(pos.south());
            IBlockData iblockstate2 = worldIn.getType(pos.west());
            IBlockData iblockstate3 = worldIn.getType(pos.east());
            EnumDirection enumfacing = (EnumDirection)state.get(FACING);

            if (enumfacing == EnumDirection.NORTH && iblockstate.b() && !iblockstate1.b())
            {
                enumfacing = EnumDirection.SOUTH;
            }
            else if (enumfacing == EnumDirection.SOUTH && iblockstate1.b() && !iblockstate.b())
            {
                enumfacing = EnumDirection.NORTH;
            }
            else if (enumfacing == EnumDirection.WEST && iblockstate2.b() && !iblockstate3.b())
            {
                enumfacing = EnumDirection.EAST;
            }
            else if (enumfacing == EnumDirection.EAST && iblockstate3.b() && !iblockstate2.b())
            {
                enumfacing = EnumDirection.WEST;
            }

            worldIn.setTypeAndData(pos, state.set(FACING, enumfacing), 2);
        }
    }

    public IBlockData getPlacedState(World worldIn, BlockPosition pos, EnumDirection facing, float hitX, float hitY, float hitZ, int meta, EntityLiving placer)
    {
        EnumDirection enumfacing = placer.getDirection().e();
        return super.getPlacedState(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).set(FACING, enumfacing);
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockData fromLegacyData(int meta)
    {
        return this.getBlockData().set(FACING, EnumDirection.fromType2(meta & 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int toLegacyData(IBlockData iblockdata)
    {
        int i = 0;
        i = i | ((EnumDirection)iblockdata.get(FACING)).get2DRotationValue();
        return i;
    }
    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation)
    {
        return iblockdata.set(FACING, enumblockrotation.a((EnumDirection)iblockdata.get(FACING)));
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] {FACING});
    }

    public TileEntity a(World worldIn, int meta)
    {
        return new TileEntityGrimoirePedestal();
    }

}
