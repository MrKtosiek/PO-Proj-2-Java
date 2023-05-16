package WorldSimulator;

import java.util.ArrayList;

public class GridWorld extends World
{
    public GridWorld(int width, int height)
    {
        super(width, height);
    }

    @Override
    public ArrayList<Vector2> GetNeighbors(Vector2 v)
    {
        ArrayList<Vector2> neighbors = new ArrayList<>();

        neighbors.add(new Vector2( v.x    , v.y + 1 ));
        neighbors.add(new Vector2( v.x + 1, v.y     ));
        neighbors.add(new Vector2( v.x    , v.y - 1 ));
        neighbors.add(new Vector2( v.x - 1, v.y     ));

        return neighbors;
    }
    @Override
    public ArrayList<Vector2> GetNeighborsWithDiagonals(Vector2 v)
    {
        ArrayList<Vector2> neighbors = new ArrayList<>();

        neighbors.add(new Vector2( v.x - 1, v.y - 1 ));
        neighbors.add(new Vector2( v.x - 1, v.y     ));
        neighbors.add(new Vector2( v.x - 1, v.y + 1 ));
        neighbors.add(new Vector2( v.x    , v.y + 1 ));
        neighbors.add(new Vector2( v.x + 1, v.y + 1 ));
        neighbors.add(new Vector2( v.x + 1, v.y     ));
        neighbors.add(new Vector2( v.x + 1, v.y - 1 ));
        neighbors.add(new Vector2( v.x    , v.y - 1 ));

        return neighbors;
    }
}
