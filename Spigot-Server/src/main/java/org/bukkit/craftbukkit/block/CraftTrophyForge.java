package org.bukkit.craftbukkit.block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.TrophyForge;
import org.bukkit.craftbukkit.inventory.CraftInventoryTrophyForge;
import org.bukkit.inventory.TrophyForgeInventory;

import net.minecraft.server.frazionz.tileentity.TileEntityTrophyForge;

public class CraftTrophyForge extends CraftContainer<TileEntityTrophyForge> implements TrophyForge {

    public CraftTrophyForge(final Block block) {
        super(block, TileEntityTrophyForge.class);
    }

    public CraftTrophyForge(final Material material, final TileEntityTrophyForge te) {
        super(material, te);
    }

    @Override
    public TrophyForgeInventory getSnapshotInventory() {
        return new CraftInventoryTrophyForge(this.getSnapshot());
    }

    @Override
    public TrophyForgeInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryTrophyForge(this.getTileEntity());
    }

    @Override
    public short getForgeTime() {
        return (short) this.getSnapshot().getProperty(0);
    }

    @Override
    public void setForgeTime(short forgeTime) {
        this.getSnapshot().setProperty(0, forgeTime);
    }

    @Override
    public void applyTo(TileEntityTrophyForge furnace) {
        super.applyTo(furnace);
    }
	public String getCustomName() {
		return null;
	}
	public void setCustomName(String name) {	
	}
}
