package manhuntgame.network.event;

import io.netty.buffer.ByteBuf;

public interface INetworkEvent extends IEvent
{
	void write(ByteBuf b);
	
	void read(ByteBuf b);
	
	void execute();
}
