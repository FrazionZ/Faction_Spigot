package org.bukkit.event.frazionz.packets;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.frazionz.enums.ExplosionBlockType;

public class FzClientSkillClaimReward extends Event {

	private Player player;
	private int skillType;
	private int level;
	private int rewardId;
	
	private static final HandlerList handlers = new HandlerList();
	
    public FzClientSkillClaimReward(Player player, int skillType, int level, int rewardId) {
    	this.player = player;
    	this.skillType = skillType;
    	this.level = level;
    	this.rewardId = rewardId;
    }
    
    public Player getPlayer() {
		return player;
	}
    
    public int getLevel() {
		return level;
	}
    
    public int getRewardId() {
		return rewardId;
	}
    
    public int getSkillType() {
		return skillType;
	}
    
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
