package dalapo.autoutils.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class AbstractMessageHandler<T extends IMessage> implements IMessageHandler<T, IMessage> {

	@Override
	public IMessage onMessage(T message, MessageContext ctx) {
		return null;
	}

}