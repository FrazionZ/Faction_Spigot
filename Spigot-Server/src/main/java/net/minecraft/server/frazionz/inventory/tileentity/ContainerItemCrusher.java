package net.minecraft.server.frazionz.inventory.tileentity;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.recipes.ItemCrusherRecipes;
import net.minecraft.server.frazionz.tileentity.TileEntityItemCrusher;
import net.minecraft.server.frazionz.tileentity.impl.TileMachine;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.craftbukkit.inventory.CraftInventoryItemCrusher;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.event.frazionz.inventory.MachineCraftEvent;

public class ContainerItemCrusher extends Container implements TileMachine {

    private static final int[] SLOT_CRAFT = new int[] {0, 1, 2};
    private final IInventory tileEntity;
    private int crushingTime;
    private int totalCrushingTime;
    private int SlotLimit = 1;
    private int isCrushing;

    private CraftInventoryView bukkitEntity = null;
    private PlayerInventory player;

    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryItemCrusher inventory = new CraftInventoryItemCrusher(this.tileEntity);
        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
        return bukkitEntity;
    }

    public ContainerItemCrusher(PlayerInventory playerInventory, IInventory crusherInventory)
    {
        this.player = playerInventory;
        this.tileEntity = crusherInventory;
        for(int i = 0; i < 3; i++) {
            this.addSlotToContainer(new Slot(crusherInventory, i, 80 + i*18, 30));
        }

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 5; ++j) {
                this.addSlotToContainer(new Slot(crusherInventory, 3 + j + i*5, 62 + j*18, 64 + i*18));
            }
        }


        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 44 + j * 18, 118 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 44 + k * 18, 176));
        }
    }

    public void addSlotListener(ICrafting listener)
    {
        super.addSlotListener(listener);
        listener.setContainerData(this, this.tileEntity);
    }

    public void b()
    {
        super.b();

        for (int i = 0; i < this.listeners.size(); ++i)
        {
            ICrafting icontainerlistener = this.listeners.get(i);

            if (this.crushingTime != this.tileEntity.getProperty(0))
            {
                icontainerlistener.setContainerData(this, 0, this.tileEntity.getProperty(0));
            }

            if (this.totalCrushingTime != this.tileEntity.getProperty(1))
            {
                icontainerlistener.setContainerData(this, 1, this.tileEntity.getProperty(1));
            }

            if (this.isCrushing != this.tileEntity.getProperty(2))
            {
                icontainerlistener.setContainerData(this, 2, this.tileEntity.getProperty(2));
            }
        }

        this.crushingTime = this.tileEntity.getProperty(0);
        this.totalCrushingTime = this.tileEntity.getProperty(1);
        this.isCrushing = this.tileEntity.getProperty(2);
    }

    public boolean canUse(EntityHuman playerIn)
    {
        return this.tileEntity.a(playerIn);
    }

    public ItemStack shiftClick(EntityHuman playerIn, int index)
    {
        ItemStack itemstack = ItemStack.a;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem())
        {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.cloneItemStack();

            if (index >= 0 && index < 13)
            {
                if (!this.a(itemstack1, 13, 49, false))
                {
                    return ItemStack.a;
                }
            }
            else if (!this.a(itemstack1, 0, 3, false))
            {
                return ItemStack.a;
            }
            else if (index >= 13 && index < 40)
            {
                if (!this.a(itemstack1, 40, 49, false))
                {
                    return ItemStack.a;
                }
            }
            else if (index >= 40 && index < 49)
            {
                if (!this.a(itemstack1, 13, 40, false))
                {
                    return ItemStack.a;
                }
            }

            else if (!this.a(itemstack1, 13, 49, false))
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

            slot.a(playerIn, itemstack1);
        }

        return itemstack;
    }

    public void startMachine(EntityHuman player) {
        if(canCrush()) {
            if(CraftEventFactory.callMachineCraftEvent(player, this.slots.get(0).getItem(), this.getBukkitView(), MachineCraftEvent.MachineType.ITEM_CRUSHER, MachineCraftEvent.MachineAction.START_CRAFTING).isCancelled())
                return;
            if(CraftEventFactory.callMachineCraftEvent(player, this.slots.get(0).getItem(), this.getBukkitView(), MachineCraftEvent.MachineType.ITEM_CRUSHER, MachineCraftEvent.MachineAction.START_CRAFTING).isCancelled())
                return;
            if(CraftEventFactory.callMachineCraftEvent(player, this.slots.get(0).getItem(), this.getBukkitView(), MachineCraftEvent.MachineType.ITEM_CRUSHER, MachineCraftEvent.MachineAction.START_CRAFTING).isCancelled())
                return;
            ((TileEntityItemCrusher)this.tileEntity).setActionner(player);
            this.tileEntity.setProperty(2, 1);
        }
    }

    public boolean isForging() {
        return this.isCrushing == 1;
    }

    public IInventory getTileEntity() {
        return tileEntity;
    }

    public ItemStack[] getRecipeResult(int id) {
        return ItemCrusherRecipes.getResult(this.slots.get(id).getItem());
    }

    public boolean canCrush() {
        boolean canCrush = false;
        for(int id : SLOT_CRAFT)
            canCrush = canCrush || this.getRecipeResult(id) != null;
        return canCrush;
    }

}
