package net.minecraft.server.frazionz.packets.server;

import fz.frazionz.enums.EnumGui;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayOut;

import java.io.IOException;

public class PacketPlayOutGuiOpener implements Packet<PacketListenerPlayOut> {

    private EnumGui gui;
    private int info;

    public PacketPlayOutGuiOpener() {}

    public PacketPlayOutGuiOpener(EnumGui gui) {
        this.gui = gui;
        this.info = 0;
    }

    public PacketPlayOutGuiOpener(EnumGui gui, int info) {
        this.gui = gui;
        this.info = info;
    }

    public void a(PacketDataSerializer buf) throws IOException {
    }

    public void b(PacketDataSerializer buf) throws IOException {
        buf.a(this.gui);
        buf.writeInt(this.info);
    }

    public void a(PacketListenerPlayOut packetlistenerplayin) {
    }

    public EnumGui getGui() {
        return gui;
    }

    public int getInfo() {
        return info;
    }
}
