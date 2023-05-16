package WorldSimulator.Animals;

import WorldSimulator.Animal;
import WorldSimulator.HexWorld;
import WorldSimulator.Organism;
import WorldSimulator.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends Animal
{
    enum HumanAction
    {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT,
        UP_LEFT,
        DOWN_RIGHT,
        ABILITY
    };
    HumanAction nextAction = HumanAction.NONE;


    final int abilityDuration = 5;
    final int abilityCooldown = 5;
    int abilityLeft = 0;
    public Human(Vector2 pos)
    {
        super(pos, 4, 5, 0, 'H', new Color(0, 255, 255));
    }

    public boolean IsAbilityActive()
    {
        return abilityLeft > abilityCooldown;
    }

    public boolean CanActivateAbility()
    {
        return abilityLeft <= 0;
    }

    public void ActivateAbility()
    {
        world.AddLog(this + " activated a special ability!\n");
        abilityLeft = abilityDuration + abilityCooldown;
    }

    public int getAbilityDurationLeft()
    {
        return abilityLeft;
    }

    public void setAbilityDurationLeft(int value)
    {
        abilityLeft = value;
    }

    public void setNextAction(int keyCode)
    {
        if (world instanceof HexWorld)
        {
            switch (keyCode)
            {
                case KeyEvent.VK_E -> nextAction = HumanAction.UP;
                case KeyEvent.VK_Z -> nextAction = HumanAction.DOWN;
                case KeyEvent.VK_A -> nextAction = HumanAction.LEFT;
                case KeyEvent.VK_D -> nextAction = HumanAction.RIGHT;
                case KeyEvent.VK_W -> nextAction = HumanAction.UP_LEFT;
                case KeyEvent.VK_X -> nextAction = HumanAction.DOWN_RIGHT;
                case KeyEvent.VK_R -> nextAction = HumanAction.ABILITY;
                default -> nextAction = HumanAction.NONE;
            }
        }
        else
        {
            switch (keyCode)
            {
                case KeyEvent.VK_W -> nextAction = HumanAction.UP;
                case KeyEvent.VK_S -> nextAction = HumanAction.DOWN;
                case KeyEvent.VK_A -> nextAction = HumanAction.LEFT;
                case KeyEvent.VK_D -> nextAction = HumanAction.RIGHT;
                case KeyEvent.VK_R -> nextAction = HumanAction.ABILITY;
                default -> nextAction = HumanAction.NONE;
            }
        }
    }

    @Override
    public void Action()
    {
        if (nextAction == HumanAction.ABILITY && CanActivateAbility())
        {
            ActivateAbility();
        }
        else
        {
            Movement();
            abilityLeft--;
        }

        if (IsAbilityActive())
        {
            world.AddLog((abilityLeft - abilityCooldown) + " turns of special ability left\n");
        }
    }

    @Override
    protected void Movement()
    {
        if (nextAction == HumanAction.NONE)
        {
            world.AddLog(this + " didn't move\n");
            return;
        }

        Vector2 target = pos;

        if (nextAction == HumanAction.UP)
            target = new Vector2( pos.x - 1, pos.y );
        if (nextAction == HumanAction.DOWN)
            target = new Vector2( pos.x + 1, pos.y );
        if (nextAction == HumanAction.LEFT)
            target = new Vector2( pos.x, pos.y - 1 );
        if (nextAction == HumanAction.RIGHT)
            target = new Vector2( pos.x, pos.y + 1 );
        if (nextAction == HumanAction.UP_LEFT)
            target = new Vector2( pos.x - 1, pos.y - 1 );
        if (nextAction == HumanAction.DOWN_RIGHT)
            target = new Vector2( pos.x + 1, pos.y + 1 );

        MoveTo(target);
    }

    @Override
    protected void Hit(Organism attacker)
    {
        if (IsAbilityActive() && world.HasEmptyNeighbor(pos))
        {
            attacker.setPos(world.GetRandomEmptyNeighbor(pos));
            world.AddLog(this + " deflected an attack from " + attacker + " on " + pos + " using the special ability\n");
        }
        else
        {
            super.Hit((Animal) attacker);
        }
    }

    @Override
    public void Die(Organism killer)
    {
        world.SetPlayerAlive(false);
        super.Die(killer);
    }

    @Override
    public String toString()
    {
        return "Human";
    }
}
