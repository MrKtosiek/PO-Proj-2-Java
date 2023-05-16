package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Vector2;

import java.awt.*;
import java.util.Random;

public class Fox extends Animal
{
    public Fox(Vector2 pos)
    {
        super(pos, 7, 3, 2, 'F', new Color(255, 132, 8));
    }

    @Override
    protected void Movement()
    {
        // make 3 attempts to move to a tile that doesn't contain a stronger organism
        Random rand = new Random();
        for (int i = 0; i < 3; i++)
        {
            Vector2 target = world.GetRandomNeighbor(pos);
            Organism org = world.GetOrganism(target);

            if (org == null || getStrength() >= org.getStrength())
            {
                MoveTo(target);
                return;
            }
        }

        world.AddLog(this + " didn't move");
    }

    @Override
    public String toString()
    {
        return "Fox";
    }
}
