package net.minecraft.server;

import com.google.common.base.Predicate;
import java.util.Iterator;
import javax.annotation.Nullable;

public class BlockBlockPlacer extends BlockFacingHorizontal {
	
	private static String blockName;

    protected BlockBlockPlacer(String name) {
        super(Material.STONE, MaterialMapColor.F);
        this.blockName = name;
        this.w(this.blockStateList.getBlockData().set(BlockBlockPlacer.FACING, EnumDirection.NORTH));
    }
    
    

    public boolean canPlace(World world, BlockPosition blockposition) {
        return world.getType(blockposition).getBlock().material.isReplaceable() && world.getType(blockposition.down()).q();
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return iblockdata.set(BlockBlockPlacer.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockBlockPlacer.FACING)));
    }

    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockBlockPlacer.FACING)));
    }

    public IBlockData getPlacedState(World world, BlockPosition blockposition, EnumDirection enumdirection, float f, float f1, float f2, int i, EntityLiving entityliving) {
        return this.getBlockData().set(BlockBlockPlacer.FACING, entityliving.getDirection().opposite());
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockBlockPlacer.FACING, EnumDirection.fromType2(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumDirection) iblockdata.get(BlockBlockPlacer.FACING)).get2DRotationValue();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockBlockPlacer.FACING});
    }
}
