package net.minecraft.server.frazionz.blocks;

import fz.frazionz.block.ExplosiveType;
import net.minecraft.server.Block;
import net.minecraft.server.Blocks;
import net.minecraft.server.Material;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionChance;

public class BlockCompactCobblestone extends Block implements FzExplosionChance
{
    public BlockCompactCobblestone()
    {
        super(Material.STONE);
    }

    @Override
    public float getExplosionChance(ExplosiveType type) {
        if(this == Blocks.COMPACT_COBBLESTONE_X1 || this == Blocks.COMPACT_COBBLESTONE_X2) {
            switch (type) {
                case TNT:
                case Z_TNT:
                    return 1F;
                default:
                    return 0;
            }
        }
        if(this == Blocks.COMPACT_COBBLESTONE_X3) {
            switch (type) {
                case TNT:
                    return 0.2F;
                case Z_TNT:
                    return 1F;
                default:
                    return 0;
            }
        }
        if(this == Blocks.COMPACT_COBBLESTONE_X4) {
            switch (type) {
                case Z_TNT:
                    return 0.8F;
                default:
                    return 0;
            }
        }
        if(this == Blocks.COMPACT_COBBLESTONE_X5) {
            switch (type) {
                case Z_TNT:
                    return 0.15F;
                default:
                    return 0;
            }
        }
        return 0;
    }
}

