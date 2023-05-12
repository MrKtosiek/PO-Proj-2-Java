package WorldSimulator;

import java.util.*;

public class World
{

    private Vector2 size;
    private Vector<Organism> organisms = new Vector<>();
    private int turnNumber = 0;
    private boolean playerAlive = true;
    private String logs;

    public World()
    {
        this(15, 15);
    }
    public World(int height, int width)
    {
        size = new Vector2(height, width);
    }
    public World(World orig)
    {
        size = orig.size;
        turnNumber = orig.turnNumber;
        playerAlive = orig.playerAlive;
        organisms = new Vector<>(orig.organisms);
    }

    public void AddOrganism(Organism org)
    {
        org.setWorld(this);
        organisms.add(org);
    }

    public void RemoveOrganism(Organism org)
    {
        for (Organism organism : organisms)
        {
            if (organism == org)
            {
                organisms.remove(organism);
                break;
            }
        }
    }

    public Organism GetOrganism(Vector2 pos)
    {
        for (Organism organism : organisms)
        {
            if (pos == organism.getPos())
                return organism;
        }
        return null;
    }

    public Vector<Organism> GetOrganisms()
    {
        return organisms;
    }

    public Vector2 GetSize()
    {
        return size;
    }

    public int GetTurnNumber()
    {
        return turnNumber;
    }
    public void SetTurnNumber(int value)
    {
        turnNumber = value;
    }

    public void AddLog(String str)
    {
        logs += str;
    }

    public boolean IsPlayerAlive()
    {
        return playerAlive;
    }

    public void SetPlayerAlive(boolean value)
    {
        playerAlive = value;
    }

    public boolean ContainsPos(Vector2 pos)
    {
        return pos.x >= 0 && pos.x < size.x&& pos.y >= 0 && pos.y < size.y;
    }

    public Vector2 GetRandomEmptyTile()
    {
        Vector2 pos = new Vector2();
        Random rand = new Random();

        int safetyLimit = 1000;
        for (int i = 0; i < safetyLimit; i++)
        {
            pos.x = rand.nextInt(size.x);
            pos.y = rand.nextInt(size.y);
            // if there is no organism on the chosen tile, return the position
            if (GetOrganism(pos) == null)
                break;
        }

        return pos;
    }

    public boolean HasEmptyNeighbor(Vector2 pos)
    {
        for (int i = 0; i < 4; i++)
        {
            Vector2 n = pos.GetNeighbor(i);
            if (ContainsPos(n) && GetOrganism(n) == null)
                return true;
        }
        return false;
    }

    public Vector2 GetEmptyNeighbor(Vector2 pos)
    {
        Vector<Vector2> targets = new Vector<>();
        for (int i = 0; i < 4; i++)
        {
            Vector2 n = pos.GetNeighbor(i);
            if (ContainsPos(n) && GetOrganism(n) == null)
                targets.add(n);
        }

        if (targets.size() > 0)
        {
            Random rand = new Random();
            return targets.get(rand.nextInt(targets.size()));
        }
        else
            return new Vector2( -1,-1 );
    }

    public void ExecuteTurn()
    {
        // order organisms by their priority
        organisms.sort((Organism org1, Organism org2) -> org2.getPriority() - org1.getPriority());

        // execute actions
        int orgCount = organisms.size();
        for (int i = 0; i < orgCount; i++)
        {
            if (!organisms.get(i).isAlive())
                continue;

            //world->Logs() << i + 1 << ". ";
            organisms.get(i).Action();

            // handle collisions
            for (int j = 0; j < organisms.size(); j++)
            {
                if (i != j && organisms.get(j).isAlive() && organisms.get(i).getPos() == organisms.get(j).getPos())
                {
                    organisms.get(i).Collide(organisms.get(j));
                }
            }
        }

        // remove dead organisms
        for (int i = organisms.size() - 1; i >= 0; i--)
        {
            if (!organisms.get(i).isAlive())
            {
                RemoveOrganism(organisms.get(i));
            }
        }


        logs += "Turn " + ++turnNumber + " finished\n";
    }

    public void DrawWorld()
    {
        // create a buffer for the drawing
        char[][] buffer = new char[size.x][size.y];
        for (int x = 0; x < size.x; x++)
        {
            for (int y = 0; y < size.y; y++)
            {
                buffer[x][y] = ' ';
            }
        }

        // draw all organisms
        for (Organism org : organisms)
        {
            org.Draw(buffer);
        }

        // draw the buffer on the console
        for (int x = 0; x < size.x; x++)
        {
            System.out.print('|');
            System.out.print(' ');
            for (int y = 0; y < size.y; y++)
            {
                System.out.print(buffer[x][y]);
                System.out.print(' ');
            }
            System.out.print("|\n");
        }
    }

    public void DrawLogs()
    {
        System.out.println(" --- Logs: ---\n\n" + logs);
    }

    public void ClearLogs()
    {
        logs = new String();
    }
}
