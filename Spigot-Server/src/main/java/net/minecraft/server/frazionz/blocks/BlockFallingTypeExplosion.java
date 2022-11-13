package net.minecraft.server.frazionz.blocks;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.BlockFalling;
import net.minecraft.server.Material;

public class BlockFallingTypeExplosion extends BlockFalling {

	private ExplosionBlockType type;
	
    public BlockFallingTypeExplosion(Material material, ExplosionBlockType type) {
        super(material);
    }
}
