package WorldSimulator;

import WorldSimulator.Animals.*;
import WorldSimulator.Plants.*;

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
        // populate the world
        int wolfCount = 3;
        int sheepCount = 3;
        int foxCount = 3;
        int turtleCount = 3;
        int antelopeCount = 3;
        int grassCount = 3;
        int dandelionCount = 2;
        int guaranaCount = 2;
        int belladonnaCount = 1;
        int heracleumCount = 1;
        for (int i = 0; i < wolfCount; i++)
        {
            Wolf newWolf = new Wolf(world.GetRandomEmptyTile());
            world.AddOrganism(newWolf);
        }
        for (int i = 0; i < sheepCount; i++)
        {
            Sheep newSheep = new Sheep(world.GetRandomEmptyTile());
            world.AddOrganism(newSheep);
        }
        for (int i = 0; i < foxCount; i++)
        {
            Fox newFox = new Fox(world.GetRandomEmptyTile());
            world.AddOrganism(newFox);
        }
        for (int i = 0; i < turtleCount; i++)
        {
            Turtle newTurtle = new Turtle(world.GetRandomEmptyTile());
            world.AddOrganism(newTurtle);
        }
        for (int i = 0; i < antelopeCount; i++)
        {
            Antelope newAntelope = new Antelope(world.GetRandomEmptyTile());
            world.AddOrganism(newAntelope);
        }
        for (int i = 0; i < grassCount; i++)
        {
            Grass newGrass = new Grass(world.GetRandomEmptyTile());
            world.AddOrganism(newGrass);
        }
        for (int i = 0; i < dandelionCount; i++)
        {
            Dandelion newDandelion = new Dandelion(world.GetRandomEmptyTile());
            world.AddOrganism(newDandelion);
        }
        for (int i = 0; i < guaranaCount; i++)
        {
            Guarana newGuarana = new Guarana(world.GetRandomEmptyTile());
            world.AddOrganism(newGuarana);
        }
        for (int i = 0; i < belladonnaCount; i++)
        {
            Belladonna newBelladonna = new Belladonna(world.GetRandomEmptyTile());
            world.AddOrganism(newBelladonna);
        }
        for (int i = 0; i < heracleumCount; i++)
        {
            Heracleum newHeracleum = new Heracleum(world.GetRandomEmptyTile());
            world.AddOrganism(newHeracleum);
        }
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
