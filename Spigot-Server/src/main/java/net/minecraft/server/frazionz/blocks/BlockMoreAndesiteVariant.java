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

public class BlockMoreAndesiteVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreAndesiteVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreAndesiteVariant.VariantType.class);

    public BlockMoreAndesiteVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreAndesiteVariant.VARIANT, BlockMoreAndesiteVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreAndesiteVariant.VariantType) iblockdata.get(BlockMoreAndesiteVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreAndesiteVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreAndesiteVariant.VariantType) iblockdata.get(BlockMoreAndesiteVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreAndesiteVariant.VARIANT, BlockMoreAndesiteVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreAndesiteVariant.VariantType) iblockdata.get(BlockMoreAndesiteVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreAndesiteVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreAndesiteVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreAndesiteVariant.VariantType) iblockdata.get(BlockMoreAndesiteVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreAndesiteVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreAndesiteVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreAndesiteVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreAndesiteVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreAndesiteVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "stone_andesite" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "stone_andesite" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "stone_andesite" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "stone_andesite" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreAndesiteVariant.VariantType[] d = new BlockMoreAndesiteVariant.VariantType[values().length];
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

        public static BlockMoreAndesiteVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreAndesiteVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreAndesiteVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreAndesiteVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreAndesiteVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreAndesiteVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
