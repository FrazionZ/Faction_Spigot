package org.bukkit.craftbukkit.entity;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;

import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityZTNTPrimed;

public class CraftZTNTPrimed extends CraftEntity implements TNTPrimed {

    public CraftZTNTPrimed(CraftServer server, EntityZTNTPrimed entity) {
        super(server, entity);
    }

    public float getYield() {
        return getHandle().yield;
    }

    public boolean isIncendiary() {
        return getHandle().isIncendiary;
    }

    public void setIsIncendiary(boolean isIncendiary) {
        getHandle().isIncendiary = isIncendiary;
    }

    public void setYield(float yield) {
        getHandle().yield = yield;
    }

    public int getFuseTicks() {
        return getHandle().getFuseTicks();
    }

    public void setFuseTicks(int fuseTicks) {
        getHandle().setFuseTicks(fuseTicks);
    }

    @Override
    public EntityZTNTPrimed getHandle() {
        return (EntityZTNTPrimed) entity;
    }

    @Override
    public String toString() {
        return "CraftZTNTPrimed";
    }

    public EntityType getType() {
        return EntityType.PRIMED_TNT;
    }

    public Entity getSource() {
        EntityLiving source = getHandle().getSource();

        return (source != null) ? source.getBukkitEntity() : null;
    }
}
