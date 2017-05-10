package dalapo.autoutils.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

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
	}
	
	public static void registerMessage(Class handlerClass, Class messageClass, Side side)
	{
		INSTANCE.registerMessage(handlerClass, messageClass, nextID(), side);
	}
	
	public static void sendToServer(AutoUtilsPacket packet)
	{
		PacketHandler.INSTANCE.sendToServer(packet);
	}
}