package dalapo.autoutils.tileentity;

import dalapo.autoutils.helper.TileEntityHelper;
import dalapo.autoutils.helper.UsefulLists;
import dalapo.autoutils.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityStackMover extends TileEntity {
	ItemStack[] filter;
	private boolean isPowered = false;
	public static final String id = "StackMover";
	
	public TileEntityStackMover()
	{
		filter = new ItemStack[9];
	}
	
	public void performTick() // Called when a block adjacent to the TE updates.
	{
		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			if (!isPowered)
			{
				Logger.info("About to enter transferStack()");
				transferStack();
				isPowered = true;
			}
		}
		else isPowered = false;
	}
	
	private boolean shouldFilter()
	{
		for (ItemStack is : filter)
		{
			if (is != null) return true;
		}
		return false;
	}
	
	private boolean checkFilter(ItemStack i)
	{
		if (!shouldFilter()) return true;
		for (ItemStack is : filter)
		{
			if (is == null) continue;
			if (is.getItem().equals(i.getItem())) return true;
		}
		return false;
	}
	
	private int getSlot(IInventory te, int side, boolean insert)
	{
		if (te == null) return -1;
		for (int i=0; i<te.getSizeInventory(); i++)
		{
			if (te instanceof ISidedInventory)
			{
				ItemStack stack = te.getStackInSlot(i);
				Logger.info("Side = " + side + " , slot = " + i);
				if (insert && ((ISidedInventory)te).canInsertItem(i, stack, side)) return i;
				if (!insert && ((ISidedInventory)te).canExtractItem(i, stack, side)) return i;
			}
			else
			{
				if (te.getStackInSlot(i) != null && checkFilter(te.getStackInSlot(i)))
				{
					return i;
				}
			}
		}
		return -1;
	}
	
	public void transferStack()
	{
		// Aggressive bugsquashing
		ForgeDirection front = UsefulLists.directions[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)];
		
		TileEntity pull = worldObj.getTileEntity(xCoord + front.offsetX, yCoord + front.offsetY, zCoord + front.offsetZ);
		TileEntity push = worldObj.getTileEntity(xCoord - front.offsetX, yCoord - front.offsetY, zCoord - front.offsetZ);
		
		if (!(pull instanceof IInventory)) return; // Nowhere to pull from
		int slot = getSlot((IInventory)pull, front.ordinal(), false);
		if (slot == -1) return; // Nothing to pull
		
		if (!(push instanceof IInventory) && !worldObj.getBlock(xCoord - front.offsetX, yCoord - front.offsetY, zCoord - front.offsetZ).isNormalCube())
		{
			ItemStack is = ((IInventory)pull).getStackInSlot(slot);
			((IInventory)pull).setInventorySlotContents(slot, null);
			EntityItem drop = new EntityItem(worldObj, xCoord - front.offsetX + 0.5D, yCoord - front.offsetY + 0.5D, zCoord - front.offsetZ + 0.5D, is);
			worldObj.spawnEntityInWorld(drop);
		}
		
		if (push != null && push instanceof IInventory)
		{
			ItemStack is = ((IInventory)pull).getStackInSlot(slot);
			ItemStack remaining = TileEntityHelper.tryInsertItem((IInventory)push, is, front.ordinal());
			((IInventory)pull).setInventorySlotContents(slot, remaining);
		}
	}
}