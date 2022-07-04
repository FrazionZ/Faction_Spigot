package net.minecraft.server.frazionz.items;

import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityDynamite;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumInteractionResult;
import net.minecraft.server.InteractionResultWrapper;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.SoundCategory;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.StatisticList;
import net.minecraft.server.World;

public class ItemDynamite extends Item {

    public ItemDynamite() {
        this.b(CreativeModeTab.f);
    }

    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (!entityhuman.abilities.canInstantlyBuild) {
            itemstack.subtract(1);
        }

        world.a((EntityHuman) null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.bz, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemDynamite.j.nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            EntityDynamite entitybigxp = new EntityDynamite(world, entityhuman);

            //entitybigxp.a(entityhuman, entityhuman.pitch, entityhuman.yaw, -20.0F, 0.5F, 1.0F);
            world.addEntity(entitybigxp);
        }

        entityhuman.b(StatisticList.b((Item) this));
        return new InteractionResultWrapper(EnumInteractionResult.SUCCESS, itemstack);
    }
}
