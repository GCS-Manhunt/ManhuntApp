package manhuntgame.network.event;

import io.netty.buffer.ByteBuf;
import manhuntgame.network.NetworkUtils;
import manhuntgame.network.ServerHandler;

import java.util.UUID;

// Event that is sent from client to server specifying the client's details (version, id, username)
// First event sent from client
public class EventSendClientDetails extends PersonalEvent implements IServerThreadEvent
{
	public int version;
	public UUID clientID;
	public String username;
	
	public EventSendClientDetails()
	{
		
	}
	
	public EventSendClientDetails(int version, UUID clientID, String username)
	{
		this.version = version;
		this.clientID = clientID;
		this.username = username;
	}
	
	@Override
	public void write(ByteBuf b) 
	{
		b.writeInt(this.version);
		NetworkUtils.writeString(b, clientID.toString());
		NetworkUtils.writeString(b, username);
	}
	
	@Override
	public void read(ByteBuf b) 
	{
		this.version = b.readInt();
		this.clientID = UUID.fromString(NetworkUtils.readString(b));
		this.username = NetworkUtils.readString(b);
	}

	@Override
	public void execute()
	{
		// Don't use this
	}

	@Override
	public void execute(ServerHandler s)
	{

	}
}
