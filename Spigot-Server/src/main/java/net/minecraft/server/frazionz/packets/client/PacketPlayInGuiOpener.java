package net.minecraft.server.frazionz.packets.client;

import java.io.IOException;

import fz.frazionz.enums.EnumGui;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayIn;

public class PacketPlayInGuiOpener implements Packet<PacketListenerPlayIn> {

    private EnumGui gui;
    private int info;

    public PacketPlayInGuiOpener() {}

    public PacketPlayInGuiOpener(EnumGui gui) {
        this.gui = gui;
        this.info = 0;
    }
    public PacketPlayInGuiOpener(EnumGui gui, int info) {
        this.gui = gui;
        this.info = info;
    }

    public void a(PacketDataSerializer buf) throws IOException {
        this.gui = (EnumGui) buf.a(EnumGui.class);
        this.info = buf.readInt();
    }

    public void b(PacketDataSerializer buf) throws IOException {
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.processClientAskOpenGui(this);
    }

    public EnumGui getGui() {
        return gui;
    }

    public int getInfo() {
        return info;
    }
}
