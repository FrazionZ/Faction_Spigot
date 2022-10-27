package net.minecraft.server.frazionz.tileentity.impl;

import net.minecraft.server.EntityHuman;

public interface TileMachine {

    public void startMachine(EntityHuman player);

    public boolean canStart();

    public boolean isRunning();

}
