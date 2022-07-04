package org.bukkit.event.frazionz;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FzShopTradeEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private int boutiqueTypeId;
	private UUID playerUUID;
	private int amount;
	private int tradeType;
    
    public FzShopTradeEvent(int boutiqueTypeId, UUID playerUUID, int amount, int tradeType) {
    	this.boutiqueTypeId = boutiqueTypeId;
    	this.playerUUID = playerUUID;
		this.amount = amount;
		this.tradeType = tradeType;
    }
    
    public int getSellAmount() {
		return amount;
	}
    
    public int getTradeType() {
		return tradeType;
	}
    
    public int getBoutiqueTypeId() {
		return boutiqueTypeId;
	}
    
    public UUID getPlayerUUID() {
		return playerUUID;
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}