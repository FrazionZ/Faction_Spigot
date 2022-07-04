package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a chest
 */
public class BauxiteChest extends DirectionalContainer {

    public BauxiteChest() {
        super(Material.BAUXITE_CHEST);
    }

    /**
     * Instantiate a chest facing in a particular direction.
     *
     * @param direction the direction the chest's lit opens towards
     */
    public BauxiteChest(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    /**
     * @param type the raw type id
     * @deprecated Magic value
     */
    @Deprecated
    public BauxiteChest(final int type) {
        super(type);
    }

    public BauxiteChest(final Material type) {
        super(type);
    }

    /**
     * @param type the raw type id
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public BauxiteChest(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public BauxiteChest(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public BauxiteChest clone() {
        return (BauxiteChest) super.clone();
    }
}
