package dalapo.autoutils.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dalapo.autoutils.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public abstract class AutoUtilsPacket implements IMessage {
	public abstract int getID();
	protected World world;
	protected EntityPlayer player;
	
	protected Handler handler = new Handler();
	
	private final void doHandle(NetHandlerPlayServer net)
	{
		Logger.info(handler == null);
		actuallyDoHandle(this, net.playerEntity.worldObj, net.playerEntity);
	}
	
	protected abstract void actuallyDoHandle(AutoUtilsPacket message, World world, EntityPlayer ep);
	
	public static class Handler implements IMessageHandler<AutoUtilsPacket, IMessage>
	{
		@Override
		public IMessage onMessage(AutoUtilsPacket message, MessageContext ctx)
		{
			message.doHandle(ctx.getServerHandler());
			return null;
		}
	}
}