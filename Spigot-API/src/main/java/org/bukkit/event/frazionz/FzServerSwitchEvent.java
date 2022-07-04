package org.bukkit.event.frazionz;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FzServerSwitchEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private UUID uidP;
	private int serverID;
    
    public FzServerSwitchEvent(UUID uidP, int serverID) {
    	this.uidP = uidP;
    	this.serverID = serverID;
    }
    
    public UUID getUidP() {
    	return this.uidP;
    }
    
    public int getServerID() {
		return serverID;
	}

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}