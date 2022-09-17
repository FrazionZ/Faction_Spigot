package net.minecraft.server;

import net.minecraft.server.frazionz.tileentity.TileEntityItemCrusher;

public class DataConverterRegistry {
  private static void a(DataConverterManager paramDataConverterManager) {
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterEquipment());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.BLOCK_ENTITY, (IDataConverter)new DataConverterSignText());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterMaterialId());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterPotionId());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterSpawnEgg());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterMinecart());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.BLOCK_ENTITY, (IDataConverter)new DataConverterMobSpawner());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterUUID());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterHealth());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterSaddle());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterHanging());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterDropChances());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterRiding());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterArmorStand());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterBook());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterCookedFish());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterZombie());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.OPTIONS, (IDataConverter)new DataConverterVBO());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterGuardian());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterSkeleton());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterZombieType());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterHorse());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.BLOCK_ENTITY, (IDataConverter)new DataConverterTileEntity());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterEntity());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterBanner());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterPotionWater());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ENTITY, (IDataConverter)new DataConverterShulker());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterShulkerBoxItem());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.BLOCK_ENTITY, (IDataConverter)new DataConverterShulkerBoxBlock());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.OPTIONS, (IDataConverter)new DataConverterLang());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterTotem());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.CHUNK, (IDataConverter)new DataConverterBedBlock());
    paramDataConverterManager.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, (IDataConverter)new DataConverterBedItem());
  }
  
  public static DataConverterManager a() {
    DataConverterManager dataConverterManager = new DataConverterManager(1343);
    
    WorldData.a(dataConverterManager);
    EntityPlayer.a(dataConverterManager);
    EntityHuman.c(dataConverterManager);
    ChunkRegionLoader.a(dataConverterManager);
    ItemStack.a(dataConverterManager);
    DefinedStructure.a(dataConverterManager);

    
    Entity.b(dataConverterManager);
    EntityArmorStand.a(dataConverterManager);
    EntityArrow.a(dataConverterManager);
    EntityBat.a(dataConverterManager);
    EntityBlaze.a(dataConverterManager);
    EntityCaveSpider.a(dataConverterManager);
    EntityChicken.a(dataConverterManager);
    EntityCow.a(dataConverterManager);
    EntityCreeper.a(dataConverterManager);
    EntityHorseDonkey.a(dataConverterManager);
    EntityDragonFireball.a(dataConverterManager);
    EntityGuardianElder.a(dataConverterManager);
    EntityEnderDragon.a(dataConverterManager);
    EntityEnderman.a(dataConverterManager);
    EntityEndermite.a(dataConverterManager);
    EntityEvoker.a(dataConverterManager);
    EntityFallingBlock.a(dataConverterManager);
    EntityFireworks.a(dataConverterManager);
    EntityGhast.a(dataConverterManager);
    EntityGiantZombie.a(dataConverterManager);
    EntityGuardian.c(dataConverterManager);
    EntityHorse.a(dataConverterManager);
    EntityZombieHusk.a(dataConverterManager);
    EntityItem.a(dataConverterManager);
    EntityItemFrame.a(dataConverterManager);
    EntityLargeFireball.a(dataConverterManager);
    EntityMagmaCube.a(dataConverterManager);
    EntityMinecartChest.a(dataConverterManager);
    EntityMinecartCommandBlock.a(dataConverterManager);
    EntityMinecartFurnace.a(dataConverterManager);
    EntityMinecartHopper.a(dataConverterManager);
    EntityMinecartRideable.a(dataConverterManager);
    EntityMinecartMobSpawner.a(dataConverterManager);
    EntityMinecartTNT.a(dataConverterManager);
    EntityHorseMule.a(dataConverterManager);
    EntityMushroomCow.c(dataConverterManager);
    EntityOcelot.a(dataConverterManager);
    EntityPig.a(dataConverterManager);
    EntityPigZombie.a(dataConverterManager);
    EntityRabbit.a(dataConverterManager);
    EntitySheep.a(dataConverterManager);
    EntityShulker.a(dataConverterManager);
    EntitySilverfish.a(dataConverterManager);
    EntitySkeleton.a(dataConverterManager);
    EntityHorseSkeleton.a(dataConverterManager);
    EntitySlime.c(dataConverterManager);
    EntitySmallFireball.a(dataConverterManager);
    EntitySnowman.a(dataConverterManager);
    EntitySnowball.a(dataConverterManager);
    EntitySpectralArrow.c(dataConverterManager);
    EntitySpider.c(dataConverterManager);
    EntitySquid.a(dataConverterManager);
    EntitySkeletonStray.a(dataConverterManager);
    EntityEgg.a(dataConverterManager);
    EntityEnderPearl.a(dataConverterManager);
    EntityThrownExpBottle.a(dataConverterManager);
    EntityBigXp.a(dataConverterManager);
    EntityPotion.a(dataConverterManager);
    EntityTippedArrow.c(dataConverterManager);
    EntityVex.a(dataConverterManager);
    EntityVillager.a(dataConverterManager);
    EntityIronGolem.a(dataConverterManager);
    EntityVindicator.a(dataConverterManager);
    EntityWitch.a(dataConverterManager);
    EntityWither.a(dataConverterManager);
    EntitySkeletonWither.a(dataConverterManager);
    EntityWitherSkull.a(dataConverterManager);
    EntityWolf.a(dataConverterManager);
    EntityZombie.c(dataConverterManager);
    EntityHorseZombie.a(dataConverterManager);
    EntityZombieVillager.a(dataConverterManager);
    EntityNetherSpider.c(dataConverterManager);

    EntityDynamite.a(dataConverterManager);
    EntityDynamiteArrow.a(dataConverterManager);
    
    TileEntityPiston.a(dataConverterManager);
    TileEntityFlowerPot.a(dataConverterManager);
    TileEntityFurnace.a(dataConverterManager);
    TileEntityChest.a(dataConverterManager);
    TileEntityDispenser.a(dataConverterManager);
    TileEntityDropper.b(dataConverterManager);
    TileEntityBrewingStand.a(dataConverterManager);
    TileEntityHopper.a(dataConverterManager);
    BlockJukeBox.a(dataConverterManager);
    TileEntityMobSpawner.a(dataConverterManager);
    TileEntityShulkerBox.a(dataConverterManager);
    TileEntityAmeliorator.a(dataConverterManager);
    TileEntityTrophyForge.a(dataConverterManager);
    TileEntityItemCrusher.a(dataConverterManager);
    
    EntityIllager.a(dataConverterManager);
    EntityHerobrine1.a(dataConverterManager);
    
    a(dataConverterManager);
    
    return dataConverterManager;
  }
  
  public static NBTTagCompound a(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt, String paramString) {
    if (paramNBTTagCompound.hasKeyOfType(paramString, 10)) {
      paramNBTTagCompound.set(paramString, (NBTBase)paramDataConverter.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, paramNBTTagCompound.getCompound(paramString), paramInt));
    }
    
    return paramNBTTagCompound;
  }
  
  public static NBTTagCompound b(DataConverter paramDataConverter, NBTTagCompound paramNBTTagCompound, int paramInt, String paramString) {
    if (paramNBTTagCompound.hasKeyOfType(paramString, 9)) {
      NBTTagList nBTTagList = paramNBTTagCompound.getList(paramString, 10);
      for (byte b = 0; b < nBTTagList.size(); b++) {
        nBTTagList.a(b, (NBTBase)paramDataConverter.a((DataConverterType)DataConverterTypes.ITEM_INSTANCE, nBTTagList.get(b), paramInt));
      }
    } 
    
    return paramNBTTagCompound;
  }
}
