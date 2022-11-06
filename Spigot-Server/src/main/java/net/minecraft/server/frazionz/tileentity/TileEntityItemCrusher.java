package net.minecraft.server.frazionz.tileentity;

import net.minecraft.server.*;
import net.minecraft.server.frazionz.inventory.tileentity.ContainerItemCrusher;
import net.minecraft.server.frazionz.recipes.ItemCrusherRecipes;
import org.bukkit.craftbukkit.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;

import java.util.Iterator;
import java.util.List;

public class TileEntityItemCrusher extends TileEntityContainer implements ITickable, IWorldInventory
{
    private static final int[] SLOT_CRAFT = new int[] {0, 1, 2};
    private static final int[] SLOT_INVENTORY = new int[] {3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    private NonNullList<ItemStack> itemstacks = NonNullList.<ItemStack>a(13, ItemStack.a);

    private int crushingTime;
    private int totalCrushingTime;
    private int isCrushing = 0;

    public List<HumanEntity> transaction = new java.util.ArrayList<>();
    private EntityHuman worker;

    public TileEntityItemCrusher()
    {
    }

    @Override
    public List<ItemStack> getContents() {
        return itemstacks;
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
    }

    @Override
    public int getSize() {
        return itemstacks.size();
    }

    public boolean x_()
    {
        Iterator iterator = this.itemstacks.iterator();

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

    /**
     * Returns the stack in the given slot.
     */
    public ItemStack getItem(int index)
    {
        return this.itemstacks.get(index);
    }


    /**
     * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
     */
    public ItemStack splitStack(int index, int count)
    {
        return ContainerUtil.a(this.itemstacks, index, count);
    }

    /**
     * Removes a stack from the given slot and returns it.
     */
    public ItemStack splitWithoutUpdate(int index)
    {
        return ContainerUtil.a(this.itemstacks, index);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setItem(int index, ItemStack stack)
    {

        this.itemstacks.set(index, stack);

        if (index >= 0 && index <= this.itemstacks.size())
        {
            this.totalCrushingTime = this.getForgeTime();
            this.crushingTime = 0;
            this.update();
        }
    }


    public int getMaxStackSize()
    {
        return 64;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with Container
     */
    public boolean a(EntityHuman player)
    {
        if (this.world.getTileEntity(this.position) != this)
        {
            return false;
        }
        else
        {
            return player.d((double)this.position.getX() + 0.5D, (double)this.position.getY() + 0.5D, (double)this.position.getZ() + 0.5D) <= 64.0D;
        }
    }


    public void startOpen(EntityHuman entityhuman)
    {
    }

    public void closeContainer(EntityHuman entityhuman)
    {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
     * guis use Slot.isItemValid
     */
    public boolean b(int index, ItemStack stack)
    {
        return false;
    }

    public String getName() {
        return "Item Crusher";
    }

    public boolean hasCustomName() {
        return false;
    }

    public static void a(DataConverterManager dataconvertermanager)
    {
        dataconvertermanager.a(DataConverterTypes.BLOCK_ENTITY, (DataInspector) (new DataInspectorItemList(TileEntityItemCrusher.class, new String[] { "Items"})));
    }

    public void load(NBTTagCompound compound)
    {
        super.load(compound);
        this.itemstacks = NonNullList.<ItemStack>a(this.getSize(), ItemStack.a);
        ContainerUtil.b(compound, this.itemstacks);
        this.crushingTime = compound.getShort("crushingTime");
        this.totalCrushingTime = compound.getShort("totalCrushingTime");
        this.isCrushing = compound.getShort("isCrushing");
    }

    public NBTTagCompound save(NBTTagCompound compound)
    {
        super.save(compound);
        compound.setShort("crushingTime", (short)this.crushingTime);
        compound.setShort("totalCrushingTime", (short)this.totalCrushingTime);
        compound.setShort("isCrushing", (short)this.isCrushing);
        ContainerUtil.a(compound, this.itemstacks);

        return compound;
    }

    public void e() {
        if (!this.world.isClientSide)
        {
            if(this.isCrushing() && !this.canCrush()) {
                this.isCrushing = 0;
                this.crushingTime = 0;
                this.world.playBlockAction(this.position, this.getBlock(), 1, 0);
            }
            else if(this.isCrushing() && this.canCrush()) {
                this.crushingTime++;
                if (this.crushingTime >= this.getForgeTime())
                {
                    this.crushingTime = 0;
                    this.totalCrushingTime = this.getForgeTime();
                    this.crushItems();
                }
            }
            this.update();
        }
    }

    public int getForgeTime()
    {
        return 100;
    }

    public ItemStack[] getRecipeResult(int id) {
        return ItemCrusherRecipes.getResult(this.itemstacks.get(id));
    }

    public boolean canCrush() {
        boolean canCrush = false;
        for(int id : SLOT_CRAFT)
            canCrush = canCrush || this.getRecipeResult(id) != null;
        return canCrush;
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void crushItems()
    {
        for(int id : SLOT_CRAFT) {
            ItemStack[] result = this.getRecipeResult(id);
            if(result != null) {
                this.itemstacks.get(id).subtract(1);
                for(ItemStack stack : result) {
                    if(stack == null)
                        continue;
                    addItemToInventory(stack);
                    if(!stack.isEmpty()) {
                        Block.a(world, getFrontPosition(), stack);
                    }
                }
            }
        }
    }

    public String getContainerName() {
        return "frazionz:item_crusher";
    }

    public Container createContainer(PlayerInventory playerInventory, EntityHuman playerIn)
    {
        return new ContainerItemCrusher(playerInventory, this);
    }

    public int getProperty(int id)
    {
        switch (id) {
            case 0:
                return this.crushingTime;

            case 1:
                return this.totalCrushingTime;

            case 2:
                return this.isCrushing;

            default:
                return 0;
        }
    }

    public void setProperty(int id, int value)
    {
        switch (id)
        {
            case 0:
                this.crushingTime = value;
                break;

            case 1:
                this.totalCrushingTime = value;
                break;

            case 2:
                this.isCrushing = value;
                this.world.playBlockAction(this.position, this.getBlock(), 1, value);
                this.world.playBlockAction(this.position, this.getBlock(), 2, 0);
                break;
        }
    }

    public int h()
    {
        return 3;
    }

    public boolean c(int id, int type)
    {
        switch(id) {
            case 1:
                this.isCrushing = type;
                return true;
            case 2:
                return true;
            default:
                return super.c(id, type);
        }
    }

    public void clear()
    {
        this.itemstacks.clear();
    }

    public boolean isCrushing() {
        return this.isCrushing == 1;
    }

    public void addItemToInventory(ItemStack item) {
        int i = -1;
        for(int id : SLOT_INVENTORY) {
            ItemStack stack = this.itemstacks.get(id);
            if(i == -1 && stack.isEmpty())
                i = id;
            if(stack.getItem() == item.getItem()) {
                int stackCount = stack.getCount();
                if(stackCount < 64) {
                    int newCount = stackCount + item.getCount();
                    if(newCount > 64) {
                        stack.setCount(64);
                        item.setCount(item.getCount() - newCount + 64);
                    }
                    else {
                        stack.setCount(newCount);
                        item.setCount(0);
                        break;
                    }
                }
            }
        }
        if(i != -1 && !item.isEmpty()) {
            this.itemstacks.set(i, item.cloneItemStack());
            item.setCount(0);
        }
    }

    public BlockPosition getFrontPosition() {
        int i = this.v();
        switch(i)
        {
            case 3:
                return this.position.a(0.5, 0.5, 0.5-1);
            case 4:
                return this.position.a(0.5, 0.5, 0.5+1);
            case 5:
                return this.position.a(0.5-1, 0.5, 0.5);
            case 6:
                return this.position.a(0.5+1, 0.5, 0.5);
        }
        return this.position;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, EnumDirection direction) {
        return false;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, EnumDirection direction) {
        return false;
    }

    @Override
    public int[] getSlotsForFace(EnumDirection side) {
        return SLOT_INVENTORY;
    }

    public void setWorker(EntityHuman forger) {
        this.worker = forger;
    }

    public EntityHuman getWorker() {
        return worker;
    }
}
