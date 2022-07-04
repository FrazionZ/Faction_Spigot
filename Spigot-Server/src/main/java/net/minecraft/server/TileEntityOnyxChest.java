package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;

import net.minecraft.server.frazionz.blocks.BlockOnyxChest;

public class TileEntityOnyxChest extends TileEntityLootable implements ITickable
{
    private NonNullList<ItemStack> items;
    
    
    public boolean adjacentChestChecked;
    
    
    public TileEntityOnyxChest adjacentChestZNeg;
    
    
    public TileEntityOnyxChest adjacentChestXPos;
    
    
    public TileEntityOnyxChest adjacentChestXNeg;
    
    
    public TileEntityOnyxChest adjacentChestZPos;
    
    
    public float lidAngle;
    
    
    public float prevLidAngle;
    
    
    public int numPlayersUsing;
    
    
    private int ticksSinceSync;
    
    public List<HumanEntity> transaction = new ArrayList();
    private int maxStack = 64;
    
    public List<ItemStack> getContents()
    {
      return this.items;
    }
    
    public void onOpen(CraftHumanEntity who)
    {
      this.transaction.add(who);
    }
    
    public void onClose(CraftHumanEntity who)
    {
      this.transaction.remove(who);
    }
    
    public List<HumanEntity> getViewers()
    {
      return this.transaction;
    }
    
    public void setMaxStackSize(int size)
    {
      this.maxStack = size;
    }
    
    public TileEntityOnyxChest()
    {
      this.items = NonNullList.a(96, ItemStack.a);
    }
    
    
    
    public int getSize()
    {
        return 96;
    }

    public boolean x_() {
        Iterator iterator = this.items.iterator();

        ItemStack itemstack;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            itemstack = (ItemStack) iterator.next();
        } while (itemstack.isEmpty());

        return false;
    }

    public String getName() 
    {
        return this.hasCustomName() ? this.o : "container.OnyxChest";
    }

    public static void a(DataConverterManager dataconvertermanager)
    {
        dataconvertermanager.a(DataConverterTypes.BLOCK_ENTITY, (DataInspector) (new DataInspectorItemList(TileEntityOnyxChest.class, new String[] { "Items"})));
    }

    public void load(NBTTagCompound nbttagcompound)
    {
        super.load(nbttagcompound);
        this.items = NonNullList.a(this.getSize(), ItemStack.a);
        
        if (!this.c(nbttagcompound))
        {
            ContainerUtil.b(nbttagcompound, this.items);
        }

        if (nbttagcompound.hasKeyOfType("CustomName", 8))
        {
            this.o = nbttagcompound.getString("CustomName");
        }
    }

    public NBTTagCompound save(NBTTagCompound nbttagcompound)
    {
        super.save(nbttagcompound);
        
        if (!this.d(nbttagcompound))
        {
            ContainerUtil.a(nbttagcompound, this.items);
        }

        if (this.hasCustomName())
        {
            nbttagcompound.setString("CustomName", this.o);
        }

        return nbttagcompound;
    }
    
    
    

    public int getMaxStackSize()
    {
        return 64;
    }

    public void invalidateBlockCache()
    {
        super.invalidateBlockCache();
        this.adjacentChestChecked = false;
    }

    
    private void a(TileEntityOnyxChest tileentityOnyxChest, EnumDirection enumdirection)
    {
        if (tileentityOnyxChest.y())
        {
            this.adjacentChestChecked = false;
        } 
        else if (this.adjacentChestChecked)
        {
            switch (enumdirection)
            {
	            case NORTH:
	                if (this.adjacentChestZNeg != tileentityOnyxChest)
	                {
	                    this.adjacentChestChecked = false;
	                }
	                
	                break;
	
	            case SOUTH:
	                if (this.adjacentChestZPos != tileentityOnyxChest)
	                {
	                    this.adjacentChestChecked = false;
	                }
	                
	                break;
	
	            case EAST:
	                if (this.adjacentChestXPos != tileentityOnyxChest)
	                {
	                    this.adjacentChestChecked = false;
	                }
	                
	                break;
	
	            case WEST:
	                if (this.adjacentChestXNeg != tileentityOnyxChest)
	                {
	                    this.adjacentChestChecked = false;
	                }
            }
        }
    }

    
    
    
    
    
    
    
    
    
    
    public void e()
    {
        int i = this.position.getX();
        int j = this.position.getY();
        int k = this.position.getZ();
        ++this.ticksSinceSync;
        
        if (!this.world.isClientSide && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0) 
        {
            this.numPlayersUsing = 0;
            float f = 5.0F;
            
            List list = this.world.a(EntityHuman.class, new AxisAlignedBB((double) ((float) i - 5.0F), (double) ((float) j - 5.0F), (double) ((float) k - 5.0F), (double) ((float) (i + 1) + 5.0F), (double) ((float) (j + 1) + 5.0F), (double) ((float) (k + 1) + 5.0F)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityHuman entityhuman = (EntityHuman) iterator.next();

                if (entityhuman.activeContainer instanceof ContainerOnyxChest) {
                    IInventory iinventory = ((ContainerOnyxChest) entityhuman.activeContainer).e();

                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        float f = 0.1F;
        double d0;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
            double d1 = (double) i + 0.5D;

            d0 = (double) k + 0.5D;
            if (this.adjacentChestZPos != null) {
                d0 += 0.5D;
            }

            if (this.adjacentChestXPos != null) {
                d1 += 0.5D;
            }

            this.world.a((EntityHuman) null, d1, (double) j + 0.5D, d0, SoundEffects.ac, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;

            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;

            if (this.lidAngle < 0.5F && f1 >= 0.5F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
                d0 = (double) i + 0.5D;
                double d2 = (double) k + 0.5D;

                if (this.adjacentChestZPos != null) {
                    d2 += 0.5D;
                }

                if (this.adjacentChestXPos != null) {
                    d0 += 0.5D;
                }

                this.world.a((EntityHuman) null, d0, (double) j + 0.5D, d2, SoundEffects.aa, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }

    }

    public boolean c(int i, int j)
    {
        if (i == 1)
        {
            this.numPlayersUsing = j;
            return true;
        }
        else
        {
            return super.c(i, j);
        }
    }

    public void startOpen(EntityHuman entityhuman)
    {
        if (!entityhuman.isSpectator())
        {
            if (this.numPlayersUsing < 0)
            {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.world.playBlockAction(this.position, this.getBlock(), 1, this.numPlayersUsing);
            this.world.applyPhysics(this.position, this.getBlock(), false);
        }

    }

    public void closeContainer(EntityHuman entityhuman)
    {
        if (!entityhuman.isSpectator() && this.getBlock() instanceof BlockOnyxChest)
        {
            --this.numPlayersUsing;
            this.world.playBlockAction(this.position, this.getBlock(), 1, this.numPlayersUsing);
            this.world.applyPhysics(this.position, this.getBlock(), false);
        }

    }
    
    
    
    

    public void z() {
        super.z();
        this.invalidateBlockCache();
    }


    public String getContainerName() {
        return "minecraft:onyx_chest";
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        this.d(entityhuman);
        return new ContainerOnyxChest(playerinventory, this, entityhuman);
    }

    protected NonNullList<ItemStack> q() {
        return this.items;
    }


}
