package org.bukkit.event.frazionz;

import fz.frazionz.enums.EnumGui;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ClientShopTradeEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private EnumGui gui;
	private int id;
	private int amount;
	private int tradeType;
    
    public ClientShopTradeEvent(Player player, EnumGui gui, int id, int amount, int tradeType) {
		this.player = player;
		this.gui = gui;
    	this.id = id;
		this.amount = amount;
		this.tradeType = tradeType;
    }

	public Player getPlayer() {
		return player;
	}

	public int getSellAmount() {
		return amount;
	}
    
    public int getTradeType() {
		return tradeType;
	}

	public int getId() {
		return id;
	}

	public EnumGui getGui() {
		return gui;
	}

	@Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}