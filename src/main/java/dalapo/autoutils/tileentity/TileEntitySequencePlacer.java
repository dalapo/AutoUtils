package dalapo.autoutils.tileentity;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.ItemIDList;
import dalapo.autoutils.reference.UsefulLists;
import dalapo.autoutils.registry.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntitySequencePlacer extends TileEntityBasicInventory implements ActionOnRedstone
{
	private int nextSlot;
	private boolean isPowered = false;
	public static final String id = "SequencePlacer";
	
	public TileEntitySequencePlacer() {
		super(18, "sequenceplacer");
		Logger.info("Created TileEntitySequencePlacer");
		nextSlot = 0;
	}
	
	private void placeNextBlock()
	{
		ForgeDirection front = UsefulLists.directions[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)];
		if (!worldObj.isAirBlock(xCoord+front.offsetX, yCoord+front.offsetY, zCoord+front.offsetZ)) return;
		ItemStack itemstack = getStackInSlot(nextSlot);
		boolean flag = false;
		for (int i=0; i<18; i++)
		{
			itemstack = getStackInSlot(nextSlot);
			if (itemstack != null && itemstack.getItem() instanceof ItemBlock)
			{
				flag = true;
				decrStackSize(nextSlot, 1);
			}
			nextSlot++;
			if (nextSlot > 17) nextSlot = 0;
			if (flag) break;
		}
		if (!flag) return;
		{
			System.out.println(itemstack);
			Block block = ((ItemBlock)itemstack.getItem()).field_150939_a;
			worldObj.setBlock(xCoord+front.offsetX, yCoord+front.offsetY, zCoord+front.offsetZ, ((ItemBlock)itemstack.getItem()).field_150939_a);
			worldObj.setBlockMetadataWithNotify(xCoord+front.offsetX, yCoord+front.offsetY, zCoord+front.offsetZ, itemstack.getItemDamage(), 3);
		}
	}

	@Override
	public void performAction() {
		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))
		{
			if (!isPowered)
			{
				placeNextBlock();
				isPowered = true;
			}
		}
		else isPowered = false;
		
	}

	@Override
	public String getDisplayName() {
		return "Block Order";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack)
	{
		return itemstack.getItem() instanceof ItemBlock;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("nextSlot", nextSlot);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		nextSlot = nbt.getInteger("nextSlot");
	}

}