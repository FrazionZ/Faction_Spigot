package net.minecraft.server.frazionz.players.stats;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemArmor;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;

public class StatHelper {

    public static ItemStack applyStats(ItemStack itemStack, EnumStats stat, int value) {
        if (itemStack.hasTag()) {
            if (itemStack.getTag().hasKey("stats")) {
                itemStack.getTag().getCompound("stats").setInt(stat.name(), value);
            } else {
                itemStack.getTag().set("stats", new NBTTagCompound());
                itemStack.getTag().getCompound("stats").setInt(stat.name(), value);
            }
        } else {
            itemStack.setTag(new NBTTagCompound());
            itemStack.getTag().set("stats", new NBTTagCompound());
            itemStack.getTag().getCompound("stats").setInt(stat.name(), value);
        }
        return itemStack;
    }

    public static int getStatValue(ItemStack itemStack, EnumStats stat) {
        if(hasStat(itemStack, stat)) {
            return itemStack.getTag().getCompound("stats").getInt(stat.name());
        }
        return 0;
    }

    public static boolean hasStat(ItemStack itemStack, EnumStats stat) {
        return hasStats(itemStack) && itemStack.getTag().getCompound("stats").hasKey(stat.name());
    }

    public static boolean hasStats(ItemStack itemStack) {
        return itemStack.hasTag() && itemStack.getTag().hasKey("stats");
    }

    public static boolean playerWearFullArmorSet(EntityPlayer player, ItemArmor.EnumArmorMaterial armor) {
        return player.isWearingFullArmorSet(armor);
    }
    
}
