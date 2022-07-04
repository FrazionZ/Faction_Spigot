package net.minecraft.server.frazionz.packets.server;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.frazionz.FzServerSwitchEvent;

import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayIn;

public abstract class PacketPlayInClickButtonTransmitter implements Packet<PacketListenerPlayIn> {
	
	protected UUID playerUUID;
	protected ButtonType buttonType;
	
	public PacketPlayInClickButtonTransmitter() {}
	
	public PacketPlayInClickButtonTransmitter(UUID playerUUID, ButtonType buttonType) {
		this.playerUUID = playerUUID;
		this.buttonType = buttonType;
	}

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    	this.playerUUID = packetdataserializer.i();
    	this.buttonType = packetdataserializer.a(PacketPlayInClickButtonTransmitter.ButtonType.class);
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
    }
	
	public enum ButtonType {
		
		SKILL_CLAIM_REWARD,
		
	}
}
