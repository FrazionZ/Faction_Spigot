package net.minecraft.server;

import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.inventory.InventoryView;

public class ContainerDirtChest extends Container
{
	
    public CraftInventoryView getBukkitView()
    {
      if (this.bukkitEntity != null) {
        return this.bukkitEntity;
      }
      CraftInventory inventory;
      if ((this.container instanceof PlayerInventory)) {
        inventory = new CraftInventoryPlayer((PlayerInventory)this.container);
      } else {
        inventory = new CraftInventory(this.container);
      }
      this.bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
      return this.bukkitEntity;
    }
	
    private final IInventory container;
    private final int numRows;

    public ContainerDirtChest(IInventory playerInventory, IInventory chestInventory, EntityHuman player)
    {
        this.container = chestInventory;
        this.numRows = chestInventory.getSize() / 9;
        chestInventory.startOpen(player);
        
        this.player = (PlayerInventory) playerInventory;
        
        int i = 0;
        
       
        for (int k = 0; k < 1; ++k)
        {
            this.a(new Slot(chestInventory, k , 8 + 4 * 18, 29));
        }


        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.a(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 74 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.a(new Slot(playerInventory, i1, 8 + i1 * 18, 132 + i));
        }
    }

    
    
    
    public boolean canUse(EntityHuman entityhuman)
    {
        return this.container.a(entityhuman);
    }

    
    
    
    public ItemStack shiftClick(EntityHuman entityhuman, int i)
    {
        ItemStack itemstack = ItemStack.a;
        Slot slot = (Slot) this.slots.get(i);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i < this.container.getSize())
            {
                if (!this.a(itemstack1, this.container.getSize(), this.slots.size(), true))
                {
                    return ItemStack.a;
                }
            }
            else if (!this.a(itemstack1, 0, this.container.getSize(), false))
            {
                return ItemStack.a;
            }

            if (itemstack1.isEmpty())
            {
                slot.set(ItemStack.a);
            } 
            else 
            {
                slot.f();
            }
        }

        return itemstack;
    }

    public void b(EntityHuman entityhuman)
    {
        super.b(entityhuman);
        this.container.closeContainer(entityhuman);
    }

    public IInventory e()
    {
        return this.container;
    }




    private CraftInventoryView bukkitEntity = null;
    private PlayerInventory player;
    

}
