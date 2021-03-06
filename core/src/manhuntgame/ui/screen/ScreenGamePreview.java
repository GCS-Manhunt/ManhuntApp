package manhuntgame.ui.screen;

import manhuntgame.app.App;
import manhuntgame.app.Drawer;
import manhuntgame.ui.Button;

public class ScreenGamePreview extends Screen
{
  public String gameName;
  public String location;
  public String duration;

  public Button back = new Button(540, 1750, 900, 200, "Go back", new Runnable()
  {
    @Override
    public void run()
    {
      App.app.screen = new ScreenMain();
    }
  });

  public Button agree = new Button(540, 1500, 900, 200, "Agree", new Runnable()
  {
    @Override
    public void run()
    {
      App.app.screen = new ScreenHeadings();
    }
  });

  public String[] rules;

  public ScreenGamePreview(String name, String location, String duration, String[] rules)
  {
    this.gameName = name;
    this.location = location;
    this.duration = duration;
    this.rules = rules;
  }

  @Override
  public void update()
  {
    back.update();
    agree.update();
  }

  @Override
  public void draw()
  {
    Drawer d = App.app.drawer;
    d.setColor(255, 0, 0);
    d.setFontSize(135);
    d.drawText(540,100, this.gameName);

    d.setFontSize(90);
    d.drawText(540,250, this.location);
    d.drawText(540,400, this.duration);

    d.drawText(540,550, "Game rules and info:");

    d.setFontSize(35);
    for (int i = 0; i < rules.length; i++)
    {
      d.drawText(25, 700 + 45 * i, rules[i], false);
    }



    back.draw();
    agree.draw();
  }
}