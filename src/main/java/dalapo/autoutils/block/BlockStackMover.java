package dalapo.autoutils.block;

import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.tileentity.TileEntityStackMover;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStackMover extends BlockDirectional {

	public BlockStackMover(Material mtl, String name) {
		super(mtl, name);
		this.setCreativeTab(CreativeTabs.tabRedstone);
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
		if (te != null && te instanceof TileEntityStackMover)
		{
			((TileEntityStackMover)te).performTick();
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		Logger.info("Entered createTileEntity in BlockStackMover");
		return new TileEntityStackMover();
	}
}