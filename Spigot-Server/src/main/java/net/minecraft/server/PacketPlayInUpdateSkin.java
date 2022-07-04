package net.minecraft.server;

import java.io.IOException;
import java.util.UUID;

public class PacketPlayInUpdateSkin implements Packet<PacketListenerPlayIn> {

    private UUID uuid;

    public PacketPlayInUpdateSkin() {}

    public PacketPlayInUpdateSkin(UUID uuid) {
        this.uuid = uuid;
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.processStartUpdateSkin(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.uuid = packetdataserializer.i();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.uuid);
    }

    public UUID getUuid() {
        return this.uuid;
    }
}

