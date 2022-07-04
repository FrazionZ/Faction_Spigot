package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUserDataDouble implements Packet<PacketListenerPlayOut> {

    private double info;

    public PacketPlayOutUserDataDouble() {}

    public PacketPlayOutUserDataDouble(double info) {
        this.info = info;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeDouble(this.info);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
    }
}
