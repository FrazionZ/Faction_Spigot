package net.minecraft.server;

import java.util.Random;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionBlockType;

public class BlockObsidian extends Block implements FzExplosionBlockType {
	
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
	public ExplosionBlockType getExplosionBlockType() {
    	if(this == Blocks.OBSIDIAN_YELLITE) {
    		return ExplosionBlockType.OBSIDIAN;
    	}
    	else if(this == Blocks.OBSIDIAN_BAUXITE) {
    		return ExplosionBlockType.OBSIDIAN;
    	}
    	else if(this == Blocks.OBSIDIAN_ONYX) {
    		return ExplosionBlockType.OBSIDIAN;
    	}
    	else if(this == Blocks.OBSIDIAN_FRAZION) {
    		return ExplosionBlockType.OBSIDIAN;
    	}
    	return ExplosionBlockType.OBSIDIAN;
	}
}
