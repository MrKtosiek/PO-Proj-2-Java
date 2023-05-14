package WorldSimulator;

import java.util.*;

public class World
{

    private final Vector2 size;
    private Vector<Organism> organisms = new Vector<>();
    private int turnNumber = 0;
    private boolean playerAlive = true;
    private ArrayList<String> logs = new ArrayList<>();

    public World()
    {
        this(15, 15);
    }
    public World(int width, int height)
    {
        size = new Vector2(width, height);
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
            if (pos.equals(organism.getPos()))
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
        logs.add(str);
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

            organisms.get(i).Action();

            // handle collisions
            for (int j = 0; j < organisms.size(); j++)
            {
                if (i != j && organisms.get(j).isAlive() && organisms.get(i).getPos().equals(organisms.get(j).getPos()))
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


        AddLog("Turn " + ++turnNumber + " finished\n");
    }

    public ArrayList<String> getLogs()
    {
        return logs;
    }
    public void ClearLogs()
    {
        logs.clear();
    }
}
