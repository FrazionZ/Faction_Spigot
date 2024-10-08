package net.minecraft.server;

public class DataConverterMaterialId implements IDataConverter {

    private static final String[] a = new String[2268];

    public DataConverterMaterialId() {}

    public int a() {
        return 102;
    }

    public NBTTagCompound a(NBTTagCompound nbttagcompound) {
        if (nbttagcompound.hasKeyOfType("id", 99)) {
            short short0 = nbttagcompound.getShort("id");

            if (short0 > 0 && short0 < DataConverterMaterialId.a.length && DataConverterMaterialId.a[short0] != null) {
                nbttagcompound.setString("id", DataConverterMaterialId.a[short0]);
            }
        }

        return nbttagcompound;
    }

    static {
        DataConverterMaterialId.a[1] = "minecraft:stone";
        DataConverterMaterialId.a[2] = "minecraft:grass";
        DataConverterMaterialId.a[3] = "minecraft:dirt";
        DataConverterMaterialId.a[4] = "minecraft:cobblestone";
        DataConverterMaterialId.a[5] = "minecraft:planks";
        DataConverterMaterialId.a[6] = "minecraft:sapling";
        DataConverterMaterialId.a[7] = "minecraft:bedrock";
        DataConverterMaterialId.a[8] = "minecraft:flowing_water";
        DataConverterMaterialId.a[9] = "minecraft:water";
        DataConverterMaterialId.a[10] = "minecraft:flowing_lava";
        DataConverterMaterialId.a[11] = "minecraft:lava";
        DataConverterMaterialId.a[12] = "minecraft:sand";
        DataConverterMaterialId.a[13] = "minecraft:gravel";
        DataConverterMaterialId.a[14] = "minecraft:gold_ore";
        DataConverterMaterialId.a[15] = "minecraft:iron_ore";
        DataConverterMaterialId.a[16] = "minecraft:coal_ore";
        DataConverterMaterialId.a[17] = "minecraft:log";
        DataConverterMaterialId.a[18] = "minecraft:leaves";
        DataConverterMaterialId.a[19] = "minecraft:sponge";
        DataConverterMaterialId.a[20] = "minecraft:glass";
        DataConverterMaterialId.a[21] = "minecraft:lapis_ore";
        DataConverterMaterialId.a[22] = "minecraft:lapis_block";
        DataConverterMaterialId.a[23] = "minecraft:dispenser";
        DataConverterMaterialId.a[24] = "minecraft:sandstone";
        DataConverterMaterialId.a[25] = "minecraft:noteblock";
        DataConverterMaterialId.a[27] = "minecraft:golden_rail";
        DataConverterMaterialId.a[28] = "minecraft:detector_rail";
        DataConverterMaterialId.a[29] = "minecraft:sticky_piston";
        DataConverterMaterialId.a[30] = "minecraft:web";
        DataConverterMaterialId.a[31] = "minecraft:tallgrass";
        DataConverterMaterialId.a[32] = "minecraft:deadbush";
        DataConverterMaterialId.a[33] = "minecraft:piston";
        DataConverterMaterialId.a[35] = "minecraft:wool";
        DataConverterMaterialId.a[37] = "minecraft:yellow_flower";
        DataConverterMaterialId.a[38] = "minecraft:red_flower";
        DataConverterMaterialId.a[39] = "minecraft:brown_mushroom";
        DataConverterMaterialId.a[40] = "minecraft:red_mushroom";
        DataConverterMaterialId.a[41] = "minecraft:gold_block";
        DataConverterMaterialId.a[42] = "minecraft:iron_block";
        DataConverterMaterialId.a[43] = "minecraft:double_stone_slab";
        DataConverterMaterialId.a[44] = "minecraft:stone_slab";
        DataConverterMaterialId.a[45] = "minecraft:brick_block";
        DataConverterMaterialId.a[46] = "minecraft:tnt";
        DataConverterMaterialId.a[47] = "minecraft:bookshelf";
        DataConverterMaterialId.a[48] = "minecraft:mossy_cobblestone";
        DataConverterMaterialId.a[49] = "minecraft:obsidian";
        DataConverterMaterialId.a[50] = "minecraft:torch";
        DataConverterMaterialId.a[51] = "minecraft:fire";
        DataConverterMaterialId.a[52] = "minecraft:mob_spawner";
        DataConverterMaterialId.a[53] = "minecraft:oak_stairs";
        DataConverterMaterialId.a[54] = "minecraft:chest";
        DataConverterMaterialId.a[56] = "minecraft:diamond_ore";
        DataConverterMaterialId.a[57] = "minecraft:diamond_block";
        DataConverterMaterialId.a[58] = "minecraft:crafting_table";
        DataConverterMaterialId.a[60] = "minecraft:farmland";
        DataConverterMaterialId.a[61] = "minecraft:furnace";
        DataConverterMaterialId.a[62] = "minecraft:lit_furnace";
        DataConverterMaterialId.a[65] = "minecraft:ladder";
        DataConverterMaterialId.a[66] = "minecraft:rail";
        DataConverterMaterialId.a[67] = "minecraft:stone_stairs";
        DataConverterMaterialId.a[69] = "minecraft:lever";
        DataConverterMaterialId.a[70] = "minecraft:stone_pressure_plate";
        DataConverterMaterialId.a[72] = "minecraft:wooden_pressure_plate";
        DataConverterMaterialId.a[73] = "minecraft:redstone_ore";
        DataConverterMaterialId.a[76] = "minecraft:redstone_torch";
        DataConverterMaterialId.a[77] = "minecraft:stone_button";
        DataConverterMaterialId.a[78] = "minecraft:snow_layer";
        DataConverterMaterialId.a[79] = "minecraft:ice";
        DataConverterMaterialId.a[80] = "minecraft:snow";
        DataConverterMaterialId.a[81] = "minecraft:cactus";
        DataConverterMaterialId.a[82] = "minecraft:clay";
        DataConverterMaterialId.a[84] = "minecraft:jukebox";
        DataConverterMaterialId.a[85] = "minecraft:fence";
        DataConverterMaterialId.a[86] = "minecraft:pumpkin";
        DataConverterMaterialId.a[87] = "minecraft:netherrack";
        DataConverterMaterialId.a[88] = "minecraft:soul_sand";
        DataConverterMaterialId.a[89] = "minecraft:glowstone";
        DataConverterMaterialId.a[90] = "minecraft:portal";
        DataConverterMaterialId.a[91] = "minecraft:lit_pumpkin";
        DataConverterMaterialId.a[95] = "minecraft:stained_glass";
        DataConverterMaterialId.a[96] = "minecraft:trapdoor";
        DataConverterMaterialId.a[97] = "minecraft:monster_egg";
        DataConverterMaterialId.a[98] = "minecraft:stonebrick";
        DataConverterMaterialId.a[99] = "minecraft:brown_mushroom_block";
        DataConverterMaterialId.a[100] = "minecraft:red_mushroom_block";
        DataConverterMaterialId.a[101] = "minecraft:iron_bars";
        DataConverterMaterialId.a[102] = "minecraft:glass_pane";
        DataConverterMaterialId.a[103] = "minecraft:melon_block";
        DataConverterMaterialId.a[106] = "minecraft:vine";
        DataConverterMaterialId.a[107] = "minecraft:fence_gate";
        DataConverterMaterialId.a[108] = "minecraft:brick_stairs";
        DataConverterMaterialId.a[109] = "minecraft:stone_brick_stairs";
        DataConverterMaterialId.a[110] = "minecraft:mycelium";
        DataConverterMaterialId.a[111] = "minecraft:waterlily";
        DataConverterMaterialId.a[112] = "minecraft:nether_brick";
        DataConverterMaterialId.a[113] = "minecraft:nether_brick_fence";
        DataConverterMaterialId.a[114] = "minecraft:nether_brick_stairs";
        DataConverterMaterialId.a[116] = "minecraft:enchanting_table";
        DataConverterMaterialId.a[119] = "minecraft:end_portal";
        DataConverterMaterialId.a[120] = "minecraft:end_portal_frame";
        DataConverterMaterialId.a[121] = "minecraft:end_stone";
        DataConverterMaterialId.a[122] = "minecraft:dragon_egg";
        DataConverterMaterialId.a[123] = "minecraft:redstone_lamp";
        DataConverterMaterialId.a[125] = "minecraft:double_wooden_slab";
        DataConverterMaterialId.a[126] = "minecraft:wooden_slab";
        DataConverterMaterialId.a[127] = "minecraft:cocoa";
        DataConverterMaterialId.a[128] = "minecraft:sandstone_stairs";
        DataConverterMaterialId.a[129] = "minecraft:emerald_ore";
        DataConverterMaterialId.a[130] = "minecraft:ender_chest";
        DataConverterMaterialId.a[131] = "minecraft:tripwire_hook";
        DataConverterMaterialId.a[133] = "minecraft:emerald_block";
        DataConverterMaterialId.a[134] = "minecraft:spruce_stairs";
        DataConverterMaterialId.a[135] = "minecraft:birch_stairs";
        DataConverterMaterialId.a[136] = "minecraft:jungle_stairs";
        DataConverterMaterialId.a[137] = "minecraft:command_block";
        DataConverterMaterialId.a[138] = "minecraft:beacon";
        DataConverterMaterialId.a[139] = "minecraft:cobblestone_wall";
        DataConverterMaterialId.a[141] = "minecraft:carrots";
        DataConverterMaterialId.a[142] = "minecraft:potatoes";
        DataConverterMaterialId.a[143] = "minecraft:wooden_button";
        DataConverterMaterialId.a[145] = "minecraft:anvil";
        DataConverterMaterialId.a[146] = "minecraft:trapped_chest";
        DataConverterMaterialId.a[147] = "minecraft:light_weighted_pressure_plate";
        DataConverterMaterialId.a[148] = "minecraft:heavy_weighted_pressure_plate";
        DataConverterMaterialId.a[151] = "minecraft:daylight_detector";
        DataConverterMaterialId.a[152] = "minecraft:redstone_block";
        DataConverterMaterialId.a[153] = "minecraft:quartz_ore";
        DataConverterMaterialId.a[154] = "minecraft:hopper";
        DataConverterMaterialId.a[155] = "minecraft:quartz_block";
        DataConverterMaterialId.a[156] = "minecraft:quartz_stairs";
        DataConverterMaterialId.a[157] = "minecraft:activator_rail";
        DataConverterMaterialId.a[158] = "minecraft:dropper";
        DataConverterMaterialId.a[159] = "minecraft:stained_hardened_clay";
        DataConverterMaterialId.a[160] = "minecraft:stained_glass_pane";
        DataConverterMaterialId.a[161] = "minecraft:leaves2";
        DataConverterMaterialId.a[162] = "minecraft:log2";
        DataConverterMaterialId.a[163] = "minecraft:acacia_stairs";
        DataConverterMaterialId.a[164] = "minecraft:dark_oak_stairs";
        DataConverterMaterialId.a[170] = "minecraft:hay_block";
        DataConverterMaterialId.a[171] = "minecraft:carpet";
        DataConverterMaterialId.a[172] = "minecraft:hardened_clay";
        DataConverterMaterialId.a[173] = "minecraft:coal_block";
        DataConverterMaterialId.a[174] = "minecraft:packed_ice";
        DataConverterMaterialId.a[175] = "minecraft:double_plant";
        DataConverterMaterialId.a[256] = "minecraft:iron_shovel";
        DataConverterMaterialId.a[257] = "minecraft:iron_pickaxe";
        DataConverterMaterialId.a[258] = "minecraft:iron_axe";
        DataConverterMaterialId.a[259] = "minecraft:flint_and_steel";
        DataConverterMaterialId.a[260] = "minecraft:apple";
        DataConverterMaterialId.a[261] = "minecraft:bow";
        DataConverterMaterialId.a[262] = "minecraft:arrow";
        DataConverterMaterialId.a[263] = "minecraft:coal";
        DataConverterMaterialId.a[264] = "minecraft:diamond";
        DataConverterMaterialId.a[265] = "minecraft:iron_ingot";
        DataConverterMaterialId.a[266] = "minecraft:gold_ingot";
        DataConverterMaterialId.a[267] = "minecraft:iron_sword";
        DataConverterMaterialId.a[268] = "minecraft:wooden_sword";
        DataConverterMaterialId.a[269] = "minecraft:wooden_shovel";
        DataConverterMaterialId.a[270] = "minecraft:wooden_pickaxe";
        DataConverterMaterialId.a[271] = "minecraft:wooden_axe";
        DataConverterMaterialId.a[272] = "minecraft:stone_sword";
        DataConverterMaterialId.a[273] = "minecraft:stone_shovel";
        DataConverterMaterialId.a[274] = "minecraft:stone_pickaxe";
        DataConverterMaterialId.a[275] = "minecraft:stone_axe";
        DataConverterMaterialId.a[276] = "minecraft:diamond_sword";
        DataConverterMaterialId.a[277] = "minecraft:diamond_shovel";
        DataConverterMaterialId.a[278] = "minecraft:diamond_pickaxe";
        DataConverterMaterialId.a[279] = "minecraft:diamond_axe";
        DataConverterMaterialId.a[280] = "minecraft:stick";
        DataConverterMaterialId.a[281] = "minecraft:bowl";
        DataConverterMaterialId.a[282] = "minecraft:mushroom_stew";
        DataConverterMaterialId.a[283] = "minecraft:golden_sword";
        DataConverterMaterialId.a[284] = "minecraft:golden_shovel";
        DataConverterMaterialId.a[285] = "minecraft:golden_pickaxe";
        DataConverterMaterialId.a[286] = "minecraft:golden_axe";
        DataConverterMaterialId.a[287] = "minecraft:string";
        DataConverterMaterialId.a[288] = "minecraft:feather";
        DataConverterMaterialId.a[289] = "minecraft:gunpowder";
        DataConverterMaterialId.a[290] = "minecraft:wooden_hoe";
        DataConverterMaterialId.a[291] = "minecraft:stone_hoe";
        DataConverterMaterialId.a[292] = "minecraft:iron_hoe";
        DataConverterMaterialId.a[293] = "minecraft:diamond_hoe";
        DataConverterMaterialId.a[294] = "minecraft:golden_hoe";
        DataConverterMaterialId.a[295] = "minecraft:wheat_seeds";
        DataConverterMaterialId.a[296] = "minecraft:wheat";
        DataConverterMaterialId.a[297] = "minecraft:bread";
        DataConverterMaterialId.a[298] = "minecraft:leather_helmet";
        DataConverterMaterialId.a[299] = "minecraft:leather_chestplate";
        DataConverterMaterialId.a[300] = "minecraft:leather_leggings";
        DataConverterMaterialId.a[301] = "minecraft:leather_boots";
        DataConverterMaterialId.a[302] = "minecraft:chainmail_helmet";
        DataConverterMaterialId.a[303] = "minecraft:chainmail_chestplate";
        DataConverterMaterialId.a[304] = "minecraft:chainmail_leggings";
        DataConverterMaterialId.a[305] = "minecraft:chainmail_boots";
        DataConverterMaterialId.a[306] = "minecraft:iron_helmet";
        DataConverterMaterialId.a[307] = "minecraft:iron_chestplate";
        DataConverterMaterialId.a[308] = "minecraft:iron_leggings";
        DataConverterMaterialId.a[309] = "minecraft:iron_boots";
        DataConverterMaterialId.a[310] = "minecraft:diamond_helmet";
        DataConverterMaterialId.a[311] = "minecraft:diamond_chestplate";
        DataConverterMaterialId.a[312] = "minecraft:diamond_leggings";
        DataConverterMaterialId.a[313] = "minecraft:diamond_boots";
        DataConverterMaterialId.a[314] = "minecraft:golden_helmet";
        DataConverterMaterialId.a[315] = "minecraft:golden_chestplate";
        DataConverterMaterialId.a[316] = "minecraft:golden_leggings";
        DataConverterMaterialId.a[317] = "minecraft:golden_boots";
        DataConverterMaterialId.a[318] = "minecraft:flint";
        DataConverterMaterialId.a[319] = "minecraft:porkchop";
        DataConverterMaterialId.a[320] = "minecraft:cooked_porkchop";
        DataConverterMaterialId.a[321] = "minecraft:painting";
        DataConverterMaterialId.a[322] = "minecraft:golden_apple";
        DataConverterMaterialId.a[323] = "minecraft:sign";
        DataConverterMaterialId.a[324] = "minecraft:wooden_door";
        DataConverterMaterialId.a[325] = "minecraft:bucket";
        DataConverterMaterialId.a[326] = "minecraft:water_bucket";
        DataConverterMaterialId.a[327] = "minecraft:lava_bucket";
        DataConverterMaterialId.a[328] = "minecraft:minecart";
        DataConverterMaterialId.a[329] = "minecraft:saddle";
        DataConverterMaterialId.a[330] = "minecraft:iron_door";
        DataConverterMaterialId.a[331] = "minecraft:redstone";
        DataConverterMaterialId.a[332] = "minecraft:snowball";
        DataConverterMaterialId.a[333] = "minecraft:boat";
        DataConverterMaterialId.a[334] = "minecraft:leather";
        DataConverterMaterialId.a[335] = "minecraft:milk_bucket";
        DataConverterMaterialId.a[336] = "minecraft:brick";
        DataConverterMaterialId.a[337] = "minecraft:clay_ball";
        DataConverterMaterialId.a[338] = "minecraft:reeds";
        DataConverterMaterialId.a[339] = "minecraft:paper";
        DataConverterMaterialId.a[340] = "minecraft:book";
        DataConverterMaterialId.a[341] = "minecraft:slime_ball";
        DataConverterMaterialId.a[342] = "minecraft:chest_minecart";
        DataConverterMaterialId.a[343] = "minecraft:furnace_minecart";
        DataConverterMaterialId.a[344] = "minecraft:egg";
        DataConverterMaterialId.a[345] = "minecraft:compass";
        DataConverterMaterialId.a[346] = "minecraft:fishing_rod";
        DataConverterMaterialId.a[347] = "minecraft:clock";
        DataConverterMaterialId.a[348] = "minecraft:glowstone_dust";
        DataConverterMaterialId.a[349] = "minecraft:fish";
        DataConverterMaterialId.a[350] = "minecraft:cooked_fished";
        DataConverterMaterialId.a[351] = "minecraft:dye";
        DataConverterMaterialId.a[352] = "minecraft:bone";
        DataConverterMaterialId.a[353] = "minecraft:sugar";
        DataConverterMaterialId.a[354] = "minecraft:cake";
        DataConverterMaterialId.a[355] = "minecraft:bed";
        DataConverterMaterialId.a[356] = "minecraft:repeater";
        DataConverterMaterialId.a[357] = "minecraft:cookie";
        DataConverterMaterialId.a[358] = "minecraft:filled_map";
        DataConverterMaterialId.a[359] = "minecraft:shears";
        DataConverterMaterialId.a[360] = "minecraft:melon";
        DataConverterMaterialId.a[361] = "minecraft:pumpkin_seeds";
        DataConverterMaterialId.a[362] = "minecraft:melon_seeds";
        DataConverterMaterialId.a[363] = "minecraft:beef";
        DataConverterMaterialId.a[364] = "minecraft:cooked_beef";
        DataConverterMaterialId.a[365] = "minecraft:chicken";
        DataConverterMaterialId.a[366] = "minecraft:cooked_chicken";
        DataConverterMaterialId.a[367] = "minecraft:rotten_flesh";
        DataConverterMaterialId.a[368] = "minecraft:ender_pearl";
        DataConverterMaterialId.a[369] = "minecraft:blaze_rod";
        DataConverterMaterialId.a[370] = "minecraft:ghast_tear";
        DataConverterMaterialId.a[371] = "minecraft:gold_nugget";
        DataConverterMaterialId.a[372] = "minecraft:nether_wart";
        DataConverterMaterialId.a[373] = "minecraft:potion";
        DataConverterMaterialId.a[374] = "minecraft:glass_bottle";
        DataConverterMaterialId.a[375] = "minecraft:spider_eye";
        DataConverterMaterialId.a[376] = "minecraft:fermented_spider_eye";
        DataConverterMaterialId.a[377] = "minecraft:blaze_powder";
        DataConverterMaterialId.a[378] = "minecraft:magma_cream";
        DataConverterMaterialId.a[379] = "minecraft:brewing_stand";
        DataConverterMaterialId.a[380] = "minecraft:cauldron";
        DataConverterMaterialId.a[381] = "minecraft:ender_eye";
        DataConverterMaterialId.a[382] = "minecraft:speckled_melon";
        DataConverterMaterialId.a[383] = "minecraft:spawn_egg";
        DataConverterMaterialId.a[384] = "minecraft:experience_bottle";
        DataConverterMaterialId.a[385] = "minecraft:fire_charge";
        DataConverterMaterialId.a[386] = "minecraft:writable_book";
        DataConverterMaterialId.a[387] = "minecraft:written_book";
        DataConverterMaterialId.a[388] = "minecraft:emerald";
        DataConverterMaterialId.a[389] = "minecraft:item_frame";
        DataConverterMaterialId.a[390] = "minecraft:flower_pot";
        DataConverterMaterialId.a[391] = "minecraft:carrot";
        DataConverterMaterialId.a[392] = "minecraft:potato";
        DataConverterMaterialId.a[393] = "minecraft:baked_potato";
        DataConverterMaterialId.a[394] = "minecraft:poisonous_potato";
        DataConverterMaterialId.a[395] = "minecraft:map";
        DataConverterMaterialId.a[396] = "minecraft:golden_carrot";
        DataConverterMaterialId.a[397] = "minecraft:skull";
        DataConverterMaterialId.a[398] = "minecraft:carrot_on_a_stick";
        DataConverterMaterialId.a[399] = "minecraft:nether_star";
        DataConverterMaterialId.a[400] = "minecraft:pumpkin_pie";
        DataConverterMaterialId.a[401] = "minecraft:fireworks";
        DataConverterMaterialId.a[402] = "minecraft:firework_charge";
        DataConverterMaterialId.a[403] = "minecraft:enchanted_book";
        DataConverterMaterialId.a[404] = "minecraft:comparator";
        DataConverterMaterialId.a[405] = "minecraft:netherbrick";
        DataConverterMaterialId.a[406] = "minecraft:quartz";
        DataConverterMaterialId.a[407] = "minecraft:tnt_minecart";
        DataConverterMaterialId.a[408] = "minecraft:hopper_minecart";
        DataConverterMaterialId.a[417] = "minecraft:iron_horse_armor";
        DataConverterMaterialId.a[418] = "minecraft:golden_horse_armor";
        DataConverterMaterialId.a[419] = "minecraft:diamond_horse_armor";
        DataConverterMaterialId.a[420] = "minecraft:lead";
        DataConverterMaterialId.a[421] = "minecraft:name_tag";
        DataConverterMaterialId.a[422] = "minecraft:command_block_minecart";
        DataConverterMaterialId.a[2256] = "minecraft:record_13";
        DataConverterMaterialId.a[2257] = "minecraft:record_cat";
        DataConverterMaterialId.a[2258] = "minecraft:record_blocks";
        DataConverterMaterialId.a[2259] = "minecraft:record_chirp";
        DataConverterMaterialId.a[2260] = "minecraft:record_far";
        DataConverterMaterialId.a[2261] = "minecraft:record_mall";
        DataConverterMaterialId.a[2262] = "minecraft:record_mellohi";
        DataConverterMaterialId.a[2263] = "minecraft:record_stal";
        DataConverterMaterialId.a[2264] = "minecraft:record_strad";
        DataConverterMaterialId.a[2265] = "minecraft:record_ward";
        DataConverterMaterialId.a[2266] = "minecraft:record_11";
        DataConverterMaterialId.a[2267] = "minecraft:record_wait";
        
        DataConverterMaterialId.a[462] = "frazionz:z_hopper";
        DataConverterMaterialId.a[463] = "frazionz:dirt_chest";
        DataConverterMaterialId.a[464] = "frazionz:yellite_chest";
        DataConverterMaterialId.a[465] = "frazionz:onyx_chest";
        DataConverterMaterialId.a[466] = "frazionz:hdv_chest";
        
        DataConverterMaterialId.a[480] = "frazionz:yellite_furnace";
        DataConverterMaterialId.a[481] = "frazionz:lit_yellite_furnace";
        DataConverterMaterialId.a[482] = "frazionz:bauxite_furnace";
        DataConverterMaterialId.a[483] = "frazionz:lit_bauxite_furnace";
        DataConverterMaterialId.a[484] = "frazionz:onyx_furnace";
        DataConverterMaterialId.a[485] = "frazionz:lit_onyx_furnace";
        DataConverterMaterialId.a[486] = "frazionz:frazion_furnace";
        DataConverterMaterialId.a[487] = "frazionz:lit_frazion_furnace";
        DataConverterMaterialId.a[488] = "frazionz:bauxite_ladder";
        
        DataConverterMaterialId.a[489] = "frazionz:crimson_log";
        DataConverterMaterialId.a[490] = "frazionz:crimson_roots";
        DataConverterMaterialId.a[491] = "frazionz:crimson_fungi";
        
        DataConverterMaterialId.a[492] = "frazionz:nether_wart_block2";
        DataConverterMaterialId.a[493] = "frazionz:sandstone2";
        DataConverterMaterialId.a[494] = "frazionz:stone_andesite";
        DataConverterMaterialId.a[495] = "frazionz:stone_andesite_smooth";
        DataConverterMaterialId.a[496] = "frazionz:stone_granite";
        DataConverterMaterialId.a[497] = "frazionz:stone_granite_smooth";
        DataConverterMaterialId.a[498] = "frazionz:stone_diorite";
        DataConverterMaterialId.a[499] = "frazionz:stone_diorite_smooth";
        DataConverterMaterialId.a[500] = "frazionz:random_ore";
        DataConverterMaterialId.a[501] = "frazionz:ameliorator";
        DataConverterMaterialId.a[502] = "frazionz:yellite_ladder";
        DataConverterMaterialId.a[503] = "frazionz:onyx_ladder";
        DataConverterMaterialId.a[504] = "frazionz:frazion_ladder";
        
        DataConverterMaterialId.a[505] = "frazionz:bauxite_chest";
        DataConverterMaterialId.a[506] = "frazionz:frazion_chest";
        DataConverterMaterialId.a[507] = "frazionz:renforced_sand";
        
        DataConverterMaterialId.a[512] = "frazionz:wither_block";
        
        DataConverterMaterialId.a[513] = "frazionz:compact_cobblestone_x1";
        DataConverterMaterialId.a[514] = "frazionz:compact_cobblestone_x2";
        DataConverterMaterialId.a[515] = "frazionz:compact_cobblestone_x3";
        DataConverterMaterialId.a[516] = "frazionz:compact_cobblestone_x4";
        DataConverterMaterialId.a[517] = "frazionz:compact_cobblestone_x5";
        
        DataConverterMaterialId.a[518] = "frazionz:reverse_fall_block";
        
        DataConverterMaterialId.a[519] = "frazionz:stone_blackstone";
        DataConverterMaterialId.a[520] = "frazionz:stone_blackstone_smooth";
        
        DataConverterMaterialId.a[521] = "frazionz:block_placer_chest";
        DataConverterMaterialId.a[522] = "frazionz:block_placer_trapchest";
        
        DataConverterMaterialId.a[523] = "frazionz:trophy_forge";
        
        DataConverterMaterialId.a[1000] = "frazionz:yellite";
        DataConverterMaterialId.a[1001] = "frazionz:bauxite";
        DataConverterMaterialId.a[1002] = "frazionz:onyx";
        DataConverterMaterialId.a[1003] = "frazionz:frazion_powder";
        DataConverterMaterialId.a[1004] = "frazionz:frazion";
        
        DataConverterMaterialId.a[1005] = "frazionz:cosmic_powder";
        DataConverterMaterialId.a[1006] = "frazionz:cosmic_nugget";
        DataConverterMaterialId.a[1007] = "frazionz:cosmic_ingot";
        
        DataConverterMaterialId.a[1008] = "frazionz:yellite_helmet";
        DataConverterMaterialId.a[1009] = "frazionz:yellite_chestplate";
        DataConverterMaterialId.a[1010] = "frazionz:yellite_leggings";
        DataConverterMaterialId.a[1011] = "frazionz:yellite_boots";
        DataConverterMaterialId.a[1012] = "frazionz:yellite_sword";
        DataConverterMaterialId.a[1013] = "frazionz:yellite_shovel";
        DataConverterMaterialId.a[1014] = "frazionz:yellite_pickaxe";
        DataConverterMaterialId.a[1015] = "frazionz:yellite_axe";
        DataConverterMaterialId.a[1016] = "frazionz:yellite_hoe";
        
        DataConverterMaterialId.a[1017] = "frazionz:bauxite_helmet";
        DataConverterMaterialId.a[1018] = "frazionz:bauxite_chestplate";
        DataConverterMaterialId.a[1019] = "frazionz:bauxite_leggings";
        DataConverterMaterialId.a[1020] = "frazionz:bauxite_boots";
        DataConverterMaterialId.a[1021] = "frazionz:bauxite_sword";
        DataConverterMaterialId.a[1022] = "frazionz:bauxite_shovel";
        DataConverterMaterialId.a[1023] = "frazionz:bauxite_pickaxe";
        DataConverterMaterialId.a[1024] = "frazionz:bauxite_axe";
        DataConverterMaterialId.a[1025] = "frazionz:bauxite_hoe";
      
        DataConverterMaterialId.a[1026] = "frazionz:onyx_helmet";
        DataConverterMaterialId.a[1027] = "frazionz:onyx_chestplate";
        DataConverterMaterialId.a[1028] = "frazionz:onyx_leggings";
        DataConverterMaterialId.a[1029] = "frazionz:onyx_boots";
        DataConverterMaterialId.a[1030] = "frazionz:onyx_sword";
        DataConverterMaterialId.a[1031] = "frazionz:onyx_shovel";
        DataConverterMaterialId.a[1032] = "frazionz:onyx_pickaxe";
        DataConverterMaterialId.a[1033] = "frazionz:onyx_axe";
        DataConverterMaterialId.a[1034] = "frazionz:onyx_hoe";
        
        DataConverterMaterialId.a[1035] = "frazionz:frazion_helmet";
        DataConverterMaterialId.a[1036] = "frazionz:frazion_chestplate";
        DataConverterMaterialId.a[1037] = "frazionz:frazion_leggings";
        DataConverterMaterialId.a[1038] = "frazionz:frazion_boots";
        DataConverterMaterialId.a[1039] = "frazionz:frazion_sword";
        DataConverterMaterialId.a[1040] = "frazionz:frazion_shovel";
        DataConverterMaterialId.a[1041] = "frazionz:frazion_pickaxe";
        DataConverterMaterialId.a[1042] = "frazionz:frazion_axe";
        DataConverterMaterialId.a[1043] = "frazionz:frazion_hoe";
        DataConverterMaterialId.a[1060] = "frazionz:frazion_hammer";
        
        DataConverterMaterialId.a[1044] = "frazionz:ultra_bow";
        
        DataConverterMaterialId.a[1045] = "frazionz:trophy_bat";
        DataConverterMaterialId.a[1046] = "frazionz:trophy_blaze";
        DataConverterMaterialId.a[1047] = "frazionz:trophy_creeper";
        DataConverterMaterialId.a[1048] = "frazionz:trophy_enderman";
        DataConverterMaterialId.a[1049] = "frazionz:trophy_ghast";
        DataConverterMaterialId.a[1050] = "frazionz:trophy_guardian";
        DataConverterMaterialId.a[1051] = "frazionz:trophy_villager";
        DataConverterMaterialId.a[1052] = "frazionz:trophy_shulker";
        DataConverterMaterialId.a[1053] = "frazionz:trophy_spider";
        DataConverterMaterialId.a[1054] = "frazionz:trophy_skeleton";
        DataConverterMaterialId.a[1055] = "frazionz:trophy_slime";
        DataConverterMaterialId.a[1056] = "frazionz:trophy_squid";
        
        
        DataConverterMaterialId.a[1095] = "frazionz:big_xp";
        
        DataConverterMaterialId.a[1103] = "frazionz:frazion_helmet_70";
        DataConverterMaterialId.a[1104] = "frazionz:frazion_chestplate_70";
        DataConverterMaterialId.a[1105] = "frazionz:frazion_leggings_70";
        DataConverterMaterialId.a[1106] = "frazionz:frazion_boots_70";

        DataConverterMaterialId.a[1107] = "frazionz:frazion_helmet_100";
        DataConverterMaterialId.a[1108] = "frazionz:frazion_chestplate_100";
        DataConverterMaterialId.a[1109] = "frazionz:frazion_leggings_100";
        DataConverterMaterialId.a[1110] = "frazionz:frazion_boots_100";
        
        DataConverterMaterialId.a[1111] = "frazionz:travelers_helmet";
        DataConverterMaterialId.a[1112] = "frazionz:travelers_chestplate";
        DataConverterMaterialId.a[1113] = "frazionz:travelers_leggings";
        DataConverterMaterialId.a[1114] = "frazionz:travelers_boots";
        
        DataConverterMaterialId.a[1115] = "frazionz:legendary_axe";
        DataConverterMaterialId.a[1116] = "frazionz:legendary_sword";
        DataConverterMaterialId.a[1117] = "frazionz:frazion_dagger";
        DataConverterMaterialId.a[1125] = "frazionz:legendary_scythe";
        
        DataConverterMaterialId.a[1200] = "frazionz:dynamite";
        DataConverterMaterialId.a[1201] = "frazionz:dynamite_arrow";
        
        
    }
}
