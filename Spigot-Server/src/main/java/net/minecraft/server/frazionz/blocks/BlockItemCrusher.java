package net.minecraft.server.frazionz.blocks;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.tileentity.TileEntityItemCrusher;
import org.bukkit.event.frazionz.FzBlockExplodeEvent;
import org.bukkit.frazionz.enums.ExplosionBlockType;

import java.util.Random;

public class BlockItemCrusher extends BlockTileEntity  {

    public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
    public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    public static final BlockStateEnum<EnumItemCrusherPart> PARTS = BlockStateEnum.of("parts", EnumItemCrusherPart.class);

    public BlockItemCrusher()
    {
        super(Material.ORE);
        this.w(this.blockStateList.getBlockData().set(FACING, EnumDirection.NORTH).set(PARTS, EnumItemCrusherPart.BASE));
        this.a(CreativeModeTab.c);
    }

    /**
     * Called after the block is set in the Chunk data, but before the Tile Entity is set
     */
    public void onPlace(World worldIn, BlockPosition pos, IBlockData state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPosition pos, IBlockData state)
    {
        if (!worldIn.isClientSide)
        {
            IBlockData IBlockData = worldIn.getType(pos.north());
            IBlockData IBlockData1 = worldIn.getType(pos.south());
            IBlockData IBlockData2 = worldIn.getType(pos.west());
            IBlockData IBlockData3 = worldIn.getType(pos.east());
            EnumDirection EnumDirection = state.get(FACING);

            if (EnumDirection == EnumDirection.NORTH && IBlockData.b() && !IBlockData1.b())
            {
                EnumDirection = EnumDirection.SOUTH;
            }
            else if (EnumDirection == EnumDirection.SOUTH && IBlockData1.b() && !IBlockData.b())
            {
                EnumDirection = EnumDirection.NORTH;
            }
            else if (EnumDirection == EnumDirection.WEST && IBlockData2.b() && !IBlockData3.b())
            {
                EnumDirection = EnumDirection.EAST;
            }
            else if (EnumDirection == EnumDirection.EAST && IBlockData3.b() && !IBlockData2.b())
            {
                EnumDirection = EnumDirection.WEST;
            }

            worldIn.setTypeAndData(pos, state.set(FACING, EnumDirection), 2);
        }
    }

    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return FULL_BLOCK;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean c(IBlockData iblockdata)
    {
        return false;
    }

    public TileEntity a(World worldIn, int meta)
    {
        if(meta != 0)
            return new TileEntityItemCrusher();
        return null;
    }

    public boolean b(IBlockData iblockdata)
    {
        return false;
    }

    public EnumRenderType a(IBlockData state)
    {
        return EnumRenderType.MODEL;
    }

    public EnumPistonReaction h(IBlockData iblockdata)
    {
        return EnumPistonReaction.IGNORE;
    }

    public Item getDropType(IBlockData state, Random rand, int fortune)
    {
        return Item.getItemOf(Blocks.ITEM_CRUSHER);
    }

    public boolean canPlace(World worldIn, BlockPosition pos)
    {
        return pos.getY() >= 255 ? false : super.canPlace(worldIn, pos) && super.canPlace(worldIn, pos.up());
    }

    public boolean interact(World worldIn, BlockPosition pos, IBlockData state, EntityHuman playerIn, EnumHand hand, EnumDirection heldItem, float side, float hitX, float hitY)
    {
        if (worldIn.isClientSide)
        {
            return true;
        }
        else
        {
            BlockPosition blockpos = pos.down();
            TileEntity tileentity = null;

            if (state.get(PARTS) == EnumItemCrusherPart.BASE && worldIn.getType(pos).getBlock() == this)
            {
                tileentity = worldIn.getTileEntity(pos);
            }
            else if (state.get(PARTS) == EnumItemCrusherPart.OTHER && worldIn.getType(pos).getBlock() == this)
            {
                tileentity = worldIn.getTileEntity(blockpos);
            }

            if (tileentity instanceof TileEntityItemCrusher)
            {
                playerIn.openContainer((TileEntityItemCrusher)tileentity);
            }

            return true;
        }
    }

    public void remove(World worldIn, BlockPosition pos, IBlockData state)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntityItemCrusher)
        {
            InventoryUtils.dropInventory(worldIn, pos, (TileEntityItemCrusher)tileentity);
        }

        super.remove(worldIn, pos, state);
    }

    public void a(World worldIn, BlockPosition pos, IBlockData state, EntityHuman player)
    {
        BlockPosition blockpos = pos.down();
        BlockPosition blockpos1 = pos.up();

        if (state.get(PARTS) == EnumItemCrusherPart.OTHER && worldIn.getType(blockpos).getBlock() == this)
        {
            if (player.abilities.canInstantlyBuild)
            {
                worldIn.setAir(pos);
            }
            worldIn.setAir(blockpos);
        }
        else if (state.get(PARTS) == EnumItemCrusherPart.BASE && worldIn.getType(blockpos1).getBlock() == this)
        {
            if (player.abilities.canInstantlyBuild)
            {
                worldIn.setAir(pos);
            }
            worldIn.setAir(blockpos1);
        }
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockData
     */
    public IBlockData getPlacedState(World worldIn, BlockPosition pos, EnumDirection facing, float hitX, float hitY, float hitZ, int meta, EntityLiving placer)
    {
        return this.getBlockData().set(FACING, placer.getDirection()).set(PARTS, EnumItemCrusherPart.BASE);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void postPlace(World worldIn, BlockPosition pos, IBlockData state, EntityLiving placer, ItemStack stack)
    {
        this.placeItemCrusher(worldIn, pos, placer.getDirection().opposite());
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockData fromLegacyData(int meta)
    {
        if(meta == 0)
            return this.getBlockData();

        EnumDirection enumfacing = EnumDirection.fromType2(meta-1);

        if (enumfacing.k() == EnumDirection.EnumAxis.Y)
        {
            enumfacing = EnumDirection.NORTH;
        }

        return this.getBlockData().set(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int toLegacyData(IBlockData state)
    {
        if (state.get(PARTS) == EnumItemCrusherPart.BASE)
            return (state.get(FACING)).a()+1;
        return 0;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData state, EnumBlockRotation rot)
    {
        return state.set(FACING, rot.a(state.get(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockData a(IBlockData state, EnumBlockMirror mirrorIn)
    {
        return state.a(mirrorIn.a(state.get(FACING)));
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, PARTS, FACING);
    }

    private void placeItemCrusher(World worldIn, BlockPosition pos, EnumDirection facing)
    {
        BlockPosition blockpos2 = pos.up();
        IBlockData IBlockData = this.getBlockData();
        worldIn.setTypeAndData(pos, IBlockData.set(FACING, facing).set(PARTS, EnumItemCrusherPart.BASE), 2);
        worldIn.setTypeAndData(blockpos2, IBlockData.set(PARTS, EnumItemCrusherPart.OTHER), 2);
        worldIn.applyPhysics(pos, this, false);
        worldIn.applyPhysics(blockpos2, this, false);
    }

    public enum EnumItemCrusherPart implements INamable
    {
        BASE,
        OTHER;

        public String toString()
        {
            return this.getName();
        }

        public String getName()
        {
            return this == BASE ? "base" : "top";
        }
    }

}
