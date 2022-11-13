package net.minecraft.server;

import fz.frazionz.block.ExplosiveType;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionChance;

import java.util.Random;

public class BlockObsidian extends Block implements FzExplosionChance {
	
    public BlockObsidian() {
        super(Material.STONE);
        this.a(CreativeModeTab.b);
        
    }

    public Item getDropType(IBlockData iblockdata, Random random, int i) {
    	
    	if(this == Blocks.OBSIDIAN) {
            return Item.getItemOf(Blocks.OBSIDIAN);
    	}
    	else if(this == Blocks.OBSIDIAN_YELLITE) {
            return Item.getItemOf(Blocks.OBSIDIAN_YELLITE);
    	}
    	else if(this == Blocks.OBSIDIAN_BAUXITE) {
            return Item.getItemOf(Blocks.OBSIDIAN_BAUXITE);
    	}
    	else if(this == Blocks.OBSIDIAN_ONYX) {
            return Item.getItemOf(Blocks.OBSIDIAN_ONYX);
    	}
    	else if(this == Blocks.OBSIDIAN_FRAZION) {
            return Item.getItemOf(Blocks.OBSIDIAN_FRAZION);
    	}
    	else {
    		return null;
    	}
    	
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return MaterialMapColor.F;
    }

    @Override
    public float getExplosionChance(ExplosiveType type) {
        if(this == Blocks.OBSIDIAN) {
            switch(type){
                case TNT:
                    return 0.08f;
                case Z_TNT:
                    return 1;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_YELLITE) {
            switch(type){
                case TNT:
                    return 0.05f;
                case Z_TNT:
                    return 1;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_BAUXITE) {
            switch(type){
                case TNT:
                    return 0.02f;
                case Z_TNT:
                    return 0.5f;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_ONYX) {
            switch(type){
                case Z_TNT:
                    return 0.2f;
                default:
                    return 0;
            }
        }
        if(this == Blocks.OBSIDIAN_FRAZION) {
            switch(type){
                case Z_TNT:
                    return 0.05f;
                default:
                    return 0;

            }
        }
        return 0;
    }
}
