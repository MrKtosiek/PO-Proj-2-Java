package WorldSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameUI
{
    private final Game game;
    private final JFrame frame;
    private JLabel[][] tiles;
    private JLabel logText;

    private static final int tileSize = 20;
    private static final int margin = 10;
    private static final int logWidth = 350;
    private static final int logHeight = 400;
    private static final int buttonHeight = 20;
    private static final int buttonWidth = 100;

    public GameUI(Game game, String title)
    {
        this.game = game;

        // create the window
        frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(
                game.getWorld().GetSize().y * tileSize + logWidth + margin * 3,
                Math.max(game.getWorld().GetSize().x * tileSize, logHeight) + margin * 2 + buttonHeight + 50));

        JPanel container = new JPanel();
        container.setLayout(null);
        frame.add(container);

        // add UI elements
        CreateGrid(container);
        CreateLog(container);
        AddButtons(container);

        // display the window
        frame.pack();
        frame.setVisible(true);

        frame.addKeyListener(new GameController(game, this));
        frame.setFocusable(true);
        frame.requestFocus();
    }

    private void CreateGrid(JPanel container)
    {
        // create the tiles
        Vector2 worldSize = game.getWorld().GetSize();
        tiles = new JLabel[worldSize.x][worldSize.y];
        JPanel grid = new JPanel();
        grid.setLayout(null);
        grid.setBounds(margin, buttonHeight + margin, worldSize.y * tileSize, worldSize.x * tileSize);

        for (int x = 0; x < worldSize.x; x++)
        {
            for (int y = 0; y < worldSize.y; y++)
            {
                JLabel tile = CreateTile();
                tile.setBounds(y * tileSize, x * tileSize, tileSize, tileSize);
                tiles[x][y] = tile;
                grid.add(tile);
            }
        }

        container.add(grid);
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
        JLabel log = new JLabel();
        log.setAlignmentY(Component.TOP_ALIGNMENT);
        log.setVerticalTextPosition(SwingConstants.TOP);
        log.setVerticalAlignment(SwingConstants.TOP);
        log.setText(" ");
        logText = log;

        JScrollPane sp = new JScrollPane(log);
        sp.setBorder(BorderFactory.createTitledBorder("Logs"));
        sp.setBounds(game.getWorld().GetSize().y * tileSize + 2 * margin, buttonHeight + margin, logWidth, logHeight);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        container.add(sp);
    }
    private void AddButtons(JPanel container)
    {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(0, 0, buttonWidth, buttonHeight);
        saveButton.setFocusable(false);
        container.add(saveButton);

        JButton loadButton = new JButton("Load");
        loadButton.setBounds(buttonWidth, 0, buttonWidth, buttonHeight);
        loadButton.setFocusable(false);
        container.add(loadButton);

        saveButton.addActionListener(e ->
        {
            game.SaveWorld("save.txt");
        });
        loadButton.addActionListener(e ->
        {
            game.LoadWorld("save.txt");
        });
    }

    public void DrawOrganisms()
    {
        Vector2 worldSize = game.getWorld().GetSize();

        // clear all tiles
        for (int x = 0; x < worldSize.x; x++)
        {
            for (int y = 0; y < worldSize.y; y++)
            {
                tiles[x][y].setText(" ");
                tiles[x][y].setBackground(Color.LIGHT_GRAY);
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
        StringBuilder text = new StringBuilder("<html>");

        for (String str : logs)
        {
            text.append(str).append("<br/>");
        }
        text.append("</html>");

        logText.setText(text.toString());

        game.getWorld().ClearLogs();
    }
}
