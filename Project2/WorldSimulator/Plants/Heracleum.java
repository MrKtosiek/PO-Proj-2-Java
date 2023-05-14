package WorldSimulator.Plants;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Plant;
import WorldSimulator.Vector2;

import java.awt.*;

public class Heracleum extends Plant
{
    public Heracleum(Vector2 pos)
    {
        super(pos, 10, 10, '%', new Color(181, 213, 41));
    }

    @Override
    public void Action()
    {
        super.Action();

        for (int i = 0; i < 8; i++)
        {
            Vector2 target = pos.GetNeighbor8Way(i);
            Organism org = world.GetOrganism(target);
            if (org instanceof Animal)
            {
                org.Die(this);
            }
        }
    }

    @Override
    public String toString()
    {
        return "Heracleum";
    }
}
