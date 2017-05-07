package dalapo.autoutils.tileentity;

import dalapo.autoutils.helper.TileEntityHelper;
import dalapo.autoutils.reference.UsefulLists;
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

public class TileEntityStackMover extends TileEntityBasicInventory implements ISidedInventory {
//	ItemStack[] filter;
	private boolean isPowered = false;
	public static final String id = "StackMover";
	private int filterSlot = 0;
	public TileEntityStackMover()
	{
		super(9, "stackmover");
	}
	
	public void performTick() // Called when a block adjacent to the TE updates.
	{
		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			if (!isPowered)
			{
//				Logger.info("About to enter transferStack()");
				transferStack();
				isPowered = true;
			}
		}
		else isPowered = false;
	}
	
	@Override
	public String getDisplayName()
	{
		return "Filter";
	}
	private boolean shouldFilter()
	{
		for (int i=0; i<9; i++)
		{
			if (getStackInSlot(i) != null) return true;
		}
		return false;
	}
	
	private int checkFilter(ItemStack is)
	{
		if (!shouldFilter()) return 9;
		for (int i=0; i<9; i++)
		{
			if (getStackInSlot(i) != null && getStackInSlot(i).getItem().equals(is.getItem())) return i;
		}
		return -1;
	}
	
	private int[] getSlot(IInventory te, int side, boolean insert)
	{
		// pair[0] = filter slot, pair[1] = inv slot
		if (te == null) return null;
		for (int i=0; i<te.getSizeInventory(); i++)
		{
			if (!insert && te.getStackInSlot(i) == null) continue;
			int filterSlot = checkFilter(te.getStackInSlot(i));
			if (TileEntityHelper.isValidSlotForSide(te, side, i) && te.getStackInSlot(i) != null && filterSlot != -1)
			{
				return new int[] {filterSlot, i};
			}
		}
		return null;
	}
	
	public void transferStack()
	{
		ForgeDirection front = UsefulLists.directions[worldObj.getBlockMetadata(xCoord, yCoord, zCoord) % 6];
		
		TileEntity pull = worldObj.getTileEntity(xCoord + front.offsetX, yCoord + front.offsetY, zCoord + front.offsetZ);
		TileEntity push = worldObj.getTileEntity(xCoord - front.offsetX, yCoord - front.offsetY, zCoord - front.offsetZ);
		if (!(pull instanceof IInventory)) return; // Nowhere to pull from
//		if (!(push instanceof IInventory) && !worldObj.getBlock(xCoord - front.offsetX, yCoord - front.offsetY, zCoord - front.offsetZ).isBlockNormalCube()) return;
		int[] pair = getSlot((IInventory)pull, front.ordinal(), false);
		if (pair == null) return; // Nothing to pull
		
		// Condition for reaching here: The TE has found an ItemStack to move
		// The slot of the ItemStack is stored in pair[1]
		int moveSize = pair[0] == 9 ? 64 : getStackInSlot(pair[0]).stackSize;
		ItemStack toMove = ((IInventory)pull).decrStackSize(pair[1], moveSize);
		
		if (!(push instanceof IInventory))
		{
			EntityItem drop = new EntityItem(worldObj, xCoord - front.offsetX + 0.5D, yCoord - front.offsetY + 0.5D, zCoord - front.offsetZ + 0.5D, toMove);
			worldObj.spawnEntityInWorld(drop);
		}
		
		if (push != null && push instanceof IInventory)
		{
			ItemStack remaining = TileEntityHelper.tryInsertItem((IInventory)push, toMove, front.ordinal());
			if (((IInventory)pull).getStackInSlot(pair[1]) == null)
			{
				((IInventory)pull).setInventorySlotContents(pair[1], remaining);
			}
			else
			{
				ItemStack is = ((IInventory)pull).getStackInSlot(pair[1]);
				// Note: It is impossible for this statement to give an illegal (>64) stack size.
				if (remaining != null) is.stackSize += remaining.stackSize;
				((IInventory)pull).setInventorySlotContents(pair[1], is);
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public String getInventoryName() {
		return "Filter";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

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
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}
}