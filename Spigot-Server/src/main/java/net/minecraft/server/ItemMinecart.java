package net.minecraft.server;

// CraftBukkit start
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.block.BlockDispenseEvent;
// CraftBukkit end

public class ItemMinecart extends Item {

    private static final IDispenseBehavior a = new DispenseBehaviorItem() {
        private final DispenseBehaviorItem b = new DispenseBehaviorItem();

        public ItemStack b(ISourceBlock isourceblock, ItemStack itemstack) {
            EnumDirection enumdirection = (EnumDirection) isourceblock.e().get(BlockDispenser.FACING);
            World world = isourceblock.getWorld();
            double d0 = isourceblock.getX() + (double) enumdirection.getAdjacentX() * 1.125D;
            double d1 = Math.floor(isourceblock.getY()) + (double) enumdirection.getAdjacentY();
            double d2 = isourceblock.getZ() + (double) enumdirection.getAdjacentZ() * 1.125D;
            BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
            IBlockData iblockdata = world.getType(blockposition);
            BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.getBlock() instanceof BlockMinecartTrackAbstract ? (BlockMinecartTrackAbstract.EnumTrackPosition) iblockdata.get(((BlockMinecartTrackAbstract) iblockdata.getBlock()).g()) : BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
            double d3;

            if (BlockMinecartTrackAbstract.i(iblockdata)) {
                if (blockminecarttrackabstract_enumtrackposition.c()) {
                    d3 = 0.6D;
                } else {
                    d3 = 0.1D;
                }
            } else {
                if (iblockdata.getMaterial() != Material.AIR || !BlockMinecartTrackAbstract.i(world.getType(blockposition.down()))) {
                    return this.b.a(isourceblock, itemstack);
                }

                IBlockData iblockdata1 = world.getType(blockposition.down());
                BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition1 = iblockdata1.getBlock() instanceof BlockMinecartTrackAbstract ? (BlockMinecartTrackAbstract.EnumTrackPosition) iblockdata1.get(((BlockMinecartTrackAbstract) iblockdata1.getBlock()).g()) : BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;

                if (enumdirection != EnumDirection.DOWN && blockminecarttrackabstract_enumtrackposition1.c()) {
                    d3 = -0.4D;
                } else {
                    d3 = -0.9D;
                }
            }

            // CraftBukkit start
            // EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, d0, d1 + d3, d2, ((ItemMinecart) itemstack.getItem()).b);
            ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
            org.bukkit.block.Block block2 = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
            CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);

            BlockDispenseEvent event = new BlockDispenseEvent(block2, craftItem.clone(), new org.bukkit.util.Vector(d0, d1 + d3, d2));
            if (!BlockDispenser.eventFired) {
                world.getServer().getPluginManager().callEvent(event);
            }

            if (event.isCancelled()) {
                itemstack.add(1);
                return itemstack;
            }

            if (!event.getItem().equals(craftItem)) {
                itemstack.add(1);
                // Chain to handler for new item
                ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
                IDispenseBehavior idispensebehavior = (IDispenseBehavior) BlockDispenser.REGISTRY.get(eventStack.getItem());
                if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
                    idispensebehavior.a(isourceblock, eventStack);
                    return itemstack;
                }
            }

            itemstack1 = CraftItemStack.asNMSCopy(event.getItem());
            EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), ((ItemMinecart) itemstack1.getItem()).b, null);

            if (itemstack.hasName()) {
                entityminecartabstract.setCustomName(itemstack.getName());
            }

            if (!world.addEntity(entityminecartabstract)) itemstack.add(1);
            // itemstack.subtract(1); // CraftBukkit - handled during event processing
            // CraftBukkit end
            return itemstack;
        }

        protected void a(ISourceBlock isourceblock) {
            isourceblock.getWorld().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
        }
    };
    private final EntityMinecartAbstract.EnumMinecartType b;

    public ItemMinecart(EntityMinecartAbstract.EnumMinecartType entityminecartabstract_enumminecarttype) {
        this.maxStackSize = 1;
        this.b = entityminecartabstract_enumminecarttype;
        this.b(CreativeModeTab.e);
        BlockDispenser.REGISTRY.a(this, ItemMinecart.a);
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        IBlockData iblockdata = world.getType(blockposition);

        if (!BlockMinecartTrackAbstract.i(iblockdata)) {
            return EnumInteractionResult.FAIL;
        } else {
            ItemStack itemstack = entityhuman.b(enumhand);

            if (!world.isClientSide) {
                BlockMinecartTrackAbstract.EnumTrackPosition blockminecarttrackabstract_enumtrackposition = iblockdata.getBlock() instanceof BlockMinecartTrackAbstract ? (BlockMinecartTrackAbstract.EnumTrackPosition) iblockdata.get(((BlockMinecartTrackAbstract) iblockdata.getBlock()).g()) : BlockMinecartTrackAbstract.EnumTrackPosition.NORTH_SOUTH;
                double d0 = 0.0D;

                if (blockminecarttrackabstract_enumtrackposition.c()) {
                    d0 = 0.5D;
                }

                EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, (double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.0625D + d0, (double) blockposition.getZ() + 0.5D, this.b, entityhuman);

                if (itemstack.hasName()) {
                    entityminecartabstract.setCustomName(itemstack.getName());
                }

                if (!world.addEntity(entityminecartabstract)) return EnumInteractionResult.PASS; // CraftBukkit
            }

            itemstack.subtract(1);
            return EnumInteractionResult.SUCCESS;
        }
    }
}
