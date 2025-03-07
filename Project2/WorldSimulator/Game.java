package WorldSimulator;

import WorldSimulator.Animals.*;
import WorldSimulator.Plants.*;
import WorldSimulator.UI.GameUI;
import WorldSimulator.UI.GridGameUI;
import WorldSimulator.UI.HexGameUI;

import java.io.*;
import java.util.Scanner;

public class Game
{
    private World world;
    private Human player;
    private GameUI gameUI;

    public Game(int width, int height, boolean hex)
    {
        if (hex)
            world = new HexWorld(width, height);
        else
            world = new GridWorld(width, height);

        player = new Human(new Vector2( width / 2, height / 2 ));
        world.AddOrganism(player);
        GenerateWorld();
    }

    public World getWorld()
    {
        return world;
    }
    public void setGameUI(GameUI gameUI)
    {
        this.gameUI = gameUI;
        gameUI.DrawOrganisms();
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
        System.out.println("Saving world state...");
        try
        {
            FileWriter fileWriter = new FileWriter(filename);

            // save world stats
            fileWriter.write((world instanceof HexWorld) + " " + world.GetSize().x + " " + world.GetSize().y + " " + world.GetTurnNumber());
            fileWriter.write(System.lineSeparator());

            // save the organisms
            for (Organism org : world.GetOrganisms())
            {
                fileWriter.write(org.getSymbol() + " " + org.getPos().x + " " + org.getPos().y + " " + org.getStrength());

                if (org instanceof Human)
                    fileWriter.write(" " + ((Human)org).getAbilityDurationLeft());

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
        System.out.println("Loading world state...");

        try
        {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            boolean hex;
            Vector2 size = new Vector2();
            int turnNum;
            hex = scanner.nextBoolean();
            size.x = scanner.nextInt();
            size.y = scanner.nextInt();
            turnNum = scanner.nextInt();

            // create a new world
            if (hex){
                world = new HexWorld(size.x, size.y);
                gameUI = new HexGameUI(this, "Filip Jezierski 196333");
            }
            else{
                world = new GridWorld(size.x, size.y);
                gameUI = new GridGameUI(this, "Filip Jezierski 196333");

            }
            world.SetTurnNumber(turnNum);

            // load the organisms
            while (scanner.hasNext())
            {
                char symbol;
                Vector2 pos = new Vector2();
                int strength;

                symbol = scanner.next().charAt(0);
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

        gameUI.DrawOrganisms();
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
