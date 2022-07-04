package net.minecraft.server;

public class SlotAmelioratorFuel extends Slot {

    public SlotAmelioratorFuel(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public boolean isAllowed(ItemStack itemstack) {
        return TileEntityAmeliorator.isItemFuel(itemstack);
    }

    public int getMaxStackSize(ItemStack itemstack) {
        return super.getMaxStackSize(itemstack);
    }
}
