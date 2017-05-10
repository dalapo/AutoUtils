package dalapo.autoutils.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import dalapo.autoutils.helper.ChatHelper;
import dalapo.autoutils.logging.Logger;
import dalapo.autoutils.tileentity.TileEntityItemRedis;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class PacketItemRedis extends AutoUtilsPacket
{
	int x;
	int y;
	int z;
	int[] vals = new int[5];
	int side;
	int change;
	boolean toggle;
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		for (int i=0; i<5; i++)
		{
			vals[i] = buf.readInt();
		}
		side = buf.readInt();
		change = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		for (int i : vals)
		{
			buf.writeInt(i);
		}
		buf.writeInt(side);
		buf.writeInt(change);
	}
	
	public PacketItemRedis(TileEntityItemRedis te, int s, int c, boolean toggle)
	{
		handler = new Handler();
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		for (int i=0; i<5; i++)
		{
			vals[i] = te.getRatio(i);
		}
		side = s;
		change = c;
		this.toggle = toggle;
	}
	
	public PacketItemRedis()
	{
		// Apparently this is a thing that you need
	}
	
	@Override
	protected void actuallyDoHandle(AutoUtilsPacket message, World world, EntityPlayer ep, boolean isToClient)
	{
		if (!isToClient)
		{
			TileEntityItemRedis te = (TileEntityItemRedis)world.getTileEntity(((PacketItemRedis)message).x, ((PacketItemRedis)message).y, ((PacketItemRedis)message).z);
			if (!(te instanceof TileEntityItemRedis)) throw new RuntimeException("Dave doesn't know how packets work!");
			((TileEntityItemRedis)te).changeRatio(((PacketItemRedis)message).side, ((PacketItemRedis)message).change);
			if (((PacketItemRedis)message).toggle) te.toggleSplit();
			te.markDirty();
			for (int i=0; i<5; i++)
			{
				vals[i] = te.getRatio(i);
			}
			PacketHandler.sendToPlayer(message, (EntityPlayerMP)ep);
		}
		else
		{
			Logger.info(world instanceof WorldServer);
			TileEntityItemRedis te = (TileEntityItemRedis)world.getTileEntity(((PacketItemRedis)message).x, ((PacketItemRedis)message).y, ((PacketItemRedis)message).z);
			if (!(te instanceof TileEntityItemRedis)) throw new RuntimeException("Dave still doesn't know how packets work!");
			for (int i=0; i<5; i++)
			{
				te.setRatio(i, vals[i]);
				Logger.info(vals[i]);
				te.markDirty();
			}
		}
	}
	
	public int getID()
	{
		return 1;
	}
}