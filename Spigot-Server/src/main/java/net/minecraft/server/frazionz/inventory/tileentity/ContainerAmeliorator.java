package net.minecraft.server.frazionz.inventory.tileentity;

import net.minecraft.server.*;
import org.bukkit.craftbukkit.inventory.CraftInventoryAmeliorator;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;

public class ContainerAmeliorator extends Container
{
	
	private final IInventory tileAmeliorator;
	
    private int cookTime;
    
    private int totalCookTime;
    
    private int burnTime;
    
    private int currentItemBurnTime;
    
    
    private CraftInventoryView bukkitEntity = null;
    private PlayerInventory player;

	@Override
	public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryAmeliorator inventory = new CraftInventoryAmeliorator(this.tileAmeliorator);
        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
        return bukkitEntity;
    }
	
    private int SlotLimit = 1;
	
	public ContainerAmeliorator(PlayerInventory playerInventory, IInventory furnaceInventory)
    {
		this.player = playerInventory;
        this.tileAmeliorator = furnaceInventory;
        this.addSlotToContainer(new SlotAmelioratorFuel(furnaceInventory, 0, 14, 13));
        this.addSlotToContainer(new Slot(furnaceInventory, 1, 86, 13)
        		{
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 2, 116, 23)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 3, 126, 53)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 4, 116, 83)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 5, 86, 93)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 6, 56, 83)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 7, 46, 53)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 8, 56, 23)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        this.addSlotToContainer(new Slot(furnaceInventory, 9, 86, 53)
        {
        	public int getMaxStackSize()
            {
                return SlotLimit;
            }}
        		);
        
        

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 14 + j * 18, 123 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 14 + k * 18, 181));
        }
    }
	
	public void addSlotListener(ICrafting listener)
    {
        super.addSlotListener(listener);
        listener.setContainerData(this, this.tileAmeliorator);
    }
	
	
	
	public void b()
    {
        super.b();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
        	ICrafting icontainerlistener = this.listeners.get(i);

            if (this.cookTime != this.tileAmeliorator.getProperty(2))
            {
                icontainerlistener.setContainerData(this, 2, this.tileAmeliorator.getProperty(2));
            }

            if (this.burnTime != this.tileAmeliorator.getProperty(0))
            {
                icontainerlistener.setContainerData(this, 0, this.tileAmeliorator.getProperty(0));
            }

            if (this.currentItemBurnTime != this.tileAmeliorator.getProperty(1))
            {
                icontainerlistener.setContainerData(this, 1, this.tileAmeliorator.getProperty(1));
            }

            if (this.totalCookTime != this.tileAmeliorator.getProperty(3))
            {
                icontainerlistener.setContainerData(this, 3, this.tileAmeliorator.getProperty(3));
            }
        }

        this.cookTime = this.tileAmeliorator.getProperty(2);
        this.burnTime = this.tileAmeliorator.getProperty(0);
        this.currentItemBurnTime = this.tileAmeliorator.getProperty(1);
        this.totalCookTime = this.tileAmeliorator.getProperty(3);
    }
	
	public void updateProgressBar(int id, int data)
    {
        this.tileAmeliorator.setProperty(id, data);
    }
	
    public boolean canUse(EntityHuman entityhuman)
    {
    	if (!this.checkReachable) return true;
        return this.tileAmeliorator.a(entityhuman);
    }
	
    public ItemStack shiftClick(EntityHuman entityhuman, int index)
    {
        ItemStack itemstack = ItemStack.a;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.cloneItemStack();
            
            if (index >= 0 && index < 10)
            {
                if (!this.a(itemstack1, 10, 46, false))
                {
                    return ItemStack.a;
                }
            }
            
            else if (!this.a(itemstack1, 0, 9, false))
                {
                        return ItemStack.a;
                }
                
        	/*else if (TileEntityAmeliorator.isItemFuel(itemstack1))
                {
                    if (!this.a(itemstack1, 0, 1, false))
                    {
                        return ItemStack.a;
                    }
                }*/
            else if (index >= 10 && index < 37)
                {
                    if (!this.a(itemstack1, 37, 46, false))
                    {
                        return ItemStack.a;
                    }
                }
            else if (index >= 37 && index < 46)
            {
                 if (!this.a(itemstack1, 10, 37, false))
                 {
                        return ItemStack.a;
                 }
            }
            
            else if (!this.a(itemstack1, 10, 46, false))
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

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.a;
            }

            slot.a(entityhuman, itemstack1);
        }

        return itemstack;
    }

}
