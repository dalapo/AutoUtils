package dalapo.autoutils.block;

import dalapo.autoutils.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockDirectional extends AutoUtilBlock {

	protected BlockDirectional(Material mtl, String name) {
		super(mtl, name);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemstack)
	{
		Logger.info("Entered onBlockPlacedBy in BlockDirectional");
		if (MathHelper.abs(placer.rotationPitch) < 60)
		{
			int angle = MathHelper.floor_double((placer.rotationYaw * 4F) / 360F + 0.5D);
			while (angle >= 4) angle-=4;
			while (angle < 0) angle+=4;
			switch (angle)
			{
			case 0:
				world.setBlockMetadataWithNotify(x, y, z, 0, 3);
				break;
			case 1:
				world.setBlockMetadataWithNotify(x, y, z, 3, 3);
				break;
			case 2:
				world.setBlockMetadataWithNotify(x, y, z, 2, 3);
				break;
			case 3:
				world.setBlockMetadataWithNotify(x, y, z, 1, 3);
				break;
			}
		}
		else
		{
			if (placer.rotationPitch > 0) world.setBlockMetadataWithNotify(x, y, z, 4, 3);
			else world.setBlockMetadataWithNotify(x, y, z, 5, 3);
		}
		world.addTileEntity(createTileEntity(world, world.getBlockMetadata(x, y, z)));
	}

	public abstract TileEntity createTileEntity(World world, int meta);
}