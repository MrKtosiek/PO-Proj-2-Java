package WorldSimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Animal extends Organism
{
    private final int breedingCooldown;
    private int currentBreedingCooldown;

    public Animal(Vector2 pos, int priority, int strength, int breedingCooldown, char symbol, Color color)
    {
        super(pos, priority, strength, symbol, color);
        this.breedingCooldown = breedingCooldown;
    }

    @Override
    public void Action()
    {
        Movement();

        currentBreedingCooldown--;
    }

    protected void Movement()
    {
        Random rand = new Random();
        ArrayList<Vector2> neighbors = world.GetNeighbors(pos);
        MoveTo(neighbors.get(rand.nextInt(neighbors.size())));
        //MoveTo(pos.GetNeighbor(rand.nextInt(4)));
    }

    protected void MoveTo(Vector2 target)
    {
        if (world.ContainsPos(target))
        {
            prevPos = pos;
            pos = target;
            world.AddLog(this + " moved to " + pos + '\n');
        }
        else
        {
            world.AddLog(this + " couldn't move to " + target + '\n');
        }
    }

    @Override
    public void Collide(Organism organism)
    {
        world.AddLog("Collision on " + pos + " (" + this + ", " + organism + ")\n");
        if (toString().equals(organism.toString()))
        {
            Breed((Animal)organism);
        }
        else
        {
            organism.Hit(this);
        }
    }

    private void Breed(Animal other)
    {
        GoBack();
        if (!world.HasEmptyNeighbor(other.getPos()))
        {
            world.AddLog("Breeding failed on " + pos + " (not enough space)\n");
            return;
        }
        if (currentBreedingCooldown > 0 || other.currentBreedingCooldown > 0)
        {
            world.AddLog("Breeding failed on " + pos + " (cooldown)\n");
            return;
        }

        Vector2 childPos = world.GetRandomEmptyNeighbor(other.getPos());
        Animal child = (Animal)clone();
        child.pos = childPos;
        world.AddOrganism(child);

        currentBreedingCooldown = breedingCooldown;
        other.currentBreedingCooldown = other.breedingCooldown;
        child.currentBreedingCooldown = child.breedingCooldown;

        world.AddLog( "Breeding on " + child.pos + "\n");
    }
}
