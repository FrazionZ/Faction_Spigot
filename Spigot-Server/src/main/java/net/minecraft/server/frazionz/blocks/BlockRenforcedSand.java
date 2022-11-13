package net.minecraft.server.frazionz.blocks;

import fz.frazionz.block.ExplosiveType;
import net.minecraft.server.Block;
import net.minecraft.server.BlockSand;
import net.minecraft.server.Blocks;
import net.minecraft.server.Material;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionChance;

public class BlockRenforcedSand extends BlockSand implements FzExplosionChance
{
    @Override
    public float getExplosionChance(ExplosiveType type) {
        switch (type) {
            case TNT:
                return 0.3333F;
            case Z_TNT:
                return 1F;
            default:
                return 0;
        }
    }
}

