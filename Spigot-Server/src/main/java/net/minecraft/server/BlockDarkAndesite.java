package net.minecraft.server;

public class BlockDarkAndesite extends Block {
	
	public static final BlockStateEnum<BlockDarkAndesite.VariantType> VARIANT = BlockStateEnum.of("variant", BlockDarkAndesite.VariantType.class);

    public BlockDarkAndesite() 
    {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockDarkAndesite.VARIANT, BlockDarkAndesite.VariantType.NORMAL));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) 
    {
        return ((BlockDarkAndesite.VariantType) iblockdata.get(BlockDarkAndesite.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) 
    {
    	nonnulllist.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.NORMAL.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.BRICK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.BRICKS.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.CARVED.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockDarkAndesite.VariantType.CHISELED.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata)
    {
        return new ItemStack(this, 1, ((BlockDarkAndesite.VariantType) iblockdata.get(BlockDarkAndesite.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i)
    {
        return this.getBlockData().set(BlockDarkAndesite.VARIANT, BlockDarkAndesite.VariantType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata)
    {
        return ((BlockDarkAndesite.VariantType) iblockdata.get(BlockDarkAndesite.VARIANT)).a();
    }

    protected BlockStateList getStateList()
    {
        return new BlockStateList(this, new IBlockState[] { BlockDarkAndesite.VARIANT });
    }

    public int getDropData(IBlockData iblockdata)
    {
        return ((BlockDarkAndesite.VariantType) iblockdata.get(BlockDarkAndesite.VARIANT)).a();
    }
    
    
    public static enum VariantType implements INamable 
    {
    	NORMAL(0, "stone_blackstone_normal", "normal", MaterialMapColor.m),
    	BRICK(1, "stone_blackstone_brick", "brick", MaterialMapColor.m),
        BRICKS(2, "stone_blackstone_bricks", "bricks", MaterialMapColor.m),
        CARVED(3, "stone_blackstone_carved", "carved", MaterialMapColor.m),
        CHISELED(4, "stone_blackstone_chiseled", "chiseled", MaterialMapColor.m),
        ;

        private static final BlockDarkAndesite.VariantType[] d = new BlockDarkAndesite.VariantType[values().length];
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

        public static BlockDarkAndesite.VariantType a(int i) {
            if (i < 0 || i >= BlockDarkAndesite.VariantType.d.length) {
                i = 0;
            }

            return BlockDarkAndesite.VariantType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockDarkAndesite.VariantType[] aBlockMoreVariant_VariantType = values();
            int i = aBlockMoreVariant_VariantType.length;

            for (int j = 0; j < i; ++j) {
            	BlockDarkAndesite.VariantType BlockMoreVariant_VariantType = aBlockMoreVariant_VariantType[j];

            	BlockDarkAndesite.VariantType.d[BlockMoreVariant_VariantType.a()] = BlockMoreVariant_VariantType;
            }

        }
    }
}
