package dalapo.autoutils.gui;

import dalapo.autoutils.tileentity.TileEntityAutoUtils;
import dalapo.autoutils.tileentity.TileEntityBasicInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBasicInventory extends Container {

	private int invRows;
	private int invCols;
	
	private TileEntityBasicInventory te;
	
	public ContainerBasicInventory(int rows, int cols, TileEntityBasicInventory te, IInventory playerInv)
	{
		invRows = rows;
		invCols = cols;
		this.te = te;
		
		int slot = 0;
		
		// TE inventory; will auto-centre if I got the math right
		for (int row=0; row<rows; row++)
		{
			for (int col=0; col<cols; col++, slot++)
			{
				// TODO: Uncentre the y-axis if necessary to prevent overlap w/ player inventory
				this.addSlotToContainer(new Slot(te, slot, (89 - (9 * cols)) + (col * 18), (71 - (18 * rows)) + (row * 18)));
			}
		}
		
		// Player inventory
		// Copypasta from modding tutorial
		for (int y = 0; y < 3; ++y)
		{
	        for (int x = 0; x < 9; ++x, slot++)
	        {
	            this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	        }
	    }
		
		// Player hotbar
		for (int i=0; i<9; i++)
		{
			// Copypasta from modding tutorial
			this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer ep, int slot)
	{
		return null;
	}
	
	public int getRows()
	{
		return invRows;
	}
	
	public int getCols()
	{
		return invCols;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer ep) {
		return te.isUseableByPlayer(ep);
	}
}