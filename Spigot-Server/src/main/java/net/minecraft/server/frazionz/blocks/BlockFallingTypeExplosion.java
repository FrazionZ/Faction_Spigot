package net.minecraft.server.frazionz.blocks;

import java.util.Random;

import org.bukkit.frazionz.enums.ExplosionBlockType;

import net.minecraft.server.Block;
import net.minecraft.server.BlockFalling;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Item;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.MaterialMapColor;
import net.minecraft.server.MathHelper;
import net.minecraft.server.World;
import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionBlockType;

public class BlockFallingTypeExplosion extends BlockFalling implements FzExplosionBlockType {

	private ExplosionBlockType type;
	
    public BlockFallingTypeExplosion(Material material, ExplosionBlockType type) {
        super(material);
    }

	@Override
	public ExplosionBlockType getExplosionBlockType() {
		return type;
	}
}
