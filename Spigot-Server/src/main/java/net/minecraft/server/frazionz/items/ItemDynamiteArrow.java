package net.minecraft.server.frazionz.items;

import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityDynamiteArrow;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.ItemArrow;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class ItemDynamiteArrow extends ItemArrow {

    public ItemDynamiteArrow() {}

    public EntityArrow a(World world, ItemStack itemstack, EntityLiving entityliving) {
        return new EntityDynamiteArrow(world, entityliving);
    }
}
