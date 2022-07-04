package net.minecraft.server;

import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;

public class ContainerFrazionChest extends Container
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

    public ContainerFrazionChest(IInventory playerInventory, IInventory chestInventory, EntityHuman player)
    {
        this.container = chestInventory;
        this.numRows = 8;
        chestInventory.startOpen(player);
        
        this.player = (PlayerInventory) playerInventory;
        
        int i = (this.numRows - 4) * 18;

        for (int j = 0; j < this.numRows; ++j)
        {
            for (int k = 0; k < 18; ++k)
            {
                this.addSlotToContainer(new Slot(chestInventory, k + j * 18, 8 + k * 18, 18 + j * 18));
            }
        }

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(playerInventory, j1 + l * 9 + 9, 89 + j1 * 18, 102 + l * 18 + i));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(playerInventory, i1, 89 + i1 * 18, 160 + i));
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
            if (i < this.numRows * 18)
            {
                if (!this.a(itemstack1, this.numRows * 18, this.slots.size(), false))
                {
                    return ItemStack.a;
                }
            }
            else if (!this.a(itemstack1, 0, this.numRows * 18, false))
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
