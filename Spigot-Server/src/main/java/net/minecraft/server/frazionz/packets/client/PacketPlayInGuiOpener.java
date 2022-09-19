package net.minecraft.server.frazionz.packets.client;

import java.io.IOException;

import fz.frazionz.enums.EnumGui;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayIn;

public class PacketPlayInGuiOpener implements Packet<PacketListenerPlayIn> {

    private EnumGui gui;

    public PacketPlayInGuiOpener() {}

    public PacketPlayInGuiOpener(EnumGui gui) {
        this.gui = gui;
    }

    public void a(PacketDataSerializer buf) throws IOException {
        this.gui = (EnumGui) buf.a(EnumGui.class);
    }

    public void b(PacketDataSerializer buf) throws IOException {
        buf.a(this.gui);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.processClientAskOpenGui(this);
    }

    public EnumGui getGui() {
        return gui;
    }
}
