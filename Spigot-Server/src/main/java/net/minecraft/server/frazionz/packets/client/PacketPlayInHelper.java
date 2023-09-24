package net.minecraft.server.frazionz.packets.client;

import com.azuriom.azauth.AuthenticationException;
import com.azuriom.azauth.AzAuthenticator;
import com.azuriom.azauth.model.User;
import fz.frazionz.enums.EnumGui;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.FrazionZUtils;
import net.minecraft.server.PacketPlayInCustomPayload;
import net.minecraft.server.SharedConstants;
import net.minecraft.server.frazionz.packets.server.PacketPlayOutGuiOpener;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.util.FzUtils;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PacketPlayInHelper {
    public static String fzAuthServer = "https://auth.frazionz.net";
    public static boolean handlePacketPlayInCustomPayload(Player player, PacketPlayInCustomPayload packet, String channel) {

        switch (channel) {
            default:
                return false;
        }
    }

}
