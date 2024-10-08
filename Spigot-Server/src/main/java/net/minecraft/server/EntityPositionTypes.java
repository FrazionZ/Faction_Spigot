package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.server.EntityBat;
import net.minecraft.server.EntityBlaze;
import net.minecraft.server.EntityCaveSpider;
import net.minecraft.server.EntityChicken;
import net.minecraft.server.EntityCow;
import net.minecraft.server.EntityCreeper;
import net.minecraft.server.EntityEnderDragon;
import net.minecraft.server.EntityEnderman;
import net.minecraft.server.EntityEndermite;
import net.minecraft.server.EntityGhast;
import net.minecraft.server.EntityGiantZombie;
import net.minecraft.server.EntityGuardian;
import net.minecraft.server.EntityHorse;
import net.minecraft.server.EntityHorseDonkey;
import net.minecraft.server.EntityHorseMule;
import net.minecraft.server.EntityHorseSkeleton;
import net.minecraft.server.EntityHorseZombie;
import net.minecraft.server.EntityInsentient;
import net.minecraft.server.EntityIronGolem;
import net.minecraft.server.EntityMagmaCube;
import net.minecraft.server.EntityMushroomCow;
import net.minecraft.server.EntityOcelot;
import net.minecraft.server.EntityParrot;
import net.minecraft.server.EntityPig;
import net.minecraft.server.EntityPigZombie;
import net.minecraft.server.EntityPositionTypes;
import net.minecraft.server.EntityRabbit;
import net.minecraft.server.EntitySheep;
import net.minecraft.server.EntitySilverfish;
import net.minecraft.server.EntitySkeleton;
import net.minecraft.server.EntitySkeletonStray;
import net.minecraft.server.EntitySkeletonWither;
import net.minecraft.server.EntitySlime;
import net.minecraft.server.EntitySnowman;
import net.minecraft.server.EntitySpider;
import net.minecraft.server.EntitySquid;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.EntityWitch;
import net.minecraft.server.EntityWither;
import net.minecraft.server.EntityWolf;
import net.minecraft.server.EntityZombie;
import net.minecraft.server.EntityZombieHusk;
import net.minecraft.server.EntityZombieVillager;

public class EntityPositionTypes
{
  private static final Map<Class<?>, EntityInsentient.EnumEntityPositionType> a = Maps.newHashMap();

  
  public static EntityInsentient.EnumEntityPositionType a(Class<?> paramClass) { return a.get(paramClass); }


  
  static  {
    a.put(EntityBat.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityChicken.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityCow.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityHorse.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityHorseSkeleton.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityHorseZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityHorseDonkey.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityHorseMule.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityMushroomCow.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityOcelot.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityPig.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityRabbit.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityParrot.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySheep.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySnowman.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySquid.class, EntityInsentient.EnumEntityPositionType.IN_WATER);
    a.put(EntityIronGolem.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityWolf.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    
    a.put(EntityVillager.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    
    a.put(EntityEnderDragon.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityWither.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    
    a.put(EntityBlaze.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityCaveSpider.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityCreeper.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityEnderman.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityEndermite.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityGhast.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityGiantZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityGuardian.class, EntityInsentient.EnumEntityPositionType.IN_WATER);
    a.put(EntityMagmaCube.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityPigZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySilverfish.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySkeleton.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySkeletonWither.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySkeletonStray.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySlime.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntitySpider.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityWitch.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityZombie.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityZombieVillager.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityZombieHusk.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityNetherSpider.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityIllager.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
    a.put(EntityHerobrine1.class, EntityInsentient.EnumEntityPositionType.ON_GROUND);
  }
}
