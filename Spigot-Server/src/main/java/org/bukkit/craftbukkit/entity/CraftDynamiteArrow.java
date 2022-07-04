package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.DynamiteArrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.SpectralArrow;

import net.minecraft.server.EntityDynamiteArrow;
import net.minecraft.server.EntitySpectralArrow;

public class CraftDynamiteArrow extends CraftArrow implements DynamiteArrow {

    public CraftDynamiteArrow(CraftServer server, EntityDynamiteArrow entity) {
        super(server, entity);
    }

    @Override
    public EntityDynamiteArrow getHandle() {
        return (EntityDynamiteArrow) entity;
    }

    @Override
    public String toString() {
        return "CraftDynamiteArrow";
    }

    @Override
    public EntityType getType() {
        return EntityType.DYNAMITE_ARROW;
    }

}
