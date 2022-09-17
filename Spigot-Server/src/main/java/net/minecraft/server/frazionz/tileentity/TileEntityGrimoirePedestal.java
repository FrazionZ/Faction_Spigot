package net.minecraft.server.frazionz.tileentity;

import net.minecraft.server.ITickable;
import net.minecraft.server.TileEntity;
import net.minecraft.server.frazionz.tileentity.impl.TickCounter;

public class TileEntityGrimoirePedestal extends TileEntity implements ITickable, TickCounter {

    private int tickCount;

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void e()
    {
        ++this.tickCount;
    }

    @Override
    public int getTickCount() {
        return tickCount;
    }
}
