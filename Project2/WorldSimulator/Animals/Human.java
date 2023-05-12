package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.Organism;
import WorldSimulator.Vector2;

public class Human extends Animal
{
    public Human(Vector2 pos)
    {
        super(pos, 4, 5, 'H');
    }

    @Override
    public String toString()
    {
        return "Human";
    }
}
