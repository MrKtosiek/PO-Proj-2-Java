package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Vector2;

import java.awt.*;

public class Sheep extends Animal
{
    public Sheep(Vector2 pos)
    {
        super(pos, 4, 4, 3, 'S', new Color(255, 255, 255));
    }

    @Override
    public String toString()
    {
        return "Sheep";
    }
}
