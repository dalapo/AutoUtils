package dalapo.autoutils.tileentity;

import dalapo.autoutils.helper.ChatHelper;
import dalapo.autoutils.helper.MiscHelper;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.UsefulLists;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityItemRedis extends TileEntityAutoUtils
{
	private boolean splitStacks;
	private int next;
	private int[] ratios;
	
	public TileEntityItemRedis()
	{
		splitStacks = true;
		next = 0;
		ratios = new int[] {1, 1, 1, 1, 1}; // Down, North, South, West, East
	}
	
	private int getSide(int n)
	{
		if (n > MiscHelper.sum(ratios)) return -1;
		int count = 0;
		int i=0;
		for (i=0; i<5; i++)
		{
			count += ratios[i];
			if (count > n) break;
		}
//		if (i != 0) i += 1;
		return i;
	}
	
	public int getRatio(int side)
	{
		if (MiscHelper.isInRange(side, 0, 4)) return ratios[side];
		return -1;
	}
	
	public void setRatio(int side, int val)
	{
		if (MiscHelper.isInRange(side, 0, 4) && val > 0)
		{
			ratios[side] = val;
		}
	}
	
	public void changeRatio(int side, int val)
	{
		if (MiscHelper.isInRange(side, 0, 4) && ratios[side] + val >= 0)
		{
			ratios[side] += val;
		}
	}
	
	public void toggleSplit()
	{
		splitStacks = !splitStacks;
	}
	
	// TODO: Support for inserting into adjacent inventories
	public void redistributeItems(ItemStack in)
	{
		if (worldObj.isRemote) return;
		if (splitStacks)
		{
			ItemStack[] stacks = new ItemStack[5];
			for (int i=0; i<5; i++) stacks[i] = new ItemStack(in.getItem(), 0, in.getItemDamage());
			int itemsRemaining = in.stackSize;
			while (itemsRemaining > 0)
			{
				stacks[getSide(next++)].stackSize++;
				if (next == MiscHelper.sum(ratios)) next = 0;
				itemsRemaining--;
			}
			for (int i=0; i<6; i++)
			{
				if (i == 1) continue; // Skip direction 1 (up)
				ForgeDirection dir = ForgeDirection.getOrientation(i);
				if (stacks[i==0?i:i-1].stackSize != 0)
				{
					EntityItem ei = new EntityItem(worldObj, xCoord + dir.offsetX + 0.5D, yCoord + dir.offsetY + 0.5D, zCoord + dir.offsetZ + 0.5D, (i == 0 ? stacks[i] : stacks[i-1]));
					ei.motionX = 0;
					ei.motionY = 0;
					ei.motionZ = 0;
					worldObj.spawnEntityInWorld(ei);
				}
			}
		}
		else
		{
			Logger.info("Next: " + next);
			ForgeDirection dir = UsefulLists.directionsMinusUp[getSide(next++)];
			Logger.info(dir.name());
			if (next >= MiscHelper.sum(ratios)) next = 0;
			EntityItem ei = new EntityItem(worldObj, xCoord + dir.offsetX + 0.5D, yCoord + dir.offsetY + 0.5D, zCoord + dir.offsetZ + 0.5D, in);
			ei.motionX = 0;
			ei.motionY = 0;
			ei.motionZ = 0;
			worldObj.spawnEntityInWorld(ei);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		String[] dirs = new String[] {"down", "north", "south", "west", "east"};
		for (int i=0; i<5; i++)
		{
			nbt.setInteger(dirs[i], ratios[i]);
		}
		nbt.setInteger("Next", next);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		String[] dirs = new String[] {"down", "north", "south", "west", "east"};
		for (int i=0; i<5; i++)
		{
			ratios[i] = nbt.getInteger(dirs[i]);
		}
		next = nbt.getInteger("next");
	}
}