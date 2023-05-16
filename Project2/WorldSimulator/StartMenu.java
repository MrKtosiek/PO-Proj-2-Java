package WorldSimulator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartMenu extends JFrame
{
    private static final int margin = 10;
    private static final int fieldWidth = 100;
    private static final int fieldHeight = 20;

    private final JButton startButton;

    private boolean hex = false;
    private final Vector2 size = new Vector2(15,15);

    public StartMenu(String title)
    {
        super(title);

        // configure the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(fieldWidth * 2 + margin * 3 + 20, fieldHeight * 4 + margin * 5 + 50));

        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));

        GridLayout gridLayout = new GridLayout(4, 2);
        gridLayout.setHgap(margin);
        gridLayout.setVgap(margin);
        container.setLayout(gridLayout);

        // create labels
        JLabel xLabel = new JLabel("Height:");
        JLabel yLabel = new JLabel("Width:");
        JLabel hexLabel = new JLabel("Hex map:");

        // create a start button
        startButton = new JButton("Start");

        // add width and height fields
        JSpinner xInput = new JSpinner(new SpinnerNumberModel(size.x, 5, 30, 1));
        JSpinner yInput = new JSpinner(new SpinnerNumberModel(size.y, 5, 30, 1));
        xInput.setBounds(fieldWidth + margin * 2, margin, fieldWidth, fieldHeight);
        yInput.setBounds(fieldWidth + margin * 2, fieldHeight + margin * 2, fieldWidth, fieldHeight);
        xInput.addChangeListener(e -> size.x = (int)xInput.getValue());
        yInput.addChangeListener(e -> size.y = (int)yInput.getValue());

        // add the hex map checkbox
        JCheckBox checkBox = new JCheckBox();
        checkBox.addChangeListener(e -> hex = checkBox.isSelected());

        // add all elements
        container.add(xLabel);
        container.add(xInput);
        container.add(yLabel);
        container.add(yInput);
        container.add(hexLabel);
        container.add(checkBox);
        container.add(startButton);
        add(container);

        // display the window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void AddListener(ActionListener listener)
    {
        startButton.addActionListener(listener);
    }

    public boolean isHexChecked()
    {
        return hex;
    }
    public Vector2 getWorldSize()
    {
        return size;
    }
}
