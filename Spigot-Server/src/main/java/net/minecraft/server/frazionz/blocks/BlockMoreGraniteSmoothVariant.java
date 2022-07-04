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

public class BlockMoreGraniteSmoothVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreGraniteSmoothVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreGraniteSmoothVariant.VariantType.class);

    public BlockMoreGraniteSmoothVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreGraniteSmoothVariant.VARIANT, BlockMoreGraniteSmoothVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreGraniteSmoothVariant.VariantType) iblockdata.get(BlockMoreGraniteSmoothVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteSmoothVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteSmoothVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteSmoothVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreGraniteSmoothVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreGraniteSmoothVariant.VariantType) iblockdata.get(BlockMoreGraniteSmoothVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreGraniteSmoothVariant.VARIANT, BlockMoreGraniteSmoothVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreGraniteSmoothVariant.VariantType) iblockdata.get(BlockMoreGraniteSmoothVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreGraniteSmoothVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreGraniteSmoothVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreGraniteSmoothVariant.VariantType) iblockdata.get(BlockMoreGraniteSmoothVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreGraniteSmoothVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreGraniteSmoothVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreGraniteSmoothVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreGraniteSmoothVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreGraniteSmoothVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreGraniteSmoothVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreGraniteSmoothVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreGraniteSmoothVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "stone_granite_smooth" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "stone_granite_smooth" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "stone_granite_smooth" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "stone_granite_smooth" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreGraniteSmoothVariant.VariantType[] d = new BlockMoreGraniteSmoothVariant.VariantType[values().length];
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

        public static BlockMoreGraniteSmoothVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreGraniteSmoothVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreGraniteSmoothVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreGraniteSmoothVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreGraniteSmoothVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreGraniteSmoothVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
