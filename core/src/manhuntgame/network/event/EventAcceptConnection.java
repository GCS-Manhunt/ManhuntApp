package manhuntgame.network.event;

import io.netty.buffer.ByteBuf;
import manhuntgame.app.App;
import manhuntgame.app.GameState;
import manhuntgame.network.NetworkUtils;
import manhuntgame.ui.screen.ScreenGamePreview;
import manhuntgame.ui.screen.ScreenSelectUsername;

public class EventAcceptConnection extends PersonalEvent
{
    public String gameName;
    public String location;
    public String time;
    public String[] rules;
    public int code;

    @Override
    public void write(ByteBuf b)
    {
        NetworkUtils.writeString(b, gameName);
        NetworkUtils.writeString(b, location);
        NetworkUtils.writeString(b, time);
        b.writeInt(rules.length);

        for (String s: rules)
            NetworkUtils.writeString(b, s);

        b.writeInt(code);
    }

    @Override
    public void read(ByteBuf b)
    {
        gameName = NetworkUtils.readString(b);
        location = NetworkUtils.readString(b);
        time = NetworkUtils.readString(b);
        rules = new String[b.readInt()];

        for (int i = 0; i < rules.length; i++)
        {
            rules[i] = NetworkUtils.readString(b);
        }

        code = b.readInt();
    }

    @Override
    public void execute()
    {
        if (this.clientID == null)
        {
            App.app.screen = new ScreenGamePreview(gameName, location, time, rules);
            GameState.game = new GameState();
            GameState.game.code = code;
        }
    }
}
