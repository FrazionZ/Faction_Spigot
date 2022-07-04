package net.minecraft.server;

public class EntityDynamiteArrow extends EntityArrow {


    public EntityDynamiteArrow(World world) {
        super(world);
    }

    public EntityDynamiteArrow(World world, EntityLiving entityliving) {
        super(world, entityliving);
    }

    public EntityDynamiteArrow(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public void B_() {
        super.B_();
        if (this.world.isClientSide && !this.inGround) {
            this.world.addParticle(EnumParticle.SPELL_INSTANT, this.locX, this.locY, this.locZ, 0.0D, 0.0D, 0.0D, new int[0]);
        }

    }

    protected ItemStack j() {
        return new ItemStack(Items.dynamite_arrow);
    }

    public static void c(DataConverterManager dataconvertermanager) {
        EntityArrow.a(dataconvertermanager, "dynamite_arrow");
    }
}
