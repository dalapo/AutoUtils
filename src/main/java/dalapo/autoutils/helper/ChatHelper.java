package dalapo.autoutils.helper;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class ChatHelper {
	private ChatHelper() {}
	
	public static void sendMessage(String msg)
	{
		if (FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT))
		{
			if (Minecraft.getMinecraft().thePlayer != null)
			{
				Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentTranslation(msg));
//				sendChatToPlayer(Minecraft.getMinecraft().thePlayer, msg);
			}
		}
	}
	
	public static void sendCoords(String label, int x, int y, int z)
	{
		String str = label + String.format("(%s, %s, %s)", x, y, z);
		sendMessage(str);
	}
	
	private static void sendChatToPlayer(EntityPlayer ep, String msg)
	{
		String[] parts = msg.split("\\n");
		for (int i=0; i<parts.length; i++)
		{
			ChatComponentTranslation chatPart = new ChatComponentTranslation(parts[i]);
			ep.addChatComponentMessage(chatPart);
		}
	}
}