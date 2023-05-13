package WorldSimulator.Animals;

import WorldSimulator.Animal;
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
        ABILITY
    };
    HumanAction nextAction = HumanAction.NONE;


    final int abilityDuration = 5;
    final int abilityCooldown = 5;
    int abilityLeft = 0;
    public Human(Vector2 pos)
    {
        super(pos, 4, 5, 'H', Color.CYAN);
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
        switch (keyCode)
        {
            case KeyEvent.VK_W -> nextAction = HumanAction.UP;
            case KeyEvent.VK_S -> nextAction = HumanAction.DOWN;
            case KeyEvent.VK_A -> nextAction = HumanAction.LEFT;
            case KeyEvent.VK_D -> nextAction = HumanAction.RIGHT;
            case KeyEvent.VK_E -> nextAction = HumanAction.ABILITY;
            default -> nextAction = HumanAction.NONE;
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

        MoveTo(target);
    }

    @Override
    protected void Hit(Organism attacker)
    {
        if (IsAbilityActive() && world.HasEmptyNeighbor(pos))
        {
            attacker.setPos(world.GetEmptyNeighbor(pos));
            world.AddLog(this + " deflected an attack from " + attacker + " on " + pos + " using the special ability\n");
        }
        else
        {
            super.Hit((Animal) attacker);
        }
    }

    @Override
    protected void Die(Organism killer)
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
