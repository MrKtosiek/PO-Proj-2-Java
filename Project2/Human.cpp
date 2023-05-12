#include "Human.h"
#include "World.h"
#include <stdlib.h>

Human::Human(const Vector2& pos) : Animal(pos, 4, 5, 10, 'H')
{
}

Human::~Human()
{
}

bool Human::IsAbilityActive() const
{
	return abilityLeft > 0;
}

bool Human::CanActivateAbility() const
{
	return abilityLeft <= -5;
}

void Human::ActivateAbility()
{
	world->Logs() << GetName() << " activated a special ability!\n";
	abilityLeft = abilityDuration;
}

int Human::GetAbilityDurationLeft() const
{
	return abilityLeft;
}

void Human::SetAbilityDurationLeft(const int& value)
{
	abilityLeft = value;
}

void Human::Action()
{
	if (nextAction == HumanAction::ABILITY && CanActivateAbility())
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
		world->Logs() << abilityLeft << " turns of special ability left\n";
	}
}

void Human::Movement()
{
	if (nextAction == HumanAction::NONE)
	{
		world->Logs() << GetName() << " didn't move\n";
		return;
	}

	Vector2 target = pos;
	
	if (nextAction == HumanAction::UP)
		target = { pos.x - 1, pos.y };
	if (nextAction == HumanAction::DOWN)
		target = { pos.x + 1, pos.y };
	if (nextAction == HumanAction::LEFT)
		target = { pos.x, pos.y - 1 };
	if (nextAction == HumanAction::RIGHT)
		target = { pos.x, pos.y + 1 };

	MoveTo(target);
}

void Human::Hit(Organism* attacker)
{
	if (IsAbilityActive() && world->HasEmptyNeighbor(pos))
	{
		attacker->SetPosition(world->GetEmptyNeighbor(pos));
		world->Logs() << GetName() << " deflected an attack from " << attacker->GetName() << " on " << pos << " using the special ability\n";
	}
	else
	{
		Animal::Hit(attacker);
	}
}

Human* Human::Clone(const Vector2& pos) const
{
	return new Human(pos);
}

void Human::SetNextAction(char code)
{
	switch (code)
	{
	case 'w':
		nextAction = HumanAction::UP;
		break;
	case 's':
		nextAction = HumanAction::DOWN;
		break;
	case 'a':
		nextAction = HumanAction::LEFT;
		break;
	case 'd':
		nextAction = HumanAction::RIGHT;
		break;
	case 'e':
		nextAction = HumanAction::ABILITY;
		break;
	default:
		nextAction = HumanAction::NONE;
		break;
	}
}

std::string Human::GetName() const
{
	return "Human";
}

void Human::Die(Organism* killer)
{
	world->SetPlayerAlive(false);
	Animal::Die(killer);
}
