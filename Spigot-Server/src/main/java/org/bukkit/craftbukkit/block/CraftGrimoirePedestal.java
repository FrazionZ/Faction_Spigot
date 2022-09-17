package org.bukkit.craftbukkit.block;

import fz.frazionz.block.GrimoirePedestal;
import net.minecraft.server.frazionz.tileentity.TileEntityGrimoirePedestal;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class CraftGrimoirePedestal extends CraftBlockEntityState<TileEntityGrimoirePedestal> implements GrimoirePedestal {

    public CraftGrimoirePedestal(final Block block) {
        super(block, TileEntityGrimoirePedestal.class);
    }

    public CraftGrimoirePedestal(final Material material, final TileEntityGrimoirePedestal te) {
        super(material, te);
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String name) {
    }
}
