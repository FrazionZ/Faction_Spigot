package net.minecraft.server;

public class BlockSmoothDarkAndesite extends Block {
	
	public static final BlockStateEnum<BlockSmoothDarkAndesite.VariantType> VARIANT = BlockStateEnum.of("variant", BlockSmoothDarkAndesite.VariantType.class);

    public BlockSmoothDarkAndesite() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockSmoothDarkAndesite.VARIANT, BlockSmoothDarkAndesite.VariantType.NORMAL));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockSmoothDarkAndesite.VariantType) iblockdata.get(BlockSmoothDarkAndesite.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
    	nonnulllist.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.NORMAL.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockSmoothDarkAndesite.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockSmoothDarkAndesite.VariantType) iblockdata.get(BlockSmoothDarkAndesite.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockSmoothDarkAndesite.VARIANT, BlockSmoothDarkAndesite.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockSmoothDarkAndesite.VariantType) iblockdata.get(BlockSmoothDarkAndesite.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockSmoothDarkAndesite.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
        return ((BlockSmoothDarkAndesite.VariantType) iblockdata.get(BlockSmoothDarkAndesite.VARIANT)).a();
    }
    
    
    public static enum VariantType implements INamable 
    {
    	NORMAL(0, "stone_blackstone_smooth_normal", "normal", MaterialMapColor.m),
    	BRICK(1, "stone_blackstone_smooth_brick", "brick", MaterialMapColor.m),
        BRICKS(2, "stone_blackstone_smooth_bricks", "bricks", MaterialMapColor.m),
        CARVED(3, "stone_blackstone_smooth_carved", "carved", MaterialMapColor.m),
        CHISELED(4, "stone_blackstone_smooth_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockSmoothDarkAndesite.VariantType[] d = new BlockSmoothDarkAndesite.VariantType[values().length];
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

        public static BlockSmoothDarkAndesite.VariantType a(int i) {
            if (i < 0 || i >= BlockSmoothDarkAndesite.VariantType.d.length) {
                i = 0;
            }

            return BlockSmoothDarkAndesite.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockSmoothDarkAndesite.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockSmoothDarkAndesite.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockSmoothDarkAndesite.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
