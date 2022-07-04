package net.minecraft.server;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.util.UUID;

public class PacketPlayOutUpdateSkin implements Packet<PacketListenerPlayOut> {

    private UUID uuid;

    public PacketPlayOutUpdateSkin() {}

    public PacketPlayOutUpdateSkin(@NotNull UUID uuid) {
         this.uuid = uuid;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.uuid = packetdataserializer.i();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(uuid);
    }

    @Override
    public void a(PacketListenerPlayOut packetIn) {
        packetIn.a(this);
    }

    public UUID getUuid() {
        return uuid;
    }
}
