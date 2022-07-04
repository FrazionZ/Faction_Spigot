package net.minecraft.server;

public class EntityBigXp extends EntityProjectile {

    public EntityBigXp(World world) {
        super(world);
    }

    public EntityBigXp(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    public EntityBigXp(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public static void a(DataConverterManager dataconvertermanager) {
        EntityProjectile.a(dataconvertermanager, "ThrowableBigXp");
    }

    protected float j() {
        return 0.07F;
    }
    
    // PVP_UPDATE
    protected float getVelocity()
    {
        return 0.7F;
    }
 // PVP_UPDATE
    protected float getInaccuracy()
    {
        return -20.0F;
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isClientSide) {
            // CraftBukkit - moved to after event
            // this.world.triggerEffect(2002, new BlockPosition(this), PotionUtil.a(Potions.b));
            int i = 30 + this.world.random.nextInt(25) + this.world.random.nextInt(25);

            // CraftBukkit start
            org.bukkit.event.entity.BigXpEvent event = org.bukkit.craftbukkit.event.CraftEventFactory.callBigXpEvent(this, i);
            i = event.getExperience();
            if (event.getShowEffect()) {
                this.world.triggerEffect(2002, new BlockPosition(this), PotionUtil.a(Potions.b));
            }
            // CraftBukkit end

            while (i > 0) {
                int j = EntityExperienceOrb.getOrbValue(i);

                i -= j;
                this.world.addEntity(new EntityExperienceOrb(this.world, this.locX, this.locY, this.locZ, j));
            }

            this.die();
        }

    }
}
