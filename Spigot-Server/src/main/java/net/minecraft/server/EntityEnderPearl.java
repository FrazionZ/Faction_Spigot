package net.minecraft.server;

import javax.annotation.Nullable;
// CraftBukkit start
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
// CraftBukkit end

public class EntityEnderPearl extends EntityProjectile {

    private EntityLiving e;

    public EntityEnderPearl(World world) {
        super(world);
    }

    public EntityEnderPearl(World world, EntityLiving entityliving) {
        super(world, entityliving);
        this.e = entityliving;
    }

    public static void a(DataConverterManager dataconvertermanager) {
        EntityProjectile.a(dataconvertermanager, "ThrownEnderpearl");
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        EntityLiving entityliving = this.getShooter();

        if (movingobjectposition.entity != null) {
            if (movingobjectposition.entity == this.e) {
                return;
            }

            movingobjectposition.entity.damageEntity(DamageSource.projectile(this, entityliving), 0.0F);
        }

        if (movingobjectposition.type == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
            BlockPosition blockposition = movingobjectposition.a();
            TileEntity tileentity = this.world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityEndGateway) {
                TileEntityEndGateway tileentityendgateway = (TileEntityEndGateway) tileentity;

                if (entityliving != null) {
                    if (entityliving instanceof EntityPlayer) {
                        CriterionTriggers.d.a((EntityPlayer) entityliving, this.world.getType(blockposition));
                    }

                    tileentityendgateway.a((Entity) entityliving);
                    this.die();
                    return;
                }

                tileentityendgateway.a((Entity) this);
                return;
            }
        }

        for (int i = 0; i < 32; ++i) {
            this.world.addParticle(EnumParticle.PORTAL, this.locX, this.locY + this.random.nextDouble() * 2.0D, this.locZ, this.random.nextGaussian(), 0.0D, this.random.nextGaussian(), new int[0]);
        }

        if (!this.world.isClientSide) {
            if (entityliving instanceof EntityPlayer) {
                EntityPlayer entityplayer = (EntityPlayer) entityliving;

                if (entityplayer.playerConnection.a().isConnected() && entityplayer.world == this.world && !entityplayer.isSleeping()) {
                    // CraftBukkit start - Fire PlayerTeleportEvent
                    org.bukkit.craftbukkit.entity.CraftPlayer player = entityplayer.getBukkitEntity();
                    org.bukkit.Location location = getBukkitEntity().getLocation();
                    location.setPitch(player.getLocation().getPitch());
                    location.setYaw(player.getLocation().getYaw());

                    double lastX = this.lastX;
                    double lastY = this.lastY;
                    double lastZ = this.lastZ;
                    
                    PlayerTeleportEvent teleEvent = new PlayerTeleportEvent(player, player.getLocation(), location, PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
                    Bukkit.getPluginManager().callEvent(teleEvent);

                    if (!teleEvent.isCancelled() && !entityplayer.playerConnection.isDisconnected()) {
                        if (this.random.nextFloat() < 0.05F && this.world.getGameRules().getBoolean("doMobSpawning")) {
                            EntityEndermite entityendermite = new EntityEndermite(this.world);

                            entityendermite.a(true);
                            entityendermite.setPositionRotation(entityliving.lastX, entityliving.lastY, entityliving.lastZ, entityliving.yaw, entityliving.pitch);
                            this.world.addEntity(entityendermite, CreatureSpawnEvent.SpawnReason.ENDER_PEARL);
                        }

                        if (entityliving.isPassenger()) {
                            entityliving.stopRiding();
                        }

                        //entityplayer.playerConnection.teleport(teleEvent.getTo());
                        Location loc = teleEvent.getTo();
                        loc.setX(lastX);
                        loc.setY(lastY);
                        loc.setZ(lastZ);
                        entityplayer.playerConnection.teleport(loc);
                        entityliving.fallDistance = 0.0F;
                        CraftEventFactory.entityDamage = this;
                        entityliving.damageEntity(DamageSource.FALL, 5.0F);
                        CraftEventFactory.entityDamage = null;
                    }
                    // CraftBukkit end
                }
            } else if (entityliving != null) {
                entityliving.enderTeleportTo(this.lastX, this.lastY, this.lastZ);
                entityliving.fallDistance = 0.0F;
            }

            this.die();
        }

    }

    public void B_() {
        EntityLiving entityliving = this.getShooter();

        if (entityliving != null && entityliving instanceof EntityHuman && !entityliving.isAlive()) {
            this.die();
        } else {
            super.B_();
        }

    }

    @Nullable
    public Entity b(int i) {
        if (this.shooter.dimension != i) {
            this.shooter = null;
        }

        return super.b(i);
    }
}
