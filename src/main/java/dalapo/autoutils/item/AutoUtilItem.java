package dalapo.autoutils.item;

import dalapo.autoutils.helper.TextureRegistryHelper;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class AutoUtilItem extends Item {
	private final String name;
	
	public AutoUtilItem(String name)
	{
		super();
		this.name = name;
		this.setUnlocalizedName(name);
		this.setTextureName(TextureRegistryHelper.formatTexName(name));
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}
	
	public String getName()
	{
		return name;
	}
}