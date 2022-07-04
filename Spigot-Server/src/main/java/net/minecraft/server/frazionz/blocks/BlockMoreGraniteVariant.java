package net.minecraft.server.frazionz.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateEnum;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.INamable;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.MaterialMapColor;
import net.minecraft.server.NonNullList;
import net.minecraft.server.World;

public class BlockMoreGraniteVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreGraniteVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreGraniteVariant.VariantType.class);

    public BlockMoreGraniteVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreGraniteVariant.VARIANT, BlockMoreGraniteVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreGraniteVariant.VariantType) iblockdata.get(BlockMoreGraniteVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreGraniteVariant.VariantType) iblockdata.get(BlockMoreGraniteVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreGraniteVariant.VARIANT, BlockMoreGraniteVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreGraniteVariant.VariantType) iblockdata.get(BlockMoreGraniteVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreGraniteVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreGraniteVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreGraniteVariant.VariantType) iblockdata.get(BlockMoreGraniteVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreGraniteVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreGraniteVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreGraniteVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreGraniteVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreGraniteVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreGraniteVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreGraniteVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreGraniteVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "stone_granite" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "stone_granite" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "stone_granite" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "stone_granite" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreGraniteVariant.VariantType[] d = new BlockMoreGraniteVariant.VariantType[values().length];
        private final int e;
        private final String f;
        private final String g;
        private final MaterialMapColor h;

        private VariantType(int i, String s, MaterialMapColor materialmapcolor) {
            this(i, s, s, materialmapcolor);
        }

        private VariantType(int i, String s, String s1, MaterialMapColor materialmapcolor) {
            this.e = i;
            this.f = s;
            this.g = s1;
            this.h = materialmapcolor;
        }

        public int a() {
            return this.e;
        }

        public String c() {
            return this.g;
        }

        public MaterialMapColor d() {
            return this.h;
        }

        public String toString() {
            return this.f;
        }

        public static BlockMoreGraniteVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreGraniteVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreGraniteVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreGraniteVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreGraniteVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreGraniteVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
