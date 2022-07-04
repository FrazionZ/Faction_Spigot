package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

import org.bukkit.craftbukkit.entity.CraftPlayer;
// CraftBukkit start
import org.bukkit.craftbukkit.inventory.CraftInventoryCrafting;
import org.bukkit.craftbukkit.inventory.CraftInventoryView;

import net.minecraft.server.frazionz.items.ItemTrophy;

public class ContainerPlayer extends Container {

    private static final EnumItemSlot[] h = new EnumItemSlot[] { EnumItemSlot.HEAD, EnumItemSlot.CHEST, EnumItemSlot.LEGS, EnumItemSlot.FEET};
    private static final EnumItemSlot[] VALID_TROPHY_SLOTS = new EnumItemSlot[] { EnumItemSlot.TROPHY_1, EnumItemSlot.TROPHY_2, EnumItemSlot.TROPHY_3};
    private List<Integer> trophySlots = Arrays.asList(46,47,48);
    public InventoryCrafting craftInventory;
    public InventoryCraftResult resultInventory = new InventoryCraftResult();
    public boolean g;
    private final EntityHuman owner;
    // CraftBukkit start
    private CraftInventoryView bukkitEntity = null;
    private PlayerInventory player;
    
    public CraftPlayer playerPerm;
    // CraftBukkit end

    public ContainerPlayer(final PlayerInventory playerinventory, boolean flag, EntityHuman entityhuman) {
    	
    	craftInventory = new InventoryCrafting(this, 2, 2);
    	
            this.g = flag;
            this.owner = entityhuman;
            // CraftBukkit start
            this.resultInventory = new InventoryCraftResult(); // CraftBukkit - moved to before InventoryCrafting construction
            this.craftInventory = new InventoryCrafting(this, 2, 2, playerinventory.player); // CraftBukkit - pass player
            this.craftInventory.resultInventory = this.resultInventory; // CraftBukkit - let InventoryCrafting know about its result slot
            this.player = playerinventory; // CraftBukkit - save player
            // CraftBukkit end
            this.a((Slot) (new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 154, 28)));

            int i;
            int j;

            for (i = 0; i < 2; ++i) {
                for (j = 0; j < 2; ++j) {
                    this.a(new Slot(this.craftInventory, j + i * 2, 98 + j * 18, 18 + i * 18));
                }
            }

            for (i = 0; i < 4; ++i) {
                final EnumItemSlot enumitemslot1 = ContainerPlayer.h[i];

                this.a(new Slot(playerinventory, 36 + (3 - i), 8, 8 + i * 18) {
                    public int getMaxStackSize() {
                        return 1;
                    }

                    public boolean isAllowed(ItemStack itemstack) {
                        return enumitemslot1 == EntityInsentient.d(itemstack); // CraftBukkit - decompile error
                    }

                    public boolean isAllowed(EntityHuman entityhuman) {
                        ItemStack itemstack = this.getItem();

                        return !itemstack.isEmpty() && !entityhuman.z() && EnchantmentManager.d(itemstack) ? false : super.isAllowed(entityhuman);
                    }
                });
            }

            for (i = 0; i < 3; ++i) {
                for (j = 0; j < 9; ++j) {
                    this.a(new Slot(playerinventory, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
                }
            }

            for (i = 0; i < 9; ++i) {
                this.a(new Slot(playerinventory, i, 8 + i * 18, 142));
            }

            this.a(new Slot(playerinventory, 40, 77, 62) {
            });
            
            
            for (int k = 0; k < 3; ++k)
            {
                final EnumItemSlot entityequipmentslot = VALID_TROPHY_SLOTS[k];
                this.addSlotToContainer(new Slot(playerinventory, 41 + k, 82, 30 + k * 18)
                {
                    public int getSlotStackLimit()
                    {
                        return 1;
                    }
                    public boolean isItemValid(ItemStack stack)
                    {
                        return stack.getItem() instanceof ItemTrophy;
                    }
                });
            }
    	
    	
    }

    public void a(IInventory iinventory) {
        this.a(this.owner.world, this.owner, this.craftInventory, this.resultInventory);
    }

    public void b(EntityHuman entityhuman) {
        super.b(entityhuman);
        this.resultInventory.clear();
        if (!entityhuman.world.isClientSide) {
            this.a(entityhuman, entityhuman.world, this.craftInventory);
        }
    }

    public boolean canUse(EntityHuman entityhuman) {
        return true;
    }

    public ItemStack shiftClick(EntityHuman entityhuman, int i)
    {
        ItemStack itemstack = ItemStack.a;
        Slot slot = (Slot) this.slots.get(i);
        
            if (slot != null && slot.hasItem()) {
                ItemStack itemstack1 = slot.getItem();

                itemstack = itemstack1.cloneItemStack();
                EnumItemSlot enumitemslot = EntityInsentient.d(itemstack);

                if (i == 0) {
                    if (!this.a(itemstack1, 9, 45, true)) {
                        return ItemStack.a;
                    }

                    slot.a(itemstack1, itemstack);
                } else if (i >= 1 && i < 5) {
                    if (!this.a(itemstack1, 9, 45, false)) {
                        return ItemStack.a;
                    }
                } else if (i >= 5 && i < 9) {
                    if (!this.a(itemstack1, 9, 45, false)) {
                        return ItemStack.a;
                    }
                } else if (enumitemslot.a() == EnumItemSlot.Function.ARMOR && !((Slot) this.slots.get(8 - enumitemslot.b())).hasItem()) {
                    int j = 8 - enumitemslot.b();

                    if (!this.a(itemstack1, j, j + 1, false)) {
                        return ItemStack.a;
                    }
                } else if (enumitemslot == EnumItemSlot.OFFHAND && !((Slot) this.slots.get(45)).hasItem()) {
                    if (!this.a(itemstack1, 45, 46, false)) {
                        return ItemStack.a;
                    }
                }
                else if (itemstack.getItem() instanceof ItemTrophy && !((Slot)this.slots.get(48 - i)).hasItem() && !this.trophySlots.contains(i) )
                {
                    if (!this.a(itemstack1, 46, 49, false))
                    {
                        return ItemStack.a;
                    }
                }
                
                else if (i >= 9 && i < 36) {
                    if (!this.a(itemstack1, 36, 45, false)) {
                        return ItemStack.a;
                    }
                } else if (i >= 36 && i < 45) {
                    if (!this.a(itemstack1, 9, 36, false)) {
                        return ItemStack.a;
                    }
                } else if (!this.a(itemstack1, 9, 45, false)) {
                    return ItemStack.a;
                }

                if (itemstack1.isEmpty()) {
                    slot.set(ItemStack.a);
                } else {
                    slot.f();
                }

                if (itemstack1.getCount() == itemstack.getCount()) {
                    return ItemStack.a;
                }

                ItemStack itemstack2 = slot.a(entityhuman, itemstack1);

                if (i == 0) {
                    entityhuman.drop(itemstack2, false);
                }
    		
    	}


        return itemstack;
    }

    public boolean a(ItemStack itemstack, Slot slot) {
        return slot.inventory != this.resultInventory && super.a(itemstack, slot);
    }

    // CraftBukkit start
    @Override
    public CraftInventoryView getBukkitView() {
        if (bukkitEntity != null) {
            return bukkitEntity;
        }

        CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
        bukkitEntity = new CraftInventoryView(this.player.player.getBukkitEntity(), inventory, this);
        return bukkitEntity;
    }
    // CraftBukkit end
}
