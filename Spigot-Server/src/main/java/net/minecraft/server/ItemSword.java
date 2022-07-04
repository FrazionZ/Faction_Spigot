package net.minecraft.server;

import com.google.common.collect.Multimap;

public class ItemSword
  extends Item
{
  private final float a;
  private final Item.EnumToolMaterial b;
  
  public ItemSword(Item.EnumToolMaterial paramEnumToolMaterial)
  {
    this.b = paramEnumToolMaterial;
    this.maxStackSize = 1;
    setMaxDurability(paramEnumToolMaterial.a());
    b(CreativeModeTab.j);
    this.a(new MinecraftKey("blocking"), new IDynamicTexture() {
    });
    
    this.a = (3.0F + paramEnumToolMaterial.c());
  }
  
  public float g()
  {
    return this.b.c();
  }
  
  public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData)
  {
    Block localBlock = paramIBlockData.getBlock();
    if (localBlock == Blocks.WEB) {
      return 15.0F;
    }
    Material localMaterial = paramIBlockData.getMaterial();
    if ((localMaterial == Material.PLANT) || (localMaterial == Material.REPLACEABLE_PLANT) || (localMaterial == Material.CORAL) || (localMaterial == Material.LEAVES) || (localMaterial == Material.PUMPKIN)) {
      return 1.5F;
    }
    return 1.0F;
  }
  
  public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2)
  {
    paramItemStack.damage(1, paramEntityLiving2);
    return true;
  }
  
  public boolean a(ItemStack paramItemStack, World paramWorld, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EntityLiving paramEntityLiving)
  {
    if (paramIBlockData.b(paramWorld, paramBlockPosition) != 0.0D) {
      paramItemStack.damage(2, paramEntityLiving);
    }
    return true;
  }
  
  public boolean canDestroySpecialBlock(IBlockData paramIBlockData)
  {
    return paramIBlockData.getBlock() == Blocks.WEB;
  }
  
  public int c()
  {
    return this.b.e();
  }
  
  public String h()
  {
    return this.b.toString();
  }
  
  public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2)
  {
    if (this.b.f() == paramItemStack2.getItem()) {
      return true;
    }
    return super.a(paramItemStack1, paramItemStack2);
  }
  
  public EnumAnimation f(ItemStack itemstack) {
      return EnumAnimation.BLOCK_SWORD;
  }

  public int e(ItemStack itemstack) {
      return 72000;
  }

  public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
      ItemStack itemstack = entityhuman.b(enumhand);

      entityhuman.c(enumhand);
      return new InteractionResultWrapper(EnumInteractionResult.SUCCESS, itemstack);
  }
  
  public Multimap<String, AttributeModifier> a(EnumItemSlot paramEnumItemSlot)
  {
    Multimap localMultimap = super.a(paramEnumItemSlot);
    
    if (paramEnumItemSlot == EnumItemSlot.MAINHAND)
    {
      localMultimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(h, "Weapon modifier", this.a, 0));
    }
    
    return localMultimap;
  }
}
