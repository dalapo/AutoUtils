package dalapo.autoutils.block;

import dalapo.autoutils.tileentity.TileEntityItemRedis;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockItemRedis extends AutoUtilBlock
{
	public BlockItemRedis(Material mtl, String name)
	{
		super(mtl, name);
	}
	
	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack is)
	{
		world.addTileEntity(new TileEntityItemRedis());
	}
}