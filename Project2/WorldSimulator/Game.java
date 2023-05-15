package WorldSimulator;

import WorldSimulator.Animals.*;
import WorldSimulator.Plants.*;

import java.io.*;
import java.util.Scanner;

public class Game
{
    private World world;
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

                if (org instanceof Human)
                    fileWriter.write(((Human)org).getAbilityDurationLeft());

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

                String line = scanner.next();

                symbol = line.charAt(0);
                System.out.println(symbol);
                Scanner lineScanner = new Scanner(line);
                pos.x = lineScanner.nextInt();
                pos.y = lineScanner.nextInt();
                strength = lineScanner.nextInt();

                switch (symbol)
                {
                    case 'H' ->
                    {
                        Human newHuman = new Human(pos);
                        int ability = scanner.nextInt();
                        newHuman.setAbilityDurationLeft(ability);
                        newHuman.strength = strength;
                        world.AddOrganism(newHuman);
                    }
                    case 'W' ->
                    {
                        Wolf newWolf = new Wolf(pos);
                        newWolf.strength = strength;
                        world.AddOrganism(newWolf);
                    }
                    case 'S' ->
                    {
                        Sheep newSheep = new Sheep(pos);
                        newSheep.strength = strength;
                        world.AddOrganism(newSheep);
                    }
                    case 'F' ->
                    {
                        Fox newFox = new Fox(pos);
                        newFox.strength = strength;
                        world.AddOrganism(newFox);
                    }
                    case 'T' ->
                    {
                        Turtle newTurtle = new Turtle(pos);
                        newTurtle.strength = strength;
                        world.AddOrganism(newTurtle);
                    }
                    case 'A' ->
                    {
                        Antelope newAntelope = new Antelope(pos);
                        newAntelope.strength = strength;
                        world.AddOrganism(newAntelope);
                    }
                    case ',' ->
                    {
                        Grass newGrass = new Grass(pos);
                        newGrass.strength = strength;
                        world.AddOrganism(newGrass);
                    }
                    case 'm' ->
                    {
                        Dandelion newDandelion = new Dandelion(pos);
                        newDandelion.strength = strength;
                        world.AddOrganism(newDandelion);
                    }
                    case 'g' ->
                    {
                        Guarana newGuarana = new Guarana(pos);
                        newGuarana.strength = strength;
                        world.AddOrganism(newGuarana);
                    }
                    case '?' ->
                    {
                        Belladonna newBelladonna = new Belladonna(pos);
                        newBelladonna.strength = strength;
                        world.AddOrganism(newBelladonna);
                    }
                    case '%' ->
                    {
                        Heracleum newHeracleum = new Heracleum(pos);
                        newHeracleum.strength = strength;
                        world.AddOrganism(newHeracleum);
                    }
                    default -> System.out.println("Unknown organism symbol '" + symbol + "'");
                }
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
}
