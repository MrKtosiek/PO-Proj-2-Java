package WorldSimulator;

public class Main
{
    public static void main(String[] args)
    {
        Vector2 worldSize = new Vector2(15,10);

        Game game = new Game(worldSize.x, worldSize.y);
        GameUI gameUI = new GameUI(game, "Filip Jezierski 196333");

        gameUI.DrawOrganisms();
    }
}
