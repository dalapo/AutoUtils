package dalapo.autoutils.helper;

import dalapo.autoutils.reference.NameList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public class TextureRegistryHelper {
	public static IIcon registerTexture(String filename, IIconRegister register)
	{
		String texName = NameList.MODID + ":" + filename;
		return register.registerIcon(texName);
	}
	
	public static String formatGuiName(String filename)
	{
		String formatted = "autoutils:textures/gui/" + filename + ".png";
		return formatted;
	}
}