package WorldSimulator;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism
{
    public int growChance;

    public Plant(Vector2 pos, int strength, int growChance, char symbol, Color color)
    {
        super(pos, 0, strength, symbol, color);
        this.growChance = growChance;
    }

    @Override
    public void Action()
    {
        Random rand = new Random();
        if (rand.nextInt(100) < growChance)
        {
            Grow();
        }
    }

    @Override
    public void Collide(Organism other)
    {

    }

    void Grow()
    {
        if (world.HasEmptyNeighbor(pos))
        {
            Vector2 childPos = world.GetEmptyNeighbor(pos);
            Plant child = (Plant) clone();
            child.pos = childPos;
            world.AddOrganism(child);
            world.AddLog(this + " spread onto " + childPos + "\n");
        }
        else
        {
            world.AddLog(this + " couldn't spread on " + pos + " (not enough space)\n");
        }
    }
}
