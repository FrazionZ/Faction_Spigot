package net.minecraft.server.frazionz.items;

import org.bukkit.event.entity.SheepDyeWoolEvent; // CraftBukkit

import net.minecraft.server.BlockPosition;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumInteractionResult;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockFragilePlantElement;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class ItemWitheredBoneMeal extends Item {
    public ItemWitheredBoneMeal() {
        this.setMaxDurability(0);
        this.b(CreativeModeTab.l);
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (a(itemstack, world, blockposition)) {
            if (!world.isClientSide) {
                world.triggerEffect(2005, blockposition, 0);
            }

            return EnumInteractionResult.SUCCESS;
        }

        return EnumInteractionResult.PASS;
    }

    public static boolean a(ItemStack itemstack, World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() instanceof IBlockFragilePlantElement) {
            IBlockFragilePlantElement iblockfragileplantelement = (IBlockFragilePlantElement) iblockdata.getBlock();

            if (iblockfragileplantelement.a(world, blockposition, iblockdata, world.isClientSide)) {
                if (!world.isClientSide) {
                    if (iblockfragileplantelement.a(world, world.random, blockposition, iblockdata)) {
                        iblockfragileplantelement.b(world, world.random, blockposition, iblockdata, true);
                    }

                    itemstack.subtract(1);
                }

                return true;
            }
        }

        return false;
    }
}
