package net.minecraft.server.frazionz.packets.client;

import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketPlayInCustomPayload;
import net.minecraft.server.SharedConstants;
import org.bukkit.entity.Player;

public class PacketPlayInHelper {

    public static void handlePacketPlayInCustomPayload(Player player, PacketPlayInCustomPayload packet, String channel) {

        PacketDataSerializer dataSerializer;

        switch (channel) {
            case "FZ|Auth_Code":
                dataSerializer = packet.b();
                if (dataSerializer != null && dataSerializer.readableBytes() >= 1) {
                    String authCode = SharedConstants.a(dataSerializer.e(32767));
                    System.out.println("Auth Code: " + authCode);
                }
                break;
        }
    }

}
