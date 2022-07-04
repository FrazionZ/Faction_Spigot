package net.minecraft.server;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import net.minecraft.server.frazionz.blocks.BlockMoreAndesiteSmoothVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreAndesiteVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreDioriteSmoothVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreDioriteVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreGraniteSmoothVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreGraniteVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreNWBVariant;
import net.minecraft.server.frazionz.blocks.BlockMoreSandstoneVariant;
import net.minecraft.server.frazionz.items.ItemBauxiteApple;
import net.minecraft.server.frazionz.items.ItemBigXp;
import net.minecraft.server.frazionz.items.ItemBottleXP;
import net.minecraft.server.frazionz.items.ItemDonuts;
import net.minecraft.server.frazionz.items.ItemDynamite;
import net.minecraft.server.frazionz.items.ItemDynamiteArrow;
import net.minecraft.server.frazionz.items.ItemFrazionApple;
import net.minecraft.server.frazionz.items.ItemFrazionHoe;
import net.minecraft.server.frazionz.items.ItemHammer;
import net.minecraft.server.frazionz.items.ItemMultiTool;
import net.minecraft.server.frazionz.items.ItemOnyxApple;
import net.minecraft.server.frazionz.items.ItemPizza;
import net.minecraft.server.frazionz.items.ItemShulkerTrophy;
import net.minecraft.server.frazionz.items.ItemSkeletonTrophy;
import net.minecraft.server.frazionz.items.ItemSpawnerPickaxe;
import net.minecraft.server.frazionz.items.ItemUltraBow;
import net.minecraft.server.frazionz.items.ItemWitheredBoneMeal;
import net.minecraft.server.frazionz.items.ItemYelliteApple;

public class Item {

    public static final RegistryMaterials<MinecraftKey, Item> REGISTRY = new RegistryMaterials();
    private static final Map<Block, Item> a = Maps.newHashMap();
    private static final IDynamicTexture b = new IDynamicTexture() {
    };
    private static final IDynamicTexture c = new IDynamicTexture() {
    };
    private static final IDynamicTexture d = new IDynamicTexture() {
    };
    private static final IDynamicTexture e = new IDynamicTexture() {
    };
    private final IRegistry<MinecraftKey, IDynamicTexture> f = new RegistrySimple();
    protected static final UUID h = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private CreativeModeTab n;
    protected static Random j = new Random();
    protected int maxStackSize = 64;
    private int durability;
    protected boolean l;
    protected boolean m;
    private Item craftingResult;
    private String name;

    public static int getId(Item item) {
        return item == null ? 0 : Item.REGISTRY.a((Item) item);
    }

    public static Item getById(int i) {
        return (Item) Item.REGISTRY.getId(i);
    }

    public static Item getItemOf(Block block) {
        Item item = (Item) Item.a.get(block);

        return item == null ? Items.a : item;
    }

    @Nullable
    public static Item b(String s) {
        Item item = (Item) Item.REGISTRY.get(new MinecraftKey(s));

        if (item == null) {
            try {
                return getById(Integer.parseInt(s));
            } catch (NumberFormatException numberformatexception) {
                ;
            }
        }

        return item;
    }

    public final void a(MinecraftKey minecraftkey, IDynamicTexture idynamictexture) {
        this.f.a(minecraftkey, idynamictexture);
    }

    public boolean a(NBTTagCompound nbttagcompound) {
        return false;
    }

    public Item() {
        this.a(new MinecraftKey("lefthanded"), Item.d);
    }

    public Item d(int i) {
        this.maxStackSize = i;
        return this;
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        return EnumInteractionResult.PASS;
    }

    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        return 1.0F;
    }

    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        return new InteractionResultWrapper(EnumInteractionResult.PASS, entityhuman.b(enumhand));
    }

    public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
        return itemstack;
    }

    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    public int filterData(int i) {
        return 0;
    }

    public boolean k() {
        return this.m;
    }

    protected Item a(boolean flag) {
        this.m = flag;
        return this;
    }

    public int getMaxDurability() {
        return this.durability;
    }

    protected Item setMaxDurability(int i) {
        this.durability = i;
        if (i > 0) {
            this.a(new MinecraftKey("damaged"), Item.b);
            this.a(new MinecraftKey("damage"), Item.c);
        }

        return this;
    }

    public boolean usesDurability() {
        return this.durability > 0 && (!this.m || this.maxStackSize == 1);
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
        return false;
    }

    public boolean canDestroySpecialBlock(IBlockData iblockdata) {
        return false;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
        return false;
    }

    public Item n() {
        this.l = true;
        return this;
    }

    public Item c(String s) {
        this.name = s;
        return this;
    }

    public String j(ItemStack itemstack) {
        return LocaleI18n.get(this.a(itemstack));
    }

    public String getName() {
        return "item." + this.name;
    }

    public String a(ItemStack itemstack) {
        return "item." + this.name;
    }

    public Item b(Item item) {
        this.craftingResult = item;
        return this;
    }

    public boolean p() {
        return true;
    }

    @Nullable
    public Item q() {
        return this.craftingResult;
    }

    public boolean r() {
        return this.craftingResult != null;
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}

    public void b(ItemStack itemstack, World world, EntityHuman entityhuman) {}

    public boolean f() {
        return false;
    }

    public EnumAnimation f(ItemStack itemstack) {
        return EnumAnimation.NONE;
    }

    public int e(ItemStack itemstack) {
        return 0;
    }

    public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {}

    public String b(ItemStack itemstack) {
        return LocaleI18n.get(this.j(itemstack) + ".name").trim();
    }

    public EnumItemRarity g(ItemStack itemstack) {
        return itemstack.hasEnchantments() ? EnumItemRarity.RARE : EnumItemRarity.COMMON;
    }

    public boolean g_(ItemStack itemstack) {
        return this.getMaxStackSize() == 1 && this.usesDurability();
    }

    protected MovingObjectPosition a(World world, EntityHuman entityhuman, boolean flag) {
        float f = entityhuman.pitch;
        float f1 = entityhuman.yaw;
        double d0 = entityhuman.locX;
        double d1 = entityhuman.locY + (double) entityhuman.getHeadHeight();
        double d2 = entityhuman.locZ;
        Vec3D vec3d = new Vec3D(d0, d1, d2);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d3 = 5.0D;
        Vec3D vec3d1 = vec3d.add((double) f6 * 5.0D, (double) f5 * 5.0D, (double) f7 * 5.0D);

        return world.rayTrace(vec3d, vec3d1, flag, !flag, false);
    }

    public int c() {
        return 0;
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
        if (this.a(creativemodetab)) {
            nonnulllist.add(new ItemStack(this));
        }
    }

    protected boolean a(CreativeModeTab creativemodetab) {
        CreativeModeTab creativemodetab1 = this.b();

        return creativemodetab1 != null && (creativemodetab == CreativeModeTab.g || creativemodetab == creativemodetab1);
    }

    @Nullable
    public CreativeModeTab b() {
        return this.n;
    }

    public Item b(CreativeModeTab creativemodetab) {
        this.n = creativemodetab;
        return this;
    }

    public boolean s() {
        return false;
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return false;
    }

    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        return HashMultimap.create();
    }

    public static void t() {
        a(Blocks.AIR, (Item) (new ItemAir(Blocks.AIR)));
        a(Blocks.STONE, (new ItemMultiTexture(Blocks.STONE, Blocks.STONE, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockStone.EnumStoneVariant.a(itemstack.getData()).d();
            }
        })).c("stone"));
        a((Block) Blocks.GRASS, (Item) (new ItemWithAuxData(Blocks.GRASS, false)));
        a(Blocks.DIRT, (new ItemMultiTexture(Blocks.DIRT, Blocks.DIRT, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockDirt.EnumDirtVariant.a(itemstack.getData()).c();
            }
        })).c("dirt"));
        b(Blocks.COBBLESTONE);
        a(Blocks.PLANKS, (new ItemMultiTexture(Blocks.PLANKS, Blocks.PLANKS, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockWood.EnumLogVariant.a(itemstack.getData()).d();
            }
        })).c("wood"));
        a(Blocks.SAPLING, (new ItemMultiTexture(Blocks.SAPLING, Blocks.SAPLING, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockWood.EnumLogVariant.a(itemstack.getData()).d();
            }
        })).c("sapling"));
        b(Blocks.BEDROCK);
        a((Block) Blocks.SAND, (new ItemMultiTexture(Blocks.SAND, Blocks.SAND, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockSand.EnumSandVariant.a(itemstack.getData()).e();
            }
        })).c("sand"));
        b(Blocks.GRAVEL);
        b(Blocks.GOLD_ORE);
        b(Blocks.IRON_ORE);
        b(Blocks.COAL_ORE);
        a(Blocks.LOG, (new ItemMultiTexture(Blocks.LOG, Blocks.LOG, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockWood.EnumLogVariant.a(itemstack.getData()).d();
            }
        })).c("log"));
        a(Blocks.LOG2, (new ItemMultiTexture(Blocks.LOG2, Blocks.LOG2, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockWood.EnumLogVariant.a(itemstack.getData() + 4).d();
            }
        })).c("log"));
        a((Block) Blocks.LEAVES, (new ItemLeaves(Blocks.LEAVES)).c("leaves"));
        a((Block) Blocks.LEAVES2, (new ItemLeaves(Blocks.LEAVES2)).c("leaves"));
        a(Blocks.SPONGE, (new ItemMultiTexture(Blocks.SPONGE, Blocks.SPONGE, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return (itemstack.getData() & 1) == 1 ? "wet" : "dry";
            }
        })).c("sponge"));
        b(Blocks.GLASS);
        b(Blocks.LAPIS_ORE);
        b(Blocks.LAPIS_BLOCK);
        b(Blocks.DISPENSER);
        a(Blocks.SANDSTONE, (new ItemMultiTexture(Blocks.SANDSTONE, Blocks.SANDSTONE, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockSandStone.EnumSandstoneVariant.a(itemstack.getData()).c();
            }
        })).c("sandStone"));
        b(Blocks.NOTEBLOCK);
        b(Blocks.GOLDEN_RAIL);
        b(Blocks.DETECTOR_RAIL);
        a((Block) Blocks.STICKY_PISTON, (Item) (new ItemPiston(Blocks.STICKY_PISTON)));
        b(Blocks.WEB);
        a((Block) Blocks.TALLGRASS, (Item) (new ItemWithAuxData(Blocks.TALLGRASS, true)).a(new String[] { "shrub", "grass", "fern"}));
        b((Block) Blocks.DEADBUSH);
        a((Block) Blocks.PISTON, (Item) (new ItemPiston(Blocks.PISTON)));
        a(Blocks.WOOL, (new ItemCloth(Blocks.WOOL)).c("cloth"));
        a((Block) Blocks.YELLOW_FLOWER, (new ItemMultiTexture(Blocks.YELLOW_FLOWER, Blocks.YELLOW_FLOWER, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockFlowers.EnumFlowerVarient.a(BlockFlowers.EnumFlowerType.YELLOW, itemstack.getData()).d();
            }
        })).c("flower"));
        a((Block) Blocks.RED_FLOWER, (new ItemMultiTexture(Blocks.RED_FLOWER, Blocks.RED_FLOWER, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockFlowers.EnumFlowerVarient.a(BlockFlowers.EnumFlowerType.RED, itemstack.getData()).d();
            }
        })).c("rose"));
        b((Block) Blocks.BROWN_MUSHROOM);
        b((Block) Blocks.RED_MUSHROOM);
        b(Blocks.GOLD_BLOCK);
        b(Blocks.IRON_BLOCK);
        a((Block) Blocks.STONE_SLAB, (new ItemStep(Blocks.STONE_SLAB, Blocks.STONE_SLAB, Blocks.DOUBLE_STONE_SLAB)).c("stoneSlab"));
        b(Blocks.BRICK_BLOCK);
        b(Blocks.TNT);
        b(Blocks.BOOKSHELF);
        b(Blocks.MOSSY_COBBLESTONE);
        b(Blocks.OBSIDIAN);
        b(Blocks.TORCH);
        b(Blocks.END_ROD);
        b(Blocks.CHORUS_PLANT);
        b(Blocks.CHORUS_FLOWER);
        b(Blocks.PURPUR_BLOCK);
        b(Blocks.PURPUR_PILLAR);
        b(Blocks.PURPUR_STAIRS);
        a((Block) Blocks.PURPUR_SLAB, (new ItemStep(Blocks.PURPUR_SLAB, Blocks.PURPUR_SLAB, Blocks.PURPUR_DOUBLE_SLAB)).c("purpurSlab"));
        b(Blocks.MOB_SPAWNER);
        b(Blocks.OAK_STAIRS);
        b((Block) Blocks.CHEST);
        b(Blocks.DIAMOND_ORE);
        b(Blocks.DIAMOND_BLOCK);
        b(Blocks.CRAFTING_TABLE);
        b(Blocks.FARMLAND);
        b(Blocks.FURNACE);
        b(Blocks.LADDER);
        b(Blocks.RAIL);
        b(Blocks.STONE_STAIRS);
        b(Blocks.LEVER);
        b(Blocks.STONE_PRESSURE_PLATE);
        b(Blocks.WOODEN_PRESSURE_PLATE);
        b(Blocks.REDSTONE_ORE);
        b(Blocks.REDSTONE_TORCH);
        b(Blocks.STONE_BUTTON);
        a(Blocks.SNOW_LAYER, (Item) (new ItemSnow(Blocks.SNOW_LAYER)));
        b(Blocks.ICE);
        b(Blocks.SNOW);
        b((Block) Blocks.CACTUS);
        b(Blocks.CLAY);
        b(Blocks.JUKEBOX);
        b(Blocks.FENCE);
        b(Blocks.SPRUCE_FENCE);
        b(Blocks.BIRCH_FENCE);
        b(Blocks.JUNGLE_FENCE);
        b(Blocks.DARK_OAK_FENCE);
        b(Blocks.ACACIA_FENCE);
        b(Blocks.PUMPKIN);
        a(Blocks.NETHERRACK, (new ItemMultiTexture(Blocks.NETHERRACK, Blocks.NETHERRACK, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockBloodStone.NetherrackType.a(itemstack.getData()).c();
            }
        })).c("netherrack"));
        b(Blocks.SOUL_SAND);
        b(Blocks.GLOWSTONE);
        b(Blocks.LIT_PUMPKIN);
        b(Blocks.TRAPDOOR);
        a(Blocks.MONSTER_EGG, (new ItemMultiTexture(Blocks.MONSTER_EGG, Blocks.MONSTER_EGG, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMonsterEggs.EnumMonsterEggVarient.a(itemstack.getData()).c();
            }
        })).c("monsterStoneEgg"));
        a(Blocks.STONEBRICK, (new ItemMultiTexture(Blocks.STONEBRICK, Blocks.STONEBRICK, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockSmoothBrick.EnumStonebrickType.a(itemstack.getData()).c();
            }
        })).c("stonebricksmooth"));
        b(Blocks.BROWN_MUSHROOM_BLOCK);
        b(Blocks.RED_MUSHROOM_BLOCK);
        b(Blocks.IRON_BARS);
        b(Blocks.GLASS_PANE);
        b(Blocks.MELON_BLOCK);
        a(Blocks.VINE, (Item) (new ItemWithAuxData(Blocks.VINE, false)));
        b(Blocks.FENCE_GATE);
        b(Blocks.SPRUCE_FENCE_GATE);
        b(Blocks.BIRCH_FENCE_GATE);
        b(Blocks.JUNGLE_FENCE_GATE);
        b(Blocks.DARK_OAK_FENCE_GATE);
        b(Blocks.ACACIA_FENCE_GATE);
        b(Blocks.BRICK_STAIRS);
        b(Blocks.STONE_BRICK_STAIRS);
        b((Block) Blocks.MYCELIUM);
        a(Blocks.WATERLILY, (Item) (new ItemWaterLily(Blocks.WATERLILY)));
        b(Blocks.NETHER_BRICK);
        b(Blocks.NETHER_BRICK_FENCE);
        b(Blocks.NETHER_BRICK_STAIRS);
        b(Blocks.ENCHANTING_TABLE);
        b(Blocks.END_PORTAL_FRAME);
        b(Blocks.END_STONE);
        b(Blocks.END_BRICKS);
        b(Blocks.DRAGON_EGG);
        b(Blocks.REDSTONE_LAMP);
        a((Block) Blocks.WOODEN_SLAB, (new ItemStep(Blocks.WOODEN_SLAB, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB)).c("woodSlab"));
        b(Blocks.SANDSTONE_STAIRS);
        b(Blocks.EMERALD_ORE);
        b(Blocks.ENDER_CHEST);
        b((Block) Blocks.TRIPWIRE_HOOK);
        b(Blocks.EMERALD_BLOCK);
        b(Blocks.SPRUCE_STAIRS);
        b(Blocks.BIRCH_STAIRS);
        b(Blocks.JUNGLE_STAIRS);
        b(Blocks.COMMAND_BLOCK);
        b((Block) Blocks.BEACON);
        a(Blocks.COBBLESTONE_WALL, (new ItemMultiTexture(Blocks.COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockCobbleWall.EnumCobbleVariant.a(itemstack.getData()).c();
            }
        })).c("cobbleWall"));
        b(Blocks.WOODEN_BUTTON);
        a(Blocks.ANVIL, (new ItemAnvil(Blocks.ANVIL)).c("anvil"));
        b(Blocks.TRAPPED_CHEST);
        b(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
        b(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE);
        b((Block) Blocks.DAYLIGHT_DETECTOR);
        b(Blocks.REDSTONE_BLOCK);
        b(Blocks.QUARTZ_ORE);
        b((Block) Blocks.HOPPER);
        a(Blocks.QUARTZ_BLOCK, (new ItemMultiTexture(Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, new String[] { "default", "chiseled", "lines"})).c("quartzBlock"));
        b(Blocks.QUARTZ_STAIRS);
        b(Blocks.ACTIVATOR_RAIL);
        b(Blocks.DROPPER);
        a(Blocks.STAINED_HARDENED_CLAY, (new ItemCloth(Blocks.STAINED_HARDENED_CLAY)).c("clayHardenedStained"));
        b(Blocks.BARRIER);
        b(Blocks.IRON_TRAPDOOR);
        b(Blocks.HAY_BLOCK);
        a(Blocks.CARPET, (new ItemCloth(Blocks.CARPET)).c("woolCarpet"));
        b(Blocks.HARDENED_CLAY);
        b(Blocks.COAL_BLOCK);
        b(Blocks.PACKED_ICE);
        b(Blocks.ACACIA_STAIRS);
        b(Blocks.DARK_OAK_STAIRS);
        b(Blocks.SLIME);
        b(Blocks.GRASS_PATH);
        a((Block) Blocks.DOUBLE_PLANT, (new ItemMultiTexture(Blocks.DOUBLE_PLANT, Blocks.DOUBLE_PLANT, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockTallPlant.EnumTallFlowerVariants.a(itemstack.getData()).c();
            }
        })).c("doublePlant"));
        a((Block) Blocks.STAINED_GLASS, (new ItemCloth(Blocks.STAINED_GLASS)).c("stainedGlass"));
        a((Block) Blocks.STAINED_GLASS_PANE, (new ItemCloth(Blocks.STAINED_GLASS_PANE)).c("stainedGlassPane"));
        a(Blocks.PRISMARINE, (new ItemMultiTexture(Blocks.PRISMARINE, Blocks.PRISMARINE, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockPrismarine.EnumPrismarineVariant.a(itemstack.getData()).c();
            }
        })).c("prismarine"));
        b(Blocks.SEA_LANTERN);
        a(Blocks.RED_SANDSTONE, (new ItemMultiTexture(Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockRedSandstone.EnumRedSandstoneVariant.a(itemstack.getData()).c();
            }
        })).c("redSandStone"));
        b(Blocks.RED_SANDSTONE_STAIRS);
        a((Block) Blocks.STONE_SLAB2, (new ItemStep(Blocks.STONE_SLAB2, Blocks.STONE_SLAB2, Blocks.DOUBLE_STONE_SLAB2)).c("stoneSlab2"));
        b(Blocks.dc);
        b(Blocks.dd);
        b(Blocks.df);
        b(Blocks.dg);
        b(Blocks.dh);
        b(Blocks.di);
        b(Blocks.dj);
        b(Blocks.dk);
        a(Blocks.WHITE_SHULKER_BOX, (Item) (new ItemShulkerBox(Blocks.WHITE_SHULKER_BOX)));
        a(Blocks.dm, (Item) (new ItemShulkerBox(Blocks.dm)));
        a(Blocks.dn, (Item) (new ItemShulkerBox(Blocks.dn)));
        a(Blocks.LIGHT_BLUE_SHULKER_BOX, (Item) (new ItemShulkerBox(Blocks.LIGHT_BLUE_SHULKER_BOX)));
        a(Blocks.dp, (Item) (new ItemShulkerBox(Blocks.dp)));
        a(Blocks.dq, (Item) (new ItemShulkerBox(Blocks.dq)));
        a(Blocks.dr, (Item) (new ItemShulkerBox(Blocks.dr)));
        a(Blocks.ds, (Item) (new ItemShulkerBox(Blocks.ds)));
        a(Blocks.dt, (Item) (new ItemShulkerBox(Blocks.dt)));
        a(Blocks.du, (Item) (new ItemShulkerBox(Blocks.du)));
        a(Blocks.dv, (Item) (new ItemShulkerBox(Blocks.dv)));
        a(Blocks.dw, (Item) (new ItemShulkerBox(Blocks.dw)));
        a(Blocks.dx, (Item) (new ItemShulkerBox(Blocks.dx)));
        a(Blocks.dy, (Item) (new ItemShulkerBox(Blocks.dy)));
        a(Blocks.dz, (Item) (new ItemShulkerBox(Blocks.dz)));
        a(Blocks.dA, (Item) (new ItemShulkerBox(Blocks.dA)));
        b(Blocks.dB);
        b(Blocks.dC);
        b(Blocks.dD);
        b(Blocks.dE);
        b(Blocks.dF);
        b(Blocks.dG);
        b(Blocks.dH);
        b(Blocks.dI);
        b(Blocks.dJ);
        b(Blocks.dK);
        b(Blocks.dL);
        b(Blocks.dM);
        b(Blocks.dN);
        b(Blocks.dO);
        b(Blocks.dP);
        b(Blocks.dQ);
        a(Blocks.dR, (new ItemCloth(Blocks.dR)).c("concrete"));
        a(Blocks.dS, (new ItemCloth(Blocks.dS)).c("concrete_powder"));
        b(Blocks.STRUCTURE_BLOCK);
        a(256, "iron_shovel", (new ItemSpade(Item.EnumToolMaterial.IRON)).c("shovelIron"));
        a(257, "iron_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.IRON)).c("pickaxeIron"));
        a(258, "iron_axe", (new ItemAxe(Item.EnumToolMaterial.IRON)).c("hatchetIron"));
        a(259, "flint_and_steel", (new ItemFlintAndSteel()).c("flintAndSteel"));
        a(260, "apple", (new ItemFood(4, 0.3F, false)).c("apple"));
        a(261, "bow", (new ItemBow()).c("bow"));
        a(262, "arrow", (new ItemArrow()).c("arrow"));
        a(263, "coal", (new ItemCoal()).c("coal"));
        a(264, "diamond", (new Item()).c("diamond").b(CreativeModeTab.l));
        a(265, "iron_ingot", (new Item()).c("ingotIron").b(CreativeModeTab.l));
        a(266, "gold_ingot", (new Item()).c("ingotGold").b(CreativeModeTab.l));
        a(267, "iron_sword", (new ItemSword(Item.EnumToolMaterial.IRON)).c("swordIron"));
        a(268, "wooden_sword", (new ItemSword(Item.EnumToolMaterial.WOOD)).c("swordWood"));
        a(269, "wooden_shovel", (new ItemSpade(Item.EnumToolMaterial.WOOD)).c("shovelWood"));
        a(270, "wooden_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.WOOD)).c("pickaxeWood"));
        a(271, "wooden_axe", (new ItemAxe(Item.EnumToolMaterial.WOOD)).c("hatchetWood"));
        a(272, "stone_sword", (new ItemSword(Item.EnumToolMaterial.STONE)).c("swordStone"));
        a(273, "stone_shovel", (new ItemSpade(Item.EnumToolMaterial.STONE)).c("shovelStone"));
        a(274, "stone_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.STONE)).c("pickaxeStone"));
        a(275, "stone_axe", (new ItemAxe(Item.EnumToolMaterial.STONE)).c("hatchetStone"));
        a(276, "diamond_sword", (new ItemSword(Item.EnumToolMaterial.DIAMOND)).c("swordDiamond"));
        a(277, "diamond_shovel", (new ItemSpade(Item.EnumToolMaterial.DIAMOND)).c("shovelDiamond"));
        a(278, "diamond_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.DIAMOND)).c("pickaxeDiamond"));
        a(279, "diamond_axe", (new ItemAxe(Item.EnumToolMaterial.DIAMOND)).c("hatchetDiamond"));
        a(280, "stick", (new Item()).n().c("stick").b(CreativeModeTab.l));
        a(281, "bowl", (new Item()).c("bowl").b(CreativeModeTab.l));
        a(282, "mushroom_stew", (new ItemSoup(6)).c("mushroomStew"));
        a(283, "golden_sword", (new ItemSword(Item.EnumToolMaterial.GOLD)).c("swordGold"));
        a(284, "golden_shovel", (new ItemSpade(Item.EnumToolMaterial.GOLD)).c("shovelGold"));
        a(285, "golden_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.GOLD)).c("pickaxeGold"));
        a(286, "golden_axe", (new ItemAxe(Item.EnumToolMaterial.GOLD)).c("hatchetGold"));
        a(287, "string", (new ItemReed(Blocks.TRIPWIRE)).c("string").b(CreativeModeTab.l));
        a(288, "feather", (new Item()).c("feather").b(CreativeModeTab.l));
        a(289, "gunpowder", (new Item()).c("sulphur").b(CreativeModeTab.l));
        a(290, "wooden_hoe", (new ItemHoe(Item.EnumToolMaterial.WOOD)).c("hoeWood"));
        a(291, "stone_hoe", (new ItemHoe(Item.EnumToolMaterial.STONE)).c("hoeStone"));
        a(292, "iron_hoe", (new ItemHoe(Item.EnumToolMaterial.IRON)).c("hoeIron"));
        a(293, "diamond_hoe", (new ItemHoe(Item.EnumToolMaterial.DIAMOND)).c("hoeDiamond"));
        a(294, "golden_hoe", (new ItemHoe(Item.EnumToolMaterial.GOLD)).c("hoeGold"));
        a(295, "wheat_seeds", (new ItemSeeds(Blocks.WHEAT, Blocks.FARMLAND)).c("seeds"));
        a(296, "wheat", (new Item()).c("wheat").b(CreativeModeTab.l));
        a(297, "bread", (new ItemFood(5, 0.6F, false)).c("bread"));
        a(298, "leather_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.HEAD)).c("helmetCloth"));
        a(299, "leather_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.CHEST)).c("chestplateCloth"));
        a(300, "leather_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.LEGS)).c("leggingsCloth"));
        a(301, "leather_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.LEATHER, 0, EnumItemSlot.FEET)).c("bootsCloth"));
        a(302, "chainmail_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.HEAD)).c("helmetChain"));
        a(303, "chainmail_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.CHEST)).c("chestplateChain"));
        a(304, "chainmail_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.LEGS)).c("leggingsChain"));
        a(305, "chainmail_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.CHAIN, 1, EnumItemSlot.FEET)).c("bootsChain"));
        a(306, "iron_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.HEAD)).c("helmetIron"));
        a(307, "iron_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.CHEST)).c("chestplateIron"));
        a(308, "iron_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.LEGS)).c("leggingsIron"));
        a(309, "iron_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.IRON, 2, EnumItemSlot.FEET)).c("bootsIron"));
        a(310, "diamond_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.HEAD)).c("helmetDiamond"));
        a(311, "diamond_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.CHEST)).c("chestplateDiamond"));
        a(312, "diamond_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.LEGS)).c("leggingsDiamond"));
        a(313, "diamond_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.DIAMOND, 3, EnumItemSlot.FEET)).c("bootsDiamond"));
        a(314, "golden_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.HEAD)).c("helmetGold"));
        a(315, "golden_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.CHEST)).c("chestplateGold"));
        a(316, "golden_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.LEGS)).c("leggingsGold"));
        a(317, "golden_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.GOLD, 4, EnumItemSlot.FEET)).c("bootsGold"));
        a(318, "flint", (new Item()).c("flint").b(CreativeModeTab.l));
        a(319, "porkchop", (new ItemFood(3, 0.3F, true)).c("porkchopRaw"));
        a(320, "cooked_porkchop", (new ItemFood(8, 0.8F, true)).c("porkchopCooked"));
        a(321, "painting", (new ItemHanging(EntityPainting.class)).c("painting"));
        a(322, "golden_apple", (new ItemGoldenApple(4, 1.2F, false)).h().c("appleGold"));
        a(323, "sign", (new ItemSign()).c("sign"));
        a(324, "wooden_door", (new ItemDoor(Blocks.WOODEN_DOOR)).c("doorOak"));
        Item item = (new ItemBucket(Blocks.AIR)).c("bucket").d(16);

        a(325, "bucket", item);
        a(326, "water_bucket", (new ItemBucket(Blocks.FLOWING_WATER)).c("bucketWater").b(item));
        a(327, "lava_bucket", (new ItemBucket(Blocks.FLOWING_LAVA)).c("bucketLava").b(item));
        a(328, "minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.RIDEABLE)).c("minecart"));
        a(329, "saddle", (new ItemSaddle()).c("saddle"));
        a(330, "iron_door", (new ItemDoor(Blocks.IRON_DOOR)).c("doorIron"));
        a(331, "redstone", (new ItemRedstone()).c("redstone"));
        a(332, "snowball", (new ItemSnowball()).c("snowball"));
        a(333, "boat", new ItemBoat(EntityBoat.EnumBoatType.OAK));
        a(334, "leather", (new Item()).c("leather").b(CreativeModeTab.l));
        a(335, "milk_bucket", (new ItemMilkBucket()).c("milk").b(item));
        a(336, "brick", (new Item()).c("brick").b(CreativeModeTab.l));
        a(337, "clay_ball", (new Item()).c("clay").b(CreativeModeTab.l));
        a(338, "reeds", (new ItemReed(Blocks.REEDS)).c("reeds").b(CreativeModeTab.l));
        a(339, "paper", (new Item()).c("paper").b(CreativeModeTab.f));
        a(340, "book", (new ItemBook()).c("book").b(CreativeModeTab.f));
        a(341, "slime_ball", (new Item()).c("slimeball").b(CreativeModeTab.f));
        a(342, "chest_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.CHEST)).c("minecartChest"));
        a(343, "furnace_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.FURNACE)).c("minecartFurnace"));
        a(344, "egg", (new ItemEgg()).c("egg"));
        a(345, "compass", (new ItemCompass()).c("compass").b(CreativeModeTab.i));
        a(346, "fishing_rod", (new ItemFishingRod()).c("fishingRod"));
        a(347, "clock", (new ItemClock()).c("clock").b(CreativeModeTab.i));
        a(348, "glowstone_dust", (new Item()).c("yellowDust").b(CreativeModeTab.l));
        a(349, "fish", (new ItemFish(false)).c("fish").a(true));
        a(350, "cooked_fish", (new ItemFish(true)).c("fish").a(true));
        a(351, "dye", (new ItemDye()).c("dyePowder"));
        a(352, "bone", (new Item()).c("bone").n().b(CreativeModeTab.f));
        a(353, "sugar", (new Item()).c("sugar").b(CreativeModeTab.l));
        a(354, "cake", (new ItemReed(Blocks.CAKE)).d(1).c("cake").b(CreativeModeTab.h));
        a(355, "bed", (new ItemBed()).d(1).c("bed"));
        a(356, "repeater", (new ItemReed(Blocks.UNPOWERED_REPEATER)).c("diode").b(CreativeModeTab.d));
        a(357, "cookie", (new ItemFood(2, 0.1F, false)).c("cookie"));
        a(358, "filled_map", (new ItemWorldMap()).c("map"));
        a(359, "shears", (new ItemShears()).c("shears"));
        a(360, "melon", (new ItemFood(2, 0.3F, false)).c("melon"));
        a(361, "pumpkin_seeds", (new ItemSeeds(Blocks.PUMPKIN_STEM, Blocks.FARMLAND)).c("seeds_pumpkin"));
        a(362, "melon_seeds", (new ItemSeeds(Blocks.MELON_STEM, Blocks.FARMLAND)).c("seeds_melon"));
        a(363, "beef", (new ItemFood(3, 0.3F, true)).c("beefRaw"));
        a(364, "cooked_beef", (new ItemFood(8, 0.8F, true)).c("beefCooked"));
        a(365, "chicken", (new ItemFood(2, 0.3F, true)).a(new MobEffect(MobEffects.HUNGER, 600, 0), 0.3F).c("chickenRaw"));
        a(366, "cooked_chicken", (new ItemFood(6, 0.6F, true)).c("chickenCooked"));
        a(367, "rotten_flesh", (new ItemFood(4, 0.1F, true)).a(new MobEffect(MobEffects.HUNGER, 600, 0), 0.8F).c("rottenFlesh"));
        a(368, "ender_pearl", (new ItemEnderPearl()).c("enderPearl"));
        a(369, "blaze_rod", (new Item()).c("blazeRod").b(CreativeModeTab.l).n());
        a(370, "ghast_tear", (new Item()).c("ghastTear").b(CreativeModeTab.k));
        a(371, "gold_nugget", (new Item()).c("goldNugget").b(CreativeModeTab.l));
        a(372, "nether_wart", (new ItemSeeds(Blocks.NETHER_WART, Blocks.SOUL_SAND)).c("netherStalkSeeds"));
        a(373, "potion", (new ItemPotion()).c("potion"));
        Item item1 = (new ItemGlassBottle()).c("glassBottle");

        a(374, "glass_bottle", item1);
        a(375, "spider_eye", (new ItemFood(2, 0.8F, false)).a(new MobEffect(MobEffects.POISON, 100, 0), 1.0F).c("spiderEye"));
        a(376, "fermented_spider_eye", (new Item()).c("fermentedSpiderEye").b(CreativeModeTab.k));
        a(377, "blaze_powder", (new Item()).c("blazePowder").b(CreativeModeTab.k));
        a(378, "magma_cream", (new Item()).c("magmaCream").b(CreativeModeTab.k));
        a(379, "brewing_stand", (new ItemReed(Blocks.BREWING_STAND)).c("brewingStand").b(CreativeModeTab.k));
        a(380, "cauldron", (new ItemReed(Blocks.cauldron)).c("cauldron").b(CreativeModeTab.k));
        a(381, "ender_eye", (new ItemEnderEye()).c("eyeOfEnder"));
        a(382, "speckled_melon", (new Item()).c("speckledMelon").b(CreativeModeTab.k));
        a(383, "spawn_egg", (new ItemMonsterEgg()).c("monsterPlacer"));
        a(384, "experience_bottle", (new ItemExpBottle()).c("expBottle"));
        a(385, "fire_charge", (new ItemFireball()).c("fireball"));
        a(386, "writable_book", (new ItemBookAndQuill()).c("writingBook").b(CreativeModeTab.f));
        a(387, "written_book", (new ItemWrittenBook()).c("writtenBook").d(16));
        a(388, "emerald", (new Item()).c("emerald").b(CreativeModeTab.l));
        a(389, "item_frame", (new ItemHanging(EntityItemFrame.class)).c("frame"));
        a(390, "flower_pot", (new ItemReed(Blocks.FLOWER_POT)).c("flowerPot").b(CreativeModeTab.c));
        a(391, "carrot", (new ItemSeedFood(3, 0.6F, Blocks.CARROTS, Blocks.FARMLAND)).c("carrots"));
        a(392, "potato", (new ItemSeedFood(1, 0.3F, Blocks.POTATOES, Blocks.FARMLAND)).c("potato"));
        a(393, "baked_potato", (new ItemFood(5, 0.6F, false)).c("potatoBaked"));
        a(394, "poisonous_potato", (new ItemFood(2, 0.3F, false)).a(new MobEffect(MobEffects.POISON, 100, 0), 0.6F).c("potatoPoisonous"));
        a(395, "map", (new ItemMapEmpty()).c("emptyMap"));
        a(396, "golden_carrot", (new ItemFood(6, 1.2F, false)).c("carrotGolden").b(CreativeModeTab.k));
        a(397, "skull", (new ItemSkull()).c("skull"));
        a(398, "carrot_on_a_stick", (new ItemCarrotStick()).c("carrotOnAStick"));
        a(399, "nether_star", (new ItemNetherStar()).c("netherStar").b(CreativeModeTab.l));
        a(400, "pumpkin_pie", (new ItemFood(8, 0.3F, false)).c("pumpkinPie").b(CreativeModeTab.h));
        a(401, "fireworks", (new ItemFireworks()).c("fireworks"));
        a(402, "firework_charge", (new ItemFireworksCharge()).c("fireworksCharge").b(CreativeModeTab.f));
        a(403, "enchanted_book", (new ItemEnchantedBook()).d(1).c("enchantedBook"));
        a(404, "comparator", (new ItemReed(Blocks.UNPOWERED_COMPARATOR)).c("comparator").b(CreativeModeTab.d));
        a(405, "netherbrick", (new Item()).c("netherbrick").b(CreativeModeTab.l));
        a(406, "quartz", (new Item()).c("netherquartz").b(CreativeModeTab.l));
        a(407, "tnt_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.TNT)).c("minecartTnt"));
        a(408, "hopper_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.HOPPER)).c("minecartHopper"));
        a(409, "prismarine_shard", (new Item()).c("prismarineShard").b(CreativeModeTab.l));
        a(410, "prismarine_crystals", (new Item()).c("prismarineCrystals").b(CreativeModeTab.l));
        a(411, "rabbit", (new ItemFood(3, 0.3F, true)).c("rabbitRaw"));
        a(412, "cooked_rabbit", (new ItemFood(5, 0.6F, true)).c("rabbitCooked"));
        a(413, "rabbit_stew", (new ItemSoup(10)).c("rabbitStew"));
        a(414, "rabbit_foot", (new Item()).c("rabbitFoot").b(CreativeModeTab.k));
        a(415, "rabbit_hide", (new Item()).c("rabbitHide").b(CreativeModeTab.l));
        a(416, "armor_stand", (new ItemArmorStand()).c("armorStand").d(16));
        a(417, "iron_horse_armor", (new Item()).c("horsearmormetal").d(1).b(CreativeModeTab.f));
        a(418, "golden_horse_armor", (new Item()).c("horsearmorgold").d(1).b(CreativeModeTab.f));
        a(419, "diamond_horse_armor", (new Item()).c("horsearmordiamond").d(1).b(CreativeModeTab.f));
        a(420, "lead", (new ItemLeash()).c("leash"));
        a(421, "name_tag", (new ItemNameTag()).c("nameTag"));
        a(422, "command_block_minecart", (new ItemMinecart(EntityMinecartAbstract.EnumMinecartType.COMMAND_BLOCK)).c("minecartCommandBlock").b((CreativeModeTab) null));
        a(423, "mutton", (new ItemFood(2, 0.3F, true)).c("muttonRaw"));
        a(424, "cooked_mutton", (new ItemFood(6, 0.8F, true)).c("muttonCooked"));
        a(425, "banner", (new ItemBanner()).c("banner"));
        a(426, "end_crystal", new ItemEndCrystal());
        a(427, "spruce_door", (new ItemDoor(Blocks.SPRUCE_DOOR)).c("doorSpruce"));
        a(428, "birch_door", (new ItemDoor(Blocks.BIRCH_DOOR)).c("doorBirch"));
        a(429, "jungle_door", (new ItemDoor(Blocks.JUNGLE_DOOR)).c("doorJungle"));
        a(430, "acacia_door", (new ItemDoor(Blocks.ACACIA_DOOR)).c("doorAcacia"));
        a(431, "dark_oak_door", (new ItemDoor(Blocks.DARK_OAK_DOOR)).c("doorDarkOak"));
        a(432, "chorus_fruit", (new ItemChorusFruit(4, 0.3F)).h().c("chorusFruit").b(CreativeModeTab.l));
        a(433, "chorus_fruit_popped", (new Item()).c("chorusFruitPopped").b(CreativeModeTab.l));
        a(434, "beetroot", (new ItemFood(1, 0.6F, false)).c("beetroot"));
        a(435, "beetroot_seeds", (new ItemSeeds(Blocks.BEETROOT, Blocks.FARMLAND)).c("beetroot_seeds"));
        a(436, "beetroot_soup", (new ItemSoup(6)).c("beetroot_soup"));
        a(437, "dragon_breath", (new Item()).b(CreativeModeTab.k).c("dragon_breath").b(item1));
        a(438, "splash_potion", (new ItemSplashPotion()).c("splash_potion"));
        a(439, "spectral_arrow", (new ItemSpectralArrow()).c("spectral_arrow"));
        a(440, "tipped_arrow", (new ItemTippedArrow()).c("tipped_arrow"));
        a(441, "lingering_potion", (new ItemLingeringPotion()).c("lingering_potion"));
        a(442, "shield", (new ItemShield()).c("shield"));
        a(443, "elytra", (new ItemElytra()).c("elytra"));
        a(444, "spruce_boat", new ItemBoat(EntityBoat.EnumBoatType.SPRUCE));
        a(445, "birch_boat", new ItemBoat(EntityBoat.EnumBoatType.BIRCH));
        a(446, "jungle_boat", new ItemBoat(EntityBoat.EnumBoatType.JUNGLE));
        a(447, "acacia_boat", new ItemBoat(EntityBoat.EnumBoatType.ACACIA));
        a(448, "dark_oak_boat", new ItemBoat(EntityBoat.EnumBoatType.DARK_OAK));
        a(449, "totem_of_undying", (new Item()).c("totem").d(1).b(CreativeModeTab.j));
        a(450, "shulker_shell", (new Item()).c("shulkerShell").b(CreativeModeTab.l));
        a(452, "iron_nugget", (new Item()).c("ironNugget").b(CreativeModeTab.l));
        a(453, "knowledge_book", (new ItemKnowledgeBook()).c("knowledgeBook"));
        a(2256, "record_13", (new ItemRecord("13", SoundEffects.gb)).c("record"));
        a(2257, "record_cat", (new ItemRecord("cat", SoundEffects.gd)).c("record"));
        a(2258, "record_blocks", (new ItemRecord("blocks", SoundEffects.gc)).c("record"));
        a(2259, "record_chirp", (new ItemRecord("chirp", SoundEffects.ge)).c("record"));
        a(2260, "record_far", (new ItemRecord("far", SoundEffects.gf)).c("record"));
        a(2261, "record_mall", (new ItemRecord("mall", SoundEffects.gg)).c("record"));
        a(2262, "record_mellohi", (new ItemRecord("mellohi", SoundEffects.gh)).c("record"));
        a(2263, "record_stal", (new ItemRecord("stal", SoundEffects.gi)).c("record"));
        a(2264, "record_strad", (new ItemRecord("strad", SoundEffects.gj)).c("record"));
        a(2265, "record_ward", (new ItemRecord("ward", SoundEffects.gl)).c("record"));
        a(2266, "record_11", (new ItemRecord("11", SoundEffects.ga)).c("record"));
        a(2267, "record_wait", (new ItemRecord("wait", SoundEffects.gk)).c("record"));
        
        a(1000, "yellite", (new Item()).c("yellite").b(CreativeModeTab.l));
        a(1001, "bauxite", (new Item()).c("bauxite").b(CreativeModeTab.l));
        a(1002, "onyx", (new Item()).c("onyx").b(CreativeModeTab.l));
        a(1003, "frazion_powder", (new Item()).c("frazion_powder").b(CreativeModeTab.l));
        a(1004, "frazion", (new Item()).c("frazion").b(CreativeModeTab.l));
        
        a(1005, "cosmic_powder", (new Item()).c("cosmic_powder").b(CreativeModeTab.l));
        a(1006, "cosmic_nugget", (new Item()).c("cosmic_nugget").b(CreativeModeTab.l));
        a(1007, "cosmic_ingot", (new Item()).c("cosmic_ingot").b(CreativeModeTab.l));
       
        
        a(1008, "yellite_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.HEAD)).c("yellite_helmet"));
        a(1009, "yellite_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.CHEST)).c("yellite_chestplate"));
        a(1010, "yellite_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.LEGS)).c("yellite_leggings"));
        a(1011, "yellite_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.FEET)).c("yellite_boots"));       
        a(1012, "yellite_sword", (new ItemSword(Item.EnumToolMaterial.YELLITE)).c("yellite_sword"));
        a(1013, "yellite_shovel", (new ItemSpade(Item.EnumToolMaterial.YELLITE)).c("yellite_shovel"));
        a(1014, "yellite_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.YELLITE)).c("yellite_pickaxe"));
        a(1015, "yellite_axe", (new ItemAxe(Item.EnumToolMaterial.YELLITE)).c("yellite_axe"));
        a(1016, "yellite_hoe", (new ItemHoe(Item.EnumToolMaterial.YELLITE)).c("yellite_hoe"));
        
        a(1017, "bauxite_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.HEAD)).c("bauxite_helmet"));
        a(1018, "bauxite_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.CHEST)).c("bauxite_chestplate"));
        a(1019, "bauxite_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.LEGS)).c("bauxite_leggings"));
        a(1020, "bauxite_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.FEET)).c("bauxite_boots"));       
        a(1021, "bauxite_sword", (new ItemSword(Item.EnumToolMaterial.BAUXITE)).c("bauxite_sword"));
        a(1022, "bauxite_shovel", (new ItemSpade(Item.EnumToolMaterial.BAUXITE)).c("bauxite_shovel"));
        a(1023, "bauxite_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.BAUXITE)).c("bauxite_pickaxe"));
        a(1024, "bauxite_axe", (new ItemAxe(Item.EnumToolMaterial.BAUXITE)).c("bauxite_axe"));
        a(1025, "bauxite_hoe", (new ItemHoe(Item.EnumToolMaterial.BAUXITE)).c("bauxite_hoe"));
        
        a(1026, "onyx_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.HEAD)).c("onyx_helmet"));
        a(1027, "onyx_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.CHEST)).c("onyx_chestplate"));
        a(1028, "onyx_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.LEGS)).c("onyx_leggings"));
        a(1029, "onyx_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.FEET)).c("onyx_boots"));       
        a(1030, "onyx_sword", (new ItemSword(Item.EnumToolMaterial.ONYX)).c("onyx_sword"));
        a(1031, "onyx_shovel", (new ItemSpade(Item.EnumToolMaterial.ONYX)).c("onyx_shovel"));
        a(1032, "onyx_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.ONYX)).c("onyx_pickaxe"));
        a(1033, "onyx_axe", (new ItemAxe(Item.EnumToolMaterial.ONYX)).c("onyx_axe"));
        a(1034, "onyx_hoe", (new ItemHoe(Item.EnumToolMaterial.ONYX)).c("onyx_hoe"));
        
        a(1035, "frazion_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.HEAD)).c("frazion_helmet"));
        a(1036, "frazion_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.CHEST)).c("frazion_chestplate"));
        a(1037, "frazion_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.LEGS)).c("frazion_leggings"));
        a(1038, "frazion_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.FEET)).c("frazion_boots"));       
        a(1039, "frazion_sword", (new ItemSword(Item.EnumToolMaterial.FRAZION)).c("frazion_sword"));
        a(1040, "frazion_shovel", (new ItemSpade(Item.EnumToolMaterial.FRAZION)).c("frazion_shovel"));
        a(1041, "frazion_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.FRAZION)).c("frazion_pickaxe"));
        a(1042, "frazion_axe", (new ItemAxe(Item.EnumToolMaterial.FRAZION)).c("frazion_axe"));
        a(1043, "frazion_hoe", (new ItemFrazionHoe(Item.EnumToolMaterial.FRAZION)).c("frazion_hoe"));
        
        a(1044, "ultra_bow", (new ItemUltraBow()).c("ultra_bow"));
        
        a(1045, "trophy_bat", (new ItemSkeletonTrophy()).c("trophy_bat").b(CreativeModeTab.l));
        a(1046, "trophy_blaze", (new ItemSkeletonTrophy()).c("trophy_blaze").b(CreativeModeTab.l));
        a(1047, "trophy_creeper", (new ItemSkeletonTrophy()).c("trophy_creeper").b(CreativeModeTab.l));
        a(1048, "trophy_enderman", (new ItemSkeletonTrophy()).c("trophy_enderman").b(CreativeModeTab.l));
        a(1049, "trophy_ghast", (new ItemSkeletonTrophy()).c("trophy_ghast").b(CreativeModeTab.l));
        a(1050, "trophy_guardian", (new ItemSkeletonTrophy()).c("trophy_guardian").b(CreativeModeTab.l));
        a(1051, "trophy_villager", (new ItemSkeletonTrophy()).c("trophy_villager").b(CreativeModeTab.l));
        a(1052, "trophy_shulker", (new ItemShulkerTrophy()).c("trophy_shulker").b(CreativeModeTab.l));
        a(1053, "trophy_spider", (new ItemSkeletonTrophy()).c("trophy_spider").b(CreativeModeTab.l));
        a(1054, "trophy_skeleton", (new ItemSkeletonTrophy()).c("trophy_skeleton").b(CreativeModeTab.l));
        a(1055, "trophy_slime", (new ItemSkeletonTrophy()).c("trophy_slime").b(CreativeModeTab.l));
        a(1056, "trophy_squid", (new ItemSkeletonTrophy()).c("trophy_squid").b(CreativeModeTab.l));
        
        b((Block) Blocks.dirt_chest);
        b((Block) Blocks.hdv_chest);
        b((Block) Blocks.ONYX_CHEST);
        b((Block) Blocks.YELLITE_CHEST);
        b((Block) Blocks.BAUXITE_CHEST);
        b((Block) Blocks.FRAZION_CHEST);
        
        a(1060, "frazion_hammer", (new ItemHammer(Item.EnumToolMaterial.FRAZION_HAMMER)).c("frazion_hammer"));
        
        a(1061, "yellite_multitool", (new ItemMultiTool(Item.EnumToolMaterial.YELLITE)).c("yellite_multitool"));
        a(1062, "bauxite_multitool", (new ItemMultiTool(Item.EnumToolMaterial.BAUXITE)).c("bauxite_multitool"));
        a(1063, "onyx_multitool", (new ItemMultiTool(Item.EnumToolMaterial.ONYX)).c("onyx_multitool"));
        a(1064, "frazion_multitool", (new ItemMultiTool(Item.EnumToolMaterial.FRAZION)).c("frazion_multitool"));
        
        b(Blocks.YELLITE_ORE);
        b(Blocks.YELLITE_BLOCK);
        b(Blocks.BAUXITE_BLOCK);
        b(Blocks.BAUXITE_ORE);
        b(Blocks.ONYX_BLOCK);
        b(Blocks.ONYX_ORE);
        b(Blocks.FRAZION_BLOCK);
        b(Blocks.FRAZION_ORE);
        
        b((Block) Blocks.Z_HOPPER);
        
        a(1057, "record_fz1", (new ItemRecord("fz1", SoundEffects.gb)).c("record_fz1"));
        a(1058, "record_fz2", (new ItemRecord("fz2", SoundEffects.gb)).c("record_fz2"));
        a(1059, "record_fz3", (new ItemRecord("fz3", SoundEffects.gb)).c("record_fz3"));
        
        b(Blocks.cristal_rouge);
        b(Blocks.cristal_bleu);
        b(Blocks.cristal_jaune);
        b(Blocks.cristal_vert);
        b(Blocks.cristal_violet);
        
        a(1065, "key_farm", (new Item()).c("key_farm").b(CreativeModeTab.l));
        a(1066, "key_vote", (new Item()).c("key_vote").b(CreativeModeTab.l));
        a(1067, "key_common", (new Item()).c("key_common").b(CreativeModeTab.l));
        a(1068, "key_rare", (new Item()).c("key_rare").b(CreativeModeTab.l));
        a(1069, "key_legendary", (new Item()).c("key_legendary").b(CreativeModeTab.l));
        
        a(1078, "strawberry", (new ItemFood(2, 1.0F, true)).h().c("strawberry"));
        a(1079, "banana", (new ItemBanana(4, 2.5F, true)).h().c("banana"));
        a(1080, "pizza", (new ItemPizza(10, 2.5F, true)).h().c("pizza"));
        a(1081, "donuts", (new ItemDonuts(4, 2.0F, false)).h().c("donuts"));
        
        a(1082, "yellite_apple", (new ItemYelliteApple(4, 2.0F, false)).h().c("yellite_apple"));
        a(1083, "bauxite_apple", (new ItemBauxiteApple(4, 2.0F, false)).h().c("bauxite_apple"));
        a(1084, "onyx_apple", (new ItemOnyxApple(4, 2.0F, false)).h().c("onyx_apple"));
        a(1085, "frazion_apple", (new ItemFrazionApple(4, 2.0F, false)).h().c("frazion_apple"));
        
        b(Blocks.YELLITE_FURNACE);
        b(Blocks.BAUXITE_FURNACE);
        b(Blocks.ONYX_FURNACE);
        b(Blocks.FRAZION_FURNACE);
        
        b(Blocks.YELLITE_LADDER);
        b(Blocks.BAUXITE_LADDER);
        b(Blocks.ONYX_LADDER);
        b(Blocks.FRAZION_LADDER);
        
        b(Blocks.crimson_log);
        b((Block) Blocks.crimson_roots);
        b((Block) Blocks.crimson_fungi);
        
        
        a(Blocks.nether_wart_block2, (new ItemMultiTexture(Blocks.nether_wart_block2, Blocks.nether_wart_block2, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreNWBVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("nether_wart_block"));
        
        
        a(Blocks.sandstone2, (new ItemMultiTexture(Blocks.sandstone2, Blocks.sandstone2, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreSandstoneVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("sandstone"));
        
        
        a(Blocks.stone_andesite, (new ItemMultiTexture(Blocks.stone_andesite, Blocks.stone_andesite, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreAndesiteVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_andesite"));
        
        
        a(Blocks.stone_andesite_smooth, (new ItemMultiTexture(Blocks.stone_andesite_smooth, Blocks.stone_andesite_smooth, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreAndesiteSmoothVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_andesite_smooth"));
        
        
        a(Blocks.stone_granite, (new ItemMultiTexture(Blocks.stone_granite, Blocks.stone_granite, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreGraniteVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_granite"));
        
        
        a(Blocks.stone_granite_smooth, (new ItemMultiTexture(Blocks.stone_granite_smooth, Blocks.stone_granite_smooth, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreGraniteSmoothVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_granite_smooth"));
        
        
        a(Blocks.stone_diorite, (new ItemMultiTexture(Blocks.stone_diorite, Blocks.stone_diorite, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreDioriteVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_diorite"));
        
        
        a(Blocks.stone_diorite_smooth, (new ItemMultiTexture(Blocks.stone_diorite_smooth, Blocks.stone_diorite_smooth, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockMoreDioriteSmoothVariant.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_diorite_smooth"));
        
        
        b(Blocks.RANDOM_ORE);
        
        b(Blocks.AMELIORATOR);
        
        
        a(1086, "nether_string", (new Item()).c("nether_string").b(CreativeModeTab.l));
        a(1087, "renforced_string", (new Item()).c("renforced_string").b(CreativeModeTab.l));
        
        a(1088, "rune", (new Item()).c("rune").b(CreativeModeTab.l));
        a(1089, "rune_wither", (new Item()).c("rune_wither").b(CreativeModeTab.l));
        a(1090, "rune_poison", (new Item()).c("rune_poison").b(CreativeModeTab.l));
        a(1091, "rune_slowness", (new Item()).c("rune_slowness").b(CreativeModeTab.l));
        a(1092, "rune_weakness", (new Item()).c("rune_weakness").b(CreativeModeTab.l));
        a(1093, "rune_mining_fatigue", (new Item()).c("rune_mining_fatigue").b(CreativeModeTab.l));
        a(1094, "rune_blindness", (new Item()).c("rune_blindness").b(CreativeModeTab.l));
        
        a(1095, "big_xp", (new ItemBigXp()).c("big_xp").b(CreativeModeTab.l));
        
        a(1096, "loot_powder", (new Item()).c("loot_powder").b(CreativeModeTab.l));
        a(1097, "farm_powder", (new Item()).c("farm_powder").b(CreativeModeTab.l));
        a(1098, "farm_nugget", (new Item()).c("farm_nugget").b(CreativeModeTab.l));
        
        a(1099, "yellite_stick", (new Item()).n().c("yellite_stick").b(CreativeModeTab.l));
        a(1100, "bauxite_stick", (new Item()).n().c("bauxite_stick").b(CreativeModeTab.l));
        a(1101, "onyx_stick", (new Item()).n().c("onyx_stick").b(CreativeModeTab.l));
        a(1102, "frazion_stick", (new Item()).n().c("frazion_stick").b(CreativeModeTab.l));
        
        
        a(1103, "frazion_helmet_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.HEAD)).c("frazion_helmet_70"));
        a(1104, "frazion_chestplate_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.CHEST)).c("frazion_chestplate_70"));
        a(1105, "frazion_leggings_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.LEGS)).c("frazion_leggings_70"));
        a(1106, "frazion_boots_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.FEET)).c("frazion_boots_70"));
        
        a(1107, "frazion_helmet_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.HEAD)).c("frazion_helmet_100"));
        a(1108, "frazion_chestplate_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.CHEST)).c("frazion_chestplate_100"));
        a(1109, "frazion_leggings_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.LEGS)).c("frazion_leggings_100"));
        a(1110, "frazion_boots_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.FEET)).c("frazion_boots_100"));
        
        a(1111, "travelers_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.HEAD)).c("travelers_helmet"));
        a(1112, "travelers_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.CHEST)).c("travelers_chestplate"));
        a(1113, "travelers_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.LEGS)).c("travelers_leggings"));
        a(1114, "travelers_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.FEET)).c("travelers_boots"));
        
        a(1115, "legendary_axe", (new ItemSword(Item.EnumToolMaterial.LEGENDARY_AXE)).c("legendary_axe"));
        a(1116, "legendary_sword", (new ItemLegendarySword(Item.EnumToolMaterial.LEGENDARY_SWORD)).c("legendary_sword"));
        a(1117, "legendary_dagger", (new ItemLegendaryDagger(Item.EnumToolMaterial.LEGENDARY_DAGGER)).c("legendary_dagger"));
        
        a(1118, "record_fz4", (new ItemRecord("fz4", SoundEffects.gb)).c("record_fz4"));
        a(1119, "record_fz5", (new ItemRecord("fz5", SoundEffects.gb)).c("record_fz5"));
        a(1120, "record_fz6", (new ItemRecord("fz6", SoundEffects.gb)).c("record_fz6"));
        a(1121, "record_fz7", (new ItemRecord("fz7", SoundEffects.gb)).c("record_fz7"));
        a(1122, "record_fz8", (new ItemRecord("fz8", SoundEffects.gb)).c("record_fz8"));
        a(1123, "record_fz9", (new ItemRecord("fz9", SoundEffects.gb)).c("record_fz9"));
        a(1124, "record_fz10", (new ItemRecord("fz10", SoundEffects.gb)).c("record_fz10"));
        
        a(1125, "legendary_scythe", (new ItemLegendaryScythe(Item.EnumToolMaterial.LEGENDARY_SCYTHE)).c("legendary_scythe"));
        
        
        a(1200, "dynamite", (new ItemDynamite()).c("dynamite").b(CreativeModeTab.l));
        a(1201, "dynamite_arrow", (new ItemDynamiteArrow()).c("dynamite_arrow"));
        
        a(1205, "spawner_pickaxe", (new ItemSpawnerPickaxe()).c("spawner_pickaxe"));
        
        a(1206, "frazion_dagger", (new ItemSword(Item.EnumToolMaterial.FRAZION_DAGGER)).c("frazion_dagger"));
        
        a(1207, "billet", (new Item()).c("billet").b(CreativeModeTab.l));
        
        a(1208, "farm_sword", (new ItemSword(Item.EnumToolMaterial.FARM_SWORD)).c("farm_sword"));
        
        b(Blocks.OBSIDIAN_YELLITE);
        b(Blocks.OBSIDIAN_BAUXITE);
        b(Blocks.OBSIDIAN_ONYX);
        b(Blocks.OBSIDIAN_FRAZION);
        b(Blocks.RENFORCED_SAND);
        
        b(Blocks.WITHER_BLOCK);
        b(Blocks.compact_cobblestone_x1);
        b(Blocks.compact_cobblestone_x2);
        b(Blocks.COMPACT_COBBLESTONE_X3);
        b(Blocks.COMPACT_COBBLESTONE_X4);
        b(Blocks.COMPACT_COBBLESTONE_X5);
        
        b(Blocks.z_tnt);
        
        a(1213, "obsidian_tower", (new Item()).c("obsidian_tower").b(CreativeModeTab.l));
        
        a(1214, "booster_xp", (new Item()).c("booster_xp").b(CreativeModeTab.l));
        a(1215, "booster_aptitude", (new Item()).c("booster_aptitude").b(CreativeModeTab.l));
        a(1216, "booster_repair", (new Item()).c("booster_repair").b(CreativeModeTab.l));
        
        a(1217, "withered_bone", (new Item()).c("withered_bone").n().b(CreativeModeTab.f));
        a(1218, "withered_bone_meal", (new ItemWitheredBoneMeal()).c("withered_bone_meal").n().b(CreativeModeTab.f));
        
        a(1219, "bottlexp", (new ItemBottleXP()).c("bottlexp"));
        a(1220, "faction_token", (new Item()).c("faction_token").b(CreativeModeTab.l));
        b(Blocks.REVERSE_FALL_BLOCK);
        
        
        a(Blocks.STONE_BLACKSTONE, (new ItemMultiTexture(Blocks.STONE_BLACKSTONE, Blocks.STONE_BLACKSTONE, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockDarkAndesite.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_blackstone"));
        
        a(Blocks.STONE_BLACKSTONE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_BLACKSTONE_SMOOTH, Blocks.STONE_BLACKSTONE_SMOOTH, new ItemMultiTexture.a() {
            public String a(ItemStack itemstack) {
                return BlockSmoothDarkAndesite.VariantType.a(itemstack.getData()).c();
            }
        })).c("stone_blackstone_smooth"));
        
        b(Blocks.BLOCK_PLACER_CHEST);
        b(Blocks.BLOCK_PLACER_TRAPCHEST);
        
        b(Blocks.TROPHY_FORGE);
    }

    private static void b(Block block) {
        a(block, (Item) (new ItemBlock(block)));
    }

    protected static void a(Block block, Item item) {
        a(Block.getId(block), (MinecraftKey) Block.REGISTRY.b(block), item);
        Item.a.put(block, item);
    }

    private static void a(int i, String s, Item item) {
        a(i, new MinecraftKey(s), item);
    }

    private static void a(int i, MinecraftKey minecraftkey, Item item) {
        Item.REGISTRY.a(i, minecraftkey, item);
    }

    public static enum EnumToolMaterial {

        WOOD(0, 59, 2.0F, 0.50F, 15),
        STONE(1, 131, 4.0F, 1.0F, 5),
        IRON(2, 250, 6.0F, 1.9F, 14),
        DIAMOND(3, 1561, 8.0F, 2.5F, 10),
        GOLD(0, 32, 12.0F, 1.9F, 22),
        YELLITE(4, 2000, 8.0F, 3.3F, 15),
        BAUXITE(5, 2500, 10.0F, 4.5F, 15),
        ONYX(6, 3000, 11.0F, 5.7F, 15),
        FRAZION(7, 4000, 13.0F, 6.9F, 15),
        SPAWNER_PICKAXE(3, 10, 14.0F, 1.5F, 15),
        FRAZION_HAMMER(6, 3000, 7.3F, 4.0F, 10),
        LEGENDARY_AXE(0, 4000, 14.0F, 8.1F, 15),
        LEGENDARY_SCYTHE(0, 4000, 14.0F, 9.2F, 15),
        LEGENDARY_SWORD(0, 4000, 14.0F, 8.1F, 15),
        LEGENDARY_DAGGER(0, 4000, 14.0F, 5.4F, 15),
        FRAZION_DAGGER(0, 2500, 0.0F, 3.8F, 15),
        FARM_SWORD(0, 4000, 14.0F, 1.5F, 15),
        ;

        private final int f;
        private final int g;
        private final float h;
        private final float i;
        private final int j;

        private EnumToolMaterial(int i, int j, float f, float f1, int k) {
            this.f = i;
            this.g = j;
            this.h = f;
            this.i = f1;
            this.j = k;
        }

        public int a() {
            return this.g;
        }

        public float b() {
            return this.h;
        }

        public float c() {
            return this.i;
        }

        public int d() {
            return this.f;
        }

        public int e() {
            return this.j;
        }

        public Item f() {
            return this == Item.EnumToolMaterial.WOOD ? Item.getItemOf(Blocks.PLANKS) : (this == Item.EnumToolMaterial.FRAZION ? Items.FRAZION : (this == Item.EnumToolMaterial.ONYX ? Items.ONYX :  (this == Item.EnumToolMaterial.BAUXITE ? Items.BAUXITE :  (this == Item.EnumToolMaterial.YELLITE ? Items.YELLITE :  (this == Item.EnumToolMaterial.STONE ? Item.getItemOf(Blocks.COBBLESTONE) : (this == Item.EnumToolMaterial.GOLD ? Items.GOLD_INGOT : (this == Item.EnumToolMaterial.IRON ? Items.IRON_INGOT : (this == Item.EnumToolMaterial.DIAMOND ? Items.DIAMOND : null))))))));
        }
    }
}
