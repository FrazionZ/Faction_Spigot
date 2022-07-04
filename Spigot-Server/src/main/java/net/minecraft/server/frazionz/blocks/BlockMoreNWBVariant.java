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

public class BlockMoreNWBVariant extends Block {
	
	public static final BlockStateEnum<BlockMoreNWBVariant.VariantType> VARIANT = BlockStateEnum.of("variant", BlockMoreNWBVariant.VariantType.class);

    public BlockMoreNWBVariant() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockMoreNWBVariant.VARIANT, BlockMoreNWBVariant.VariantType.BRICK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockMoreNWBVariant.VariantType) iblockdata.get(BlockMoreNWBVariant.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
        nonnulllist.add(new ItemStack(this, 1, BlockMoreNWBVariant.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreNWBVariant.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreNWBVariant.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockMoreNWBVariant.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockMoreNWBVariant.VariantType) iblockdata.get(BlockMoreNWBVariant.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockMoreNWBVariant.VARIANT, BlockMoreNWBVariant.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockMoreNWBVariant.VariantType) iblockdata.get(BlockMoreNWBVariant.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockMoreNWBVariant.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
    	
        BlockMoreNWBVariant.VariantType BlockMoreVariant_VariantType = (BlockMoreNWBVariant.VariantType) iblockdata.get(BlockMoreNWBVariant.VARIANT);

        if (BlockMoreVariant_VariantType == BlockMoreNWBVariant.VariantType.BRICK) {
            BlockMoreVariant_VariantType = BlockMoreNWBVariant.VariantType.BRICK;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreNWBVariant.VariantType.BRICKS) {
            BlockMoreVariant_VariantType = BlockMoreNWBVariant.VariantType.BRICKS;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreNWBVariant.VariantType.CARVED) {
            BlockMoreVariant_VariantType = BlockMoreNWBVariant.VariantType.CARVED;
        }
        else if (BlockMoreVariant_VariantType == BlockMoreNWBVariant.VariantType.CHISELED) {
            BlockMoreVariant_VariantType = BlockMoreNWBVariant.VariantType.CHISELED;
        }

        return BlockMoreVariant_VariantType.a();
    }
    
    
    public static enum VariantType implements INamable 
    {

    	BRICK(0, "nether_wart_block" + "_brick", "brick", MaterialMapColor.m),
        BRICKS(1, "nether_wart_block" + "bricks", "bricks", MaterialMapColor.m),
        CARVED(2, "nether_wart_block" + "_carved", "carved", MaterialMapColor.m),
        CHISELED(3, "nether_wart_block" + "_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockMoreNWBVariant.VariantType[] d = new BlockMoreNWBVariant.VariantType[values().length];
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

        public static BlockMoreNWBVariant.VariantType a(int i) {
            if (i < 0 || i >= BlockMoreNWBVariant.VariantType.d.length) {
                i = 0;
            }

            return BlockMoreNWBVariant.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockMoreNWBVariant.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockMoreNWBVariant.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockMoreNWBVariant.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
