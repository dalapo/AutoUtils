package dalapo.autoutils.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import dalapo.autoutils.logging.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHandler
{
	private static int packetID = 0;
	public static SimpleNetworkWrapper INSTANCE;
	
	public static int nextID()
	{
		return packetID++;
	}
	
	public static void registerMessages(String channelName)
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerMessages();
	}
	
	public static void registerMessages()
	{
		registerMessage(PacketItemRedis.Handler.class, PacketItemRedis.class, Side.SERVER);
		registerMessage(PacketItemRedis.Handler.class, PacketItemRedis.class, Side.CLIENT);
	}
	
	public static void registerMessage(Class handlerClass, Class messageClass, Side side)
	{
		INSTANCE.registerMessage(handlerClass, messageClass, nextID(), side);
	}
	
	public static void sendToServer(AutoUtilsPacket packet)
	{
		PacketHandler.INSTANCE.sendToServer(packet);
	}
	
	public static void sendToPlayer(AutoUtilsPacket packet, EntityPlayerMP ep)
	{
		Logger.info("Entered sendToPlayer in PacketHandler");
//		if (!(ep instanceof EntityPlayerMP)) throw new IllegalArgumentException("Attempted to send an illegal packet.");
		PacketHandler.INSTANCE.sendTo(packet, (EntityPlayerMP)ep);
	}
}