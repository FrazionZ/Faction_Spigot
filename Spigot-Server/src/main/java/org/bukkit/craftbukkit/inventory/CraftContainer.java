package org.bukkit.craftbukkit.inventory;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import net.minecraft.server.ChatComponentText;
import net.minecraft.server.Container;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerAmeliorator;
import net.minecraft.server.ContainerAnvil;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerBauxiteChest;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerBauxiteFurnace;
import net.minecraft.server.ContainerBeacon;
import net.minecraft.server.ContainerBrewingStand;
import net.minecraft.server.ContainerChest;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerDirtChest;
import net.minecraft.server.ContainerDispenser;
import net.minecraft.server.ContainerEnchantTable;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerFrazionChest;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerFrazionFurnace;
import net.minecraft.server.ContainerFurnace;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerHdvChest;
import net.minecraft.server.ContainerHopper;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerOnyxChest;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerOnyxFurnace;
import net.minecraft.server.ContainerShulkerBox;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerSpawnerInventory;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerYelliteChest;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerYelliteFurnace;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerZHopper;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.PacketPlayOutOpenWindow;
import net.minecraft.server.PlayerInventory;
import net.minecraft.server.Slot;

public class CraftContainer extends Container {

    private final InventoryView view;
    private InventoryType cachedType;
    private String cachedTitle;
    private Container delegate;
    private final int cachedSize;

    public CraftContainer(InventoryView view, EntityHuman player, int id) {
        this.view = view;
        this.windowId = id;
        // TODO: Do we need to check that it really is a CraftInventory?
        IInventory top = ((CraftInventory) view.getTopInventory()).getInventory();
        PlayerInventory bottom = (PlayerInventory) ((CraftInventory) view.getBottomInventory()).getInventory();
        cachedType = view.getType();
        cachedTitle = view.getTitle();
        cachedSize = getSize();
        setupSlots(top, bottom, player);
    }

    public CraftContainer(final Inventory inventory, final EntityHuman player, int id) {
        this(new InventoryView() {
            @Override
            public Inventory getTopInventory() {
                return inventory;
            }

            @Override
            public Inventory getBottomInventory() {
                return getPlayer().getInventory();
            }

            @Override
            public HumanEntity getPlayer() {
                return player.getBukkitEntity();
            }

            @Override
            public InventoryType getType() {
                return inventory.getType();
            }
        }, player, id);
    }

    @Override
    public InventoryView getBukkitView() {
        return view;
    }

    private int getSize() {
        return view.getTopInventory().getSize();
    }

    @Override
    public boolean c(EntityHuman entityhuman) {
        if (cachedType == view.getType() && cachedSize == getSize() && cachedTitle.equals(view.getTitle())) {
            return true;
        }
        // If the window type has changed for some reason, update the player
        // This method will be called every tick or something, so it's
        // as good a place as any to put something like this.
        boolean typeChanged = (cachedType != view.getType());
        cachedType = view.getType();
        cachedTitle = view.getTitle();
        if (view.getPlayer() instanceof CraftPlayer) {
            CraftPlayer player = (CraftPlayer) view.getPlayer();
            String type = getNotchInventoryType(cachedType);
            IInventory top = ((CraftInventory) view.getTopInventory()).getInventory();
            PlayerInventory bottom = (PlayerInventory) ((CraftInventory) view.getBottomInventory()).getInventory();
            this.items.clear();
            this.slots.clear();
            if (typeChanged) {
                setupSlots(top, bottom, player.getHandle());
            }
            int size = getSize();
            player.getHandle().playerConnection.sendPacket(new PacketPlayOutOpenWindow(this.windowId, type, new ChatComponentText(cachedTitle), size));
            player.updateInventory();
        }
        return true;
    }

    public static String getNotchInventoryType(InventoryType type) {
        switch (type) {
            case WORKBENCH:
                return "minecraft:crafting_table";
            case FURNACE:
                return "minecraft:furnace";
            case DISPENSER:
                return "minecraft:dispenser";
            case ENCHANTING:
                return "minecraft:enchanting_table";
            case BREWING:
                return "minecraft:brewing_stand";
            case BEACON:
                return "minecraft:beacon";
            case ANVIL:
                return "minecraft:anvil";
            case HOPPER:
                return "minecraft:hopper";
            case Z_HOPPER:
                return "frazionz:z_hopper";
            case DROPPER:
                return "minecraft:dropper";
            case SHULKER_BOX:
                return "minecraft:shulker_box";
            case YELLITE_FURNACE:
                return "frazionz:yellite_furnace";
                
            case BAUXITE_FURNACE:
                return "frazionz:bauxite_furnace";
                
            case ONYX_FURNACE:
                return "frazionz:onyx_furnace";
                
            case FRAZION_FURNACE:
                return "frazionz:frazion_furnace";
                
            case HDV_CHEST:
                return "frazionz:hdv_chest";
            case spawner_inventory:
                return "frazionz:spawner_inventory";
                
            case AMELIORATOR:
                return "frazionz:ameliorator";
              
            case TROPHY_FORGE:
                return "frazionz:trophy_forge";
                
            default:
                return "minecraft:hdv_chest";
                
        }
    }

    private void setupSlots(IInventory top, PlayerInventory bottom, EntityHuman entityhuman) {
        switch (cachedType) {
            case CREATIVE:
                break; // TODO: This should be an error?
            case PLAYER:
            case CHEST:
                delegate = new ContainerChest(bottom, top, entityhuman);
                break;
            case DIRT_CHEST:
                delegate = new ContainerDirtChest(bottom, top, entityhuman);
                break;
            case YELLITE_CHEST:
                delegate = new ContainerYelliteChest(bottom, top, entityhuman);
                break;
            case BAUXITE_CHEST:
                delegate = new ContainerBauxiteChest(bottom, top, entityhuman);
                break;
            case FRAZION_CHEST:
                delegate = new ContainerFrazionChest(bottom, top, entityhuman);
                break;
            case ONYX_CHEST:
                delegate = new ContainerOnyxChest(bottom, top, entityhuman);
                break;
            case HDV_CHEST:
                delegate = new ContainerHdvChest(bottom, top, entityhuman);
            case spawner_inventory:
                delegate = new ContainerSpawnerInventory(bottom, top, entityhuman);
                break;
            case DISPENSER:
            case DROPPER:
                delegate = new ContainerDispenser(bottom, top);
                break;
            case YELLITE_FURNACE:
                delegate = new ContainerYelliteFurnace(bottom, top);
                break;
                
            case BAUXITE_FURNACE:
                delegate = new ContainerBauxiteFurnace(bottom, top);
                break;
                
            case ONYX_FURNACE:
                delegate = new ContainerOnyxFurnace(bottom, top);
                break;
                
            case FRAZION_FURNACE:
                delegate = new ContainerFrazionFurnace(bottom, top);
                break;
                
            case AMELIORATOR:
                delegate = new ContainerAmeliorator(bottom, top);
                break;
                
            case FURNACE:
                delegate = new ContainerFurnace(bottom, top);
                break;
            case CRAFTING: // TODO: This should be an error?
            case WORKBENCH:
                setupWorkbench(top, bottom); // SPIGOT-3812 - manually set up slots so we can use the delegated inventory and not the automatically created one
                break;
            case ENCHANTING:
                delegate = new ContainerEnchantTable(bottom, entityhuman.world, entityhuman.getChunkCoordinates());
                break;
            case BREWING:
                delegate = new ContainerBrewingStand(bottom, top);
                break;
            case HOPPER:
                delegate = new ContainerHopper(bottom, top, entityhuman);
                break;
            case ANVIL:
                delegate = new ContainerAnvil(bottom, entityhuman.world, entityhuman.getChunkCoordinates(), entityhuman);
                break;
            case BEACON:
                delegate = new ContainerBeacon(bottom, top);
                break;
            case SHULKER_BOX:
                delegate = new ContainerShulkerBox(bottom, top, entityhuman);
                break;
            case Z_HOPPER:
                delegate = new ContainerZHopper(bottom, top, entityhuman);
        }

        if (delegate != null) {
            this.items = delegate.items;
            this.slots = delegate.slots;
        }
    }

    private void setupWorkbench(IInventory top, IInventory bottom) {
        // This code copied from ContainerWorkbench
        this.a(new Slot(top, 0, 124, 35));

        int row;
        int col;

        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 3; ++col) {
                this.a(new Slot(top, 1 + col + row * 3, 30 + col * 18, 17 + row * 18));
            }
        }

        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 9; ++col) {
                this.a(new Slot(bottom, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (col = 0; col < 9; ++col) {
            this.a(new Slot(bottom, col, 8 + col * 18, 142));
        }
        // End copy from ContainerWorkbench
    }

    @Override
    public ItemStack shiftClick(EntityHuman entityhuman, int i) {
        return (delegate != null) ? delegate.shiftClick(entityhuman, i) : super.shiftClick(entityhuman, i);
    }

    @Override
    public boolean canUse(EntityHuman entity) {
        return true;
    }
}
