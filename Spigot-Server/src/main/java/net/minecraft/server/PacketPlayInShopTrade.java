package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.frazionz.FzServerSwitchEvent;
import org.bukkit.event.frazionz.FzShopTradeEvent;

public class PacketPlayInShopTrade implements Packet<PacketListenerPlayIn> {

	private int boutiqueTypeId;
	private UUID playerUUID;
	private int amount;
	private int tradeType;
    public PacketPlayInShopTrade() {
	}
    
    public PacketPlayInShopTrade(int boutiqueTypeId, UUID playerUUID, int amount, int tradeType) {
    	this.boutiqueTypeId = boutiqueTypeId;
    	this.playerUUID = playerUUID;
		this.amount = amount;
		this.tradeType = tradeType;
	}
    
    public void a(PacketListenerPlayIn packetlistenerplayin) {
    	Bukkit.getServer().getPluginManager().callEvent(new FzShopTradeEvent(this.boutiqueTypeId, this.playerUUID, this.amount, this.tradeType));
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    	this.boutiqueTypeId = packetdataserializer.readInt();
    	this.playerUUID = packetdataserializer.i();
		this.amount = packetdataserializer.readInt();
		this.tradeType = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
    	packetdataserializer.writeInt(this.boutiqueTypeId);
    	packetdataserializer.a(this.playerUUID);
		packetdataserializer.writeInt(this.amount);
		packetdataserializer.writeInt(this.tradeType);
    }
    
    public int getAmount() {
		return amount;
	}
    
    public int getTradeType() {
		return tradeType;
	}
    
    public UUID getPlayerUUID() {
		return playerUUID;
	}
}
