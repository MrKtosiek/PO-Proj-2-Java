package WorldSimulator;

import java.awt.*;

public abstract class Organism implements Cloneable
{
    protected Vector2 pos = new Vector2();
    protected Vector2 prevPos = new Vector2();
    protected int priority = 0;
    protected int strength = 0;
    protected final char symbol;
    protected final Color color;
    protected boolean isAlive = true;
    protected World world = null;


    public Organism(Vector2 pos, int priority, int strength, char symbol, Color color)
    {
        this.pos = pos;
        this.priority = priority;
        this.strength = strength;
        this.symbol = symbol;
        this.color = color;
    }

    public Vector2 getPos()
    {
        return pos;
    }

    public void setPos(Vector2 pos)
    {
        this.pos = pos;
    }

    public int getPriority()
    {
        return priority;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public char getSymbol()
    {
        return symbol;
    }

    public Color getColor()
    {
        return color;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public abstract void Action();

    public abstract void Collide(Organism organism);

    public void GoBack()
    {
        pos = prevPos;
    }

    @Override
    public abstract String toString();

    protected void Hit(Organism attacker)
    {
        world.AddLog(attacker + " attacked " + this + " on " + pos + "\n");
        if (attacker.getStrength() >= getStrength())
        {
            // attacker wins
            this.Die(attacker);
        }
        else
        {
            // this organism wins
            attacker.Die(this);
        }
    }

    public void Die(Organism killer)
    {
        if (!isAlive)
            return;

        isAlive = false;
        world.AddLog(this + " [" + getStrength() + "] was killed by " + killer + " [" + killer.getStrength() + "]\n");
    }

    @Override
    public Organism clone()
    {
        try
        {
            Organism clone = (Organism) super.clone();

            return clone;
        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }
}
