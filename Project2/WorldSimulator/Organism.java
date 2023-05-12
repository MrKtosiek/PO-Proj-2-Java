package WorldSimulator;

public abstract class Organism
{
    protected Vector2 pos = new Vector2();
    protected Vector2 prevPos = new Vector2();
    protected int priority = 0;
    protected int strength = 0;
    protected char symbol = 0;
    protected boolean isAlive = true;
    protected World world = null;


    public Organism(Vector2 pos, int priority, int strength, char symbol)
    {
        this.pos = pos;
        this.priority = priority;
        this.strength = strength;
        this.symbol = symbol;
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

    public boolean isAlive()
    {
        return isAlive;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public void Draw(char[][] buffer)
    {
        buffer[pos.x][pos.y] = getSymbol();
    }

    public abstract void Action();

    public abstract void Collide(Organism organism);

    protected void GoBack()
    {
        pos = prevPos;
    }

    protected abstract Organism Clone(Vector2 pos);
    @Override
    public abstract String toString();

    protected void Hit(Animal attacker)
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

    protected void Die(Organism killer)
    {
        if (!isAlive)
            return;

        isAlive = false;
        world.AddLog(this + " [" + getStrength() + "] was killed by " + killer + " [" + killer.getStrength() + "]\n");
    }
}
