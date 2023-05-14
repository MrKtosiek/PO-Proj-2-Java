package WorldSimulator.Plants;

import WorldSimulator.Plant;
import WorldSimulator.Vector2;

import java.awt.*;

public class Grass extends Plant
{
    public Grass(Vector2 pos)
    {
        super(pos, 0, 30, ',', new Color(87, 231, 18));
    }

    @Override
    public String toString()
    {
        return "Grass";
    }
}
