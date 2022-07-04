package net.minecraft.server.frazionz.packets.client;

import java.io.IOException;
import java.util.Locale;

import com.sun.istack.internal.NotNull;

import net.minecraft.server.IChatBaseComponent;
import net.minecraft.server.Packet;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketListenerPlayOut;

public class PacketPlayOutToast implements Packet<PacketListenerPlayOut> {

    private PacketPlayOutToast.EnumToastAction typeToast;
    private PacketPlayOutToast.EnumToastIcon icon;
    private IChatBaseComponent title;
    private IChatBaseComponent subtitle;

    public PacketPlayOutToast() {}

    public PacketPlayOutToast(@NotNull PacketPlayOutToast.EnumToastAction typeToast, @NotNull PacketPlayOutToast.EnumToastIcon icon, @NotNull IChatBaseComponent title, @NotNull IChatBaseComponent subtitle) {
        this.typeToast = typeToast;
        this.icon = icon;
        this.title = title;
        this.subtitle = subtitle;
    }

    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.typeToast = (PacketPlayOutToast.EnumToastAction) packetdataserializer.a(PacketPlayOutToast.EnumToastAction.class);
        this.icon = (PacketPlayOutToast.EnumToastIcon) packetdataserializer.a(PacketPlayOutToast.EnumToastIcon.class);
        this.title = packetdataserializer.f();
        this.subtitle = packetdataserializer.f();
    }

    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.typeToast);
        packetdataserializer.a((Enum) this.icon);
        packetdataserializer.a(this.title);
        packetdataserializer.a(this.subtitle);
    }

    public void a(PacketListenerPlayOut packetlistenerplayout) {
        packetlistenerplayout.a(this);
    }

    public static enum EnumToastAction {

        NORMAL, SUCCESS;

        private EnumToastAction() {}

        public static PacketPlayOutToast.EnumToastAction a(String s) {
            PacketPlayOutToast.EnumToastAction[] apacketplayouttoast_enumtoastaction = values();
            int i = apacketplayouttoast_enumtoastaction.length;

            for (int j = 0; j < i; ++j) {
                PacketPlayOutToast.EnumToastAction packetplayouttoast_enumtoastaction = apacketplayouttoast_enumtoastaction[j];

                if (packetplayouttoast_enumtoastaction.name().equalsIgnoreCase(s)) {
                    return packetplayouttoast_enumtoastaction;
                }
            }

            return EnumToastAction.NORMAL;
        }

        public static String[] a() {
            String[] astring = new String[values().length];
            int i = 0;
            PacketPlayOutToast.EnumToastAction[] apacketplayouttoast_enumtoastaction = values();
            int j = apacketplayouttoast_enumtoastaction.length;

            for (int k = 0; k < j; ++k) {
                PacketPlayOutToast.EnumToastAction packetplayouttoast_enumtoastaction = apacketplayouttoast_enumtoastaction[k];

                astring[i++] = packetplayouttoast_enumtoastaction.name().toLowerCase(Locale.ROOT);
            }

            return astring;
        }
    }

    public static enum EnumToastIcon {
        LOGO,
        MOBS;

        private EnumToastIcon() {}

        public static PacketPlayOutToast.EnumToastIcon a(String s) {
            PacketPlayOutToast.EnumToastIcon[] apacketplayouttoast_enumtoasticon = values();
            int i = apacketplayouttoast_enumtoasticon.length;

            for (int j = 0; j < i; ++j) {
                PacketPlayOutToast.EnumToastIcon packetplayouttoast_enumtoasticon = apacketplayouttoast_enumtoasticon[j];

                if (packetplayouttoast_enumtoasticon.name().equalsIgnoreCase(s)) {
                    return packetplayouttoast_enumtoasticon;
                }
            }

            return EnumToastIcon.MOBS;
        }

        public static String[] a() {
            String[] astring = new String[values().length];
            int i = 0;
            PacketPlayOutToast.EnumToastIcon[] apacketplayouttoast_enumtoasticon = values();
            int j = apacketplayouttoast_enumtoasticon.length;

            for (int k = 0; k < j; ++k) {
                PacketPlayOutToast.EnumToastIcon packetplayouttoast_enumtoasticon = apacketplayouttoast_enumtoasticon[k];

                astring[i++] = packetplayouttoast_enumtoasticon.name().toLowerCase(Locale.ROOT);
            }

            return astring;
        }
    }

}
