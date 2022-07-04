package net.minecraft.server.frazionz.packets.client;

import java.io.IOException;

import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayOut;

public class PacketPlayOutGuiOpener implements Packet<PacketListenerPlayOut> {

    private int guiId;

    public PacketPlayOutGuiOpener() {}

    public PacketPlayOutGuiOpener(int guiId) {
        this.guiId = guiId;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeInt(this.guiId);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
    }
}
