package WorldSimulator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.chrono.JapaneseEra;
import java.util.ArrayList;

public class GameUI
{
    private final Game game;
    private final JFrame frame;
    private JLabel[][] tiles;
    private JLabel logText;

    private static final int tileSize = 20;

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

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        frame.add(container);

        CreateGrid(container);
        CreateLog(container);

        // display the window
        frame.pack();
        frame.setVisible(true);
    }
    private void CreateGrid(JPanel container)
    {
        JPanel gridHolder = new JPanel();

        // create the tiles
        Vector2 worldSize = game.getWorld().GetSize();
        tiles = new JLabel[worldSize.x][worldSize.y];
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(worldSize.y, worldSize.x));
        grid.setPreferredSize(new Dimension(worldSize.x * tileSize, worldSize.y * tileSize));
        grid.setBorder(BorderFactory.createLineBorder(Color.RED));

        for (int x = 0; x < worldSize.x; x++)
        {
            for (int y = 0; y < worldSize.y; y++)
            {
                JLabel tile = CreateTile();
                tiles[x][y] = tile;
                grid.add(tile);
            }
        }

        gridHolder.add(grid);
        container.add(gridHolder);
    }
    private JLabel CreateTile()
    {
        JLabel label = new JLabel(" ");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        label.setPreferredSize(new Dimension(tileSize, tileSize));
        return label;
    }
    private void CreateLog(JPanel container)
    {
        JPanel logHolder = new JPanel();
        JLabel log = new JLabel();
        log.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        log.setAlignmentY(Component.TOP_ALIGNMENT);
        log.setText("<html>Logs:</html>");
        logText = log;
        logHolder.add(log);
        container.add(logHolder);
    }

    public void DrawWorld()
    {
        Vector2 worldSize = game.getWorld().GetSize();

        // clear all tiles
        for (int x = 0; x < worldSize.x; x++)
        {
            for (int y = 0; y < worldSize.y; y++)
            {
                tiles[x][y].setText(" ");
            }
        }

        for (Organism org : game.getWorld().GetOrganisms())
        {
            tiles[org.pos.x][org.pos.y].setText("" + org.getSymbol());
            tiles[org.pos.x][org.pos.y].setBackground(org.getColor());
        }
    }
    public void WriteLogs()
    {
        ArrayList<String> logs = game.getWorld().getLogs();
        StringBuilder text = new StringBuilder("<html>Logs:");

        for (String str : logs)
        {
            text.append("<br/>").append(str);
        }
        text.append("</html>");

        logText.setText(text.toString());

        game.getWorld().ClearLogs();
    }
}
