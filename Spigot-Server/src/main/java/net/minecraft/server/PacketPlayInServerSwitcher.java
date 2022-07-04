package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.frazionz.FzServerSwitchEvent;

public class PacketPlayInServerSwitcher implements Packet<PacketListenerPlayIn> {

	private UUID uidP;
    private int serverId;

    public PacketPlayInServerSwitcher() {
	}
    
    public PacketPlayInServerSwitcher(UUID uidP, int serverId) {
    	this.uidP = uidP;
    	this.serverId = serverId;
	}
    
    public void a(PacketListenerPlayIn packetlistenerplayin) {
    	Bukkit.getServer().getPluginManager().callEvent(new FzServerSwitchEvent(this.uidP, this.serverId));
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    	this.uidP = packetdataserializer.i();
        this.serverId = packetdataserializer.readInt();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
    	packetdataserializer.a(this.uidP);
        packetdataserializer.writeInt(this.serverId);
    }
    
    public UUID getUidP() {
        return this.uidP;
    }

    public int getServerID() {
        return this.serverId;
    }
}
