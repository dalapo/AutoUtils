package dalapo.autoutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.helper.TextureRegistryHelper;
import dalapo.autoutils.reference.NameList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class AutoUtilBlock extends Block {

	final String name;
	
	protected AutoUtilBlock(Material mtl, String name) {
		super(mtl);
		this.name = name;
		setBlockName(name);
		setCreativeTab(CreativeTabs.tabRedstone);
		setHardness(4F);
		setHarvestLevel("pickaxe", 1);
		setResistance(4F);
//		this.setBlockTextureName(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int dir)
	{
		return blockIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = TextureRegistryHelper.registerTexture(getUnwrappedUnlocalizedName(this.getName()), register);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", NameList.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}