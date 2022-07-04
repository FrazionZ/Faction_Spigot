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

public class BlockMoreDioriteVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreDioriteVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreDioriteVariant.VariantType.class);

    public BlockMoreDioriteVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreDioriteVariant.VARIANT, BlockMoreDioriteVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreDioriteVariant.VariantType) iblockdata.get(BlockMoreDioriteVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreDioriteVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreDioriteVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreDioriteVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreDioriteVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreDioriteVariant.VariantType) iblockdata.get(BlockMoreDioriteVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreDioriteVariant.VARIANT, BlockMoreDioriteVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreDioriteVariant.VariantType) iblockdata.get(BlockMoreDioriteVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreDioriteVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreDioriteVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreDioriteVariant.VariantType) iblockdata.get(BlockMoreDioriteVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreDioriteVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreDioriteVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreDioriteVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreDioriteVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreDioriteVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreDioriteVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreDioriteVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreDioriteVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "stone_diorite" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "stone_diorite" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "stone_diorite" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "stone_diorite" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreDioriteVariant.VariantType[] d = new BlockMoreDioriteVariant.VariantType[values().length];
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

        public static BlockMoreDioriteVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreDioriteVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreDioriteVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreDioriteVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreDioriteVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreDioriteVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
