package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.server.DataConverterHealth;
import net.minecraft.server.IDataConverter;
import net.minecraft.server.NBTTagCompound;


public class DataConverterHealth
  implements IDataConverter
{
  private static final Set<String> a = Sets.newHashSet("herobrine_1", "illager", "nether_spider", "ArmorStand", "Bat", "Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderDragon", "Enderman", "Endermite", "EntityHorse", "Ghast", "Giant", "Guardian", "LavaSlime", "MushroomCow", "Ozelot", "Pig", "PigZombie", "Rabbit", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime", "SnowMan", "Spider", "Squid", "Villager", "VillagerGolem", "Witch", "WitherBoss", "Wolf", "Zombie" );

  public int a()
  {
	
	  return 109;
	  
  }


  
  public NBTTagCompound a(NBTTagCompound paramNBTTagCompound) {
    if (a.contains(paramNBTTagCompound.getString("id"))) {
      float f;
      if (paramNBTTagCompound.hasKeyOfType("HealF", 99)) {
        f = paramNBTTagCompound.getFloat("HealF");
        paramNBTTagCompound.remove("HealF");
      } else if (paramNBTTagCompound.hasKeyOfType("Health", 99)) {
        f = paramNBTTagCompound.getFloat("Health");
      } else {
        return paramNBTTagCompound;
      } 
      paramNBTTagCompound.setFloat("Health", f);
    } 
    
    return paramNBTTagCompound;
  }
}
