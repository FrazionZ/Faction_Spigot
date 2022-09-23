package net.minecraft.server.frazionz.packets.client;

import java.io.IOException;

import fz.frazionz.enums.EnumGui;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayIn;

public class PacketPlayInShopTrade implements Packet<PacketListenerPlayIn> {

	private EnumGui gui;
	private int id;
	private int amount;
	private int tradeType;
    public PacketPlayInShopTrade() {
	}
    
    public PacketPlayInShopTrade(EnumGui gui, int id, int amount, int tradeType) {
		this.gui = gui;
    	this.id = id;
		this.amount = amount;
		this.tradeType = tradeType;
	}
    
    public void a(PacketListenerPlayIn packetlistenerplayin) {
		packetlistenerplayin.processClientShopTrade(this);
    }

    public void a(PacketDataSerializer buf) throws IOException {
		this.gui = buf.a(EnumGui.class);
    	this.id = buf.readInt();
		this.amount = buf.readInt();
		this.tradeType = buf.readInt();
    }

    public void b(PacketDataSerializer buf) throws IOException {
		buf.a(this.gui);
		buf.writeInt(this.id);
		buf.writeInt(this.amount);
		buf.writeInt(this.tradeType);
    }
    
    public int getAmount() {
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
}
