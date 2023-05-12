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
    protected Organism Clone(Vector2 pos)
    {
        return new Human(pos);
    }

    @Override
    public String toString()
    {
        return "Human";
    }
}
