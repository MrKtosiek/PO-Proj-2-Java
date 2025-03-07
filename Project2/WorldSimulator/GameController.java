package WorldSimulator;

import WorldSimulator.UI.GameUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {

    private final Game game;
    private final GameUI gameUI;

    public GameController(Game game, GameUI gameUI)
    {
        this.game = game;
        this.gameUI = gameUI;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        game.SimulateWorld(e.getKeyCode());
        gameUI.DrawOrganisms();
        gameUI.WriteLogs();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}