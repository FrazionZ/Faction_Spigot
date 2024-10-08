package org.bukkit.enchantments;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Represents the applicable target for a {@link Enchantment}
 */
public enum EnchantmentTarget {
    /**
     * Allows the Enchantment to be placed on all items
     */
    ALL {
        @Override
        public boolean includes(Material item) {
            return true;
        }
    },

    /**
     * Allows the Enchantment to be placed on armor
     */
    ARMOR {
        @Override
        public boolean includes(Material item) {
            return ARMOR_FEET.includes(item)
                || ARMOR_LEGS.includes(item)
                || ARMOR_HEAD.includes(item)
                || ARMOR_TORSO.includes(item);
        }
    },

    /**
     * Allows the Enchantment to be placed on feet slot armor
     */
    ARMOR_FEET {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_BOOTS)
                || item.equals(Material.CHAINMAIL_BOOTS)
                || item.equals(Material.IRON_BOOTS)
                || item.equals(Material.DIAMOND_BOOTS)
                || item.equals(Material.GOLD_BOOTS);
        }
    },

    /**
     * Allows the Enchantment to be placed on leg slot armor
     */
    ARMOR_LEGS {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_LEGGINGS)
                || item.equals(Material.CHAINMAIL_LEGGINGS)
                || item.equals(Material.IRON_LEGGINGS)
                || item.equals(Material.DIAMOND_LEGGINGS)
                || item.equals(Material.GOLD_LEGGINGS);
        }
    },

    /**
     * Allows the Enchantment to be placed on torso slot armor
     */
    ARMOR_TORSO {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_CHESTPLATE)
                || item.equals(Material.CHAINMAIL_CHESTPLATE)
                || item.equals(Material.IRON_CHESTPLATE)
                || item.equals(Material.DIAMOND_CHESTPLATE)
                || item.equals(Material.GOLD_CHESTPLATE);
        }
    },

    /**
     * Allows the Enchantment to be placed on head slot armor
     */
    ARMOR_HEAD {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.LEATHER_HELMET)
                || item.equals(Material.CHAINMAIL_HELMET)
                || item.equals(Material.DIAMOND_HELMET)
                || item.equals(Material.IRON_HELMET)
                || item.equals(Material.GOLD_HELMET);
        }
    },

    /**
     * Allows the Enchantment to be placed on weapons (swords)
     */
    WEAPON {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.WOOD_SWORD)
                || item.equals(Material.STONE_SWORD)
                || item.equals(Material.IRON_SWORD)
                || item.equals(Material.DIAMOND_SWORD)
                || item.equals(Material.GOLD_SWORD)
	            || item.equals(Material.YELLITE_SWORD)
	            || item.equals(Material.ONYX_SWORD)
	            || item.equals(Material.BAUXITE_SWORD)
	            || item.equals(Material.FRAZION_SWORD);
        }
    },

    /**
     * Allows the Enchantment to be placed on tools (spades, pickaxe, hoes,
     * axes)
     */
    TOOL {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.WOOD_SPADE)
                || item.equals(Material.STONE_SPADE)
                || item.equals(Material.IRON_SPADE)
                || item.equals(Material.DIAMOND_SPADE)
                || item.equals(Material.GOLD_SPADE)
                || item.equals(Material.WOOD_PICKAXE)
                || item.equals(Material.STONE_PICKAXE)
                || item.equals(Material.IRON_PICKAXE)
                || item.equals(Material.DIAMOND_PICKAXE)
                || item.equals(Material.GOLD_PICKAXE)
                || item.equals(Material.WOOD_HOE)
                || item.equals(Material.STONE_HOE)
                || item.equals(Material.IRON_HOE)
                || item.equals(Material.DIAMOND_HOE)
                || item.equals(Material.GOLD_HOE)
                || item.equals(Material.WOOD_AXE)
                || item.equals(Material.STONE_AXE)
                || item.equals(Material.IRON_AXE)
                || item.equals(Material.DIAMOND_AXE)
                || item.equals(Material.GOLD_AXE)
                || item.equals(Material.SHEARS)
                || item.equals(Material.FLINT_AND_STEEL);
        }
    },

    /**
     * Allows the Enchantment to be placed on bows.
     */
    BOW {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.BOW);
        }
    },

    /**
     * Allows the Enchantment to be placed on fishing rods.
     */
    FISHING_ROD {
        @Override
        public boolean includes(Material item) {
            return item.equals(Material.FISHING_ROD);
        }
    },

    /**
     * Allows the enchantment to be placed on items with durability.
     */
    BREAKABLE {
        @Override
        public boolean includes(Material item) {
            return item.getMaxDurability() > 0 && item.getMaxStackSize() == 1;
        }
    },

    /**
     * Allows the enchantment to be placed on wearable items.
     */
    WEARABLE {
        @Override
        public boolean includes(Material item) {
            return ARMOR.includes(item)
                    || item.equals(Material.ELYTRA)
                    || item.equals(Material.PUMPKIN)
                    || item.equals(Material.JACK_O_LANTERN)
                    || item.equals(Material.SKULL_ITEM);
        }
    };

    /**
     * Check whether this target includes the specified item.
     *
     * @param item The item to check
     * @return True if the target includes the item
     */
    public abstract boolean includes(Material item);

    /**
     * Check whether this target includes the specified item.
     *
     * @param item The item to check
     * @return True if the target includes the item
     */
    public boolean includes(ItemStack item) {
        return includes(item.getType());
    }
}
