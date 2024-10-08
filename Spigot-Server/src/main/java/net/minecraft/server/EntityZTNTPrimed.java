package net.minecraft.server;

import javax.annotation.Nullable;

import fz.frazionz.block.ExplosiveType;
import org.bukkit.event.entity.ExplosionPrimeEvent; // CraftBukkit
import org.bukkit.frazionz.enums.ExplosionBlockType;

public class EntityZTNTPrimed extends Entity {

    private static final DataWatcherObject<Integer> FUSE_TICKS = DataWatcher.a(EntityZTNTPrimed.class, DataWatcherRegistry.b);
    @Nullable
    private EntityLiving source;
    private int c;
    public float yield = 4; // CraftBukkit - add field
    public boolean isIncendiary = false; // CraftBukkit - add field

    public EntityZTNTPrimed(World world) {
        super(world);
        this.c = 80;
        this.i = true;
        this.fireProof = true;
        this.setSize(0.98F, 0.98F);
    }

    public EntityZTNTPrimed(World world, double d0, double d1, double d2, EntityLiving entityliving) {
        this(world);
        this.setPosition(d0, d1, d2);
        float f = (float) (Math.random() * 6.2831854820251465D);

        this.motX = (double) (-((float) Math.sin((double) f)) * 0.02F);
        this.motY = 0.20000000298023224D;
        this.motZ = (double) (-((float) Math.cos((double) f)) * 0.02F);
        this.setFuseTicks(80);
        this.lastX = d0;
        this.lastY = d1;
        this.lastZ = d2;
        this.source = entityliving;
    }

    protected void i() {
        this.datawatcher.register(EntityZTNTPrimed.FUSE_TICKS, Integer.valueOf(80));
    }

    protected boolean playStepSound() {
        return false;
    }

    public boolean isInteractable() {
        return !this.dead;
    }

    public void B_() {
        if (world.spigotConfig.currentPrimedTnt++ > world.spigotConfig.maxTntTicksPerTick) { return; } // Spigot
        this.lastX = this.locX;
        this.lastY = this.locY;
        this.lastZ = this.locZ;
        if (!this.isNoGravity()) {
            this.motY -= 0.03999999910593033D;
        }

        this.move(EnumMoveType.SELF, this.motX, this.motY, this.motZ);
        this.motX *= 0.9800000190734863D;
        this.motY *= 0.9800000190734863D;
        this.motZ *= 0.9800000190734863D;
        if (this.onGround) {
            this.motX *= 0.699999988079071D;
            this.motZ *= 0.699999988079071D;
            this.motY *= -0.5D;
        }

        --this.c;
        if (this.c <= 0) {
            // CraftBukkit start - Need to reverse the order of the explosion and the entity death so we have a location for the event
            // this.die();
            if (!this.world.isClientSide) {
                this.explode();
            }
            this.die();
            // CraftBukkit end
        } else {
            this.aq();
            this.world.addParticle(EnumParticle.SMOKE_NORMAL, this.locX, this.locY + 0.5D, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }

    }

    private void explode() {
        // CraftBukkit start
        // float f = 4.0F;

        org.bukkit.craftbukkit.CraftServer server = this.world.getServer();
        ExplosionPrimeEvent event = new ExplosionPrimeEvent((org.bukkit.entity.Explosive) org.bukkit.craftbukkit.entity.CraftEntity.getEntity(server, this));
        server.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
        	float f = 4.0F;
            this.world.createExplosion(this, this.locX, this.locY + (double) (this.length / 16.0F), this.locZ, f, event.getFire(), true, ExplosiveType.Z_TNT);
        }
        // CraftBukkit end
    }

    protected void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.setShort("Fuse", (short) this.getFuseTicks());
    }

    protected void a(NBTTagCompound nbttagcompound) {
        this.setFuseTicks(nbttagcompound.getShort("Fuse"));
    }

    @Nullable
    public EntityLiving getSource() {
        return this.source;
    }

    public float getHeadHeight() {
        return 0.0F;
    }

    public void setFuseTicks(int i) {
        this.datawatcher.set(EntityZTNTPrimed.FUSE_TICKS, Integer.valueOf(i));
        this.c = i;
    }

    public void a(DataWatcherObject<?> datawatcherobject) {
        if (EntityZTNTPrimed.FUSE_TICKS.equals(datawatcherobject)) {
            this.c = this.k();
        }

    }

    public int k() {
        return ((Integer) this.datawatcher.get(EntityZTNTPrimed.FUSE_TICKS)).intValue();
    }

    public int getFuseTicks() {
        return this.c;
    }
}
