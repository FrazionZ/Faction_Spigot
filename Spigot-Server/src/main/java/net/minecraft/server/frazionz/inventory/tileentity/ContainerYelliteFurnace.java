package net.minecraft.server.frazionz.inventory.tileentity;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.tileentity.TileEntityYelliteFurnace;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;
import org.bukkit.craftbukkit.inventory.CraftInventoryYelliteFurnace;
// CraftBukkit end

public class ContainerYelliteFurnace extends Container {

    private final IInventory furnace;
    private int f;
    private int g;
    private int h;
    private int i;

    // CraftBukkit start
    private CraftInventoryView bukkitEntity = null;
    private PlayerInventory player;

    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryYelliteFurnace inventory = new CraftInventoryYelliteFurnace((TileEntityYelliteFurnace) this.furnace);
        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
        return bukkitEntity;
    }
    // CraftBukkit end

    public ContainerYelliteFurnace(PlayerInventory playerinventory, IInventory iinventory) {
        this.furnace = iinventory;
        this.addSlotToContainer(new Slot(iinventory, 0, 40, 36));
        this.addSlotToContainer(new Slot(iinventory, 1, 58, 36));
        this.addSlotToContainer(new SlotFurnaceFuel(iinventory, 2, 49, 72));
        this.addSlotToContainer(new SlotYelliteFurnaceResult(playerinventory.player, iinventory, 3, 112, 54));
        this.addSlotToContainer(new SlotYelliteFurnaceResult(playerinventory.player, iinventory, 4, 130, 54));

        
        this.player = playerinventory; // CraftBukkit - save player

        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.a(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.a(new Slot(playerinventory, i, 8 + i * 18, 142));
        }

    }

    public void addSlotListener(ICrafting icrafting) {
        super.addSlotListener(icrafting);
        icrafting.setContainerData(this, this.furnace);
    }

    public void b() {
        super.b();

        for (int i = 0; i < this.listeners.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.listeners.get(i);

            if (this.f != this.furnace.getProperty(2)) {
                icrafting.setContainerData(this, 2, this.furnace.getProperty(2));
            }

            if (this.h != this.furnace.getProperty(0)) {
                icrafting.setContainerData(this, 0, this.furnace.getProperty(0));
            }

            if (this.i != this.furnace.getProperty(1)) {
                icrafting.setContainerData(this, 1, this.furnace.getProperty(1));
            }

            if (this.g != this.furnace.getProperty(3)) {
                icrafting.setContainerData(this, 3, this.furnace.getProperty(3));
            }
        }

        this.f = this.furnace.getProperty(2);
        this.h = this.furnace.getProperty(0);
        this.i = this.furnace.getProperty(1);
        this.g = this.furnace.getProperty(3);
    }

    public boolean canUse(EntityHuman entityhuman) {
        if (!this.checkReachable) return true; // CraftBukkit
        return this.furnace.a(entityhuman);
    }

    public ItemStack shiftClick(EntityHuman entityhuman, int i) {
        ItemStack itemstack = ItemStack.a;
        Slot slot = (Slot) this.slots.get(i);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();

            itemstack = itemstack1.cloneItemStack();
            if (i == 3 || i == 4)
            {
                if (!this.a(itemstack1, 5, 41, true))
                {
                    return ItemStack.a;
                }

                slot.a(itemstack1, itemstack);
            } 
            else if (i != 0 && i != 1 && i != 2)
            {
                if (!RecipesFurnace.getInstance().getResult(itemstack1).isEmpty())
                {
                    if (!this.a(itemstack1, 0, 2, false))
                    {
                        return ItemStack.a;
                    }
                }
                else if (TileEntityYelliteFurnace.isFuel(itemstack1))
                {
                    if (!this.a(itemstack1, 2, 4, false))
                    {
                        return ItemStack.a;
                    }
                }
                else if (i >= 5 && i < 32)
                {
                    if (!this.a(itemstack1, 32, 41, false))
                    {
                        return ItemStack.a;
                    }
                }
                else if (i >= 32 && i < 41 && !this.a(itemstack1, 5, 32, false))
                {
                    return ItemStack.a;
                }
            }
            else if (!this.a(itemstack1, 5, 41, false))
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
