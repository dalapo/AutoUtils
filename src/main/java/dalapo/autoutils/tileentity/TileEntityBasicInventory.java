package dalapo.autoutils.tileentity;

import dalapo.autoutils.helper.MiscHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class TileEntityBasicInventory extends TileEntityAutoUtils implements IInventory {

	private ItemStack[] inventory;
	private String name;
//	protected static int stackLimit = 64;
	
	public TileEntityBasicInventory(int size, String name)
	{
		inventory = new ItemStack[size];
		this.name = name;
	}
	
	public int getSizeInventory()
	{
		return inventory.length;
	}
	@Override
	public ItemStack getStackInSlot(int slot) {
		if (!MiscHelper.isInRange(slot, 0, getSizeInventory())) return null;
		return this.inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num) {
		if (!MiscHelper.isInRange(slot, 0, getSizeInventory()) || inventory[slot] == null) return null;
		
		ItemStack itemstack;
		if (inventory[slot].stackSize > num)
		{
			itemstack = new ItemStack(inventory[slot].getItem(), num);
			inventory[slot].stackSize -= num;
			markDirty();
			return itemstack;
		}
		// If we get to this point, we are trying to remove more items than in the slot
		itemstack = inventory[slot];
		inventory[slot] = null;
		markDirty();
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		if (!MiscHelper.isInRange(slot, 0, getSizeInventory())) return;
		if (itemstack == null || itemstack.stackSize == 0)
		{
			inventory[slot] = null;
			return;
		}
		if (itemstack.stackSize > getInventoryStackLimit()) itemstack.stackSize = getInventoryStackLimit();
		inventory[slot] = itemstack;
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public abstract int getInventoryStackLimit();

	@Override
	public boolean isUseableByPlayer(EntityPlayer ep) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this &&
				ep.getDistanceSq(xCoord+0.5, yCoord+0.5, zCoord+0.5) <= 64;
	}

	public abstract String getDisplayName();
	
	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList list = new NBTTagList();
		for (int i=0; i<getSizeInventory(); i++)
		{
			NBTTagCompound stackTag = new NBTTagCompound();
			stackTag.setByte("Slot", (byte)i);
			ItemStack is = getStackInSlot(i);
			if (is != null) is.writeToNBT(stackTag);
			list.appendTag(stackTag);
		}
		nbt.setTag("Items", list);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i=0; i<list.tagCount(); i++)
		{
			NBTTagCompound tag = list.getCompoundTagAt(i);
			int slot = tag.getByte("Slot");
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(tag);
			inventory[slot] = itemstack;
		}
	}
}
