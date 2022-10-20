package net.minecraft.server.frazionz.packets.client;

import com.azuriom.azauth.AuthenticationException;
import com.azuriom.azauth.AzAuthenticator;
import com.azuriom.azauth.model.User;
import fz.frazionz.enums.EnumGui;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.PacketDataSerializer;
import net.minecraft.server.PacketPlayInCustomPayload;
import net.minecraft.server.SharedConstants;
import net.minecraft.server.frazionz.packets.server.PacketPlayOutGuiOpener;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;

public class PacketPlayInHelper {
    public static String fzAuthServer = "https://auth.frazionz.net";
    public static boolean handlePacketPlayInCustomPayload(Player player, PacketPlayInCustomPayload packet, String channel) {

        switch (channel) {
            case "FZ|Auth_Code":
                if (packet.b() != null && packet.b().readableBytes() >= 1) {
                    String authCode = SharedConstants.a(packet.b().e(32767));
                    AzAuthenticator authFz = new AzAuthenticator(fzAuthServer);
                    try {
                        authFz.persocode(player.getFZUser().getAccessToken(), authCode, User.class);
                        player.setWalkSpeed(0.2f);
                        player.setFlySpeed(0.2f);
                        player.setGameMode(GameMode.SURVIVAL);
                        ((EntityPlayer) ((CraftPlayer) player).getHandle()).playerConnection.sendPacket(new PacketPlayOutGuiOpener(EnumGui.NULL));
                    } catch (AuthenticationException | IllegalStateException | IOException e) {
                        System.out.println("[FzAuth] Internal Server API ERROR, "+e.getMessage());
                        player.kickPlayer("Le code personnel est incorrect");
                    }
                }
                return true;
            default:
                return false;
        }
    }

}
