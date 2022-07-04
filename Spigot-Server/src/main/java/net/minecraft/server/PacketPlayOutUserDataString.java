package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUserDataString implements Packet<PacketListenerPlayOut> {

    private String info;

    public PacketPlayOutUserDataString() {}

    public PacketPlayOutUserDataString(String info) {
        this.info = info;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a(this.info);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
    }
}
