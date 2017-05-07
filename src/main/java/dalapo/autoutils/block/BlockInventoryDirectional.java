package dalapo.autoutils.block;

import dalapo.autoutils.tileentity.ActionOnRedstone;
import dalapo.autoutils.tileentity.TileEntityStackMover;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockInventoryDirectional extends BlockDirectional {

	public BlockInventoryDirectional(Material mtl, String name) {
		super(mtl, name);
	}
	
	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourID)
	{
		super.onNeighborBlockChange(world, x, y, z, neighbourID);
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof ActionOnRedstone)
		{
			((ActionOnRedstone)te).performAction();
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par)
	{
		IInventory inv = (IInventory)world.getTileEntity(x, y, z);
		for (int i=0; i<inv.getSizeInventory(); i++)
		{
			if (inv.getStackInSlot(i) != null)
			{
				EntityItem ei = new EntityItem(world, x+0.5, y+0.5, z+0.5, inv.getStackInSlot(i));
				world.spawnEntityInWorld(ei);
			}
		}
	}
	
	public abstract TileEntity createTileEntity(World world, int meta);
}