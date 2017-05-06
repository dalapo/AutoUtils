package dalapo.autoutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.reference.NameList;
import dalapo.autoutils.tileentity.TileEntityStackMover;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockStackMover extends BlockDirectional {

	private IIcon frontSide;
	private IIcon backSide;
	private IIcon otherSide;
	
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
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int dir)
	{
		if (side == dir) return frontSide;
		return otherSide;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		super.registerBlockIcons(register);
		this.otherSide = register.registerIcon("autoutils:stackmoverside");
		this.frontSide = register.registerIcon("autoutils:stackmoverfront");
		this.backSide = register.registerIcon("autoutils:stackmoverback");
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