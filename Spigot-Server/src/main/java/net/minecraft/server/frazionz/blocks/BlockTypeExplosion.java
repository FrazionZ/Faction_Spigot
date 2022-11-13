package net.minecraft.server.frazionz.blocks;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.Block;
import net.minecraft.server.Material;

public class BlockTypeExplosion extends Block {

	private ExplosionBlockType type;
	
    public BlockTypeExplosion(Material material, ExplosionBlockType type) {
        super(material);
    }
}
