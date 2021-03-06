package manhuntgame.network;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NetworkUtils 
{
	public static final Charset charset = StandardCharsets.UTF_8;
	
	public static String readString(ByteBuf b)
	{
		int l = b.readInt();
		return b.readCharSequence(l, charset).toString();
	}
	
	public static void writeString(ByteBuf b, String s)
	{
		int extra = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == '\u00A7')
				extra++;
		
		b.writeInt(s.length() + extra);
		b.writeCharSequence(s, charset);
	}
}
