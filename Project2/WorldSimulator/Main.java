package WorldSimulator;

import WorldSimulator.UI.GridGameUI;
import WorldSimulator.UI.HexGameUI;
import WorldSimulator.UI.StartMenu;

public class Main
{
    public static void main(String[] args)
    {
        StartMenu startMenu = new StartMenu("Start menu");
        startMenu.AddListener(e ->
        {
            Vector2 worldSize = startMenu.getWorldSize();

            Game game = new Game(worldSize.x, worldSize.y, startMenu.isHexChecked());
            if (startMenu.isHexChecked())
                game.setGameUI(new HexGameUI(game, "Filip Jezierski 196333"));
            else
                game.setGameUI(new GridGameUI(game, "Filip Jezierski 196333"));

            startMenu.dispose();
        });
    }
}
