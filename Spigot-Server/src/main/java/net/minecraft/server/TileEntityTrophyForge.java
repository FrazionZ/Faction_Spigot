package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.event.CraftEventFactory;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.frazionz.inventory.MachineCraftEvent.MachineAction;
import org.bukkit.event.frazionz.inventory.MachineCraftEvent.MachineType;

import net.minecraft.server.frazionz.items.ItemTrophy;

public class TileEntityTrophyForge extends TileEntityContainer implements ITickable
{
	
	private static final int[] SLOT_CRAFT = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    
    private NonNullList<ItemStack> TrophyForgeItemStacks = NonNullList.<ItemStack>a(12, ItemStack.a);
    
    private int forgeTime;
    private int totalForgeTime;
    private int isForging = 0;
    
 // CraftBukkit start - add fields and methods
    private int lastTick = MinecraftServer.currentTick;
    private int maxStack = MAX_STACK;
    public List<HumanEntity> transaction = new java.util.ArrayList<HumanEntity>();

    private EntityHuman forger;
    
    public List<ItemStack> getContents()
    {
        return this.TrophyForgeItemStacks;
    }

    public void onOpen(CraftHumanEntity who) {
        transaction.add(who);
    }

    public void onClose(CraftHumanEntity who) {
        transaction.remove(who);
    }

    public List<HumanEntity> getViewers() {
        return transaction;
    }

    public void setMaxStackSize(int size) {
        maxStack = size;
    }
    // CraftBukkit end
    
	public int getSize()
	{
		return this.TrophyForgeItemStacks.size();
	}
	
	public boolean x_() 
	{
        Iterator iterator = this.TrophyForgeItemStacks.iterator();

        ItemStack itemstack;

        do
        {
            if (!iterator.hasNext())
            {
                return true;
            }

            itemstack = (ItemStack) iterator.next();
        }
        while (itemstack.isEmpty());

        return false;
    }
	
	public ItemStack getItem(int i)
	{
        return this.TrophyForgeItemStacks.get(i);
    }
	
	public ItemStack splitStack(int i, int j)
	{
        return ContainerUtil.a(this.TrophyForgeItemStacks, i, j);
    }

    public ItemStack splitWithoutUpdate(int i)
    {
        return ContainerUtil.a(this.TrophyForgeItemStacks, i);
    }
	
	
    public void setItem(int index, ItemStack stack)
    {
    	this.TrophyForgeItemStacks.set(index, stack);
    	
        if (index >= 0 && index <= this.TrophyForgeItemStacks.size())
        {
        	this.totalForgeTime = this.getForgeTime();
        	this.forgeTime = 0;
            this.update();
        }
    }
    
	public int getMaxStackSize()
	{
		return 64;
	}
    
	public boolean a(EntityHuman entityhuman)
	{
        return this.world.getTileEntity(this.position) != this ? false : entityhuman.d((double) this.position.getX() + 0.5D, (double) this.position.getY() + 0.5D, (double) this.position.getZ() + 0.5D) <= 64.0D;
    }
    
	public void startOpen(EntityHuman entityhuman)
    {
    }

    public void closeContainer(EntityHuman entityhuman) 
    {	
    }
    
    public boolean b(int index, ItemStack stack)
    {
    	return false;
    }
    
    public String getName() {
		
		return "TrophyForge";
		
	}
	
	public boolean hasCustomName() {
		
		return false;
	}
	
    public static void a(DataConverterManager dataconvertermanager)
    {
        dataconvertermanager.a(DataConverterTypes.BLOCK_ENTITY, (DataInspector) (new DataInspectorItemList(TileEntityTrophyForge.class, new String[] { "Items"})));
    }
	
    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.TrophyForgeItemStacks = NonNullList.a(this.getSize(), ItemStack.a);
        ContainerUtil.b(nbttagcompound, this.TrophyForgeItemStacks);
        this.forgeTime = nbttagcompound.getShort("ForgeTime");
        this.totalForgeTime = nbttagcompound.getShort("TotalForgeTime");
        this.isForging = nbttagcompound.getShort("IsForging");

    }

    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        nbttagcompound.setShort("ForgeTime", (short) this.forgeTime);
        nbttagcompound.setShort("TotalForgeTime", (short) this.totalForgeTime);
        nbttagcompound.setShort("IsForging", (short) this.isForging);
        ContainerUtil.a(nbttagcompound, this.TrophyForgeItemStacks);

        return nbttagcompound;
    }
    
	public boolean canPlaceItemThroughFace(int arg0, ItemStack arg1, EnumDirection arg2) {
		return false;
	}

	
	public boolean canTakeItemThroughFace(int arg0, ItemStack arg1, EnumDirection arg2) {
		return false;
	}
	
	public void e() {

        if (!this.world.isClientSide) {
        	
        	if(this.isForging() && !this.canForge()) {
        		this.isForging = 0;
        		this.forgeTime = 0;
        	}
        	else if(this.isForging() && this.canForge()) {
        		this.forgeTime++;
        		
                if (this.forgeTime >= this.getForgeTime())
                {
                	this.forgeTime = 0;
                	this.totalForgeTime = this.getForgeTime();
                    this.forgeItem();
                }
        		
        	}
            
            this.update();
        }
	}

    public int getForgeTime()
    {
    	return 2400;
    }
	
    public ItemStack getRecipeResult() {
        return TrophyForgeRecipes.getTrophyForgeResult(new ItemStack[] {
        		this.TrophyForgeItemStacks.get(0),
        		this.TrophyForgeItemStacks.get(1),
        		this.TrophyForgeItemStacks.get(2),
        		this.TrophyForgeItemStacks.get(3),
        		this.TrophyForgeItemStacks.get(4),
        		this.TrophyForgeItemStacks.get(5),
        		this.TrophyForgeItemStacks.get(6),
        		this.TrophyForgeItemStacks.get(7),
        		this.TrophyForgeItemStacks.get(8),
        		});
    }
    
    public boolean canForge() {
    	return this.getRecipeResult() != null;
    }
    
    public void forgeItem()
    {
        ItemStack result = this.getRecipeResult();
        
        if(CraftEventFactory.callMachineCraftEvent(forger, result, null, MachineType.TROPHY_FORGE, MachineAction.CRAFT_END).isCancelled())
        	return;
        
    	if(result.getItem() instanceof ItemTrophy) {
    		ItemTrophy item = (ItemTrophy) result.getItem();
    		
    		result.setTag(new NBTTagCompound());
    		AttributeModifier attribute = item.getRandomAttributeModifier();
    		result.a(item.getMonsterAttributes().getName(), attribute, EnumItemSlot.TROPHY_1);
    		result.a(item.getMonsterAttributes().getName(), attribute, EnumItemSlot.TROPHY_2);
    		result.a(item.getMonsterAttributes().getName(), attribute, EnumItemSlot.TROPHY_3);
    	}
        for(int i = 0; i < 12; i++) {
        	this.TrophyForgeItemStacks.get(i).subtract(1);
        }
        
        this.TrophyForgeItemStacks.set(4, result.cloneItemStack());
    }
    
	public String getContainerName()
    {
        return "minecraft:trophy_forge";
    }
	
    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) 
    {
        return new ContainerTrophyForge(playerinventory, this);
    }
    
    
    public int getProperty(int i) {
        switch (i) 
        {
	        case 0:
	            return this.forgeTime;
	
	        case 1:
	            return this.totalForgeTime;
	            
	        case 2:
	        	return this.isForging;
	
	        default:
	            return 0;
        }
    }

    public void setProperty(int i, int j)
    {
        switch (i)
        {
            case 0:
                this.forgeTime = j;
                break;

            case 1:
                this.totalForgeTime = j;
                break;
                
            case 2: 
            	this.isForging = j;
            	break;
        }
    }
    
	public int h() {
		return 3;
	}
	
	public void clear() {
        this.TrophyForgeItemStacks.clear();
    }
	
    public boolean isForging() {
		return this.isForging == 1;
	}
	
    @Nullable
    public PacketPlayOutTileEntityData getUpdatePacket() {
        return new PacketPlayOutTileEntityData(this.position, 3, this.d());
    }

    public NBTTagCompound d() {
        return this.save(new NBTTagCompound());
    }
    
    public EntityHuman getForger() {
		return forger;
	}
    
    public void setForger(EntityHuman forger) {
		this.forger = forger;
	}

}
