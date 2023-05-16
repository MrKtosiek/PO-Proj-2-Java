package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Vector2;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal
{
    public Turtle(Vector2 pos)
    {
        super(pos, 1, 2, 1, 'T', new Color(71, 143, 36));
    }

    @Override
    protected void Hit(Organism attacker)
    {
        if (attacker.getStrength() < 5)
        {
            world.AddLog(this + " deflected an attack from " + attacker + " on " + pos);
            attacker.GoBack();
            return;
        }

        super.Hit(attacker);
    }

    @Override
    protected void Movement()
    {
        Random rand = new Random();
        if (rand.nextInt() % 4 == 0)
            MoveTo(world.GetRandomNeighbor(pos));
        else
            world.AddLog(this + " stayed on " + pos);
    }

    @Override
    public String toString()
    {
        return "Turtle";
    }
}
