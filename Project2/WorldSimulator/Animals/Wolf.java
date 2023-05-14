package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Vector2;

import java.awt.*;

public class Wolf extends Animal
{
    public Wolf(Vector2 pos)
    {
        super(pos, 5, 9, 5, 'W', new Color(128, 128, 128));
    }

    @Override
    public String toString()
    {
        return "Wolf";
    }
}
