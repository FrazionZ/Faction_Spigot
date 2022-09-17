package org.bukkit.craftbukkit.inventory;

import fz.frazionz.inventory.ItemCrusherInventory;
import net.minecraft.server.IInventory;

public class CraftInventoryItemCrusher extends CraftInventory implements ItemCrusherInventory
{
    public CraftInventoryItemCrusher(IInventory tileTrophyForge) {
        super(tileTrophyForge);
    }
}