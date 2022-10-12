package net.minecraft.server.frazionz.packets.client;

import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketPlayInCustomPayload;
import org.bukkit.entity.Player;

public class PacketPlayInHelper {

    public static void handlePacketPlayInCustomPayload(Player player, PacketPlayInCustomPayload packet, String channel) {

        PacketDataSerializer dataSerializer;

        switch (channel) {
            case "FZ|Auth_Code":
                dataSerializer = packet.b();
                String authCode = dataSerializer.e(6);
                System.out.println("Auth Code: " + authCode);
                break;
        }
    }

}
