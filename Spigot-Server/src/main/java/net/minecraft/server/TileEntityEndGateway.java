package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// CraftBukkit start
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.player.PlayerTeleportEvent;
// CraftBukkit end

public class TileEntityEndGateway extends TileEntityEnderPortal implements ITickable {

    private static final Logger a = LogManager.getLogger();
    private long f;
    private int g;
    public BlockPosition exitPortal;
    public boolean exactTeleport;

    public TileEntityEndGateway() {}

    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        nbttagcompound.setLong("Age", this.f);
        if (this.exitPortal != null) {
            nbttagcompound.set("ExitPortal", GameProfileSerializer.a(this.exitPortal));
        }

        if (this.exactTeleport) {
            nbttagcompound.setBoolean("ExactTeleport", this.exactTeleport);
        }

        return nbttagcompound;
    }

    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.f = nbttagcompound.getLong("Age");
        if (nbttagcompound.hasKeyOfType("ExitPortal", 10)) {
            this.exitPortal = GameProfileSerializer.c(nbttagcompound.getCompound("ExitPortal"));
        }

        this.exactTeleport = nbttagcompound.getBoolean("ExactTeleport");
    }

    public void e() {
        boolean flag = this.a();
        boolean flag1 = this.f();

        ++this.f;
        if (flag1) {
            --this.g;
        } else if (!this.world.isClientSide) {
            List list = this.world.a(Entity.class, new AxisAlignedBB(this.getPosition()));

            if (!list.isEmpty()) {
                this.a((Entity) list.get(0));
            }

            if (this.f % 2400L == 0L) {
                this.h();
            }
        }

        if (flag != this.a() || flag1 != this.f()) {
            this.update();
        }

    }

    public boolean a() {
        return this.f < 200L;
    }

    public boolean f() {
        return this.g > 0;
    }

    @Nullable
    public PacketPlayOutTileEntityData getUpdatePacket() {
        return new PacketPlayOutTileEntityData(this.position, 8, this.d());
    }

    public NBTTagCompound d() {
        return this.save(new NBTTagCompound());
    }

    public void h() {
        if (!this.world.isClientSide) {
            this.g = 40;
            this.world.playBlockAction(this.getPosition(), this.getBlock(), 1, 0);
            this.update();
        }

    }

    public boolean c(int i, int j) {
        if (i == 1) {
            this.g = 40;
            return true;
        } else {
            return super.c(i, j);
        }
    }

    public void a(Entity entity) {
        if (!this.world.isClientSide && !this.f()) {
            this.g = 100;
            if (this.exitPortal == null && this.world.worldProvider instanceof WorldProviderTheEnd) {
                this.k();
            }

            if (this.exitPortal != null) {
                BlockPosition blockposition = this.exactTeleport ? this.exitPortal : this.j(); 

                // CraftBukkit start - Fire PlayerTeleportEvent
                if (entity instanceof EntityPlayer)
                {
                    org.bukkit.craftbukkit.entity.CraftPlayer player = (CraftPlayer) entity.getBukkitEntity();
                    org.bukkit.Location location = new Location(world.getWorld(), (double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.5D, (double) blockposition.getZ() + 0.5D);
                    location.setPitch(player.getLocation().getPitch());
                    location.setYaw(player.getLocation().getYaw());

                    PlayerTeleportEvent teleEvent = new PlayerTeleportEvent(player, player.getLocation(), location, PlayerTeleportEvent.TeleportCause.END_GATEWAY);
                    Bukkit.getPluginManager().callEvent(teleEvent);
                    if (teleEvent.isCancelled()) {
                        return;
                    }

                    ((EntityPlayer) entity).playerConnection.teleport(teleEvent.getTo());
                    this.h(); // CraftBukkit - call at end of method
                    return;

                }
                // CraftBukkit end

                entity.enderTeleportTo((double) blockposition.getX() + 0.5D, (double) blockposition.getY() + 0.5D, (double) blockposition.getZ() + 0.5D);
            }

            this.h();
        }
    }

    private BlockPosition j() {
        BlockPosition blockposition = a(this.world, this.exitPortal, 5, false);

        TileEntityEndGateway.a.debug("Best exit position for portal at {} is {}", this.exitPortal, blockposition);
        return blockposition.up();
    }

    private void k() {
        Vec3D vec3d = (new Vec3D((double) this.getPosition().getX(), 0.0D, (double) this.getPosition().getZ())).a();
        Vec3D vec3d1 = vec3d.a(1024.0D);

        int i;

        for (i = 16; a(this.world, vec3d1).g() > 0 && i-- > 0; vec3d1 = vec3d1.e(vec3d.a(-16.0D))) {
            TileEntityEndGateway.a.debug("Skipping backwards past nonempty chunk at {}", vec3d1);
        }

        for (i = 16; a(this.world, vec3d1).g() == 0 && i-- > 0; vec3d1 = vec3d1.e(vec3d.a(16.0D))) {
            TileEntityEndGateway.a.debug("Skipping forward past empty chunk at {}", vec3d1);
        }

        TileEntityEndGateway.a.debug("Found chunk at {}", vec3d1);
        Chunk chunk = a(this.world, vec3d1);

        this.exitPortal = a(chunk);
        if (this.exitPortal == null) {
            this.exitPortal = new BlockPosition(vec3d1.x + 0.5D, 75.0D, vec3d1.z + 0.5D);
            TileEntityEndGateway.a.debug("Failed to find suitable block, settling on {}", this.exitPortal);
            (new WorldGenEndIsland()).generate(this.world, new Random(this.exitPortal.asLong()), this.exitPortal);
        } else {
            TileEntityEndGateway.a.debug("Found block at {}", this.exitPortal);
        }

        this.exitPortal = a(this.world, this.exitPortal, 16, true);
        TileEntityEndGateway.a.debug("Creating portal at {}", this.exitPortal);
        this.exitPortal = this.exitPortal.up(10);
        this.c(this.exitPortal);
        this.update();
    }

    private static BlockPosition a(World world, BlockPosition blockposition, int i, boolean flag) {
        BlockPosition blockposition1 = null;

        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                if (j != 0 || k != 0 || flag) {
                    for (int l = 255; l > (blockposition1 == null ? 0 : blockposition1.getY()); --l) {
                        BlockPosition blockposition2 = new BlockPosition(blockposition.getX() + j, l, blockposition.getZ() + k);
                        IBlockData iblockdata = world.getType(blockposition2);

                        if (iblockdata.k() && (flag || iblockdata.getBlock() != Blocks.BEDROCK)) {
                            blockposition1 = blockposition2;
                            break;
                        }
                    }
                }
            }
        }

        return blockposition1 == null ? blockposition : blockposition1;
    }

    private static Chunk a(World world, Vec3D vec3d) {
        return world.getChunkAt(MathHelper.floor(vec3d.x / 16.0D), MathHelper.floor(vec3d.z / 16.0D));
    }

    @Nullable
    private static BlockPosition a(Chunk chunk) {
        BlockPosition blockposition = new BlockPosition(chunk.locX * 16, 30, chunk.locZ * 16);
        int i = chunk.g() + 16 - 1;
        BlockPosition blockposition1 = new BlockPosition(chunk.locX * 16 + 16 - 1, i, chunk.locZ * 16 + 16 - 1);
        BlockPosition blockposition2 = null;
        double d0 = 0.0D;
        Iterator iterator = BlockPosition.a(blockposition, blockposition1).iterator();

        while (iterator.hasNext()) {
            BlockPosition blockposition3 = (BlockPosition) iterator.next();
            IBlockData iblockdata = chunk.getBlockData(blockposition3);

            if (iblockdata.getBlock() == Blocks.END_STONE && !chunk.getBlockData(blockposition3.up(1)).k() && !chunk.getBlockData(blockposition3.up(2)).k()) {
                double d1 = blockposition3.g(0.0D, 0.0D, 0.0D);

                if (blockposition2 == null || d1 < d0) {
                    blockposition2 = blockposition3;
                    d0 = d1;
                }
            }
        }

        return blockposition2;
    }

    private void c(BlockPosition blockposition) {
        (new WorldGenEndGateway()).generate(this.world, new Random(), blockposition);
        TileEntity tileentity = this.world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityEndGateway) {
            TileEntityEndGateway tileentityendgateway = (TileEntityEndGateway) tileentity;

            tileentityendgateway.exitPortal = new BlockPosition(this.getPosition());
            tileentityendgateway.update();
        } else {
            TileEntityEndGateway.a.warn("Couldn\'t save exit portal at {}", blockposition);
        }

    }

    public void b(BlockPosition blockposition) {
        this.exactTeleport = true;
        this.exitPortal = blockposition;
    }
}
