package net.minecraft.server.frazionz.manager;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.blocks.*;
import net.minecraft.server.frazionz.items.*;

public class ItemManager {

    public static void registerItems()
    {
        // ORE //
        registerItem(1000, "yellite", (new Item()).c("yellite").b(CreativeModeTab.l));
        registerItem(1001, "bauxite", (new Item()).c("bauxite").b(CreativeModeTab.l));
        registerItem(1002, "onyx", (new Item()).c("onyx").b(CreativeModeTab.l));
        registerItem(1003, "frazion_powder", (new Item()).c("frazion_powder").b(CreativeModeTab.l));
        registerItem(1004, "frazion", (new Item()).c("frazion").b(CreativeModeTab.l));
        registerItem(1005, "cosmic_powder", (new Item()).c("cosmic_powder").b(CreativeModeTab.l));
        registerItem(1006, "cosmic_nugget", (new Item()).c("cosmic_nugget").b(CreativeModeTab.l));
        registerItem(1007, "cosmic_ingot", (new Item()).c("cosmic_ingot").b(CreativeModeTab.l));
        // ARMOR //
        registerItem(1008, "yellite_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.HEAD)).c("yellite_helmet"));
        registerItem(1009, "yellite_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.CHEST)).c("yellite_chestplate"));
        registerItem(1010, "yellite_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.LEGS)).c("yellite_leggings"));
        registerItem(1011, "yellite_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.YELLITE, 5, EnumItemSlot.FEET)).c("yellite_boots"));
        registerItem(1012, "yellite_sword", (new ItemSword(Item.EnumToolMaterial.YELLITE)).c("yellite_sword"));
        registerItem(1013, "yellite_shovel", (new ItemSpade(Item.EnumToolMaterial.YELLITE)).c("yellite_shovel"));
        registerItem(1014, "yellite_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.YELLITE)).c("yellite_pickaxe"));
        registerItem(1015, "yellite_axe", (new ItemAxe(Item.EnumToolMaterial.YELLITE)).c("yellite_axe"));
        registerItem(1016, "yellite_hoe", (new ItemHoe(Item.EnumToolMaterial.YELLITE)).c("yellite_hoe"));
        registerItemBlock(Blocks.YELLITE_BLOCK);
        registerItemBlock(Blocks.YELLITE_ORE);
        registerItem(1017, "bauxite_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.HEAD)).c("bauxite_helmet"));
        registerItem(1018, "bauxite_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.CHEST)).c("bauxite_chestplate"));
        registerItem(1019, "bauxite_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.LEGS)).c("bauxite_leggings"));
        registerItem(1020, "bauxite_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.BAUXITE, 6, EnumItemSlot.FEET)).c("bauxite_boots"));
        registerItem(1021, "bauxite_sword", (new ItemSword(Item.EnumToolMaterial.BAUXITE)).c("bauxite_sword"));
        registerItem(1022, "bauxite_shovel", (new ItemSpade(Item.EnumToolMaterial.BAUXITE)).c("bauxite_shovel"));
        registerItem(1023, "bauxite_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.BAUXITE)).c("bauxite_pickaxe"));
        registerItem(1024, "bauxite_axe", (new ItemAxe(Item.EnumToolMaterial.BAUXITE)).c("bauxite_axe"));
        registerItem(1025, "bauxite_hoe", (new ItemHoe(Item.EnumToolMaterial.BAUXITE)).c("bauxite_hoe"));
        registerItemBlock(Blocks.BAUXITE_ORE);
        registerItemBlock(Blocks.BAUXITE_BLOCK);
        registerItem(1026, "onyx_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.HEAD)).c("onyx_helmet"));
        registerItem(1027, "onyx_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.CHEST)).c("onyx_chestplate"));
        registerItem(1028, "onyx_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.LEGS)).c("onyx_leggings"));
        registerItem(1029, "onyx_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.ONYX, 7, EnumItemSlot.FEET)).c("onyx_boots"));
        registerItem(1030, "onyx_sword", (new ItemSword(Item.EnumToolMaterial.ONYX)).c("onyx_sword"));
        registerItem(1031, "onyx_shovel", (new ItemSpade(Item.EnumToolMaterial.ONYX)).c("onyx_shovel"));
        registerItem(1032, "onyx_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.ONYX)).c("onyx_pickaxe"));
        registerItem(1033, "onyx_axe", (new ItemAxe(Item.EnumToolMaterial.ONYX)).c("onyx_axe"));
        registerItem(1034, "onyx_hoe", (new ItemHoe(Item.EnumToolMaterial.ONYX)).c("onyx_hoe"));
        registerItemBlock(Blocks.ONYX_ORE);
        registerItemBlock(Blocks.ONYX_BLOCK);
        registerItem(1035, "frazion_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.HEAD)).c("frazion_helmet"));
        registerItem(1036, "frazion_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.CHEST)).c("frazion_chestplate"));
        registerItem(1037, "frazion_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.LEGS)).c("frazion_leggings"));
        registerItem(1038, "frazion_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION, 8, EnumItemSlot.FEET)).c("frazion_boots"));
        registerItem(1039, "frazion_sword", (new ItemSword(Item.EnumToolMaterial.FRAZION)).c("frazion_sword"));
        registerItem(1040, "frazion_shovel", (new ItemSpade(Item.EnumToolMaterial.FRAZION)).c("frazion_shovel"));
        registerItem(1041, "frazion_pickaxe", (new ItemPickaxe(Item.EnumToolMaterial.FRAZION)).c("frazion_pickaxe"));
        registerItem(1042, "frazion_axe", (new ItemAxe(Item.EnumToolMaterial.FRAZION)).c("frazion_axe"));
        registerItem(1043, "frazion_hoe", (new ItemFrazionHoe(Item.EnumToolMaterial.FRAZION)).c("frazion_hoe"));
        registerItemBlock(Blocks.FRAZION_ORE);
        registerItemBlock(Blocks.FRAZION_BLOCK);
        registerItem(1044, "ultra_bow", (new ItemUltraBow()).c("ultra_bow"));

        registerItemBlock(Blocks.Z_HOPPER);
        registerItemBlock(Blocks.DIRT_CHEST);
        registerItemBlock(Blocks.YELLITE_CHEST);
        registerItemBlock(Blocks.BAUXITE_CHEST);
        registerItemBlock(Blocks.ONYX_CHEST);
        registerItemBlock(Blocks.FRAZION_CHEST);
        registerItem(1060, "frazion_hammer", (new ItemHammer(Item.EnumToolMaterial.FRAZION_HAMMER)).c("frazion_hammer"));
        registerItem(1061, "yellite_multitool", (new ItemMultiTool(Item.EnumToolMaterial.YELLITE)).c("yellite_multitool"));
        registerItem(1062, "bauxite_multitool", (new ItemMultiTool(Item.EnumToolMaterial.BAUXITE)).c("bauxite_multitool"));
        registerItem(1063, "onyx_multitool", (new ItemMultiTool(Item.EnumToolMaterial.ONYX)).c("onyx_multitool"));
        registerItem(1064, "frazion_multitool", (new ItemMultiTool(Item.EnumToolMaterial.FRAZION)).c("frazion_multitool"));
        registerItemBlock(Blocks.YELLITE_FURNACE);
        registerItemBlock(Blocks.BAUXITE_FURNACE);
        registerItemBlock(Blocks.ONYX_FURNACE);
        registerItemBlock(Blocks.FRAZION_FURNACE);
        registerItemBlock(Blocks.CRYSTAL_BLEU);
        registerItemBlock(Blocks.CRYSTAL_JAUNE);
        registerItemBlock(Blocks.CRYSTAL_ROUGE);
        registerItemBlock(Blocks.CRYSTAL_VERT);
        registerItemBlock(Blocks.CRYSTAL_VIOLET);
        registerItemBlock(Blocks.OBSIDIAN_YELLITE);
        registerItemBlock(Blocks.OBSIDIAN_BAUXITE);
        registerItemBlock(Blocks.OBSIDIAN_ONYX);
        registerItemBlock(Blocks.OBSIDIAN_FRAZION);
        registerItemBlock(Blocks.Z_TNT);
        registerItemBlock(Blocks.BAUXITE_BLOCK);
        registerItem(1065, "key_farm", (new Item()).c("key_farm").b(CreativeModeTab.l));
        registerItem(1066, "key_vote", (new Item()).c("key_vote").b(CreativeModeTab.l));
        registerItem(1067, "key_common", (new Item()).c("key_common").b(CreativeModeTab.l));
        registerItem(1068, "key_rare", (new Item()).c("key_rare").b(CreativeModeTab.l));
        registerItem(1069, "key_legendary", (new Item()).c("key_legendary").b(CreativeModeTab.l));

        registerItem(1078, "strawberry", (new ItemSeedFood(2, 1.0F, Blocks.STRAWBERRIES, Blocks.FARMLAND)).c("strawberry"));
        registerItem(1079, "banana", (new ItemBanana(4, 2.5F, true)).h().c("banana"));
        registerItem(1080, "pizza", (new ItemPizza(10, 2.5F, true)).h().c("pizza"));
        registerItem(1081, "donuts", (new ItemDonuts(4, 2.0F, false)).h().c("donuts"));

        registerItem(1082, "yellite_apple", (new ItemYelliteApple(4, 2.0F, false)).h().c("yellite_apple"));
        registerItem(1083, "bauxite_apple", (new ItemBauxiteApple(4, 2.0F, false)).h().c("bauxite_apple"));
        registerItem(1084, "onyx_apple", (new ItemOnyxApple(4, 2.0F, false)).h().c("onyx_apple"));
        registerItem(1085, "frazion_apple", (new ItemFrazionApple(4, 2.0F, false)).h().c("frazion_apple"));

        registerItemBlock(Blocks.BAUXITE_LADDER);
        registerItemBlock(Blocks.YELLITE_LADDER);
        registerItemBlock(Blocks.ONYX_LADDER);
        registerItemBlock(Blocks.FRAZION_LADDER, (new ItemBlock(Blocks.FRAZION_LADDER)));

        registerItemBlock(Blocks.CRIMSON_LOG);
        registerItemBlock(Blocks.CRIMSON_ROOTS);
        registerItemBlock(Blocks.CRIMSON_FUNGI);

        registerItemBlock(Blocks.NETHER_WART_BLOCK2, (new ItemMultiTexture(Blocks.NETHER_WART_BLOCK2, Blocks.NETHER_WART_BLOCK2, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreNWBVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("nether_wart_block"));

        registerItemBlock(Blocks.SANDSTONE2, (new ItemMultiTexture(Blocks.SANDSTONE2, Blocks.SANDSTONE2, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreSandstoneVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("sandstone"));



        registerItemBlock(Blocks.STONE_ANDESITE, (new ItemMultiTexture(Blocks.STONE_ANDESITE, Blocks.STONE_ANDESITE, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreAndesiteVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_andesite"));

        registerItemBlock(Blocks.STONE_ANDESITE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_ANDESITE_SMOOTH, Blocks.STONE_ANDESITE_SMOOTH, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreAndesiteSmoothVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_andesite_smooth"));



        registerItemBlock(Blocks.STONE_GRANITE, (new ItemMultiTexture(Blocks.STONE_GRANITE, Blocks.STONE_GRANITE, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreGraniteVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_granite"));

        registerItemBlock(Blocks.STONE_GRANITE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_GRANITE_SMOOTH, Blocks.STONE_GRANITE_SMOOTH, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreGraniteSmoothVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_granite_smooth"));



        registerItemBlock(Blocks.STONE_DIORITE, (new ItemMultiTexture(Blocks.STONE_DIORITE, Blocks.STONE_DIORITE, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreDioriteVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_diorite"));

        registerItemBlock(Blocks.STONE_DIORITE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_DIORITE_SMOOTH, Blocks.STONE_DIORITE_SMOOTH, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockMoreDioriteSmoothVariant.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_diorite_smooth"));


        registerItemBlock(Blocks.RANDOM_ORE, (new ItemBlock(Blocks.RANDOM_ORE)));

        registerItemBlock(Blocks.AMELIORATOR);

        registerItem(1086, "nether_string", (new Item()).c("nether_string").b(CreativeModeTab.l));
        registerItem(1087, "renforced_string", (new Item()).c("renforced_string").b(CreativeModeTab.l));

        registerItem(1095, "big_xp", (new ItemBigXp()).c("big_xp").b(CreativeModeTab.l));

        registerItem(1096, "loot_powder", (new Item()).c("loot_powder").b(CreativeModeTab.l));
        registerItem(1097, "farm_powder", (new Item()).c("farm_powder").b(CreativeModeTab.l));
        registerItem(1098, "farm_nugget", (new Item()).c("farm_nugget").b(CreativeModeTab.l));

        registerItem(1099, "yellite_stick", (new Item()).c("yellite_stick").b(CreativeModeTab.l));
        registerItem(1100, "bauxite_stick", (new Item()).c("bauxite_stick").b(CreativeModeTab.l));
        registerItem(1101, "onyx_stick", (new Item()).c("onyx_stick").b(CreativeModeTab.l));
        registerItem(1102, "frazion_stick", (new Item()).c("frazion_stick").b(CreativeModeTab.l));

        registerItem(1103, "frazion_helmet_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.HEAD)).c("frazion_helmet_70"));
        registerItem(1104, "frazion_chestplate_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.CHEST)).c("frazion_chestplate_70"));
        registerItem(1105, "frazion_leggings_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.LEGS)).c("frazion_leggings_70"));
        registerItem(1106, "frazion_boots_70", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_70, 9, EnumItemSlot.FEET)).c("frazion_boots_70"));

        registerItem(1107, "frazion_helmet_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.HEAD)).c("frazion_helmet_100"));
        registerItem(1108, "frazion_chestplate_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.CHEST)).c("frazion_chestplate_100"));
        registerItem(1109, "frazion_leggings_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.LEGS)).c("frazion_leggings_100"));
        registerItem(1110, "frazion_boots_100", (new ItemArmor(ItemArmor.EnumArmorMaterial.FRAZION_100, 10, EnumItemSlot.FEET)).c("frazion_boots_100"));

        registerItem(1111, "travelers_helmet", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.HEAD)).c("travelers_helmet"));
        registerItem(1112, "travelers_chestplate", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.CHEST)).c("travelers_chestplate"));
        registerItem(1113, "travelers_leggings", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.LEGS)).c("travelers_leggings"));
        registerItem(1114, "travelers_boots", (new ItemArmor(ItemArmor.EnumArmorMaterial.TRAVELERS, 11, EnumItemSlot.FEET)).c("travelers_boots"));

        registerItem(1115, "legendary_axe", (new ItemLegendarySword(Item.EnumToolMaterial.LEGENDARY_AXE)).c("legendary_axe"));
        registerItem(1116, "legendary_sword", (new ItemLegendarySword(Item.EnumToolMaterial.LEGENDARY_SWORD)).c("legendary_sword"));
        registerItem(1117, "legendary_dagger", (new ItemLegendaryDagger(Item.EnumToolMaterial.LEGENDARY_DAGGER)).c("legendary_dagger"));

        registerItem(1057, "fz_record_1", (new ItemRecord("fz1", SoundEffects.FZ_RECORD_1)).c("fz_record_1"));
        registerItem(1058, "fz_record_2", (new ItemRecord("fz2", SoundEffects.FZ_RECORD_2)).c("fz_record_2"));
        registerItem(1059, "fz_record_3", (new ItemRecord("fz3", SoundEffects.FZ_RECORD_3)).c("fz_record_3"));
        registerItem(1118, "fz_record_4", (new ItemRecord("fz4", SoundEffects.FZ_RECORD_4)).c("fz_record_4"));
        registerItem(1119, "fz_record_5", (new ItemRecord("fz5", SoundEffects.FZ_RECORD_5)).c("fz_record_5"));
        registerItem(1120, "fz_record_6", (new ItemRecord("fz6", SoundEffects.FZ_RECORD_6)).c("fz_record_6"));
        registerItem(1121, "fz_record_7", (new ItemRecord("fz7", SoundEffects.FZ_RECORD_7)).c("fz_record_7"));
        registerItem(1122, "fz_record_8", (new ItemRecord("fz8", SoundEffects.FZ_RECORD_8)).c("fz_record_8"));
        registerItem(1123, "fz_record_9", (new ItemRecord("fz9", SoundEffects.FZ_RECORD_9)).c("fz_record_9"));
        registerItem(1124, "fz_record_10", (new ItemRecord("fz10", SoundEffects.FZ_RECORD_10)).c("fz_record_10"));

        registerItem(1125, "legendary_scythe", (new ItemLegendaryScythe(Item.EnumToolMaterial.LEGENDARY_SCYTHE)).c("legendary_scythe"));

        registerItem(1200, "dynamite", (new ItemDynamite()).c("dynamite").b(CreativeModeTab.l));
        registerItem(1201, "dynamite_arrow", (new ItemDynamiteArrow()).c("dynamite_arrow"));

        registerItem(1205, "spawner_pickaxe", (new ItemSpawnerPickaxe()).c("spawner_pickaxe"));

        registerItem(1206, "frazion_dagger", (new ItemSword(Item.EnumToolMaterial.FRAZION_DAGGER)).c("frazion_dagger"));

        registerItem(1207, "billet", (new Item()).c("billet").b(CreativeModeTab.l));

        registerItem(1208, "farm_sword", (new ItemSword(Item.EnumToolMaterial.FARM_SWORD)).c("farm_sword"));

        registerItemBlock(Blocks.RENFORCED_SAND, (new ItemBlock(Blocks.RENFORCED_SAND)));

        registerItem(1213, "obsidian_tower", (new Item()).c("obsidian_tower").b(CreativeModeTab.l));

        registerItem(1214, "booster_xp", (new Item()).c("booster_xp").b(CreativeModeTab.l));
        registerItem(1215, "booster_aptitude", (new Item()).c("booster_aptitude").b(CreativeModeTab.l));
        registerItem(1216, "booster_repair", (new Item()).c("booster_repair").b(CreativeModeTab.l));

        registerItem(1217, "withered_bone", (new Item()).c("withered_bone").b(CreativeModeTab.f));
        registerItem(1218, "withered_bone_meal", (new ItemWitheredBoneMeal()).c("withered_bone_meal").b(CreativeModeTab.f));

        registerItem(1219, "bottle_xp", (new ItemBottleXP()).c("bottle_xp"));
        registerItem(1220, "faction_token", (new Item()).c("faction_token").b(CreativeModeTab.l));

        registerItemBlock(Blocks.WITHER_BLOCK);
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X1);
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X2);
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X3);
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X4);
        registerItemBlock(Blocks.COMPACT_COBBLESTONE_X5);
        registerItemBlock(Blocks.REVERSE_FALL_BLOCK);

        registerItemBlock(Blocks.STONE_BLACKSTONE, (new ItemMultiTexture(Blocks.STONE_BLACKSTONE, Blocks.STONE_BLACKSTONE, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockDarkAndesite.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_blackstone"));
        registerItemBlock(Blocks.STONE_BLACKSTONE_SMOOTH, (new ItemMultiTexture(Blocks.STONE_BLACKSTONE_SMOOTH, Blocks.STONE_BLACKSTONE_SMOOTH, new ItemMultiTexture.a()
        {
            public String a(ItemStack p_apply_1_)
            {
                return BlockSmoothDarkAndesite.VariantType.a(p_apply_1_.getData()).c();
            }
        })).c("stone_blackstone_smooth"));

        registerItemBlock(Blocks.BLOCK_PLACER_CHEST);
        registerItemBlock(Blocks.BLOCK_PLACER_TRAPCHEST);

        registerItemBlock(Blocks.TROPHY_FORGE);
        registerItemBlock(Blocks.ITEM_CRUSHER);
        registerItemBlock(Blocks.GRIMOIRE_PEDESTAL);

        registerItem(1250, "trophy_bat", (new ItemSkeletonTrophy()).c("trophy_bat"));
        registerItem(1251, "trophy_blaze", (new ItemSkeletonTrophy()).c("trophy_blaze"));
        registerItem(1252, "trophy_creeper", (new ItemSkeletonTrophy()).c("trophy_creeper"));
        registerItem(1253, "trophy_enderman", (new ItemSkeletonTrophy()).c("trophy_enderman"));
        registerItem(1254, "trophy_ghast", (new ItemSkeletonTrophy()).c("trophy_ghast"));
        registerItem(1255, "trophy_guardian", (new ItemSkeletonTrophy()).c("trophy_guardian"));
        registerItem(1256, "trophy_villager", (new ItemSkeletonTrophy()).c("trophy_villager"));
        registerItem(1257, "trophy_shulker", (new ItemShulkerTrophy()).c("trophy_shulker"));
        registerItem(1258, "trophy_spider", (new ItemSkeletonTrophy()).c("trophy_spider"));
        registerItem(1259, "trophy_skeleton", (new ItemSkeletonTrophy()).c("trophy_skeleton"));
        registerItem(1260, "trophy_slime", (new ItemSkeletonTrophy()).c("trophy_slime"));
        registerItem(1261, "trophy_squid", (new ItemSkeletonTrophy()).c("trophy_squid"));
        registerItem(1262, "trophy_pig", (new ItemSkeletonTrophy()).c("trophy_pig"));
        registerItem(1263, "trophy_sheep", (new ItemSkeletonTrophy()).c("trophy_sheep"));
        registerItem(1264, "trophy_iron_golem", (new ItemSkeletonTrophy()).c("trophy_iron_golem"));
        registerItem(1265, "trophy_silverfish", (new ItemSkeletonTrophy()).c("trophy_silverfish"));

        registerItem(1300, "rune_anti_malus", (new Item()).c("rune_anti_malus").b(CreativeModeTab.l));
        registerItem(1301, "rune_bonus", (new Item()).c("rune_bonus").b(CreativeModeTab.l));
        registerItem(1302, "rune_chance", (new Item()).c("rune_chance").b(CreativeModeTab.l));
        registerItem(1303, "rune_damage", (new Item()).c("rune_damage").b(CreativeModeTab.l));
        registerItem(1304, "rune_health", (new Item()).c("rune_health").b(CreativeModeTab.l));
        registerItem(1305, "rune_mining", (new Item()).c("rune_mining").b(CreativeModeTab.l));
        registerItem(1306, "rune_regeneration", (new Item()).c("rune_regeneration").b(CreativeModeTab.l));
        registerItem(1307, "rune_resistance", (new Item()).c("rune_resistance").b(CreativeModeTab.l));
        registerItem(1308, "rune_speed", (new Item()).c("rune_speed").b(CreativeModeTab.l));
    }


    /**
     * Register a default ItemBlock for the given Block.
     */
    private static void registerItemBlock(Block blockIn)
    {
        registerItemBlock(blockIn, new ItemBlock(blockIn));
    }

    /**
     * Register the given Item as the ItemBlock for the given Block.
     */
    protected static void registerItemBlock(Block blockIn, Item itemIn)
    {
        registerItem(Block.getId(blockIn), Block.REGISTRY.b(blockIn), itemIn);
        Item.BLOCK_TO_ITEM.put(blockIn, itemIn);
    }

    private static void registerItem(int id, String textualID, Item itemIn)
    {
        registerItem(id, new MinecraftKey("frazionz", textualID), itemIn);
    }

    private static void registerItem(int id, MinecraftKey textualID, Item itemIn)
    {
        Item.REGISTRY.a(id, textualID, itemIn);
    }

}
