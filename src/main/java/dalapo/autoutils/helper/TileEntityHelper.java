package dalapo.autoutils.helper;

import dalapo.autoutils.logging.Logger;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityHelper {
	private TileEntityHelper() {}
	
	public static boolean isValidSlotForSide(IInventory inv, int side, int slot, boolean insert)
	{
		if (!(inv instanceof ISidedInventory)) return true;
		ItemStack stack = inv.getStackInSlot(slot);
		int[] slots = ((ISidedInventory)inv).getAccessibleSlotsFromSide(side);
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
			if (inv instanceof ISidedInventory && !((ISidedInventory)inv).canInsertItem(i, itemstack, side)) continue;
			
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