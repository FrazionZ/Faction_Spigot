package net.minecraft.server.frazionz.blocks;

import net.minecraft.server.Block;
import net.minecraft.server.BlockPosition;
import net.minecraft.server.BlockStateBoolean;
import net.minecraft.server.BlockStateList;
import net.minecraft.server.Blocks;
import net.minecraft.server.CreativeModeTab;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityArrow;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityZTNTPrimed;
import net.minecraft.server.EnumDirection;
import net.minecraft.server.EnumHand;
import net.minecraft.server.Explosion;
import net.minecraft.server.IBlockData;
import net.minecraft.server.IBlockState;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Items;
import net.minecraft.server.Material;
import net.minecraft.server.SoundCategory;
import net.minecraft.server.SoundEffects;
import net.minecraft.server.World;

public class BlockZTnt extends Block {

    public static final BlockStateBoolean EXPLODE = BlockStateBoolean.of("explode");

    public BlockZTnt() {
        super(Material.TNT);
        this.w(this.blockStateList.getBlockData().set(BlockZTnt.EXPLODE, Boolean.valueOf(false)));
        this.a(CreativeModeTab.d);
    }

    public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
        super.onPlace(world, blockposition, iblockdata);
        if (world.isBlockIndirectlyPowered(blockposition)) {
            this.postBreak(world, blockposition, iblockdata.set(BlockZTnt.EXPLODE, Boolean.valueOf(true)));
            world.setAir(blockposition);
        }

    }

    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
        if (world.isBlockIndirectlyPowered(blockposition)) {
            this.postBreak(world, blockposition, iblockdata.set(BlockZTnt.EXPLODE, Boolean.valueOf(true)));
            world.setAir(blockposition);
        }

    }

    public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {
        if (!world.isClientSide) {
            EntityZTNTPrimed entitytntprimed = new EntityZTNTPrimed(world, (double) ((float) blockposition.getX() + 0.5F), (double) blockposition.getY(), (double) ((float) blockposition.getZ() + 0.5F), explosion.getSource());

            entitytntprimed.setFuseTicks((short) (world.random.nextInt(entitytntprimed.getFuseTicks() / 4) + entitytntprimed.getFuseTicks() / 8));
            world.addEntity(entitytntprimed);
        }
    }

    public void postBreak(World world, BlockPosition blockposition, IBlockData iblockdata) {
        this.a(world, blockposition, iblockdata, (EntityLiving) null);
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving) {
        if (!world.isClientSide) {
            if (((Boolean) iblockdata.get(BlockZTnt.EXPLODE)).booleanValue()) {
            	EntityZTNTPrimed entitytntprimed = new EntityZTNTPrimed(world, (double) ((float) blockposition.getX() + 0.5F), (double) blockposition.getY(), (double) ((float) blockposition.getZ() + 0.5F), entityliving);

                world.addEntity(entitytntprimed);
                world.a((EntityHuman) null, entitytntprimed.locX, entitytntprimed.locY, entitytntprimed.locZ, SoundEffects.hW, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

        }
    }

    public boolean interact(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman, EnumHand enumhand, EnumDirection enumdirection, float f, float f1, float f2) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (!itemstack.isEmpty() && (itemstack.getItem() == Items.FLINT_AND_STEEL || itemstack.getItem() == Items.FIRE_CHARGE)) {
            this.a(world, blockposition, iblockdata.set(BlockZTnt.EXPLODE, Boolean.valueOf(true)), (EntityLiving) entityhuman);
            world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 11);
            if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
                itemstack.damage(1, entityhuman);
            } else if (!entityhuman.abilities.canInstantlyBuild) {
                itemstack.subtract(1);
            }

            return true;
        } else {
            return super.interact(world, blockposition, iblockdata, entityhuman, enumhand, enumdirection, f, f1, f2);
        }
    }

    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
        if (!world.isClientSide && entity instanceof EntityArrow) {
            EntityArrow entityarrow = (EntityArrow) entity;

            if (entityarrow.isBurning()) {
                // CraftBukkit start
                if (org.bukkit.craftbukkit.event.CraftEventFactory.callEntityChangeBlockEvent(entityarrow, blockposition, Blocks.AIR, 0).isCancelled()) {
                    return;
                }
                // CraftBukkit end
                this.a(world, blockposition, world.getType(blockposition).set(BlockZTnt.EXPLODE, Boolean.valueOf(true)), entityarrow.shooter instanceof EntityLiving ? (EntityLiving) entityarrow.shooter : null);
                world.setAir(blockposition);
            }
        }

    }

    public boolean a(Explosion explosion) {
        return false;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockZTnt.EXPLODE, Boolean.valueOf((i & 1) > 0));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((Boolean) iblockdata.get(BlockZTnt.EXPLODE)).booleanValue() ? 1 : 0;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockZTnt.EXPLODE});
    }
}
