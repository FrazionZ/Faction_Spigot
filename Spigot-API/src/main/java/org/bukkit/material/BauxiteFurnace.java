package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * Represents a furnace.
 */
public class BauxiteFurnace extends FurnaceAndDispenser {

    public BauxiteFurnace() {
        super(Material.BAUXITE_FURNACE);
    }

    /**
     * Instantiate a furnace facing in a particular direction.
     *
     * @param direction the direction the furnace's "opening" is facing
     */
    public BauxiteFurnace(BlockFace direction) {
        this();
        setFacingDirection(direction);
    }

    /**
     * @param type the raw type id
     * @deprecated Magic value
     */
    @Deprecated
    public BauxiteFurnace(final int type) {
        super(type);
    }

    public BauxiteFurnace(final Material type) {
        super(type);
    }

    /**
     * @param type the raw type id
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public BauxiteFurnace(final int type, final byte data) {
        super(type, data);
    }

    /**
     * @param type the type
     * @param data the raw data value
     * @deprecated Magic value
     */
    @Deprecated
    public BauxiteFurnace(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public BauxiteFurnace clone() {
        return (BauxiteFurnace) super.clone();
    }
}
