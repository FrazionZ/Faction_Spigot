package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a chest
 */
public class YelliteChest extends DirectionalContainer {

    public YelliteChest() {
        super(Material.YELLITE_CHEST);
    }

    /**
     * Instantiate a chest facing in a particular direction.
     *
     * @param direction the direction the chest's lit opens towards
     */
    public YelliteChest(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    /**
     * @param type the raw type id
     * @deprecated Magic value
     */
    @Deprecated
    public YelliteChest(final int type) {
        super(type);
    }

    public YelliteChest(final Material type) {
        super(type);
    }

    /**
     * @param type the raw type id
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public YelliteChest(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public YelliteChest(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public YelliteChest clone() {
        return (YelliteChest) super.clone();
    }
}
