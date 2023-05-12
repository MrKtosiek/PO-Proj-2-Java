package WorldSimulator;

import WorldSimulator.Animals.Human;

public class Game
{
    private World world;
    private Human player;

    public Game(int height, int width)
    {
        world = new World(height, width);
        player = new Human(new Vector2( height / 2, width / 2 ));
        world.AddOrganism(player);
        GenerateWorld();
        DrawMenu();
        world.DrawWorld();
    }

    public World getWorld()
    {
        return world;
    }

    public void GenerateWorld()
    {

    }
    public void SimulateWorld(char input)
    {

    }
    public void SaveWorld()
    {

    }
    public void LoadWorld()
    {

    }
    public void DrawMenu()
    {

    }
}
