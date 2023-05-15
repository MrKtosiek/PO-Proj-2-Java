package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Vector2;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal
{
	final int motionRange = 2;

    public Antelope(Vector2 pos)
    {
        super(pos, 4, 4, 3, 'A', new Color(219, 127, 62));
    }

    @Override
    protected void Hit(Organism attacker)
    {
        Random rand = new Random();
        if (rand.nextBoolean() && world.HasEmptyNeighbor(pos))
        {
            MoveTo(world.GetEmptyNeighbor(pos));
            return;
        }
        super.Hit(attacker);
    }

    @Override
    protected void Movement()
    {
        Random rand = new Random();
        Vector2 target = pos;
        for (int i = 0; i < motionRange; i++)
        {
            target = target.GetNeighbor(rand.nextInt(4));
        }
        MoveTo(target);
    }

    @Override
    public String toString()
    {
        return "Antelope";
    }
}
