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

public class BlockMoreAndesiteSmoothVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreAndesiteSmoothVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreAndesiteSmoothVariant.VariantType.class);

    public BlockMoreAndesiteSmoothVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreAndesiteSmoothVariant.VARIANT, BlockMoreAndesiteSmoothVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreAndesiteSmoothVariant.VariantType) iblockdata.get(BlockMoreAndesiteSmoothVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteSmoothVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteSmoothVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteSmoothVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteSmoothVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreAndesiteSmoothVariant.VariantType) iblockdata.get(BlockMoreAndesiteSmoothVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreAndesiteSmoothVariant.VARIANT, BlockMoreAndesiteSmoothVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreAndesiteSmoothVariant.VariantType) iblockdata.get(BlockMoreAndesiteSmoothVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreAndesiteSmoothVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreAndesiteSmoothVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreAndesiteSmoothVariant.VariantType) iblockdata.get(BlockMoreAndesiteSmoothVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreAndesiteSmoothVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteSmoothVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreAndesiteSmoothVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteSmoothVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreAndesiteSmoothVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteSmoothVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreAndesiteSmoothVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteSmoothVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "stone_andesite_smooth" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "stone_andesite_smooth" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "stone_andesite_smooth" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "stone_andesite_smooth" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreAndesiteSmoothVariant.VariantType[] d = new BlockMoreAndesiteSmoothVariant.VariantType[values().length];
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

        public static BlockMoreAndesiteSmoothVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreAndesiteSmoothVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreAndesiteSmoothVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreAndesiteSmoothVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreAndesiteSmoothVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreAndesiteSmoothVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
