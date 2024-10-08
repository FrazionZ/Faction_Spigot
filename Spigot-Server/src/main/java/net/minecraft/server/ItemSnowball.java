package net.minecraft.server;

public class ItemSnowball extends Item {

    public ItemSnowball() {
        this.maxStackSize = 16;
        this.b(CreativeModeTab.f);
    }

    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);

        // CraftBukkit start - moved down
        /*
        if (!entityhuman.abilities.canInstantlyBuild) {
            itemstack.subtract(1);
        }

        world.a((EntityHuman) null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.hp, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemSnowball.j.nextFloat() * 0.4F + 0.8F));
        */
        if (!world.isClientSide) {
            EntitySnowball entitysnowball = new EntitySnowball(world, entityhuman);

            // PVP_UPDATE
            //entitysnowball.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);
            if (world.addEntity(entitysnowball)) {
                if (!entityhuman.abilities.canInstantlyBuild) {
                    itemstack.subtract(1);
                }

                world.a((EntityHuman) null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.hp, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemSnowball.j.nextFloat() * 0.4F + 0.8F));
            } else if (entityhuman instanceof EntityPlayer) {
                ((EntityPlayer) entityhuman).getBukkitEntity().updateInventory();
            }
        }
        // CraftBukkit end

        entityhuman.b(StatisticList.b((Item) this));
        return new InteractionResultWrapper(EnumInteractionResult.SUCCESS, itemstack);
    }
}
