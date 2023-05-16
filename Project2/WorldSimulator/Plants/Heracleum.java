package WorldSimulator.Plants;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Plant;
import WorldSimulator.Vector2;

import java.awt.*;
import java.util.ArrayList;

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

        ArrayList<Vector2> targets = world.GetNeighborsWithDiagonals(pos);
        for (Vector2 target : targets)
        {
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
