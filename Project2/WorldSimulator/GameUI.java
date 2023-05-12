package WorldSimulator;

import javax.swing.*;
import java.awt.*;

public class GameUI
{
    private final Game game;
    private final JFrame frame;
    private JLabel[][] tiles;

    public GameUI(Game game)
    {
        this.game = game;
        frame = new JFrame();
    }

    public void CreateAndShowWindow(String title, int width, int height)
    {
        // create the window
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));

        // create the tiles
        tiles = new JLabel[game.getWorld().GetSize().x][game.getWorld().GetSize().y];
        Panel grid = new Panel();
        grid.setLayout(new GridLayout(game.getWorld().GetSize().x, game.getWorld().GetSize().y));

        for (int x = 0; x < game.getWorld().GetSize().x; x++)
        {
            for (int y = 0; y < game.getWorld().GetSize().y; y++)
            {
                JLabel tile = CreateTile(x);
                tiles[x][y] = tile;
                grid.add(tile);
            }
        }

        frame.add(grid);

        // display the window
        frame.pack();
        frame.setVisible(true);
    }

    private static JLabel CreateTile(int x)
    {
        JLabel label = new JLabel("T");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(x*10,255, 255));
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        label.setPreferredSize(new Dimension(20, 20));
        return label;
    }
}
