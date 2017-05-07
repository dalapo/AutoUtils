package dalapo.autoutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.AutoUtils;
import dalapo.autoutils.helper.TextureRegistryHelper;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.GUIIDList;
import dalapo.autoutils.tileentity.TileEntitySequencePlacer;
import dalapo.autoutils.tileentity.TileEntityStackMover;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSequencePlacer extends BlockInventoryDirectional
{
	private IIcon front;
	private IIcon other;
	public BlockSequencePlacer(Material mtl, String name)
	{
		super(mtl, name);
		setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemstack)
	{
		super.onBlockPlacedBy(world, x, y, z, placer, itemstack);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int side, float hitX, float hitY, float hitZ)
	{
		super.onBlockActivated(world, x, y, z, ep, side, hitX, hitY, hitZ);
		if (!world.isRemote)
		{
			ep.openGui(AutoUtils.instance, GUIIDList.ORDERPLACER, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public IIcon getIcon(int side, int dir)
	{
		if (side == dir) return front;
		else return other;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		front = TextureRegistryHelper.registerTexture("sequenceplacerfront", register);
		other = TextureRegistryHelper.registerTexture("stackmoverside", register);
	}
	@Override
	public boolean hasTileEntity(int meta)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		Logger.info("Entered createTileEntity in BlockSequencePlacer");
		return new TileEntitySequencePlacer();
	}
}