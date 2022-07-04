package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vindicator;

import net.minecraft.server.EntityIllager;

public class CraftNewIllager extends CraftIllager implements Vindicator {

    public CraftNewIllager(CraftServer server, EntityIllager entity) {
        super(server, entity);
    }

    @Override
    public EntityIllager getHandle() {
        return (EntityIllager) super.getHandle();
    }

    @Override
    public String toString() {
        return "EntityIllager";
    }

    @Override
    public EntityType getType() {
        return EntityType.COSMICKER;
    }
}
