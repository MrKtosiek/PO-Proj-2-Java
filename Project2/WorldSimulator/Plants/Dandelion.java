package WorldSimulator.Plants;

import WorldSimulator.Plant;
import WorldSimulator.Vector2;

import java.awt.*;

public class Dandelion extends Plant
{
    public Dandelion(Vector2 pos)
    {
        super(pos, 0, 5, 'm', new Color(255, 219, 41));
    }

    @Override
    public void Action()
    {
        for (int i = 0; i < 3; i++)
        {
            super.Action();
        }
    }

    @Override
    public String toString()
    {
        return "Dandelion";
    }
}
