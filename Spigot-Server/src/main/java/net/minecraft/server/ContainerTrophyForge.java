package net.minecraft.server;

import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftInventoryTrophyForge;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.event.frazionz.inventory.MachineCraftEvent.MachineAction;
import org.bukkit.event.frazionz.inventory.MachineCraftEvent.MachineType;

public class ContainerTrophyForge extends Container
{
	
	private final IInventory tileTrophyForge;
    private int forgeTime;
    private int totalForgeTime;
    private int SlotLimit = 1;
	private int isForging;
    
    
    private CraftInventoryView bukkitEntity = null;
    private PlayerInventory player;

	@Override
	public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryTrophyForge inventory = new CraftInventoryTrophyForge(this.tileTrophyForge);
        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
        return bukkitEntity;
    }
	
	public ContainerTrophyForge(PlayerInventory playerInventory, IInventory tileTrophyForge)
    {
		this.player = playerInventory;
        this.tileTrophyForge = tileTrophyForge;
        for(int i = 0; i < 3; ++i) {
        	for(int j = 0; j < 3; ++j) {
        		this.addSlotToContainer(new Slot(tileTrophyForge, j + i * 3, 14 + i*22, 29 + j*22));
        	}
        }
        
        for(int i = 0; i < 3; i++) {
        	this.addSlotToContainer(new Slot(tileTrophyForge, 9 + i, 108, 29 + i*22));
        }
        
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
        listener.setContainerData(this, this.tileTrophyForge);
    }
	
	public void b()
    {
        super.b();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
        	ICrafting icontainerlistener = this.listeners.get(i);

            if (this.forgeTime != this.tileTrophyForge.getProperty(0))
            {
                icontainerlistener.setContainerData(this, 0, this.tileTrophyForge.getProperty(2));
            }

            if (this.totalForgeTime != this.tileTrophyForge.getProperty(1))
            {
                icontainerlistener.setContainerData(this, 1, this.tileTrophyForge.getProperty(0));
            }

            if (this.isForging != this.tileTrophyForge.getProperty(2))
            {
                icontainerlistener.setContainerData(this, 2, this.tileTrophyForge.getProperty(1));
            }
        }

        this.forgeTime = this.tileTrophyForge.getProperty(0);
        this.totalForgeTime = this.tileTrophyForge.getProperty(1);
        this.isForging = this.tileTrophyForge.getProperty(2);
    }
	
	public void updateProgressBar(int id, int data)
    {
        this.tileTrophyForge.setProperty(id, data);
    }
	
    public boolean canUse(EntityHuman entityhuman)
    {
        return this.tileTrophyForge.a(entityhuman);
    }
	
    public ItemStack shiftClick(EntityHuman entityhuman, int index)
    {
        ItemStack itemstack = ItemStack.a;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.cloneItemStack();
            
            if (index >= 0 && index < 12)
            {
                if (!this.a(itemstack1, 12, 48, false))
                {
                    return ItemStack.a;
                }
            }
            
            else if (!this.a(itemstack1, 0, 9, false))
                {
                        return ItemStack.a;
                }
            else if (index >= 12 && index < 39)
                {
                    if (!this.a(itemstack1, 39, 48, false))
                    {
                        return ItemStack.a;
                    }
                }
            else if (index >= 39 && index < 48)
            {
                 if (!this.a(itemstack1, 12, 39, false))
                 {
                        return ItemStack.a;
                 }
            }
            
            else if (!this.a(itemstack1, 12, 48, false))
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
    
    public void startAction(EntityPlayer player) {
    	if(canForge()) {
    		if(CraftEventFactory.callMachineCraftEvent(player, getRecipeResult(), this.getBukkitView(), MachineType.TROPHY_FORGE, MachineAction.START_CRAFTING).isCancelled())
    			return;
    		((TileEntityTrophyForge)this.tileTrophyForge).setForger(player);
    		this.tileTrophyForge.setProperty(2, 1);
    	} 
    }
    
    public boolean canForge()
    {
        return getRecipeResult() != null;
    }
    
    public ItemStack getRecipeResult() {
    	return TrophyForgeRecipes.getTrophyForgeResult(new ItemStack[] {
        		this.slots.get(0).getItem(),
        		this.slots.get(1).getItem(),
        		this.slots.get(2).getItem(),
        		this.slots.get(3).getItem(),
        		this.slots.get(4).getItem(),
        		this.slots.get(5).getItem(),
        		this.slots.get(6).getItem(),
        		this.slots.get(7).getItem(),
        		this.slots.get(8).getItem(),
        	});
    }
    
    public boolean isForging() {
    	return this.isForging == 1;
    }

}
