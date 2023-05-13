package WorldSimulator;

import WorldSimulator.Animals.Human;

public class Game
{
    private final World world;
    private final Human player;

    public Game(int width, int height)
    {
        world = new World(width, height);
        player = new Human(new Vector2( width / 2, height / 2 ));
        world.AddOrganism(player);
        GenerateWorld();
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
