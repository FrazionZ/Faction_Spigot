package net.minecraft.server;

import java.util.*;

// CraftBukkit start
import net.minecraft.server.frazionz.players.stats.EnumStats;
import net.minecraft.server.frazionz.players.stats.modifier.StatFallDamageModifier;
import net.minecraft.server.frazionz.players.stats.modifier.StatFireDamageModifier;
import net.minecraft.server.frazionz.players.stats.modifier.StatValueCappingModifier;
import net.minecraft.server.frazionz.players.stats.modifier.StatModifier;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.block.BlockDispenseEvent;
// CraftBukkit end

import com.google.common.base.Predicates;
import com.google.common.collect.Multimap;

public class ItemArmor extends Item {

    private static final int[] n = new int[] { 40, 45, 50, 35};
    private static final UUID[] o = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};
    public static final String[] a = new String[] { "minecraft:items/empty_armor_slot_boots", "minecraft:items/empty_armor_slot_leggings", "minecraft:items/empty_armor_slot_chestplate", "minecraft:items/empty_armor_slot_helmet"};
    public static final IDispenseBehavior b = new DispenseBehaviorItem() {
        protected ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
            ItemStack itemstack1 = ItemArmor.a(isourceblock, itemstack);

            return itemstack1.isEmpty() ? super.b(isourceblock, itemstack) : itemstack1;
        }
    };
    public final EnumItemSlot c;
    public final int d;
    public final float e;
    public final int f;
    private final ItemArmor.EnumArmorMaterial p;

    public static ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
        BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection) isourceblock.e().get(BlockDispenser.FACING));
        List list = isourceblock.getWorld().a(EntityLiving.class, new AxisAlignedBB(blockposition), Predicates.and(IEntitySelector.e, new IEntitySelector.EntitySelectorEquipable(itemstack)));

        if (list.isEmpty()) {
            return ItemStack.a;
        } else {
            EntityLiving entityliving = (EntityLiving) list.get(0);
            EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);
            ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
            // CraftBukkit start
            World world = isourceblock.getWorld();
            org.bukkit.block.Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
            CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);

            BlockDispenseEvent event = new BlockDispenseEvent(block, craftItem.clone(), new org.bukkit.util.Vector(0, 0, 0));
            if (!BlockDispenser.eventFired) {
                world.getServer().getPluginManager().callEvent(event);
            }

            if (event.isCancelled()) {
                itemstack.add(1);
                return itemstack;
            }

            if (!event.getItem().equals(craftItem)) {
                itemstack.add(1);
                // Chain to handler for new item
                ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
                IDispenseBehavior idispensebehavior = (IDispenseBehavior) BlockDispenser.REGISTRY.get(eventStack.getItem());
                if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != ItemArmor.b) {
                    idispensebehavior.a(isourceblock, eventStack);
                    return itemstack;
                }
            }
            // CraftBukkit end

            entityliving.setSlot(enumitemslot, itemstack1);
            if (entityliving instanceof EntityInsentient) {
                ((EntityInsentient) entityliving).a(enumitemslot, 2.0F);
            }

            return itemstack;
        }
    }

    public ItemArmor(ItemArmor.EnumArmorMaterial itemarmor_enumarmormaterial, int i, EnumItemSlot enumitemslot) {
        this.p = itemarmor_enumarmormaterial;
        this.c = enumitemslot;
        this.f = i;
        this.d = itemarmor_enumarmormaterial.b(enumitemslot);
        this.setMaxDurability(itemarmor_enumarmormaterial.a(enumitemslot));
        this.e = itemarmor_enumarmormaterial.e();
        this.maxStackSize = 1;
        this.b(CreativeModeTab.j);
        BlockDispenser.REGISTRY.a(this, ItemArmor.b);
    }

    public int c() {
        return this.p.a();
    }

    public ItemArmor.EnumArmorMaterial d() {
        return this.p;
    }

    public boolean e_(ItemStack itemstack) {
        if (this.p != ItemArmor.EnumArmorMaterial.LEATHER) {
            return false;
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            return nbttagcompound != null && nbttagcompound.hasKeyOfType("display", 10) ? nbttagcompound.getCompound("display").hasKeyOfType("color", 3) : false;
        }
    }

    public int c(ItemStack itemstack) {
        if (this.p != ItemArmor.EnumArmorMaterial.LEATHER) {
            return 16777215;
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1 != null && nbttagcompound1.hasKeyOfType("color", 3)) {
                    return nbttagcompound1.getInt("color");
                }
            }

            return 10511680;
        }
    }

    public void d(ItemStack itemstack) {
        if (this.p == ItemArmor.EnumArmorMaterial.LEATHER) {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound != null) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

                if (nbttagcompound1.hasKey("color")) {
                    nbttagcompound1.remove("color");
                }

            }
        }
    }

    public void a(ItemStack itemstack, int i) {
        if (this.p != ItemArmor.EnumArmorMaterial.LEATHER) {
            throw new UnsupportedOperationException("Can\'t dye non-leather!");
        } else {
            NBTTagCompound nbttagcompound = itemstack.getTag();

            if (nbttagcompound == null) {
                nbttagcompound = new NBTTagCompound();
                itemstack.setTag(nbttagcompound);
            }

            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("display");

            if (!nbttagcompound.hasKeyOfType("display", 10)) {
                nbttagcompound.set("display", nbttagcompound1);
            }

            nbttagcompound1.setInt("color", i);
        }
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return this.p.c() == itemstack1.getItem() ? true : super.a(itemstack, itemstack1);
    }

    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);
        EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);
        ItemStack itemstack1 = entityhuman.getEquipment(enumitemslot);

        if (itemstack1.isEmpty()) {
            entityhuman.setSlot(enumitemslot, itemstack.cloneItemStack());
            itemstack.setCount(0);
            return new InteractionResultWrapper(EnumInteractionResult.SUCCESS, itemstack);
        } else {
            return new InteractionResultWrapper(EnumInteractionResult.FAIL, itemstack);
        }
    }

    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        Multimap multimap = super.a(enumitemslot);

        if (enumitemslot == this.c) {
            multimap.put(GenericAttributes.h.getName(), new AttributeModifier(ItemArmor.o[enumitemslot.b()], "Armor modifier", (double) this.d, 0));
            multimap.put(GenericAttributes.i.getName(), new AttributeModifier(ItemArmor.o[enumitemslot.b()], "Armor toughness", (double) this.e, 0));
        }

        return multimap;
    }

    public enum EnumArmorMaterial {
    	
        LEATHER("leather", 3, new int[]{1, 1, 1, 1}, 15, SoundEffects.t, 0.0F),
        CHAIN("chainmail", 7, new int[]{2, 2, 2, 2}, 12, SoundEffects.n, 0.0F),
        IRON("iron", 8, new int[]{3, 4, 3, 2}, 9, SoundEffects.s, 0.0F),
        GOLD("gold", 2, new int[]{3, 4, 3, 2}, 25, SoundEffects.r, 0.0F),
        DIAMOND("diamond", 10, new int[]{4, 5, 4, 3}, 10, SoundEffects.o, /*0.25F*/0.0f),
        YELLITE("yellite", 25, new int[]{4, 5, 5, 4}, 12, SoundEffects.o, /*0.50F*/0.0f),
        BAUXITE("bauxite", 30, new int[]{5, 6, 6, 5}, 12, SoundEffects.o, /*0.80F*/0.0f),
        ONYX("onyx", 35, new int[]{6, 7, 7, 6}, 12, SoundEffects.o, /*1.0F*/0.0f),
        FRAZION("frazion", 45, new int[]{7, 8, 8, 7}, 12, SoundEffects.o, /*1.2F*/0.0f),
        FRAZION_70("frazion_70", 65, new int[]{7, 8, 8, 7}, 12, SoundEffects.o, 0.3125f,
                new HashMap<EnumStats, Integer>() {{
                    put(EnumStats.SPEED, 8);
                    put(EnumStats.RESISTANCE, 3);
                    put(EnumStats.DAMAGE, 1);
                }}),
        FRAZION_100("frazion_100", 80, new int[]{7, 8, 8, 7}, 12, SoundEffects.o, 0.625f,
                new HashMap<EnumStats, Integer>() {{
                    put(EnumStats.SPEED, 15);
                    put(EnumStats.DAMAGE, 5);
                    put(EnumStats.RESISTANCE, 5);
                    put(EnumStats.HEALTH, 10);
                }}),
        TRAVELERS("travelers", 18, new int[]{4, 5, 4, 3}, 12, SoundEffects.o, 0.0f,
                new HashMap<EnumStats, Integer>() {{
                    put(EnumStats.SPEED, 100);
                    put(EnumStats.MINING_SPEED, 50);
                }},
                new ArrayList<StatModifier>() {{
                    add(new StatValueCappingModifier(StatValueCappingModifier.StatCapType.MAX, EnumStats.SPEED, 240));
                    add(new StatFallDamageModifier(false));
                    add(new StatFireDamageModifier(false));
                }}
        ),
        ;
    	

        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEffect soundEvent;
        private final float toughness;
        private final HashMap<EnumStats, Integer> stats;
        private final List<StatModifier> modifiers;


        private EnumArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEffect soundEventIn, float toughnessIn)
        {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.stats = new HashMap<EnumStats, Integer>();
            this.modifiers = new ArrayList<StatModifier>();
        }
        private EnumArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEffect soundEventIn, float toughnessIn, HashMap<EnumStats, Integer> stats)
        {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.stats = stats;
            this.modifiers = new ArrayList<StatModifier>();
        }

        private EnumArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEffect soundEventIn, float toughnessIn, HashMap<EnumStats, Integer> stats, List<StatModifier> modifiers)
        {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.stats = stats;
            this.modifiers = modifiers;
        }
        public int a(EnumItemSlot enumitemslot) {
            return ItemArmor.n[enumitemslot.b()] * this.maxDamageFactor;
        }

        public int b(EnumItemSlot enumitemslot) {
            return this.damageReductionAmountArray[enumitemslot.b()];
        }

        public int a() {
            return this.enchantability;
        }

        public SoundEffect b() {
            return this.soundEvent;
        }

        public Item c() {
            return this == ItemArmor.EnumArmorMaterial.LEATHER ? Items.LEATHER : (this == ItemArmor.EnumArmorMaterial.ONYX ? Items.ONYX : (this == ItemArmor.EnumArmorMaterial.BAUXITE ? Items.BAUXITE : (this == ItemArmor.EnumArmorMaterial.YELLITE ? Items.YELLITE : (this == ItemArmor.EnumArmorMaterial.FRAZION ? Items.FRAZION : (this == ItemArmor.EnumArmorMaterial.TRAVELERS ? Items.LEATHER : (this == ItemArmor.EnumArmorMaterial.FRAZION_100 ? Items.FRAZION : (this == ItemArmor.EnumArmorMaterial.FRAZION_70 ? Items.FRAZION : (this == ItemArmor.EnumArmorMaterial.CHAIN ? Items.IRON_INGOT : (this == ItemArmor.EnumArmorMaterial.GOLD ? Items.GOLD_INGOT : (this == ItemArmor.EnumArmorMaterial.IRON ? Items.IRON_INGOT : (this == ItemArmor.EnumArmorMaterial.DIAMOND ? Items.DIAMOND : null)))))))))));
        }

        public float e() {
            return this.toughness;
        }

        public HashMap<EnumStats, Integer> getStats() {
            return this.stats;
        }

        public List<StatModifier> getModifiers() {
            return this.modifiers;
        }
    }
}
