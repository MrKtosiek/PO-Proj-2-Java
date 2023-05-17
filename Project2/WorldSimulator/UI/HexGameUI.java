package WorldSimulator.UI;

import WorldSimulator.Game;
import WorldSimulator.Vector2;

public class HexGameUI extends GameUI
{
    public HexGameUI(Game game, String title)
    {
        super(game, title);
    }

    @Override
    protected int GetBoardWidth()
    {
        return (int)(game.getWorld().GetSize().y * tileSize * 1.5);
    }
    @Override
    protected int GetBoardHeight()
    {
        return game.getWorld().GetSize().x * tileSize;
    }
    @Override
    protected Vector2 GetTilePos(int x, int y)
    {
        return new Vector2(y * tileSize + (game.getWorld().GetSize().x - x) * tileSize / 2, x * tileSize);
    }
}
