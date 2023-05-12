#include "Organism.h"
#include "World.h"

Organism::Organism(const Vector2& pos, const int priority, const int strength, const char symbol)
	: pos(pos), prevPos(pos), priority(priority), strength(strength), symbol(symbol)
{
}
Organism::~Organism()
{
}

void Organism::SetWorld(World* world)
{
	this->world = world;
}

Vector2 Organism::GetPosition() const
{
	return pos;
}

void Organism::SetPosition(const Vector2& value)
{
	pos = value;
}

int Organism::GetPriority() const
{
	return priority;
}

int Organism::GetStrength() const
{
	return strength;
}

char Organism::GetSymbol() const
{
	return symbol;
}

void Organism::SetStrength(const int value)
{
	strength = value;
}

bool Organism::IsAlive() const
{
	return isAlive;
}

void Organism::Hit(Organism* attacker)
{
	world->Logs() << attacker->GetName() << " attacked " << GetName() << " on " << pos << "\n";
	if (attacker->GetStrength() >= GetStrength())
	{
		// attacker wins
		this->Die(attacker);
	}
	else
	{
		// this organism wins
		attacker->Die(this);
	}
}

void Organism::GoBack()
{
	pos = prevPos;
}

void Organism::Draw(char** buffer) const
{
	buffer[pos.x][pos.y] = symbol;
}

void Organism::Die(Organism* killer)
{
	if (!isAlive)
		return;

	isAlive = false;
	world->Logs() << GetName() << " [" << GetStrength() << "] was killed by " << killer->GetName() << " [" << killer->GetStrength() << "]\n";
}
