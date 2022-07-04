package org.bukkit.craftbukkit.inventory;

import net.minecraft.server.EntityInsentient;
import net.minecraft.server.EnumItemSlot;

import org.bukkit.craftbukkit.entity.CraftLivingEntity;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class CraftEntityEquipment implements EntityEquipment {

    private final CraftLivingEntity entity;

    public CraftEntityEquipment(CraftLivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public ItemStack getItemInMainHand() {
        return getEquipment(EnumItemSlot.MAINHAND);
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        setEquipment(EnumItemSlot.MAINHAND, item);
    }

    @Override
    public ItemStack getItemInOffHand() {
        return getEquipment(EnumItemSlot.OFFHAND);
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
        setEquipment(EnumItemSlot.OFFHAND, item);
    }

    @Override
    public ItemStack getItemInHand() {
        return getItemInMainHand();
    }

    @Override
    public void setItemInHand(ItemStack stack) {
        setItemInMainHand(stack);
    }

    public ItemStack getHelmet() {
        return getEquipment(EnumItemSlot.HEAD);
    }

    public void setHelmet(ItemStack helmet) {
        setEquipment(EnumItemSlot.HEAD, helmet);
    }

    public ItemStack getChestplate() {
        return getEquipment(EnumItemSlot.CHEST);
    }

    public void setChestplate(ItemStack chestplate) {
        setEquipment(EnumItemSlot.CHEST, chestplate);
    }

    public ItemStack getLeggings() {
        return getEquipment(EnumItemSlot.LEGS);
    }

    public void setLeggings(ItemStack leggings) {
        setEquipment(EnumItemSlot.LEGS, leggings);
    }

    public ItemStack getBoots() {
        return getEquipment(EnumItemSlot.FEET);
    }

    public void setBoots(ItemStack boots) {
        setEquipment(EnumItemSlot.FEET, boots);
    }
    
    public ItemStack getTrophy1() {
        return getEquipment(EnumItemSlot.TROPHY_1);
    }

    public void setTrophy1(ItemStack helmet) {
        setEquipment(EnumItemSlot.TROPHY_1, helmet);
    }

    public ItemStack getTrophy2() {
        return getEquipment(EnumItemSlot.TROPHY_2);
    }

    public void setTrophy2(ItemStack chestplate) {
        setEquipment(EnumItemSlot.TROPHY_2, chestplate);
    }

    public ItemStack getTrophy3() {
        return getEquipment(EnumItemSlot.TROPHY_3);
    }

    public void setTrophy3(ItemStack leggings) {
        setEquipment(EnumItemSlot.TROPHY_3, leggings);
    }

    public ItemStack[] getArmorContents() {
        ItemStack[] armor = new ItemStack[]{
                getEquipment(EnumItemSlot.FEET),
                getEquipment(EnumItemSlot.LEGS),
                getEquipment(EnumItemSlot.CHEST),
                getEquipment(EnumItemSlot.HEAD),
        };
        return armor;
    }

    public void setArmorContents(ItemStack[] items) {
        setEquipment(EnumItemSlot.FEET, items.length >= 1 ? items[0] : null);
        setEquipment(EnumItemSlot.LEGS, items.length >= 2 ? items[1] : null);
        setEquipment(EnumItemSlot.CHEST, items.length >= 3 ? items[2] : null);
        setEquipment(EnumItemSlot.HEAD, items.length >= 4 ? items[3] : null);
    }
    
    public ItemStack[] getTrophyContents() {
        ItemStack[] trophy = new ItemStack[]{
                getEquipment(EnumItemSlot.TROPHY_1),
                getEquipment(EnumItemSlot.TROPHY_2),
                getEquipment(EnumItemSlot.TROPHY_3),
        };
        return trophy;
    }
    
    public void setTrophyContents(ItemStack[] items) {
        setEquipment(EnumItemSlot.TROPHY_1, items.length >= 1 ? items[0] : null);
        setEquipment(EnumItemSlot.TROPHY_2, items.length >= 2 ? items[1] : null);
        setEquipment(EnumItemSlot.TROPHY_3, items.length >= 3 ? items[2] : null);
    }

    private ItemStack getEquipment(EnumItemSlot slot) {
        return CraftItemStack.asBukkitCopy(entity.getHandle().getEquipment(slot));
    }

    private void setEquipment(EnumItemSlot slot, ItemStack stack) {
        entity.getHandle().setSlot(slot, CraftItemStack.asNMSCopy(stack));
    }

    public void clear() {
        for (EnumItemSlot slot : EnumItemSlot.values()) {
            setEquipment(slot, null);
        }
    }

    public Entity getHolder() {
        return entity;
    }

    @Override
    public float getItemInHandDropChance() {
        return getItemInMainHandDropChance();
    }

    @Override
    public void setItemInHandDropChance(float chance) {
        setItemInMainHandDropChance(chance);
    }

    @Override
    public float getItemInMainHandDropChance() {
       return getDropChance(EnumItemSlot.MAINHAND);
    }

    @Override
    public void setItemInMainHandDropChance(float chance) {
        setDropChance(EnumItemSlot.MAINHAND, chance);
    }

    @Override
    public float getItemInOffHandDropChance() {
        return getDropChance(EnumItemSlot.OFFHAND);
    }

    @Override
    public void setItemInOffHandDropChance(float chance) {
        setDropChance(EnumItemSlot.OFFHAND, chance);
    }

    public float getHelmetDropChance() {
        return getDropChance(EnumItemSlot.HEAD);
    }

    public void setHelmetDropChance(float chance) {
        setDropChance(EnumItemSlot.HEAD, chance);
    }

    public float getChestplateDropChance() {
        return getDropChance(EnumItemSlot.CHEST);
    }

    public void setChestplateDropChance(float chance) {
        setDropChance(EnumItemSlot.CHEST, chance);
    }

    public float getLeggingsDropChance() {
        return getDropChance(EnumItemSlot.LEGS);
    }

    public void setLeggingsDropChance(float chance) {
        setDropChance(EnumItemSlot.LEGS, chance);
    }

    public float getBootsDropChance() {
        return getDropChance(EnumItemSlot.FEET);
    }

    public void setBootsDropChance(float chance) {
        setDropChance(EnumItemSlot.FEET, chance);
    }

    private void setDropChance(EnumItemSlot slot, float chance) {
        if (slot == EnumItemSlot.MAINHAND || slot == EnumItemSlot.OFFHAND) {
            ((EntityInsentient) entity.getHandle()).dropChanceHand[slot.b()] = chance - 0.1F;
        } else {
            ((EntityInsentient) entity.getHandle()).dropChanceArmor[slot.b()] = chance - 0.1F;
        }
    }

    private float getDropChance(EnumItemSlot slot) {
        if (slot == EnumItemSlot.MAINHAND || slot == EnumItemSlot.OFFHAND) {
            return ((EntityInsentient) entity.getHandle()).dropChanceHand[slot.b()] + 0.1F;
        } else {
            return ((EntityInsentient) entity.getHandle()).dropChanceArmor[slot.b()] + 0.1F;
        }
    }
}
