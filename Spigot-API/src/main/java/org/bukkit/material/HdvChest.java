package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a chest
 */
public class HdvChest extends DirectionalContainer {

    public HdvChest() {
        super(Material.HDV_CHEST);
    }

    /**
     * Instantiate a chest facing in a particular direction.
     *
     * @param direction the direction the chest's lit opens towards
     */
    public HdvChest(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    /**
     * @param type the raw type id
     * @deprecated Magic value
     */
    @Deprecated
    public HdvChest(final int type) {
        super(type);
    }

    public HdvChest(final Material type) {
        super(type);
    }

    /**
     * @param type the raw type id
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public HdvChest(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public HdvChest(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public HdvChest clone() {
        return (HdvChest) super.clone();
    }
}
