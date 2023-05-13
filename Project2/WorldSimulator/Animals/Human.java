package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Vector2;

import java.awt.*;

public class Human extends Animal
{
    public Human(Vector2 pos)
    {
        super(pos, 4, 5, 'H', Color.CYAN);
    }

    @Override
    public String toString()
    {
        return "Human";
    }
}
