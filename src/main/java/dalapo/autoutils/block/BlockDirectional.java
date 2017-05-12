package dalapo.autoutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.logging.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class BlockDirectional extends AutoUtilBlock {

	public IIcon front;
	public IIcon back;
	public IIcon side;
	
	protected BlockDirectional(Material mtl, String name) {
		super(mtl, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int s, int meta)
	{
		if (s == meta) return front;
		else if (s == ForgeDirection.OPPOSITES[meta]) return back;
		else return side;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemstack)
	{
//		Logger.info("Entered onBlockPlacedBy in BlockDirectional");
		int direction = -1;
		if (MathHelper.abs(placer.rotationPitch) < 60)
		{
			int angle = MathHelper.floor_double((placer.rotationYaw * 4F) / 360F + 0.5D);
			while (angle >= 4) angle-=4;
			while (angle < 0) angle+=4;
			switch (angle)
			{
			case 0:
				direction = 2;
				break;
			case 1:
				direction = 5;
				break;
			case 2:
				direction = 3;
				break;
			case 3:
				direction = 4;
				break;
			}
		}
		else
		{
			if (placer.rotationPitch > 0) direction = 1;
			else direction = 0;
		}
		if (placer.isSneaking()) direction = ForgeDirection.OPPOSITES[direction];
		world.setBlockMetadataWithNotify(x, y, z, direction, 3);
		Logger.info("Direction: " + ForgeDirection.getOrientation(direction).toString() + String.format("(%s)", direction));
//		world.addTileEntity(createTileEntity(world, world.getBlockMetadata(x, y, z) % 6));
	}
	
	@Override
	public int damageDropped(int meta)
	{
		return meta / 6;
	}


}