package net.minecraft.server.frazionz.items;

import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.EnumAnimation;
import net.minecraft.server.EnumHand;
import net.minecraft.server.EnumInteractionResult;
import net.minecraft.server.IDynamicTexture;
import net.minecraft.server.InteractionResultWrapper;
import net.minecraft.server.Item;
import net.minecraft.server.ItemArrow;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Items;
import net.minecraft.server.MinecraftKey;
import net.minecraft.server.SoundCategory;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.StatisticList;
import net.minecraft.server.World;
import net.minecraft.server.EntityArrow.PickupStatus;

public class ItemUltraBow extends Item
{
    public ItemUltraBow() 
    {
        this.maxStackSize = 1;
        this.setMaxDurability(500);
        this.b(CreativeModeTab.j);
        
        this.a(new MinecraftKey("pull"), new IDynamicTexture() {
        });
        this.a(new MinecraftKey("pulling"), new IDynamicTexture() {
        });
    }

    private ItemStack a(EntityHuman entityhuman)
    {
        if (this.d(entityhuman.b(EnumHand.OFF_HAND)))
        {
            return entityhuman.b(EnumHand.OFF_HAND);
        }
        else if (this.d(entityhuman.b(EnumHand.MAIN_HAND))) 
        {
            return entityhuman.b(EnumHand.MAIN_HAND);
        }
        else
        {
            for (int i = 0; i < entityhuman.inventory.getSize(); ++i) 
            {
                ItemStack itemstack = entityhuman.inventory.getItem(i);

                if (this.d(itemstack))
                {
                    return itemstack;
                }
            }

            return ItemStack.a;
        }
    }

    protected boolean d(ItemStack itemstack)
    {
        return itemstack.getItem() instanceof ItemArrow;
    }

    public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i)
    {
        if (entityliving instanceof EntityHuman)
        {
            EntityHuman entityhuman = (EntityHuman) entityliving;
            boolean flag = entityhuman.abilities.canInstantlyBuild;
            ItemStack itemstack1 = this.a(entityhuman);

            if (!itemstack1.isEmpty() || flag)
            {
                if (itemstack1.isEmpty())
                {
                    itemstack1 = new ItemStack(Items.ARROW);
                }

                int j = this.e(itemstack) - i;
                float f = (float)j / 20.0F;
                f = (f * f + f * 2.0F) / 3.0F;

                if ((double)f < 0.1D)
                {
                    return;
                }

                if (f > 1.0F)
                {
                    f = 1.0F;
                }

                    boolean flag1 = flag && itemstack1.getItem() == Items.ARROW;

                    if (!world.isClientSide) 
                    {
                    	
                        ItemArrow itemarrow = (ItemArrow) (itemstack1.getItem() instanceof ItemArrow ? itemstack1.getItem() : Items.ARROW);
                        EntityArrow entityarrow = itemarrow.a(world, itemstack1, entityhuman);
                        //entityarrow.setAim(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, f * 2.0F, 1.0F);
                        // PVP_UPDATE
                        entityarrow.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, f * 2.0F, 1.0F);
                        
                        if (f == 1.0F) {
                            entityarrow.setCritical(true);
                        }

                        entityarrow.setKnockbackStrength(3);

                        // CraftBukkit start
                        org.bukkit.event.entity.EntityShootBowEvent event = org.bukkit.craftbukkit.event.CraftEventFactory.callEntityShootBowEvent(entityhuman, itemstack, entityarrow, f);
                        if (event.isCancelled()) {
                            event.getProjectile().remove();
                            return;
                        }

                        itemstack.damage(1, entityhuman);
                        if (flag1 || entityhuman.abilities.canInstantlyBuild && (itemstack1.getItem() == Items.SPECTRAL_ARROW || itemstack1.getItem() == Items.TIPPED_ARROW)) {
                            entityarrow.fromPlayer = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }

                        if (event.getProjectile() == entityarrow.getBukkitEntity()) {
                            if (!world.addEntity(entityarrow)) {
                                if (entityhuman instanceof EntityPlayer) {
                                    ((EntityPlayer) entityhuman).getBukkitEntity().updateInventory();
                                }
                                return;
                            }
                        }
                        // CraftBukkit end
                    }

                    world.a((EntityHuman) null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.w, SoundCategory.PLAYERS, 1.0F, 1.0F / (ItemUltraBow.j.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !entityhuman.abilities.canInstantlyBuild)
                    {
                        itemstack1.subtract(1);
                        
                        if (itemstack1.isEmpty())
                        {
                            entityhuman.inventory.f(itemstack1);
                        }
                    }

                    entityhuman.b(StatisticList.b((Item) this));
            }
        }
    }

    public static float b(int i) 
    {
        float f = (float) i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        
        if (f > 1.0F) 
        {
            f = 1.0F;
        }

        return f;
    }

    public int e(ItemStack itemstack) {
        return 72000;
    }

    public EnumAnimation f(ItemStack itemstack) {
        return EnumAnimation.BOW;
    }

    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);
        boolean flag = !this.a(entityhuman).isEmpty();

        if (!entityhuman.abilities.canInstantlyBuild && !flag)
        {
            return flag ? new InteractionResultWrapper(EnumInteractionResult.PASS, itemstack) : new InteractionResultWrapper(EnumInteractionResult.FAIL, itemstack);
        } 
        else 
        {
            entityhuman.c(enumhand);
            return new InteractionResultWrapper(EnumInteractionResult.SUCCESS, itemstack);
        }
    }

    public int c() {
        return 1;
    }
}
