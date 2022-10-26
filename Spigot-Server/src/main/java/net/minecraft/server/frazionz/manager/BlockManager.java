package net.minecraft.server.frazionz.manager;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.blocks.*;
import org.bukkit.frazionz.enums.ExplosionBlockType;

public class BlockManager {

    public static void registerBlocks()
    {
        registerBlock(454, "yellite_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("yellite_ore"));
        registerBlock(455, "yellite_block", (new Block(Material.ORE, MaterialMapColor.H)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("yellite_block").a(CreativeModeTab.b));
        registerBlock(456, "bauxite_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("bauxite_ore"));
        registerBlock(457, "bauxite_block", (new Block(Material.ORE, MaterialMapColor.H)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("bauxite_block").a(CreativeModeTab.b));
        registerBlock(458, "onyx_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("onyx_ore"));
        registerBlock(459, "onyx_block", (new Block(Material.ORE, MaterialMapColor.H)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("onyx_block").a(CreativeModeTab.b));
        registerBlock(460, "frazion_ore", (new BlockOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("frazion_ore"));
        registerBlock(461, "frazion_block", (new Block(Material.ORE, MaterialMapColor.H)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("frazion_block").a(CreativeModeTab.b));

        registerBlock(462, "z_hopper", (new BlockZHopper()).c(3.0F).b(8.0F).a(SoundEffectType.e).c("z_hopper"));

        registerBlock(466, "hdv_chest", (new BlockHdvChest()).c(2.5F).a(SoundEffectType.e).c("hdv_chest"));
        registerBlock(463, "dirt_chest", (new BlockDirtChest()).c(2.5F).a(SoundEffectType.a).c("dirt_chest"));//
        registerBlock(464, "yellite_chest", (new BlockYelliteChest()).c(2.5F).b(5.0F).a(SoundEffectType.e).c("yellite_chest"));
        registerBlock(465, "onyx_chest", (new BlockOnyxChest()).c(2.5F).b(15.0F).a(SoundEffectType.e).c("onyx_chest"));

        registerBlock(469, "cristal_rouge", (new BlockCristal(Material.SHATTERABLE, false)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("cristal_rouge").a(CreativeModeTab.b).e(0));
        registerBlock(470, "cristal_bleu", (new BlockCristal(Material.SHATTERABLE, false)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("cristal_bleu").a(CreativeModeTab.b).e(0));
        registerBlock(471, "cristal_vert", (new BlockCristal(Material.SHATTERABLE, false)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("cristal_vert").a(CreativeModeTab.b).e(0));
        registerBlock(472, "cristal_jaune", (new BlockCristal(Material.SHATTERABLE, false)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("cristal_jaune").a(CreativeModeTab.b).e(0));
        registerBlock(473, "cristal_violet", (new BlockCristal(Material.SHATTERABLE, false)).c(5.0F).b(10.0F).a(SoundEffectType.e).c("cristal_violet").a(CreativeModeTab.b).e(0));
        registerBlock(474, "obsidian_yellite", (new BlockObsidian()).c(60.0F).b(2000.0F).a(SoundEffectType.d).c("obsidian_yellite"));
        registerBlock(475, "obsidian_bauxite", (new BlockObsidian()).c(80.0F).b(2000.0F).a(SoundEffectType.d).c("obsidian_bauxite"));
        registerBlock(476, "obsidian_onyx", (new BlockObsidian()).c(100.0F).b(2000.0F).a(SoundEffectType.d).c("obsidian_onyx"));
        registerBlock(477, "obsidian_frazion", (new BlockObsidian()).c(120.0F).b(2000.0F).a(SoundEffectType.d).c("obsidian_frazion"));
        registerBlock(478, "z_tnt", (new BlockZTnt()).c(0.0F).a(SoundEffectType.c).c("z_tnt"));

        registerBlock(480, "yellite_furnace", (new BlockYelliteFurnace(false)).c(3.5F).a(SoundEffectType.d).c("yellite_furnace").a(CreativeModeTab.c));
        registerBlock(481, "lit_yellite_furnace", (new BlockYelliteFurnace(true)).c(3.5F).a(SoundEffectType.d).a(0.875F).c("lit_yellite_furnace"));

        registerBlock(482, "bauxite_furnace", (new BlockBauxiteFurnace(false)).c(3.5F).a(SoundEffectType.d).c("bauxite_furnace").a(CreativeModeTab.c));
        registerBlock(483, "lit_bauxite_furnace", (new BlockBauxiteFurnace(true)).c(3.5F).a(SoundEffectType.d).a(0.875F).c("bauxite_furnace"));

        registerBlock(484, "onyx_furnace", (new BlockOnyxFurnace(false)).c(3.5F).a(SoundEffectType.d).c("onyx_furnace").a(CreativeModeTab.c));
        registerBlock(485, "lit_onyx_furnace", (new BlockOnyxFurnace(true)).c(3.5F).a(SoundEffectType.d).a(0.875F).c("onyx_furnace"));

        registerBlock(486, "frazion_furnace", (new BlockFrazionFurnace(false)).c(3.5F).a(SoundEffectType.d).c("frazion_furnace").a(CreativeModeTab.c));
        registerBlock(487, "lit_frazion_furnace", (new BlockFrazionFurnace(true)).c(3.5F).a(SoundEffectType.d).a(0.875F).c("frazion_furnace"));

        registerBlock(488, "bauxite_ladder", (new BlockLadder()).c(0.4F).a(SoundEffectType.j).c("bauxite_ladder"));

        registerBlock(489, "crimson_log", (new BlockRotatable(Material.WOOD, MaterialMapColor.s)).c("crimson_log").a(SoundEffectType.a));
        registerBlock(490, "crimson_roots", (new BlockCrimsonRoots()).c(0.0F).a(SoundEffectType.c).c("crimson_roots"));
        registerBlock(491, "crimson_fungi", (new BlockCrimsonFungi()).c(0.0F).a(SoundEffectType.c).c("crimson_fungi"));

        registerBlock(492, "nether_wart_block2", (new BlockMoreNWBVariant()).c(1.0F).a(SoundEffectType.a).c("nether_wart_block"));
        registerBlock(493, "sandstone2", (new BlockMoreSandstoneVariant()).c(0.8F).a(SoundEffectType.d).c("sandstone"));
        registerBlock(494, "stone_andesite", (new BlockMoreAndesiteVariant()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_andesite"));
        registerBlock(495, "stone_andesite_smooth", (new BlockMoreAndesiteSmoothVariant()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_andesite_smooth"));
        registerBlock(496, "stone_granite", (new BlockMoreGraniteVariant()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_granite"));
        registerBlock(497, "stone_granite_smooth", (new BlockMoreGraniteSmoothVariant()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_granite_smooth"));
        registerBlock(498, "stone_diorite", (new BlockMoreDioriteVariant()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_diorite"));
        registerBlock(499, "stone_diorite_smooth", (new BlockMoreDioriteSmoothVariant()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_diorite_smooth"));

        registerBlock(500, "random_ore", (new BlockRandomOre()).c(3.0F).b(5.0F).a(SoundEffectType.d).c("random_ore"));

        registerBlock(501, "ameliorator", (new BlockAmeliorator()).a(0.500F).c(25.0F).b(1000.0F).a(SoundEffectType.d).c("ameliorator"));

        registerBlock(502, "yellite_ladder", (new BlockLadder()).c(0.4F).a(SoundEffectType.j).c("yellite_ladder"));
        registerBlock(503, "onyx_ladder", (new BlockLadder()).c(0.4F).a(SoundEffectType.j).c("onyx_ladder"));
        registerBlock(504, "frazion_ladder", (new BlockLadder()).c(0.4F).a(SoundEffectType.j).c("frazion_ladder"));

        registerBlock(505, "bauxite_chest", (new BlockBauxiteChest()).c(2.5F).b(10.0F).a(SoundEffectType.e).c("bauxite_chest"));
        registerBlock(506, "frazion_chest", (new BlockFrazionChest()).c(2.5F).b(20.0F).a(SoundEffectType.e).c("frazion_chest"));

        registerBlock(507, "renforced_sand", (new BlockFallingTypeExplosion(Material.SAND, ExplosionBlockType.RENFORCED_SAND)).c(1.0F).b(10.0F).a(SoundEffectType.h).c("renforced_sand"));

        registerBlock(512, "wither_block", (new BlockWitherBlock()).c(10.0F).b(2000.0F).a(SoundEffectType.d).c("wither_block"));
        registerBlock(513, "compact_cobblestone_x1", (new Block(Material.STONE)).c(2.0F).b(12.0F).a(SoundEffectType.d).c("compact_cobblestone_x1").a(CreativeModeTab.b));
        registerBlock(514, "compact_cobblestone_x2", (new Block(Material.STONE)).c(2.3F).b(16.0F).a(SoundEffectType.d).c("compact_cobblestone_x2").a(CreativeModeTab.b));
        registerBlock(515, "compact_cobblestone_x3", (new Block(Material.STONE)).c(2.6F).b(20.0F).a(SoundEffectType.d).c("compact_cobblestone_x3").a(CreativeModeTab.b));
        registerBlock(516, "compact_cobblestone_x4", (new Block(Material.STONE)).c(3.0F).b(24.0F).a(SoundEffectType.d).c("compact_cobblestone_x4").a(CreativeModeTab.b));
        registerBlock(517, "compact_cobblestone_x5", (new Block(Material.STONE)).c(3.4F).b(35.0F).a(SoundEffectType.d).c("compact_cobblestone_x5").a(CreativeModeTab.b));
        registerBlock(518, "reverse_fall_block", (new BlockReverseFall()).c(0.25F).b(10.0F).a(SoundEffectType.l).c("reverse_fall_block").a(CreativeModeTab.b));


        registerBlock(519, "stone_blackstone", (new BlockDarkAndesite()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_blackstone"));
        registerBlock(520, "stone_blackstone_smooth", (new BlockSmoothDarkAndesite()).c(1.5F).b(10.0F).a(SoundEffectType.d).c("stone_blackstone_smooth"));

        registerBlock(521, "block_placer_chest", (new BlockBlockPlacer("block_placer_chest")).j().b(6000000.0F).a(SoundEffectType.d).c("block_placer_chest"));
        registerBlock(522, "block_placer_trapchest", (new BlockBlockPlacer("block_placer_trapchest")).j().b(6000000.0F).a(SoundEffectType.d).c("block_placer_trapchest"));

        registerBlock(523, "trophy_forge", (new BlockTrophyForge()).c(25.0F).b(1000.0F).a(SoundEffectType.d).c("trophy_forge"));
        registerBlock(524, "grimoire_pedestal", (new BlockGrimoirePedestal()).c(25.0F).b(1000.0F).a(SoundEffectType.d).c("grimoire_pedestal"));
        registerBlock(525, "item_crusher", (new BlockItemCrusher()).c(25.0F).b(1000.0F).a(SoundEffectType.d).c("item_crusher"));

        registerBlock(530, "strawberries", (new BlockStrawberries()).c("strawberries"));
    }

    private static void registerBlock(int id, MinecraftKey textualID, Block block_)
    {
        Block.REGISTRY.a(id, textualID, block_);
    }

    private static void registerBlock(int id, String textualID, Block block_)
    {
        registerBlock(id, new MinecraftKey("frazionz", textualID), block_);
    }

}
