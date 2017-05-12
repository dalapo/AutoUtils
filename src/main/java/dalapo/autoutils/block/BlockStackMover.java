package dalapo.autoutils.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.AutoUtils;
import dalapo.autoutils.helper.MiscHelper;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.GUIIDList;
import dalapo.autoutils.reference.NameList;
import dalapo.autoutils.tileentity.TileEntityStackMover;
import dalapo.autoutils.helper.TextureRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockStackMover extends BlockInventoryDirectional {
	
	private boolean isFiltered;
	
	public BlockStackMover(Material mtl, String name, boolean filter) {
		super(mtl, name);
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.isFiltered = filter;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemstack)
	{
		super.onBlockPlacedBy(world, x, y, z, placer, itemstack);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		super.onBlockActivated(world, x, y, z, entityplayer, par6, par7, par8, par9);
		if (!isFiltered) return false;
		if (!world.isRemote)
		{
			entityplayer.openGui(AutoUtils.instance, GUIIDList.STACKMOVER, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
//		super.registerBlockIcons(register);
		String base = isFiltered ? "filtermover" : "stackmover";
		this.side = TextureRegistryHelper.registerTexture(base + "side", register);
		this.front = TextureRegistryHelper.registerTexture(base + "front", register);
		this.back = TextureRegistryHelper.registerTexture(base + "back", register);
	}
	
	@Override
	public TileEntity createTileEntity(World world, int meta)
	{
//		Logger.info("Entered createTileEntity in BlockStackMover");
		return new TileEntityStackMover();
	}
}