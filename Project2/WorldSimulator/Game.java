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

    public Human getPlayer()
    {
        return player;
    }

    public void GenerateWorld()
    {

    }
    public void SimulateWorld(int input)
    {
        if (world.IsPlayerAlive())
            player.setNextAction(input);

        world.ClearLogs();
        world.ExecuteTurn();
        
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
