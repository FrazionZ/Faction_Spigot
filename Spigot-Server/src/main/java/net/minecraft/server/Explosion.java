package net.minecraft.server;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.server.frazionz.blocks.interfaces.FzExplosionChance;
import org.bukkit.Location;
// CraftBukkit start
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.event.block.BlockExplodeEvent;
// CraftBukkit end
import org.bukkit.event.entity.EntityExplodeEvent;
import fz.frazionz.block.ExplosiveType;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Explosion {

    private final boolean a;
    private final boolean b;
    private final Random c = new Random();
    private final World world;
    private final double posX;
    private final double posY;
    private final double posZ;
    public final Entity source;
    private final float size;
    private final List<BlockPosition> blocks = Lists.newArrayList();
    private final Map<EntityHuman, Vec3D> k = Maps.newHashMap();
    public boolean wasCanceled = false; // CraftBukkit - add field

    public Explosion(World world, Entity entity, double d0, double d1, double d2, float f, boolean flag, boolean flag1) {
        this.world = world;
        this.source = entity;
        this.size = (float) Math.max(f, 0.0); // CraftBukkit - clamp bad values
        this.posX = d0;
        this.posY = d1;
        this.posZ = d2;
        this.a = flag;
        this.b = flag1;
    }
    
    public void doExplosionA() {
        // CraftBukkit start
        if (this.size < 0.1F) {
            return;
        }
        // CraftBukkit end
        HashSet hashset = Sets.newHashSet();
        boolean flag = true;

        int i;
        int j;

        for (int k = 0; k < 16; ++k) {
            for (i = 0; i < 16; ++i) {
                for (j = 0; j < 16; ++j) {
                    if (k == 0 || k == 15 || i == 0 || i == 15 || j == 0 || j == 15) {
                        double d0 = (double) ((float) k / 15.0F * 2.0F - 1.0F);
                        double d1 = (double) ((float) i / 15.0F * 2.0F - 1.0F);
                        double d2 = (double) ((float) j / 15.0F * 2.0F - 1.0F);
                        double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

                        d0 /= d3;
                        d1 /= d3;
                        d2 /= d3;
                        float f = this.size * (0.7F + this.world.random.nextFloat() * 0.6F);
                        double d4 = this.posX;
                        double d5 = this.posY;
                        double d6 = this.posZ;

                        for (float f1 = 0.3F; f > 0.0F; f -= 0.22500001F) {
                            BlockPosition blockposition = new BlockPosition(d4, d5, d6);
                            IBlockData iblockdata = this.world.getType(blockposition);

                            if (iblockdata.getMaterial() != Material.AIR) {
                                float f2 = this.source != null ? this.source.a(this, this.world, blockposition, iblockdata) : iblockdata.getBlock().a((Entity) null);

                                f -= (f2 + 0.3F) * 0.3F;
                            }

                            if (/*f > 0.0F &&*/ (this.source == null || this.source.a(this, this.world, blockposition, iblockdata, f)) && blockposition.getY() < 256 && blockposition.getY() >= 0) { // CraftBukkit - don't wrap explosions
                                hashset.add(blockposition);
                            }

                            d4 += d0 * 0.30000001192092896D;
                            d5 += d1 * 0.30000001192092896D;
                            d6 += d2 * 0.30000001192092896D;
                        }
                    }
                }
            }
        }

        this.blocks.addAll(hashset);
        float f3 = this.size * 2.0F;

        i = MathHelper.floor(this.posX - (double) f3 - 1.0D);
        j = MathHelper.floor(this.posX + (double) f3 + 1.0D);
        int l = MathHelper.floor(this.posY - (double) f3 - 1.0D);
        int i1 = MathHelper.floor(this.posY + (double) f3 + 1.0D);
        int j1 = MathHelper.floor(this.posZ - (double) f3 - 1.0D);
        int k1 = MathHelper.floor(this.posZ + (double) f3 + 1.0D);
        List list = this.world.getEntities(this.source, new AxisAlignedBB((double) i, (double) l, (double) j1, (double) j, (double) i1, (double) k1));
        Vec3D vec3d = new Vec3D(this.posX, this.posY, this.posZ);

        for (int l1 = 0; l1 < list.size(); ++l1) {
            Entity entity = (Entity) list.get(l1);

            if (!entity.bB()) {
                double d7 = entity.e(this.posX, this.posY, this.posZ) / (double) f3;

                if (d7 <= 1.0D) {
                    double d8 = entity.locX - this.posX;
                    double d9 = entity.locY + (double) entity.getHeadHeight() - this.posY;
                    double d10 = entity.locZ - this.posZ;
                    double d11 = (double) MathHelper.sqrt(d8 * d8 + d9 * d9 + d10 * d10);

                    if (d11 != 0.0D) {
                        d8 /= d11;
                        d9 /= d11;
                        d10 /= d11;
                        double d12 = (double) this.world.a(vec3d, entity.getBoundingBox());
                        double d13 = (1.0D - d7) * d12;

                        // CraftBukkit start
                        // entity.damageEntity(DamageSource.explosion(this), (float) ((int) ((d13 * d13 + d13) / 2.0D * 7.0D * (double) f3 + 1.0D)));
                        CraftEventFactory.entityDamage = source;
                        entity.forceExplosionKnockback = false;
                        boolean wasDamaged = entity.damageEntity(DamageSource.explosion(this), (float) ((int) ((d13 * d13 + d13) / 2.0D * 7.0D * (double) f3 + 1.0D)));
                        CraftEventFactory.entityDamage = null;
                        if (!wasDamaged && !(entity instanceof EntityTNTPrimed || entity instanceof EntityZTNTPrimed || entity instanceof EntityFallingBlock) && !entity.forceExplosionKnockback) {
                            continue;
                        }
                        // CraftBukkit end
                        double d14 = d13;

                        if (entity instanceof EntityLiving) {
                            d14 = EnchantmentProtection.a((EntityLiving) entity, d13);
                        }

                        entity.motX += d8 * d14;
                        entity.motY += d9 * d14;
                        entity.motZ += d10 * d14;
                        if (entity instanceof EntityHuman) {
                            EntityHuman entityhuman = (EntityHuman) entity;

                            if (!entityhuman.isSpectator() && (!entityhuman.z() || !entityhuman.abilities.isFlying)) {
                                this.k.put(entityhuman, new Vec3D(d8 * d13, d9 * d13, d10 * d13));
                            }
                        }
                    }
                }
            }
        }
    }

    public void doExplosionB() {
        this.doExplosionB(ExplosiveType.TNT);
    }
    public void doExplosionB(ExplosiveType type) {
        this.world.a((EntityHuman) null, this.posX, this.posY, this.posZ, SoundEffects.bV, SoundCategory.BLOCKS, 4.0F, (1.0F + (this.world.random.nextFloat() - this.world.random.nextFloat()) * 0.2F) * 0.7F);
        /*if (this.size >= 2.0F && this.b) {
            //this.world.addParticle(EnumParticle.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
        } else {
            //this.world.addParticle(EnumParticle.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
        }*/

        Iterator iterator;
        BlockPosition blockposition;

        if (this.b) {
            // CraftBukkit start
            org.bukkit.World bworld = this.world.getWorld();
            org.bukkit.entity.Entity explode = this.source == null ? null : this.source.getBukkitEntity();
            Location location = new Location(bworld, this.posX, this.posY, this.posZ);

            List<org.bukkit.block.Block> blockList = Lists.newArrayList();
            for (int i1 = this.blocks.size() - 1; i1 >= 0; i1--) {
                BlockPosition cpos = (BlockPosition) this.blocks.get(i1);
                org.bukkit.block.Block bblock = bworld.getBlockAt(cpos.getX(), cpos.getY(), cpos.getZ());
                if (bblock.getType() != org.bukkit.Material.AIR) {
                    blockList.add(bblock);
                }
            }

            boolean cancelled;
            List<org.bukkit.block.Block> bukkitBlocks;
            float yield;

            if (explode != null) {
                EntityExplodeEvent event = new EntityExplodeEvent(explode, location, blockList, 1.0F / this.size);
                this.world.getServer().getPluginManager().callEvent(event);
                cancelled = event.isCancelled();
                bukkitBlocks = event.blockList();
                yield = event.getYield();
            } else {
                BlockExplodeEvent event = new BlockExplodeEvent(location.getBlock(), blockList, 1.0F / this.size);
                this.world.getServer().getPluginManager().callEvent(event);
                cancelled = event.isCancelled();
                bukkitBlocks = event.blockList();
                yield = event.getYield();
            }

            this.blocks.clear();

            for (org.bukkit.block.Block bblock : bukkitBlocks) {
                BlockPosition coords = new BlockPosition(bblock.getX(), bblock.getY(), bblock.getZ());
                blocks.add(coords);
            }

            if (cancelled) {
                this.wasCanceled = true;
                return;
            }
            // CraftBukkit end
            iterator = this.blocks.iterator();

            for (BlockPosition blockpos : this.blocks)
            {

                float randomize = new Random().nextFloat();

                IBlockData iblockdata = this.world.getType(blockpos);
                Block block = iblockdata.getBlock();

                if (iblockdata.getMaterial() != Material.AIR && iblockdata.getBlock() != Blocks.BEDROCK)
                {
                    if(block instanceof FzExplosionChance) {
                        if(randomize <= ((FzExplosionChance) block).getExplosionChance(type)) {
                            doExplosionC(block, blockpos);
                        }
                    }
                    else {
                        if (block.a(this)) {
                            block.dropNaturally(this.world, blockpos, iblockdata, 1.0F / this.size, 0);
                        }
                        this.world.setTypeAndData(blockpos, Blocks.AIR.getBlockData(), 3);
                        block.wasExploded(this.world, blockpos, this);
                    }
                }
            }
        }

        if (this.a) {
            iterator = this.blocks.iterator();

            while (iterator.hasNext()) {
                blockposition = (BlockPosition) iterator.next();
                if (this.world.getType(blockposition).getMaterial() == Material.AIR && this.world.getType(blockposition.down()).b() && this.c.nextInt(3) == 0) {
                    // CraftBukkit start - Ignition by explosion
                    if (!org.bukkit.craftbukkit.event.CraftEventFactory.callBlockIgniteEvent(this.world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), this).isCancelled()) {
                        this.world.setTypeUpdate(blockposition, Blocks.FIRE.getBlockData());
                    }
                    // CraftBukkit end
                }
            }
        }

    }
    
    public void doExplosionC(Block block, BlockPosition blockposition) {
        block.dropNaturally(this.world, blockposition, this.world.getType(blockposition), 1.0F / this.size, 0);
        this.world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
        block.wasExploded(this.world, blockposition, this);
    }

    public Map<EntityHuman, Vec3D> b() {
        return this.k;
    }

    @Nullable
    public EntityLiving getSource() {
        // CraftBukkit start - obtain Fireball shooter for explosion tracking
    	
    	if(source == null)
    		return null;
    	
    	if(source instanceof EntityProjectile)
    		return ((EntityProjectile) this.source).shooter;
    	
    	if(source instanceof EntityMinecartTNT)
    		return ((EntityMinecartTNT) this.source).placer;
    	
    	if(source instanceof EntityZTNTPrimed)
    		return ((EntityZTNTPrimed) this.source).getSource();
    	
    	if(source instanceof EntityTNTPrimed)
    		return ((EntityTNTPrimed) this.source).getSource();
    	
    	if(source instanceof EntityFireball)
    		return ((EntityFireball) this.source).shooter;
    	
    	if(source instanceof EntityLiving)
    		return (EntityLiving) this.source;
    	
    	return null;
        // CraftBukkit end
    }

    public void clearBlocks() {
        this.blocks.clear();
    }

    public List<BlockPosition> getBlocks() {
        return this.blocks;
    }
}
