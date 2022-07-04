package org.bukkit.craftbukkit.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.NetworkManager;

public class FzLoginEvent extends Event {

	private final String token;
	private final GameProfile gp;
	private final NetworkManager networkManager;
	private static final HandlerList HANDLERS = new HandlerList();
	
	public FzLoginEvent(GameProfile gp, String token, NetworkManager networkManager) {
		this.token = token;
		this.gp = gp;
		this.networkManager = networkManager;
	}
	
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public static HandlerList getHandlerList()
	{
		return HANDLERS;
	}
	
	public String getToken() {
		return token;
	}
	
	public GameProfile getGameProfile() {
		return gp;
	}
	
	public NetworkManager getNetworkManager() {
		return networkManager;
	}
	
}
