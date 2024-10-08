package net.minecraft.server;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.frazionz.packets.server.PacketPlayOutToast;

import java.util.UUID;

public class FrazionZUtils {

    public static String pluginPrefix = "§6[ §eFrazionZ §6] §e";

    public static void sendToast(EntityPlayer p, PacketPlayOutToast.EnumToastAction typeToast, PacketPlayOutToast.EnumToastIcon icon, String title, String subtitle){
        p.playerConnection.sendPacket(new PacketPlayOutToast(typeToast, icon, new ChatComponentText(title), new ChatComponentText(subtitle)));
    }

    public static void sendUpdateSkin(UUID fromPlayer){
        for(Player p : Bukkit.getOnlinePlayers())
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutUpdateSkin(fromPlayer));
    }

}
