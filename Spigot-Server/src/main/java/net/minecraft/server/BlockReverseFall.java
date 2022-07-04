package net.minecraft.server;

public class BlockReverseFall extends BlockHalfTransparent {

    public BlockReverseFall() {
        super(Material.CLAY, false, MaterialMapColor.u);
        this.a(CreativeModeTab.b);
    }

    public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
    	if(entity instanceof EntityLiving && ((EntityLiving) entity).hasEffect(MobEffectList.fromId(28))) {
    		super.fallOn(world, blockposition, entity, f);
    	}
    	else {
            entity.e(f, 0.0F);
    	}
    }
}
