package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class EntityNetherSpider extends EntityMonster {

    private static final DataWatcherObject<Byte> a = DataWatcher.a(EntityNetherSpider.class, DataWatcherRegistry.a);

    public EntityNetherSpider(World world) {
        super(world);
        this.setSize(1.8F, 0.9F);
    }

    public static void c(DataConverterManager dataconvertermanager) {
        EntityInsentient.a(dataconvertermanager, EntityNetherSpider.class);
    }

    protected void r() {
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
        this.goalSelector.a(4, new EntityNetherSpider.PathfinderGoalSpiderMeleeAttack(this));
        this.goalSelector.a(5, new PathfinderGoalRandomStrollLand(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        this.targetSelector.a(3, new EntityNetherSpider.PathfinderGoalSpiderNearestAttackableTarget(this, EntityIronGolem.class));
    }

    public double aG() {
        return (double) (this.length * 0.5F);
    }

    protected NavigationAbstract b(World world) {
        return new NavigationSpider(this, world);
    }

    protected void i() {
        super.i();
        this.datawatcher.register(EntityNetherSpider.a, Byte.valueOf((byte) 0));
    }

    public void B_() {
        super.B_();
        if (!this.world.isClientSide) {
            this.a(this.positionChanged);
        }

    }

    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(15.0D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(40.0D);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.30000001192092896D);
    }

    protected SoundEffect F() {
        return SoundEffects.hz;
    }

    protected SoundEffect d(DamageSource damagesource) {
        return SoundEffects.hB;
    }

    protected SoundEffect cf() {
        return SoundEffects.hA;
    }

    protected void a(BlockPosition blockposition, Block block) {
        this.a(SoundEffects.hC, 0.15F, 1.0F);
    }

    @Nullable
    protected MinecraftKey J() {
        return LootTables.ENTITIES_NETHER_SPIDER;
    }

    public boolean m_() {
        return this.p();
    }

    public void ba() {}

    public EnumMonsterType getMonsterType() {
        return EnumMonsterType.ARTHROPOD;
    }

    public boolean d(MobEffect mobeffect) {
        return mobeffect.getMobEffect() == MobEffects.POISON ? false : super.d(mobeffect);
    }

    public boolean p() {
        return (((Byte) this.datawatcher.get(EntityNetherSpider.a)).byteValue() & 1) != 0;
    }

    public void a(boolean flag) {
        byte b0 = ((Byte) this.datawatcher.get(EntityNetherSpider.a)).byteValue();

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.datawatcher.set(EntityNetherSpider.a, Byte.valueOf(b0));
    }

    @Nullable
    public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
        Object object = super.prepare(difficultydamagescaler, groupdataentity);

        if (this.world.random.nextInt(100) == 0) {
            EntitySkeleton entityskeleton = new EntitySkeleton(this.world);

            entityskeleton.setPositionRotation(this.locX, this.locY, this.locZ, this.yaw, 0.0F);
            entityskeleton.prepare(difficultydamagescaler, (GroupDataEntity) null);
            this.world.addEntity(entityskeleton, org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.JOCKEY); // CraftBukkit - add SpawnReason
            entityskeleton.startRiding(this);
        }

        if (object == null) {
            object = new EntityNetherSpider.GroupDataSpider();
            if (this.world.getDifficulty() == EnumDifficulty.HARD && this.world.random.nextFloat() < 0.1F * difficultydamagescaler.d()) {
                ((EntityNetherSpider.GroupDataSpider) object).a(this.world.random);
            }
        }

        if (object instanceof EntityNetherSpider.GroupDataSpider) {
            MobEffectList mobeffectlist = ((EntityNetherSpider.GroupDataSpider) object).a;

            if (mobeffectlist != null) {
                this.addEffect(new MobEffect(mobeffectlist, Integer.MAX_VALUE));
            }
        }

        return (GroupDataEntity) object;
    }

    public float getHeadHeight() {
        return 0.65F;
    }

    static class PathfinderGoalSpiderNearestAttackableTarget<T extends EntityLiving> extends PathfinderGoalNearestAttackableTarget<T> {

        public PathfinderGoalSpiderNearestAttackableTarget(EntityNetherSpider entityspider, Class<T> oclass) {
            super(entityspider, oclass, true);
        }

        public boolean a() {
            float f = this.e.aw();

            return f >= 0.5F ? false : super.a();
        }
    }

    static class PathfinderGoalSpiderMeleeAttack extends PathfinderGoalMeleeAttack {

        public PathfinderGoalSpiderMeleeAttack(EntityNetherSpider entityspider) {
            super(entityspider, 2.0D, true);
        }

        public boolean b() {
            float f = this.b.aw();

            if (f >= 0.5F && this.b.getRandom().nextInt(100) == 0) {
                this.b.setGoalTarget((EntityLiving) null);
                return false;
            } else {
                return super.b();
            }
        }

        protected double a(EntityLiving entityliving) {
            return (double) (4.0F + entityliving.width);
        }
    }

    public static class GroupDataSpider implements GroupDataEntity {

        public MobEffectList a;

        public GroupDataSpider() {}

        public void a(Random random) {
            int i = random.nextInt(5);

            if (i <= 1) {
                this.a = MobEffects.FASTER_MOVEMENT;
            } else if (i <= 2) {
                this.a = MobEffects.INCREASE_DAMAGE;
            } else if (i <= 3) {
                this.a = MobEffects.REGENERATION;
            }

        }
    }
}
