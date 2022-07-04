package net.minecraft.server;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

public class EntityHerobrine1 extends EntityIllagerWizard {

    private EntitySheep c;  
    private final BossBattleServer bossInfo = (BossBattleServer) (new BossBattleServer(this.getScoreboardDisplayName(), BossBattle.BarColor.YELLOW, BossBattle.BarStyle.PROGRESS)).setDarkenSky(true);

    public EntityHerobrine1(World world)
    {
        super(world);	
        this.setSize(0.6F, 1.95F);
        this.b_ = 10;
    }
    
    public static void a(DataConverterManager dataconvertermanager)
    {
        EntityInsentient.a(dataconvertermanager, EntityHerobrine1.class);
    }
    
    protected void r() 
    {
        super.r();
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new EntityHerobrine1.b());
        this.goalSelector.a(2, new PathfinderGoalAvoidTarget(this, EntityHuman.class, 8.0F, 0.6D, 0.6D));
        this.goalSelector.a(4, new EntityHerobrine1.AISummonSpell());
        this.goalSelector.a(5, new EntityHerobrine1.a());
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityInsentient.class, 8.0F));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true, new Class[] { EntityHerobrine1.class}));
        this.targetSelector.a(2, (new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true)).b(300));
        this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, false)).b(300));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, EntityIronGolem.class, false));
    }
    
    protected void initAttributes()
    {
        super.initAttributes();
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5D);
        this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(64.0D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(1000.0D);
        this.getAttributeInstance(GenericAttributes.h).setValue(10.0D);
    }  

    protected void i()
    {
        super.i();
    }
    
    public boolean damageEntity(DamageSource damagesource, float f)
    {
    	
    	int random = this.random.nextInt(4);
    	
        if (this.isInvulnerable(damagesource))
        {
            return false;
        }
        
        else {
        	
            boolean flag = super.damageEntity(damagesource, f);
            return flag;
        }
    }

    // TELEPORT //

    public void b(NBTTagCompound nbttagcompound) 
    {
        super.b(nbttagcompound);
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void a(NBTTagCompound nbttagcompound) 
    {
        super.a(nbttagcompound);
    }
    
    protected MinecraftKey J()
    {
        return LootTables.au;
    }
     
    protected void M() 
    {
        
        if (this.world.D() && this.ticksLived >= 600)
        {
            float f = this.aw();

            if (f > 0.5F && this.world.h(new BlockPosition(this)) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
            {
                this.setGoalTarget((EntityLiving) null);
            }
        } 	

        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
        
        super.M();
    }
    
    /**
     * Called to update the entity's position/logic.
     */
    public void B_()
    {
        super.B_();
    }
       
    public void b(EntityPlayer entityplayer)
    {
        super.b(entityplayer);
        this.bossInfo.addPlayer(entityplayer);
    }

    public void c(EntityPlayer entityplayer) 
    {
        super.c(entityplayer);
        this.bossInfo.removePlayer(entityplayer);
    }

    public void setCustomName(String s) {
        super.setCustomName(s);
        this.bossInfo.a(this.getScoreboardDisplayName());
    }


    public boolean r(Entity entity) {
        return entity == null ? false : (entity == this ? true : (super.r(entity) ? true : (entity instanceof EntityVex ? this.r(((EntityVex) entity).p()) : (entity instanceof EntityLiving && ((EntityLiving) entity).getMonsterType() == EnumMonsterType.ILLAGER ? this.aY() == null && entity.aY() == null : false))));
    }

    protected SoundEffect F() {
        return SoundEffects.bs;
    }

    protected SoundEffect cf() {
        return SoundEffects.bu;
    }

    protected SoundEffect d(DamageSource damagesource) {
        return SoundEffects.bv;
    }

    private void a(@Nullable EntitySheep entitysheep) {
        this.c = entitysheep;
    }

    @Nullable
    private EntitySheep dq() {
        return this.c;
    }

    protected SoundEffect dm() {
        return SoundEffects.bt;
    }
    
    
    public boolean dn() {
        return this.getHealth() <= this.getMaxHealth() / 2.0F;
    }


    class a extends EntityIllagerWizard.c {

        private a() {
            super();
        }

        protected int f() {
            return 20;
        }

        protected int i() {
            return 50;
        }

        protected void j() {
            EntityLiving entityliving = EntityHerobrine1.this.getGoalTarget();
            double d0 = Math.min(entityliving.locY, EntityHerobrine1.this.locY);
            double d1 = Math.max(entityliving.locY, EntityHerobrine1.this.locY) + 1.0D;
            float f = (float) MathHelper.c(entityliving.locZ - EntityHerobrine1.this.locZ, entityliving.locX - EntityHerobrine1.this.locX);
            int i;

            if (EntityHerobrine1.this.h(entityliving) < 9.0D) {
                float f1;

                for (i = 0; i < 5; ++i) {
                    f1 = f + (float) i * 3.1415927F * 0.4F;
                    this.a(EntityHerobrine1.this.locX + (double) MathHelper.cos(f1) * 1.5D, EntityHerobrine1.this.locZ + (double) MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for (i = 0; i < 8; ++i) {
                    f1 = f + (float) i * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
                    this.a(EntityHerobrine1.this.locX + (double) MathHelper.cos(f1) * 2.5D, EntityHerobrine1.this.locZ + (double) MathHelper.sin(f1) * 2.5D, d0, d1, f1, 3);
                }
            } else {
                for (i = 0; i < 16; ++i) {
                    double d2 = 1.25D * (double) (i + 1);
                    int j = 1 * i;

                    this.a(EntityHerobrine1.this.locX + (double) MathHelper.cos(f) * d2, EntityHerobrine1.this.locZ + (double) MathHelper.sin(f) * d2, d0, d1, f, j);
                }
            }

        }

        private void a(double d0, double d1, double d2, double d3, float f, int i) {
            BlockPosition blockposition = new BlockPosition(d0, d3, d1);
            boolean flag = false;
            double d4 = 0.0D;

            do {
                if (!EntityHerobrine1.this.world.d(blockposition, true) && EntityHerobrine1.this.world.d(blockposition.down(), true)) {
                    if (!EntityHerobrine1.this.world.isEmpty(blockposition)) {
                        IBlockData iblockdata = EntityHerobrine1.this.world.getType(blockposition);
                        AxisAlignedBB axisalignedbb = iblockdata.d(EntityHerobrine1.this.world, blockposition);

                        if (axisalignedbb != null) {
                            d4 = axisalignedbb.e;
                        }
                    }

                    flag = true;
                    break;
                }

                blockposition = blockposition.down();
            } while (blockposition.getY() >= MathHelper.floor(d2) - 1);

            if (flag) {
                EntityEvokerFangs entityevokerfangs1 = new EntityEvokerFangs(EntityHerobrine1.this.world, d0, (double) blockposition.getY() + d4, d1, f, i, EntityHerobrine1.this);
                EntityEvokerFangs entityevokerfangs2 = new EntityEvokerFangs(EntityHerobrine1.this.world, d0 - 1, (double) blockposition.getY() + d4, d1 - 1, f, i, EntityHerobrine1.this);
                EntityEvokerFangs entityevokerfangs3 = new EntityEvokerFangs(EntityHerobrine1.this.world, d0 + 1, (double) blockposition.getY() + d4, d1 - 1, f, i, EntityHerobrine1.this);
                EntityEvokerFangs entityevokerfangs4 = new EntityEvokerFangs(EntityHerobrine1.this.world, d0 - 2, (double) blockposition.getY() + d4, d1 - 2, f, i, EntityHerobrine1.this);
                EntityEvokerFangs entityevokerfangs5 = new EntityEvokerFangs(EntityHerobrine1.this.world, d0 + 2, (double) blockposition.getY() + d4, d1 - 2, f, i, EntityHerobrine1.this);

                EntityHerobrine1.this.world.addEntity(entityevokerfangs1);
                EntityHerobrine1.this.world.addEntity(entityevokerfangs2);
                EntityHerobrine1.this.world.addEntity(entityevokerfangs3);
                EntityHerobrine1.this.world.addEntity(entityevokerfangs4);
                EntityHerobrine1.this.world.addEntity(entityevokerfangs5);
            }

        }

        protected SoundEffect k() {
            return SoundEffects.bw;
        }

        protected EntityIllagerWizard.Spell l() {
            return EntityIllagerWizard.Spell.FANGS;
        }

        a(Object object) {
            this();
        }
    }

    class AICastingSpell extends EntityIllagerWizard.b {

        private AICastingSpell() {
            super();
        }

        public void e() {
            if (EntityHerobrine1.this.getGoalTarget() != null)
            {
                EntityHerobrine1.this.getControllerLook().a(EntityHerobrine1.this.getGoalTarget(), (float) EntityHerobrine1.this.O(), (float) EntityHerobrine1.this.N());
            }
            else if (EntityHerobrine1.this.dq() != null)
            {
                EntityHerobrine1.this.getControllerLook().a(EntityHerobrine1.this.dq(), (float) EntityHerobrine1.this.O(), (float) EntityHerobrine1.this.N());
            }

        }
    }
    
    
    class AISummonSpell extends EntityIllagerWizard.c {

        private AISummonSpell()
        {
        }

        public boolean a()
        {
            if (!super.a()) 
            {
                return false;
            }
            else
            {
                int i = EntityHerobrine1.this.world.a(EntityVindicator.class, EntityHerobrine1.this.getBoundingBox().g(16.0D)).size();
                return EntityHerobrine1.this.random.nextInt(8) + 1 > i;
            }
        }

        protected int f()
        {
            return 50;
        }

        protected int i()
        {
            return 50;
        }

        protected void j()
        {
            for (int i = 0; i < 3; ++i)
            {
                BlockPosition blockposition = (new BlockPosition(EntityHerobrine1.this)).a(-2 + EntityHerobrine1.this.random.nextInt(5), 1, -2 + EntityHerobrine1.this.random.nextInt(5));
                EntityVindicator entity = new EntityVindicator(EntityHerobrine1.this.world);

                entity.setPositionRotation(blockposition, 0.0F, 0.0F);
                entity.prepare(EntityHerobrine1.this.world.D(blockposition), (GroupDataEntity) null);
                EntityHerobrine1.this.world.addEntity(entity);
            }

        }

        protected SoundEffect k()
        {
            return SoundEffects.bx;
        }

        protected EntityIllagerWizard.Spell l()
        {
            return EntityIllagerWizard.Spell.SUMMON_VEX;
        }
    }
    
    
    public class AIWololoSpell extends EntityIllagerWizard.c {

        final Predicate<EntitySheep> a = new Predicate() {
            public boolean a(EntitySheep entitysheep) {
                return entitysheep.getColor() == EnumColor.BLUE;
            }

            public boolean apply(Object object) {
                return this.a((EntitySheep) object);
            }
        };

        public AIWololoSpell() {
            super();
        }

        public boolean a() {
            if (EntityHerobrine1.this.getGoalTarget() != null)
            {
                return false;
            } else if (EntityHerobrine1.this.dn())
            {
                return false;
            } else if (EntityHerobrine1.this.ticksLived < this.d)
            {
                return false;
            }
            else if (!EntityHerobrine1.this.world.getGameRules().getBoolean("mobGriefing"))
            {
                return false;
            } 
            else
            {
                List list = EntityHerobrine1.this.world.a(EntitySheep.class, EntityHerobrine1.this.getBoundingBox().grow(16.0D, 4.0D, 16.0D), this.a);

                if (list.isEmpty()) 
                {
                    return false;
                }
                else
                {
                    EntityHerobrine1.this.a((EntitySheep) list.get(EntityHerobrine1.this.random.nextInt(list.size())));
                    return true;
                }
            }
        }

        public boolean b() {
            return EntityHerobrine1.this.dq() != null && this.c > 0;
        }

        public void d() {
            super.d();
            EntityHerobrine1.this.a((EntitySheep) null);
        }

        protected void j() {
            EntitySheep entitysheep = EntityHerobrine1.this.dq();

            if (entitysheep != null && entitysheep.isAlive()) {
                entitysheep.setColor(EnumColor.RED);
            }

        }

        protected int m() {
            return 40;
        }

        protected int f() {
            return 60;
        }

        protected int i() {
            return 140;
        }

        protected SoundEffect k() {
            return SoundEffects.by;
        }

        protected EntityIllagerWizard.Spell l() {
            return EntityIllagerWizard.Spell.WOLOLO;
        }
    }
}
