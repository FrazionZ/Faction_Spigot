package net.minecraft.server.frazionz.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockTileEntity;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumRenderType;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.InventoryUtils;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.TileEntity;
import net.minecraft.server.frazionz.tileentity.TileEntityAmeliorator;
import net.minecraft.server.World;

public class BlockAmeliorator extends BlockTileEntity
{
	public static final AxisAlignedBB BASE_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.1D, 0.9D);
    public static final AxisAlignedBB STICK_AABB = new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 0.5D, 0.6D);
    public static final AxisAlignedBB PLATE_AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.75D, 0.9D);

    public static final AxisAlignedBB BLOCK_HITBOX = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.75D, 0.9D);
    
    private static boolean keepInventory;
    
    public BlockAmeliorator()
    {
        super(Material.ORE);
        this.a(CreativeModeTab.c);
    }
    
    
    // Create a new TileEntityAmeliorator //

	public TileEntity a(World worldIn, int meta)
	{
		return new TileEntityAmeliorator();
	}
	
	
    public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {

        return BLOCK_HITBOX;
    }
	
    public boolean c(IBlockData iblockdata) {
        return false;
    }

    public boolean b(IBlockData iblockdata) {
        return false;
    }
	
	
	
	// Render a Model //
	
	public EnumRenderType a(IBlockState state)
    {
        return EnumRenderType.MODEL;
    }
	
	
	
	// Collision Box //

	public void a(IBlockData state, World worldIn, BlockPosition pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        a(pos, entityBox, collidingBoxes, STICK_AABB);
        a(pos, entityBox, collidingBoxes, BASE_AABB);
        a(pos, entityBox, collidingBoxes, PLATE_AABB);
    }
	
    
    // Drop un Ameliorator //
    
    public Item getDropType(IBlockData state, Random rand, int fortune)
    {
        return Item.getItemOf(Blocks.AMELIORATOR);
    }
    
    public void postPlace(World worldIn, BlockPosition pos, IBlockData state, EntityLiving placer, ItemStack stack)
    {
        if (stack.hasName())
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

        }
    }
    
    
    // Quand le Block est Activé //
    
    public boolean interact(World worldIn, BlockPosition pos, IBlockData state, EntityHuman playerIn, EnumHand hand, EnumDirection heldItem, float side, float hitX, float hitY)
    {
        if (worldIn.isClientSide)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityAmeliorator)
            {
                playerIn.openContainer((TileEntityAmeliorator)tileentity);
            }

            return true;
        }
    }
    
    // Break block & drop Item //
    
    public void remove(World worldIn, BlockPosition pos, IBlockData state)
    {
    	if (!keepInventory)
        {
        TileEntity tileentity = worldIn.getTileEntity(pos);

	        if (tileentity instanceof TileEntityAmeliorator)
	        {
	        	InventoryUtils.dropInventory(worldIn, pos, (TileEntityAmeliorator)tileentity);
	        }
        }
    }
}
