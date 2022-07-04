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

public class BlockMoreSandstoneVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreSandstoneVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreSandstoneVariant.VariantType.class);

    public BlockMoreSandstoneVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreSandstoneVariant.VARIANT, BlockMoreSandstoneVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreSandstoneVariant.VariantType) iblockdata.get(BlockMoreSandstoneVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreSandstoneVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreSandstoneVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreSandstoneVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreSandstoneVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreSandstoneVariant.VariantType) iblockdata.get(BlockMoreSandstoneVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreSandstoneVariant.VARIANT, BlockMoreSandstoneVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreSandstoneVariant.VariantType) iblockdata.get(BlockMoreSandstoneVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreSandstoneVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreSandstoneVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreSandstoneVariant.VariantType) iblockdata.get(BlockMoreSandstoneVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreSandstoneVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreSandstoneVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreSandstoneVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreSandstoneVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreSandstoneVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreSandstoneVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreSandstoneVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreSandstoneVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "sandstone" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "sandstone" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "sandstone" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "sandstone" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreSandstoneVariant.VariantType[] d = new BlockMoreSandstoneVariant.VariantType[values().length];
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

        public static BlockMoreSandstoneVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreSandstoneVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreSandstoneVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreSandstoneVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreSandstoneVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreSandstoneVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
