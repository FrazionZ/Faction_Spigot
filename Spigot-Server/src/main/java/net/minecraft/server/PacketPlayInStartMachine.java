package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInStartMachine implements Packet<PacketListenerPlayIn> {

    private int windowId;

    public PacketPlayInStartMachine() {}

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.processStartMachine(this);
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.windowId = packetdataserializer.readByte();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.writeByte(this.windowId);
    }

    public int getWindowId() {
        return this.windowId;
    }
}
