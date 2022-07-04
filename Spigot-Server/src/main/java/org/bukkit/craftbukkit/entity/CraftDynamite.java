package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Dynamite;
import org.bukkit.entity.EntityType;

import net.minecraft.server.EntityDynamite;

public class CraftDynamite extends CraftProjectile implements Dynamite {
    public CraftDynamite(CraftServer server, EntityDynamite entity) {
        super(server, entity);
    }

    @Override
    public EntityDynamite getHandle() {
        return (EntityDynamite) entity;
    }

    @Override
    public String toString() {
        return "EntityDynamite";
    }

    public EntityType getType() {
        return EntityType.DYNAMITE;
    }
}
