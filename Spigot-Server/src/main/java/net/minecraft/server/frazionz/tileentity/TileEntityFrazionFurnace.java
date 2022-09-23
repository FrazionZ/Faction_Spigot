package net.minecraft.server.frazionz.tileentity;

import java.util.Iterator;
// CraftBukkit start
import java.util.List;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerFrazionFurnace;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
// CraftBukkit end
import org.bukkit.entity.HumanEntity;

import net.minecraft.server.frazionz.blocks.BlockFrazionFurnace;

public class TileEntityFrazionFurnace extends TileEntityContainer implements ITickable, IWorldInventory {

    private static final int[] a = new int[] {0, 1, 2, 3, 4, 5};
    private static final int[] f = new int[] {6, 7, 8, 9, 10, 11, 12};
    private static final int[] g = new int[] {6};
    private NonNullList<ItemStack> items;
    private int burnTime;
    private int ticksForCurrentFuel;
    private int cookTime;
    private int cookTimeTotal;
    private String m;

    // CraftBukkit start - add fields and methods
    private int lastTick = MinecraftServer.currentTick;
    private int maxStack = MAX_STACK;
    public List<HumanEntity> transaction = new java.util.ArrayList<HumanEntity>();

    public List<ItemStack> getContents() {
        return this.items;
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

    public TileEntityFrazionFurnace() {
        this.items = NonNullList.a(13, ItemStack.a);
    }

    public int getSize() {
        return this.items.size();
    }

    public boolean x_() {
        Iterator iterator = this.items.iterator();

        ItemStack itemstack;

        do {
            if (!iterator.hasNext()) {
                return true;
            }

            itemstack = (ItemStack) iterator.next();
        } while (itemstack.isEmpty());

        return false;
    }

    public ItemStack getItem(int i) {
        return (ItemStack) this.items.get(i);
    }

    public ItemStack splitStack(int i, int j) {
        return ContainerUtil.a(this.items, i, j);
    }

    public ItemStack splitWithoutUpdate(int i) {
        return ContainerUtil.a(this.items, i);
    }

    public void setItem(int i, ItemStack itemstack) {
        ItemStack itemstack1 = (ItemStack) this.items.get(i);
        boolean flag = !itemstack.isEmpty() && itemstack.doMaterialsMatch(itemstack1) && ItemStack.equals(itemstack, itemstack1);

        this.items.set(i, itemstack);
        if (itemstack.getCount() > this.getMaxStackSize()) {
            itemstack.setCount(this.getMaxStackSize());
        }

        if ((i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) && !flag) {
            this.cookTimeTotal = this.a(itemstack);
            this.cookTime = 0;
            this.update();
        }

    }

    public String getName() {
        return this.hasCustomName() ? this.m : "container.furnace";
    }

    public boolean hasCustomName() {
        return this.m != null && !this.m.isEmpty();
    }

    public void setCustomName(String s) {
        this.m = s;
    }

    public static void a(DataConverterManager dataconvertermanager) {
        dataconvertermanager.a(DataConverterTypes.BLOCK_ENTITY, (DataInspector) (new DataInspectorItemList(TileEntityFrazionFurnace.class, new String[] { "Items"})));
    }

    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        this.items = NonNullList.a(this.getSize(), ItemStack.a);
        ContainerUtil.b(nbttagcompound, this.items);
        this.burnTime = nbttagcompound.getShort("BurnTime");
        this.cookTime = nbttagcompound.getShort("CookTime");
        this.cookTimeTotal = nbttagcompound.getShort("CookTimeTotal");
        this.ticksForCurrentFuel = fuelTime((ItemStack) this.items.get(6));
        if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
            this.m = nbttagcompound.getString("CustomName");
        }

    }

    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short) this.burnTime);
        nbttagcompound.setShort("CookTime", (short) this.cookTime);
        nbttagcompound.setShort("CookTimeTotal", (short) this.cookTimeTotal);
        ContainerUtil.a(nbttagcompound, this.items);
        if (this.hasCustomName()) {
            nbttagcompound.setString("CustomName", this.m);
        }

        return nbttagcompound;
    }

    public int getMaxStackSize() {
        return 64;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }
    
    
    public void e()
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.burnTime;
        }

        if (!this.world.isClientSide)
        {
            ItemStack itemstack = this.items.get(6);

            // SI CA BRULE OU BRULE PAS //
            
            if ((this.isBurning() || (!itemstack.isEmpty() && !((ItemStack)this.items.get(0)).isEmpty()) || (!itemstack.isEmpty() && !((ItemStack)this.items.get(5)).isEmpty()) || (!itemstack.isEmpty() && !((ItemStack)this.items.get(4)).isEmpty()) || (!itemstack.isEmpty() && !((ItemStack)this.items.get(3)).isEmpty()) || (!itemstack.isEmpty() && !((ItemStack)this.items.get(2)).isEmpty()) || ( !itemstack.isEmpty() && !((ItemStack)this.items.get(1)).isEmpty())))
            {
                if (!this.isBurning() && this.canBurn())
                {
                    this.burnTime = fuelTime(itemstack);
                    this.ticksForCurrentFuel = this.burnTime;

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (!itemstack.isEmpty())
                        {
                            Item item = itemstack.getItem();
                            itemstack.subtract(1);

                            if (itemstack.isEmpty())
                            {
                                Item item1 = item.q();
                                this.items.set(6, item1 == null ? ItemStack.a : new ItemStack(item1));
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canBurn())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.cookTimeTotal)
                    {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.a((ItemStack) this.items.get(0));
                        this.burn();
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
                BlockFrazionFurnace.a(this.isBurning(), this.world, this.position);
            }
        }

        if (flag1)
        {
            this.update();
        }
    }


    public int a(ItemStack itemstack) {
        return 5;
    }
    
    private boolean yolo(int aaa) {
    	
    	return ((ItemStack) this.items.get(aaa)).isEmpty();
    	
    }

    private boolean canBurn()
    {
        if (yolo(0) && yolo(1) && yolo(2) && yolo(3) && yolo(4) && yolo(5))
        {
            return false;
        }
        else
        {
            ItemStack itemstack = RecipesFurnace.getInstance().getResult((ItemStack) this.items.get(0));
            ItemStack itemstackA = RecipesFurnace.getInstance().getResult((ItemStack) this.items.get(1));
            ItemStack itemstackB = RecipesFurnace.getInstance().getResult((ItemStack) this.items.get(2));
            ItemStack itemstackC = RecipesFurnace.getInstance().getResult((ItemStack) this.items.get(3));
            ItemStack itemstackD = RecipesFurnace.getInstance().getResult((ItemStack) this.items.get(4));
            ItemStack itemstackE = RecipesFurnace.getInstance().getResult((ItemStack) this.items.get(5));
            
        	if (itemstack.isEmpty() && !this.items.get(0).equals(ItemStack.a))
            {
                return false;
            }
        	else if (itemstackA.isEmpty() && !this.items.get(1).equals(ItemStack.a))
            {
                return false;
            }
        	else if (itemstackB.isEmpty() && !this.items.get(2).equals(ItemStack.a))
            {
                return false;
            }
        	else if (itemstackC.isEmpty() && !this.items.get(3).equals(ItemStack.a))
            {
                return false;
            }
        	else if (itemstackD.isEmpty() && !this.items.get(4).equals(ItemStack.a))
            {
                return false;
            }
        	else if (itemstackE.isEmpty() && !this.items.get(5).equals(ItemStack.a))
            {
                return false;
            }
        	else if (itemstack.isEmpty() && itemstackA.isEmpty() && itemstackB.isEmpty() && itemstackC.isEmpty() && itemstackD.isEmpty() && itemstackE.isEmpty())
            {
                return false;
            }
            else
            {
                ItemStack itemstack1 = (ItemStack) this.items.get(7);
                
                ItemStack itemstackA1 = (ItemStack) this.items.get(8);
                
                ItemStack itemstackB1 = (ItemStack) this.items.get(9);
                
                ItemStack itemstackC1 = (ItemStack) this.items.get(10);
                
                ItemStack itemstackD1 = (ItemStack) this.items.get(11);
                
                ItemStack itemstackE1 = (ItemStack) this.items.get(12);
                    
                if (itemstack1.isEmpty() && itemstackA1.isEmpty() && itemstackB1.isEmpty() && itemstackC1.isEmpty() && itemstackD1.isEmpty() && itemstackE1.isEmpty())
                {
                    return true;
                }
                else if (!itemstack1.doMaterialsMatch(itemstack) && !itemstackA1.doMaterialsMatch(itemstackA) && !itemstackB1.doMaterialsMatch(itemstackB) && !itemstackC1.doMaterialsMatch(itemstackC) && !itemstackD1.doMaterialsMatch(itemstackD) && !itemstackE1.doMaterialsMatch(itemstackE))
                {
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() < itemstack1.getMaxStackSize() )
                {
                    return true;
                }
                else if (itemstackA1.getCount() + itemstackA.getCount() <= this.getMaxStackSize() && itemstackA1.getCount() + itemstackA.getCount() < itemstackA1.getMaxStackSize() )
                {
                    return true;
                }
                else if (itemstackB1.getCount() + itemstackB.getCount() <= this.getMaxStackSize() && itemstackB1.getCount() + itemstackB.getCount() < itemstackB1.getMaxStackSize() )
                {
                    return true;
                }
                else if (itemstackC1.getCount() + itemstackC.getCount() <= this.getMaxStackSize() && itemstackC1.getCount() + itemstackC.getCount() < itemstackC1.getMaxStackSize() )
                {
                    return true;
                }
                else if (itemstackD1.getCount() + itemstackD.getCount() <= this.getMaxStackSize() && itemstackD1.getCount() + itemstackD.getCount() < itemstackD1.getMaxStackSize() )
                {
                    return true;
                }
                else if (itemstackE1.getCount() + itemstackE.getCount() <= this.getMaxStackSize() && itemstackE1.getCount() + itemstackE.getCount() < itemstackE1.getMaxStackSize() )
                {
                    return true;
                }
                else
                {
                    return itemstack1.getCount() < itemstack.getMaxStackSize();
                }
                
            }
        }
    }

    public void burn() {
        if (this.canBurn()) {
            ItemStack itemstack = (ItemStack) this.items.get(0);
            ItemStack itemstackA = (ItemStack) this.items.get(1);
            ItemStack itemstackB = (ItemStack) this.items.get(2);
            ItemStack itemstackC = (ItemStack) this.items.get(3);
            ItemStack itemstackD = (ItemStack) this.items.get(4);
            ItemStack itemstackE = (ItemStack) this.items.get(5);
            ItemStack itemstack1 = RecipesFurnace.getInstance().getResult(itemstack);
            ItemStack itemstackA1 = RecipesFurnace.getInstance().getResult(itemstackA);
            ItemStack itemstackB1 = RecipesFurnace.getInstance().getResult(itemstackB);
            ItemStack itemstackC1 = RecipesFurnace.getInstance().getResult(itemstackC);
            ItemStack itemstackD1 = RecipesFurnace.getInstance().getResult(itemstackD);
            ItemStack itemstackE1 = RecipesFurnace.getInstance().getResult(itemstackE);
            ItemStack itemstack2 = (ItemStack) this.items.get(7);
            ItemStack itemstackA2 = (ItemStack) this.items.get(8);
            ItemStack itemstackB2 = (ItemStack) this.items.get(9);
            ItemStack itemstackC2 = (ItemStack) this.items.get(10);
            ItemStack itemstackD2 = (ItemStack) this.items.get(11);
            ItemStack itemstackE2 = (ItemStack) this.items.get(12);

            // CraftBukkit start - fire FurnaceSmeltEvent
           /* CraftItemStack source = CraftItemStack.asCraftMirror(itemstack);
            org.bukkit.inventory.ItemStack result = CraftItemStack.asBukkitCopy(itemstack1);

            FurnaceSmeltEvent furnaceSmeltEvent = new FurnaceSmeltEvent(this.world.getWorld().getBlockAt(position.getX(), position.getY(), position.getZ()), source, result);
            this.world.getServer().getPluginManager().callEvent(furnaceSmeltEvent);

            if (furnaceSmeltEvent.isCancelled()) {
                return;
            }

            result = furnaceSmeltEvent.getResult();
            itemstack1 = CraftItemStack.asNMSCopy(result);

            if (!itemstack1.isEmpty()) {
                if (itemstack2.isEmpty()) {
                    this.items.set(3, itemstack1.cloneItemStack());
                } else if (CraftItemStack.asCraftMirror(itemstack2).isSimilar(result)) {
                    itemstack2.add(itemstack1.getCount());
                } else {
                    return;
                }
            }*/

            if (itemstack2.isEmpty())
            {
                this.items.set(7, itemstack1.cloneItemStack());
                itemstack.subtract(1);
            } 
            else if (itemstack2.getItem() == itemstack1.getItem())
            {
                itemstack2.add(1);
                itemstack.subtract(1);
            }
            
            if (itemstackA2.isEmpty())
            {
                this.items.set(8, itemstackA1.cloneItemStack());
                itemstackA.subtract(1);
            } 
            else if (itemstackA2.getItem() == itemstackA1.getItem())
            {
                itemstackA2.add(1);
                itemstackA.subtract(1);
            }
            
            if (itemstackB2.isEmpty())
            {
                this.items.set(9, itemstackB1.cloneItemStack());
                itemstackB.subtract(1);
            } 
            else if (itemstackB2.getItem() == itemstackB1.getItem())
            {
                itemstackB2.add(1);
                itemstackB.subtract(1);
            }
            
            
            if (itemstackC2.isEmpty())
            {
                this.items.set(10, itemstackC1.cloneItemStack());
                itemstackC.subtract(1);
            } 
            else if (itemstackC2.getItem() == itemstackC1.getItem())
            {
                itemstackC2.add(1);
                itemstackC.subtract(1);
            }
            
            
            if (itemstackD2.isEmpty())
            {
                this.items.set(11, itemstackD1.cloneItemStack());
                itemstackD.subtract(1);
            } 
            else if (itemstackD2.getItem() == itemstackD1.getItem())
            {
                itemstackD2.add(1);
                itemstackD.subtract(1);
            }
            
            
            if (itemstackE2.isEmpty())
            {
                this.items.set(12, itemstackE1.cloneItemStack());
                itemstackE.subtract(1);
            } 
            else if (itemstackE2.getItem() == itemstackE1.getItem())
            {
                itemstackE2.add(1);
                itemstackE.subtract(1);
            }
            
        }
    }

    public static int fuelTime(ItemStack itemstack) {
        if (itemstack.isEmpty()) {
            return 0;
        } else {
            Item item = itemstack.getItem();
            
            if(item == Items.BLAZE_ROD) {
            	return 1000;
            }
            else {
            	return 0;
            }

        }
    }

    public static boolean isFuel(ItemStack itemstack) {
        return fuelTime(itemstack) > 0;
    }

    public boolean a(EntityHuman entityhuman) {
        return this.world.getTileEntity(this.position) != this ? false : entityhuman.d((double) this.position.getX() + 0.5D, (double) this.position.getY() + 0.5D, (double) this.position.getZ() + 0.5D) <= 64.0D;
    }

    public void startOpen(EntityHuman entityhuman)
    {
    }

    public void closeContainer(EntityHuman entityhuman) 
    {	
    }

    
    
    public boolean b(int i, ItemStack itemstack)
    {
        if (i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12) 
        {
            return false;
        }
        else if (i != 6)
        {
            return true;
        }
        else
        {
            ItemStack itemstack1 = (ItemStack) this.items.get(6);
            return isFuel(itemstack) || SlotFurnaceFuel.d_(itemstack) && itemstack1.getItem() != Items.BUCKET;
        }
    }
    
    public int[] getSlotsForFace(EnumDirection side)
    {
        if (side == EnumDirection.DOWN)
        {
            return f;
        }
        else
        {
            return side == EnumDirection.UP ? a : g;
        }
    }

    public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection)
    {
        return this.b(i, itemstack);
    }

    public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection)
    {
        if (enumdirection == EnumDirection.DOWN && i == 6)
        {
            Item item = itemstack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
    }

    public String getContainerName()
    {
        return "minecraft:frazion_furnace";
    }

    public Container createContainer(PlayerInventory playerinventory, EntityHuman entityhuman) {
        return new ContainerFrazionFurnace(playerinventory, this);
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
        this.items.clear();
    }
}
