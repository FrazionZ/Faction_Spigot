package net.minecraft.server.frazionz.items;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumItemRarity;
import net.minecraft.server.ItemFood;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MobEffect;
import net.minecraft.server.MobEffects;
import net.minecraft.server.World;

public class ItemBauxiteApple extends ItemFood {

    public ItemBauxiteApple(int i, float f, boolean flag) {
        super(i, f, flag);
        this.a(true);
        this.d(8);
    }

    public EnumItemRarity g(ItemStack itemstack) {
        return itemstack.getData() == 0 ? EnumItemRarity.RARE : EnumItemRarity.EPIC;
    }

    protected void a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!world.isClientSide) {   
                entityhuman.addEffect(new MobEffect(MobEffects.RESISTANCE, 5000, 0));
                entityhuman.addEffect(new MobEffect(MobEffects.FIRE_RESISTANCE, 5000, 0));
                entityhuman.addEffect(new MobEffect(MobEffects.REGENERATION, 600, 1));
                entityhuman.addEffect(new MobEffect(MobEffects.HEALTH_BOOST, 24000, 1));
                entityhuman.addEffect(new MobEffect(MobEffects.FASTER_MOVEMENT, 5000, 0));
        }

    }
}
