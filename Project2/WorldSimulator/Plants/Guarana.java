package WorldSimulator.Plants;

import WorldSimulator.Organism;
import WorldSimulator.Plant;
import WorldSimulator.Vector2;

import java.awt.*;

public class Guarana extends Plant
{
    public Guarana(Vector2 pos)
    {
        super(pos, 0, 15, 'g', new Color(18, 93, 231));
    }

    @Override
    protected void Hit(Organism attacker)
    {
        super.Hit(attacker);
        attacker.setStrength(attacker.getStrength() + 3);
        world.AddLog(this + " made " + attacker + " stronger [" + attacker.getStrength() + "]");
    }

    @Override
    public String toString()
    {
        return "Guarana";
    }
}
