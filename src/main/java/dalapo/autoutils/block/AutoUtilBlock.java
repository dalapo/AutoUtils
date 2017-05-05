package dalapo.autoutils.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dalapo.autoutils.reference.NameList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

public class AutoUtilBlock extends Block {

	final String name;
	
	protected AutoUtilBlock(Material mtl, String name) {
		super(mtl);
		this.name = name;
		this.setBlockName(name);
//		this.setBlockTextureName(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.blockIcon = register.registerIcon(getUnwrappedUnlocalizedName(this.getName()));
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