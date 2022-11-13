package net.minecraft.server;

import fz.frazionz.block.ExplosiveType;

public class EntityDynamite extends EntityProjectile {

    public EntityDynamite(World world) {
        super(world);
    }

    public EntityDynamite(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    public EntityDynamite(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public static void a(DataConverterManager dataconvertermanager) {
        EntityProjectile.a(dataconvertermanager, "ThrowableDynamite");
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
        	
        	
        	
            this.die();
            
            float f = 2.0F;
            this.world.createExplosion(this, this.locX, this.locY + (double)(this.length / 16.0F), this.locZ, f, true, ExplosiveType.DYNAMITE);
        }

    }
}
