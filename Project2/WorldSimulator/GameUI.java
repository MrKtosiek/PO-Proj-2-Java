package WorldSimulator;

import WorldSimulator.Animals.*;
import WorldSimulator.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameUI
{
    private final Game game;
    private JFrame frame;
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

        CreateWindow(title);
    }

    private void CreateWindow(String title)
    {
        // create the window
        frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(
                game.getWorld().GetSize().y * tileSize + logWidth + margin * 3,
                Math.max(game.getWorld().GetSize().x * tileSize, logHeight) + margin * 2 + buttonHeight + 50));

        // create a container for the UI elements
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
                JLabel tile = CreateTile(x,y);
                tiles[x][y] = tile;
                grid.add(tile);
            }
        }

        container.add(grid);
    }
    private JLabel CreateTile(int x, int y)
    {
        JLabel label = new JLabel(" ");
        label.setBounds(y * tileSize, x * tileSize, tileSize, tileSize);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        label.setPreferredSize(new Dimension(tileSize, tileSize));

        label.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.println("Click");
                EditPopup(new Vector2(x, y));
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                label.setBackground(label.getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                label.setBackground(label.getBackground().brighter());
            }
        });

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
            String title = frame.getTitle();
            frame.dispose();
            CreateWindow(title);
            DrawOrganisms();
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

    private void EditPopup(Vector2 pos)
    {
        JFrame popup = new JFrame("Edit");
        popup.setLayout(null);

        Map<String, Character> options = new HashMap<>();

        // create the options and map them to organism symbols
        Organism org = new Antelope(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Fox(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Sheep(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Turtle(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Wolf(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Belladonna(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Dandelion(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Grass(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Guarana(new Vector2());
        options.put(org.toString(), org.getSymbol());
        org = new Heracleum(new Vector2());
        options.put(org.toString(), org.getSymbol());
        options.put("None", ' ');


        JComboBox<String> dropdown = new JComboBox<>(options.keySet().toArray(new String[0]));
        dropdown.setBounds(margin, margin, buttonWidth, buttonHeight);
        popup.add(dropdown);

        JButton button = new JButton("Confirm");
        button.setAction(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                World world = game.getWorld();

                Organism target = world.GetOrganism(pos);
                if (target != null)
                    world.RemoveOrganism(target);

                System.out.println("Adding organism " + options.get((String)dropdown.getSelectedItem()));
                Organism newOrg = game.CreateOrganism(options.get((String)dropdown.getSelectedItem()), pos);

                if (newOrg != null)
                    world.AddOrganism(newOrg);
                DrawOrganisms();
            }
        });
        button.setBounds(margin, margin * 2 + buttonHeight, buttonWidth, buttonHeight);
        popup.add(button);

        popup.setPreferredSize(new Dimension(buttonWidth + 2 * margin,100));
        popup.pack();
        popup.setVisible(true);
    }

}
