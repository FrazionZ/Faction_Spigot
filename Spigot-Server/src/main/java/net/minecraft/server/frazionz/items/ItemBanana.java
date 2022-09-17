package net.minecraft.server.frazionz.items;

import net.minecraft.server.*;

public class ItemBanana extends ItemFood {

    public ItemBanana(int i, float f, boolean flag) {
        super(i, f, flag);
        this.a(true);
        this.d(8);
    }

    public EnumItemRarity g(ItemStack itemstack) {
        return itemstack.getData() == 0 ? EnumItemRarity.RARE : EnumItemRarity.EPIC;
    }

    protected void a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (!world.isClientSide) {
                entityhuman.addEffect(new MobEffect(MobEffects.FASTER_MOVEMENT, 6000, 1));
        }

    }
}
