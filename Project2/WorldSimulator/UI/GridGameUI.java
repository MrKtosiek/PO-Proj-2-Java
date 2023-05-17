package WorldSimulator.UI;

import WorldSimulator.Game;
import WorldSimulator.Vector2;

public class GridGameUI extends GameUI
{
    public GridGameUI(Game game, String title)
    {
        super(game, title);
    }

    @Override
    protected int GetBoardWidth()
    {
        return game.getWorld().GetSize().y * tileSize;
    }
    @Override
    protected int GetBoardHeight()
    {
        return game.getWorld().GetSize().x * tileSize;
    }
    @Override
    protected Vector2 GetTilePos(int x, int y)
    {
        return new Vector2(y * tileSize, x * tileSize);
    }
}
