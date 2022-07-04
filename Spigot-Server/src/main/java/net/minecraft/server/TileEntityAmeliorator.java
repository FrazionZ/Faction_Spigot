package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;

public class TileEntityAmeliorator extends TileEntityContainer implements ITickable
{
	
	private static final int[] SLOT_CRAFT = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] SLOT_FUEL = new int[] {0};
    
    private NonNullList<ItemStack> AmelioratorItemStacks = NonNullList.<ItemStack>a(10, ItemStack.a);
    
    private int burnTime;
    private int ticksForCurrentFuel;
    private int cookTime;
    private int cookTimeTotal;
    
 // CraftBukkit start - add fields and methods
    private int lastTick = MinecraftServer.currentTick;
    private int maxStack = MAX_STACK;
    public List<HumanEntity> transaction = new java.util.ArrayList<HumanEntity>();

    public List<ItemStack> getContents()
    {
        return this.AmelioratorItemStacks;
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
		
		return this.AmelioratorItemStacks.size();
		
	}
	
	public boolean x_() 
	{
        Iterator iterator = this.AmelioratorItemStacks.iterator();

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
        return this.AmelioratorItemStacks.get(i);
    }
	
	public ItemStack splitStack(int i, int j)
	{
        return ContainerUtil.a(this.AmelioratorItemStacks, i, j);
    }

    public ItemStack splitWithoutUpdate(int i)
    {
        return ContainerUtil.a(this.AmelioratorItemStacks, i);
    }
	
	
    public void setItem(int index, ItemStack stack)
    {
    	this.AmelioratorItemStacks.set(index, stack);
    	
        if (index >= 0 && index <= this.AmelioratorItemStacks.size())
        {
        	this.cookTimeTotal = this.getCookTime();
        	this.cookTime = 0;
            this.update();
        }
    }
    
    public static void a(DataConverterManager dataconvertermanager)
    {
        dataconvertermanager.a(DataConverterTypes.BLOCK_ENTITY, (DataInspector) (new DataInspectorItemList(TileEntityAmeliorator.class, new String[] { "Items"})));
    }

    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.AmelioratorItemStacks = NonNullList.a(this.getSize(), ItemStack.a);
        ContainerUtil.b(nbttagcompound, this.AmelioratorItemStacks);
        this.burnTime = nbttagcompound.getShort("BurnTime");
        this.cookTime = nbttagcompound.getShort("CookTime");
        this.cookTimeTotal = nbttagcompound.getShort("CookTimeTotal");
        this.ticksForCurrentFuel = getItemBurnTime(this.AmelioratorItemStacks.get(0));

    }

    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short) this.burnTime);
        nbttagcompound.setShort("CookTime", (short) this.cookTime);
        nbttagcompound.setShort("CookTimeTotal", (short) this.cookTimeTotal);
        ContainerUtil.a(nbttagcompound, this.AmelioratorItemStacks);

        return nbttagcompound;
    }
    
    public int a(ItemStack itemstack)
    {
        return 140;
    }

	
	public int getMaxStackSize()
	{
		
		return 64;
		
	}
    
    public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
    
    public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            Item item = stack.getItem();

            if (item == Items.big_xp)
            {
                return 190;
            }
            else
            {
                return 0;
            }
        }
    }
	
    
    public String getName() {
		
		return "Ameliorator";
		
	}
	
	public boolean hasCustomName() {
		
		return false;
	}
	
	
	public boolean isBurning()
    {
        return this.burnTime > 0;
    }
	
	public ItemStack getStackInSlot(int index)
    {
        return this.AmelioratorItemStacks.get(index);
    }
	
	public void e() {
		
		
		boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }
        
        if (!this.world.isClientSide) {
        	
        	ItemStack itemstack = this.AmelioratorItemStacks.get(0);
        	
        	if (this.isBurning() || this.canSmelt())
            {
     	
	            if (!this.isBurning() && this.canSmelt())
	            {
	            	this.burnTime = getItemBurnTime(itemstack);
	                this.ticksForCurrentFuel = this.burnTime;
	                
	                if (this.isBurning())
                    {
                        flag1 = true;
                        this.splitStack(0, 1);
                        
                    }
	            }
	     
	            if (this.isBurning() && this.canSmelt())
	            {
	                this.cookTime++;
	                
	                if (this.cookTime >= this.getCookTime())
	                {
	                	this.cookTime = 0;
	                	this.cookTimeTotal = this.getCookTime();
	                    this.smeltItem();
                        flag1 = true;
	                }
	            }
	            else
	            {
	            	this.cookTime = 0;
	            }
            
            }
        	
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }
            
            if (flag != this.isBurning())
            {
                flag1 = true;
            }
            
            this.update();
        }
        		
		
	}
	
    public int getCookTime()
    {
        return 6000;
    }

	public boolean canSmelt() {
        return this.getRecipeResult() != null;
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    
    public ItemStack getRecipeResult() {
        return AmelioratorRecipes.getAmelioratorResult(new ItemStack[] {
        		this.AmelioratorItemStacks.get(1),
        		this.AmelioratorItemStacks.get(2),
        		this.AmelioratorItemStacks.get(3),
        		this.AmelioratorItemStacks.get(4),
        		this.AmelioratorItemStacks.get(5),
        		this.AmelioratorItemStacks.get(6),
        		this.AmelioratorItemStacks.get(7),
        		this.AmelioratorItemStacks.get(8),
        		this.AmelioratorItemStacks.get(9),
        		});
    }
    
    public void smeltItem()
    {
        
        try {
        	
        	ItemStack result = this.getRecipeResult();
        	
            ItemStack i1 = this.AmelioratorItemStacks.get(1);
            ItemStack i2 = this.AmelioratorItemStacks.get(2);
            ItemStack i3 = this.AmelioratorItemStacks.get(3);
            ItemStack i4 = this.AmelioratorItemStacks.get(4);
            ItemStack i5 = this.AmelioratorItemStacks.get(5);
            ItemStack i6 = this.AmelioratorItemStacks.get(6);
            ItemStack i7 = this.AmelioratorItemStacks.get(7);
            ItemStack i8 = this.AmelioratorItemStacks.get(8);
            ItemStack i9 = this.AmelioratorItemStacks.get(9);
            
            // On enlève un item de chaque ingrédient
            i1.subtract(1);
            i2.subtract(1);
            i3.subtract(1);
            i4.subtract(1);
            i5.subtract(1);
            i6.subtract(1);
            i7.subtract(1);
            i8.subtract(1);
            i9.subtract(1);
            // On récupère le slot de résultat
            ItemStack stack4 = this.getStackInSlot(9);
            // Si il est vide
            	
            this.AmelioratorItemStacks.set(9, result.cloneItemStack());
            	
			
		} catch(NullPointerException e)
        {
			System.out.println("NullPointerException: Error smeltItem");
		}
        
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
    
	public boolean canPlaceItemThroughFace(int arg0, ItemStack arg1, EnumDirection arg2) {
		
		return false;
	}

	
	public boolean canTakeItemThroughFace(int arg0, ItemStack arg1, EnumDirection arg2) {
		
		return false;
	}
    
	public String getContainerName()
    {
        return "minecraft:ameliorator";
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerAmeliorator(playerinventory, this);
    }
    
    public int getProperty(int i) {
        switch (i) {
        case 0:
            return this.burnTime;

        case 1:
            return this.ticksForCurrentFuel;

        case 2:
            return this.cookTime;

        case 3:
            return this.cookTimeTotal;

        default:
            return 0;
        }
    }

    public void setProperty(int i, int j) {
        switch (i) {
        case 0:
            this.burnTime = j;
            break;

        case 1:
            this.ticksForCurrentFuel = j;
            break;

        case 2:
            this.cookTime = j;
            break;

        case 3:
            this.cookTimeTotal = j;
        }

    }
    
	public int h() {

		return 4;
	}
    
	public void clear() {
        this.AmelioratorItemStacks.clear();
    }

}
