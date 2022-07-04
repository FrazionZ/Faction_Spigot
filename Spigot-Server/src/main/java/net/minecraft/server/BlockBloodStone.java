package net.minecraft.server;

public class BlockBloodStone extends Block {
	
	public static final BlockStateEnum<BlockBloodStone.NetherrackType> VARIANT = BlockStateEnum.of("variant", BlockBloodStone.NetherrackType.class);

    public BlockBloodStone() {
        super(Material.STONE);
        this.w(this.blockStateList.getBlockData().set(BlockBloodStone.VARIANT, BlockBloodStone.NetherrackType.NETHERRACK));
        this.a(CreativeModeTab.b);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return ((BlockBloodStone.NetherrackType) iblockdata.get(BlockBloodStone.VARIANT)).d();
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
        nonnulllist.add(new ItemStack(this, 1, BlockBloodStone.NetherrackType.NETHERRACK.a()));
        nonnulllist.add(new ItemStack(this, 1, BlockBloodStone.NetherrackType.NETHERRACK_NYLIUM.a()));
    }

    public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        return new ItemStack(this, 1, ((BlockBloodStone.NetherrackType) iblockdata.get(BlockBloodStone.VARIANT)).a());
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockBloodStone.VARIANT, BlockBloodStone.NetherrackType.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((BlockBloodStone.NetherrackType) iblockdata.get(BlockBloodStone.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockBloodStone.VARIANT });
    }

    public int getDropData(IBlockData iblockdata) {
        BlockBloodStone.NetherrackType BlockBloodStone_NetherrackType = (BlockBloodStone.NetherrackType) iblockdata.get(BlockBloodStone.VARIANT);

        if (BlockBloodStone_NetherrackType == BlockBloodStone.NetherrackType.NETHERRACK) {
            BlockBloodStone_NetherrackType = BlockBloodStone.NetherrackType.NETHERRACK;
        }
        else if (BlockBloodStone_NetherrackType == BlockBloodStone.NetherrackType.NETHERRACK_NYLIUM) {
            BlockBloodStone_NetherrackType = BlockBloodStone.NetherrackType.NETHERRACK_NYLIUM;
        }

        return BlockBloodStone_NetherrackType.a();
    }
    
    
    public static enum NetherrackType implements INamable {

        NETHERRACK(0, "netherrack", "default", MaterialMapColor.m),
        NETHERRACK_NYLIUM(1, "netherrack_nylium", "nylium", MaterialMapColor.m);

        private static final BlockBloodStone.NetherrackType[] d = new BlockBloodStone.NetherrackType[values().length];
        private final int e;
        private final String f;
        private final String g;
        private final MaterialMapColor h;

        private NetherrackType(int i, String s, MaterialMapColor materialmapcolor) {
            this(i, s, s, materialmapcolor);
        }

        private NetherrackType(int i, String s, String s1, MaterialMapColor materialmapcolor) {
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

        public static BlockBloodStone.NetherrackType a(int i) {
            if (i < 0 || i >= BlockBloodStone.NetherrackType.d.length) {
                i = 0;
            }

            return BlockBloodStone.NetherrackType.d[i];
        }

        public String getName() {
            return this.f;
        }

        static {
        	BlockBloodStone.NetherrackType[] aBlockBloodStone_NetherrackType = values();
            int i = aBlockBloodStone_NetherrackType.length;

            for (int j = 0; j < i; ++j) {
            	BlockBloodStone.NetherrackType BlockBloodStone_NetherrackType = aBlockBloodStone_NetherrackType[j];

            	BlockBloodStone.NetherrackType.d[BlockBloodStone_NetherrackType.a()] = BlockBloodStone_NetherrackType;
            }

        }
    }
}
