package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUserDataBoolean implements Packet<PacketListenerPlayOut> {

    private boolean info;

    public PacketPlayOutUserDataBoolean() {}

    public PacketPlayOutUserDataBoolean(boolean info) {
        this.info = info;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeBoolean(this.info);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
    }
}
