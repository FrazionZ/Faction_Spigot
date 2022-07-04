package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownBigXp;

import net.minecraft.server.EntityBigXp;

public class CraftBigXp extends CraftProjectile implements ThrownBigXp {
    public CraftBigXp(CraftServer server, EntityBigXp entity) {
        super(server, entity);
    }

    @Override
    public EntityBigXp getHandle() {
        return (EntityBigXp) entity;
    }

    @Override
    public String toString() {
        return "EntityBigXp";
    }

    public EntityType getType() {
        return EntityType.BIG_XP;
    }
}
