package WorldSimulator;

public class Main
{
    public static void main(String[] args)
    {
        StartMenu startMenu = new StartMenu("Start menu");
        startMenu.AddListener(e ->
        {
            Vector2 worldSize = startMenu.getWorldSize();

            Game game = new Game(worldSize.x, worldSize.y, startMenu.isHexChecked());
            GameUI gameUI = new GameUI(game, "Filip Jezierski 196333");

            gameUI.DrawOrganisms();
        });
    }
}
