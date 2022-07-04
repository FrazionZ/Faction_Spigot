package net.minecraft.server.frazionz.blocks;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.DamageSource;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EnumParticle;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.IBlockData;
import net.minecraft.server.Material;
import net.minecraft.server.MaterialMapColor;
import net.minecraft.server.SoundCategory;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.World;
import net.minecraft.server.WorldServer;

public class BlockWitherBlock extends Block {

    public BlockWitherBlock() {
        super(Material.STONE);
        this.a(CreativeModeTab.b);
        this.a(0.2F);
        this.a(true);
    }

    public MaterialMapColor c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return MaterialMapColor.L;
    }

    public void stepOn(World world, BlockPosition blockposition, Entity entity)
    {
        if (entity instanceof EntityLiving) {
            org.bukkit.craftbukkit.event.CraftEventFactory.blockDamage = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()); // CraftBukkit
            entity.damageEntity(DamageSource.WITHER, 1.5F);
            org.bukkit.craftbukkit.event.CraftEventFactory.blockDamage = null; // CraftBukkit
        }

        super.stepOn(world, blockposition, entity);
    }

    public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
        BlockPosition blockposition1 = blockposition.up();
        IBlockData iblockdata1 = world.getType(blockposition1);

        if (iblockdata1.getBlock() == Blocks.WATER || iblockdata1.getBlock() == Blocks.FLOWING_WATER) {
            world.setAir(blockposition1);
            world.a((EntityHuman) null, blockposition, SoundEffects.bN, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
            if (world instanceof WorldServer) {
                ((WorldServer) world).a(EnumParticle.SMOKE_LARGE, (double) blockposition1.getX() + 0.5D, (double) blockposition1.getY() + 0.25D, (double) blockposition1.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D, new int[0]);
            }
        }

    }
}
