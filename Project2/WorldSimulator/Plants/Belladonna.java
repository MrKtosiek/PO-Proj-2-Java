package WorldSimulator.Plants;

import WorldSimulator.Organism;
import WorldSimulator.Plant;
import WorldSimulator.Vector2;

import java.awt.*;

public class Belladonna extends Plant
{
    public Belladonna(Vector2 pos)
    {
        super(pos, 99, 10, '?', new Color(150, 15, 149));
    }

    @Override
    protected void Hit(Organism attacker)
    {
        super.Hit(attacker);
        this.Die(attacker);
    }

    @Override
    public String toString()
    {
        return "Belladonna";
    }
}
