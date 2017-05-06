package dalapo.autoutils.tileentity;

import dalapo.autoutils.reference.UsefulLists;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileEntityDirectional extends TileEntity {
	ForgeDirection front;
	
	public TileEntityDirectional(ForgeDirection d)
	{
		front = d;
	}
	
	public TileEntityDirectional(int meta)
	{
		this(UsefulLists.directions[meta]);
	}
}