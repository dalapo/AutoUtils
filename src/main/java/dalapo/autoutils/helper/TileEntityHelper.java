package dalapo.autoutils.helper;

import dalapo.autoutils.logging.Logger;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityHelper {
	private TileEntityHelper() {}
	
	public static ItemStack getFirstItem(IInventory inv, int side)
	{
		for (int i=0; i<inv.getSizeInventory(); i++)
		{
			if (isValidSlotForSide(inv, side, i) && inv.getStackInSlot(i) != null)
			{
				return inv.getStackInSlot(i);
			}
		}
		return null;
	}
	
	/**
	 * Returns true if at least one item in the given ItemStack can be inserted into
	 * the given inventory on the given side.
	 * @param inv
	 * @return
	 */
	public static boolean hasSpaceForItem(IInventory inv, ItemStack is, int side)
	{
		if (inv == null) return true;
		for (int i=0; i<inv.getSizeInventory(); i++)
		{
			ItemStack test = inv.getStackInSlot(i);
			if (isValidSlotForSide(inv, side, i) && (test == null || (is.isItemEqual(test) && test.stackSize < test.getMaxStackSize())))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidSlotForSide(IInventory inv, int side, int slot)
	{
		if (!(inv instanceof ISidedInventory)) return true;
		ItemStack stack = inv.getStackInSlot(slot);
		int[] slots = ((ISidedInventory)inv).getAccessibleSlotsFromSide(side);
		if (slots == null) return false;
		for (int s : slots)
		{
			if (s == slot)
			{
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack tryInsertItem(IInventory inv, ItemStack itemstack, int side)
	{
		if (itemstack == null) return null;
		Logger.info("Entered tryInsertItem with ItemStack " + itemstack.toString());
		for (int i=0; i<inv.getSizeInventory(); i++)
		{
			if (!isValidSlotForSide(inv, side, i)) continue;
			ItemStack is = inv.getStackInSlot(i);
			if (is == null)
			{
				inv.setInventorySlotContents(i, itemstack.copy());
				itemstack.stackSize = 0;
			}
			else if (is.getItem().equals(itemstack.getItem()))
			{
				int transfer = is.getMaxStackSize() - is.stackSize;
				if (transfer > itemstack.stackSize) transfer = itemstack.stackSize;
				inv.getStackInSlot(i).stackSize += transfer;
				itemstack.stackSize -= transfer;
			}
			if (itemstack.stackSize == 0) return null;
		}
		return itemstack;
	} // Returns remainder of stack once inventory is full
	
	
}
