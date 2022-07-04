package org.bukkit.event.frazionz;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.frazionz.enums.ExplosionBlockType;

public class FzBlockExplodeEvent extends CancellableEvent {

	private Player player;
	private Block block;
	private ExplosiveType explosionType;
	private ExplosionBlockType blockType;
	
    public FzBlockExplodeEvent(Player player, Block block, ExplosiveType explosionType, ExplosionBlockType blockType) {
    	this.player = player;
    	this.block = block;
    	this.explosionType = explosionType;
    	this.blockType = blockType;
    }
    
    public Player getPlayer() {
		return player;
	}
    
    public Block getBlock() {
		return block;
	}
    
    public ExplosiveType getExplosionType() {
		return explosionType;
	}
    
    public ExplosionBlockType getBlockType() {
		return blockType;
	}
    
    public enum ExplosiveType {
    	
    	TNT,
    	Z_TNT,
    	DYNAMITE,
    	DYNAMITE_ARROW,
    	MINECART_TNT,
    	FIREBALL,
    	UNKNOWN
    	
    }
}
