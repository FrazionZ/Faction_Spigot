package net.minecraft.server;

import net.minecraft.server.DataConverterSpawnEgg;
import net.minecraft.server.IDataConverter;
import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagCompound;

public class DataConverterSpawnEgg
  implements IDataConverter {
  private static final String[] a = new String[256];
  
  static  {
    String[] arrayOfString = a;

    
    arrayOfString[1] = "Item";
    arrayOfString[2] = "XPOrb";
    
    arrayOfString[7] = "ThrownEgg";
    arrayOfString[8] = "LeashKnot";
    arrayOfString[9] = "Painting";
    arrayOfString[10] = "Arrow";
    arrayOfString[11] = "Snowball";
    arrayOfString[12] = "Fireball";
    arrayOfString[13] = "SmallFireball";
    arrayOfString[14] = "ThrownEnderpearl";
    arrayOfString[15] = "EyeOfEnderSignal";
    arrayOfString[16] = "ThrownPotion";
    arrayOfString[17] = "ThrownExpBottle";
    arrayOfString[18] = "ItemFrame";
    arrayOfString[19] = "WitherSkull";
    
    arrayOfString[20] = "PrimedTnt";
    arrayOfString[21] = "FallingSand";
    arrayOfString[22] = "FireworksRocketEntity";
    arrayOfString[23] = "TippedArrow";
    arrayOfString[24] = "SpectralArrow";
    arrayOfString[25] = "ShulkerBullet";
    arrayOfString[26] = "DragonFireball";
    
    arrayOfString[30] = "ArmorStand";
    
    arrayOfString[41] = "Boat";
    
    arrayOfString[42] = "MinecartRideable";
    arrayOfString[43] = "MinecartChest";
    arrayOfString[44] = "MinecartFurnace";
    arrayOfString[45] = "MinecartTNT";
    arrayOfString[46] = "MinecartHopper";
    arrayOfString[47] = "MinecartSpawner";
    arrayOfString[40] = "MinecartCommandBlock";
    
    arrayOfString[48] = "Mob";
    arrayOfString[49] = "Monster";
    
    arrayOfString[50] = "Creeper";
    arrayOfString[51] = "Skeleton";
    arrayOfString[52] = "Spider";
    arrayOfString[53] = "Giant";
    arrayOfString[54] = "Zombie";
    arrayOfString[55] = "Slime";
    arrayOfString[56] = "Ghast";
    arrayOfString[57] = "PigZombie";
    arrayOfString[58] = "Enderman";
    arrayOfString[59] = "CaveSpider";
    arrayOfString[60] = "Silverfish";
    arrayOfString[61] = "Blaze";
    arrayOfString[62] = "LavaSlime";
    arrayOfString[63] = "EnderDragon";
    arrayOfString[64] = "WitherBoss";
    arrayOfString[65] = "Bat";
    arrayOfString[66] = "Witch";
    arrayOfString[67] = "Endermite";
    arrayOfString[68] = "Guardian";
    arrayOfString[69] = "Shulker";
    
    arrayOfString[90] = "Pig";
    arrayOfString[91] = "Sheep";
    arrayOfString[92] = "Cow";
    arrayOfString[93] = "Chicken";
    arrayOfString[94] = "Squid";
    arrayOfString[95] = "Wolf";
    arrayOfString[96] = "MushroomCow";
    arrayOfString[97] = "SnowMan";
    arrayOfString[98] = "Ozelot";
    arrayOfString[99] = "VillagerGolem";
    arrayOfString[100] = "EntityHorse";
    arrayOfString[101] = "Rabbit";
    
    arrayOfString[120] = "Villager";
    
    arrayOfString[200] = "EnderCrystal";
    
    arrayOfString[211] = "nether_spider";
    arrayOfString[212] = "big_xp";
    arrayOfString[213] = "dynamite";
    arrayOfString[214] = "dynamite_arrow";
    arrayOfString[215] = "illager";
    arrayOfString[216] = "herobrine_1";
    
    arrayOfString[217] = "PrimedZTnt";
  }


  
  public int a() { return 105; }


  
  public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
    if ("minecraft:spawn_egg".equals(paramNBTTagCompound.getString("id"))) {
      NBTTagCompound nBTTagCompound1 = paramNBTTagCompound.getCompound("tag");
      NBTTagCompound nBTTagCompound2 = nBTTagCompound1.getCompound("EntityTag");
      short s = paramNBTTagCompound.getShort("Damage");
      
      if (!nBTTagCompound2.hasKeyOfType("id", 8)) {
        String str = a[s & 0xFF];
        if (str != null) {
          nBTTagCompound2.setString("id", str);
          nBTTagCompound1.set("EntityTag", (NBTBase)nBTTagCompound2);
          paramNBTTagCompound.set("tag", (NBTBase)nBTTagCompound1);
        } 
      } 
      
      if (s != 0) {
        paramNBTTagCompound.setShort("Damage", (short)0);
      }
    } 
    
    return paramNBTTagCompound;
  }
}
