package WorldSimulator;

import WorldSimulator.Animals.*;
import WorldSimulator.Plants.*;

import java.io.*;
import java.util.Scanner;

public class Game
{
    private World world;
    private Human player;

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
    public void SaveWorld(String filename)
    {
        System.out.println("Saving world state");
        try
        {
            FileWriter fileWriter = new FileWriter(filename);

            // save world stats
            fileWriter.write(world.GetSize().x + " " + world.GetSize().y + " " + world.GetTurnNumber());
            fileWriter.write(System.lineSeparator());

            // save the organisms
            for (Organism org : world.GetOrganisms())
            {
                fileWriter.write(org.getSymbol() + " " + org.getPos().x + " " + org.getPos().y + " " + org.getStrength());

                if (org instanceof Human){
                    System.out.println("Writing human");
                    fileWriter.write(" " + ((Human)org).getAbilityDurationLeft());
                }

                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();

            System.out.println("World state saved successfully!");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    public void LoadWorld(String filename)
    {
        System.out.println("Loading world state");

        try
        {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            Vector2 size = new Vector2();
            int turnNum;
            size.x = scanner.nextInt();
            size.y = scanner.nextInt();
            turnNum = scanner.nextInt();
            System.out.println("World stats: " + size.x + "," + size.y + " " + turnNum);

            // create a new world
            world = new World(size.x, size.y);
            world.SetTurnNumber(turnNum);

            // load the organisms
            while (scanner.hasNext())
            {
                System.out.println("Reading organism");
                char symbol;
                Vector2 pos = new Vector2();
                int strength;

                symbol = scanner.next().charAt(0);
                System.out.println(symbol);
                pos.x = scanner.nextInt();
                pos.y = scanner.nextInt();
                strength = scanner.nextInt();

                Organism newOrg = CreateOrganism(symbol, pos);
                newOrg.strength = strength;

                if (newOrg instanceof Human)
                {
                    int ability = scanner.nextInt();
                    ((Human)newOrg).setAbilityDurationLeft(ability);
                    player = (Human)newOrg;
                }

                world.AddOrganism(newOrg);
            }

            scanner.close();

            System.out.println("World state loaded successfully");
        }
        catch (IOException e)
        {
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }


    public Organism CreateOrganism(char symbol, Vector2 pos)
    {
        switch (symbol)
        {
            case 'H':
                return new Human(pos);
            case 'W':
                return new Wolf(pos);
            case 'S':
                return new Sheep(pos);
            case 'F':
                return new Fox(pos);
            case 'T':
                return new Turtle(pos);
            case 'A':
                return new Antelope(pos);
            case ',':
                return new Grass(pos);
            case 'm':
                return new Dandelion(pos);
            case 'g':
                return new Guarana(pos);
            case '?':
                return new Belladonna(pos);
            case '%':
                return new Heracleum(pos);
            default:
                System.out.println("Unknown organism symbol '" + symbol + "'");
                return null;
        }
    }
}
