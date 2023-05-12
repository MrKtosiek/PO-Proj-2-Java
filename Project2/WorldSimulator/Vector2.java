package WorldSimulator;

public class Vector2
{
    public int x;
    public int y;

    public Vector2()
    {
        this(0, 0);
    }
    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2 GetNeighbor(int i)
    {
        if (i == 0) return new Vector2( x    , y + 1 );
        if (i == 1) return new Vector2( x + 1, y     );
        if (i == 2) return new Vector2( x    , y - 1 );
        if (i == 3) return new Vector2( x - 1, y     );

        return this;
    }

    public Vector2 GetNeighbor8Way(int i)
    {
        if (i == 0) return new Vector2( x - 1, y - 1 );
        if (i == 1) return new Vector2( x - 1, y     );
        if (i == 2) return new Vector2( x - 1, y + 1 );
        if (i == 3) return new Vector2( x    , y + 1 );
        if (i == 4) return new Vector2( x + 1, y + 1 );
        if (i == 5) return new Vector2( x + 1, y     );
        if (i == 6) return new Vector2( x + 1, y - 1 );
        if (i == 7) return new Vector2( x    , y - 1 );

        return this;
    }

    @Override
    public String toString()
    {
        return "" + x + "," + y;
    }
}
