package net.minecraft.server;

import fz.frazionz.block.ExplosiveType;
import org.bukkit.event.entity.ExplosionPrimeEvent; // CraftBukkit

public class EntityWitherSkull extends EntityFireball {

    private static final DataWatcherObject<Boolean> e = DataWatcher.a(EntityWitherSkull.class, DataWatcherRegistry.h);

    public EntityWitherSkull(World world) {
        super(world);
        this.setSize(0.3125F, 0.3125F);
    }

    public EntityWitherSkull(World world, EntityLiving entityliving, double d0, double d1, double d2) {
        super(world, entityliving, d0, d1, d2);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void a(DataConverterManager dataconvertermanager) {
        EntityFireball.a(dataconvertermanager, "WitherSkull");
    }

    protected float l() {
        return this.isCharged() ? 0.73F : super.l();
    }

    public boolean isBurning() {
        return false;
    }

    public float a(Explosion explosion, World world, BlockPosition blockposition, IBlockData iblockdata) {
        float f = super.a(explosion, world, blockposition, iblockdata);
        Block block = iblockdata.getBlock();

        if (this.isCharged() && EntityWither.a(block)) {
            f = Math.min(0.8F, f);
        }

        return f;
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.world.isClientSide) {
            if (movingobjectposition.entity != null) {
                // Spigot start
                boolean didDamage = false;
                if (this.shooter != null) {
                    didDamage = movingobjectposition.entity.damageEntity(DamageSource.projectile(this, shooter), 8.0F);
                    if (didDamage) { // CraftBukkit
                        if (movingobjectposition.entity.isAlive()) {
                            this.a(this.shooter, movingobjectposition.entity);
                        } else {
                            this.shooter.heal(5.0F, org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason.WITHER); // CraftBukkit
                        }
                    }
                } else {
                    didDamage = movingobjectposition.entity.damageEntity(DamageSource.MAGIC, 5.0F);
                }

                if (didDamage && movingobjectposition.entity instanceof EntityLiving) {
                // Spigot end
                    byte b0 = 0;

                    if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
                        b0 = 10;
                    } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
                        b0 = 40;
                    }

                    if (b0 > 0) {
                        ((EntityLiving) movingobjectposition.entity).addEffect(new MobEffect(MobEffects.WITHER, 20 * b0, 1));
                    }
                }
            }

            // CraftBukkit start
            // this.world.createExplosion(this, this.locX, this.locY, this.locZ, 1.0F, false, this.world.getGameRules().getBoolean("mobGriefing"));
            ExplosionPrimeEvent event = new ExplosionPrimeEvent(this.getBukkitEntity(), 1.0F, false);
            this.world.getServer().getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                this.world.createExplosion(this, this.locX, this.locY, this.locZ, event.getRadius(), event.getFire(), this.world.getGameRules().getBoolean("mobGriefing"), ExplosiveType.WITHER);
            }
            // CraftBukkit end
            this.die();
        }

    }

    public boolean isInteractable() {
        return false;
    }

    public boolean damageEntity(DamageSource damagesource, float f) {
        return false;
    }

    protected void i() {
        this.datawatcher.register(EntityWitherSkull.e, Boolean.valueOf(false));
    }

    public boolean isCharged() {
        return ((Boolean) this.datawatcher.get(EntityWitherSkull.e)).booleanValue();
    }

    public void setCharged(boolean flag) {
        this.datawatcher.set(EntityWitherSkull.e, Boolean.valueOf(flag));
    }

    protected boolean k() {
        return false;
    }
}
