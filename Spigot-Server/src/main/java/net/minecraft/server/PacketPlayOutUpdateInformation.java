package net.minecraft.server;

import java.io.IOException;

public class PacketPlayOutUpdateInformation implements Packet<PacketListenerPlayOut> {

    private int information;

    public PacketPlayOutUpdateInformation() {}

    public PacketPlayOutUpdateInformation(int information) {
        this.information = information;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {}

    public void b(PacketDataSerializer packetdataserializer) throws IOException
    {
        packetdataserializer.writeInt(this.information);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {}
}
