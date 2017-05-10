package dalapo.autoutils.block;

import dalapo.autoutils.AutoUtils;
import dalapo.autoutils.helper.ChatHelper;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.GUIIDList;
import dalapo.autoutils.tileentity.TileEntityItemRedis;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
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
		world.addTileEntity(createTileEntity(world, 0));
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int s, float f1, float f2, float f3)
	{
		super.onBlockActivated(world, x, y, z, ep, s, f1, f2, f3);
		if (world.isRemote)
		{
			ep.openGui(AutoUtils.instance, GUIIDList.DISTRIBUTOR, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
		return new TileEntityItemRedis();
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		float f = 0.0625F;
		AxisAlignedBB bb = AxisAlignedBB.getBoundingBox((double)x + f, (double)y + f, (double)z + f, (double)(x+1)-f, (double)(y+1)-f, (double)(z+1)-f);
		return bb;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (!(entity instanceof EntityItem)) return;
//		Logger.info(world.getTileEntity(x, y, z) == null);
//		ChatHelper.sendCoords("Entity: ", (int)entity.posX, (int)entity.posY, (int)entity.posZ);
//		ChatHelper.sendCoords("Block: ", x, y, z);
		if ((int)entity.posX == x && (int)entity.posY > y && (int)entity.posZ == z/* && world.getTileEntity(x, y, z) instanceof TileEntityItemRedis*/)
		{
			ItemStack is = ((EntityItem)entity).getEntityItem();
			entity.setDead();
			((TileEntityItemRedis)(world.getTileEntity(x, y, z))).redistributeItems(is);
		}
	}
}