package dalapo.autoutils.block;

import dalapo.autoutils.event.RSToggleEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class BlockRSNotifier extends AutoUtilBlock {

	public BlockRSNotifier(Material mtl, String name) {
		super(mtl, name);
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	/*
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourID)
	{
		RSToggleEvent event;
		if (world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			event = new RSToggleEvent(x, y, z, world, this, 0, true);
		}
		else event = new RSToggleEvent(x, y, z, world, this, 0, false);
		MinecraftForge.EVENT_BUS.post(event);
	}
	*/
}