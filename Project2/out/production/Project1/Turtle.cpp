#include "Turtle.h"
#include "World.h"
#include <stdlib.h>

Turtle::Turtle(const Vector2& pos) : Animal(pos, 1, 2, 1, 'T')
{
}

Turtle::~Turtle()
{
}

void Turtle::Hit(Organism* attacker)
{
	if (attacker->GetStrength() < 5)
	{
		world->Logs() << GetName() << " deflected an attack from " << attacker->GetName() << " on " << pos << "\n";
		attacker->GoBack();
		return;
	}

	Animal::Hit(attacker);
}

void Turtle::Movement()
{
	if (rand() % 4 == 0)
		MoveTo(pos.GetNeighbor(rand() % 4));
	else
		world->Logs() << GetName() << " stayed on " << pos << "\n";
}
Turtle* Turtle::Clone(const Vector2& pos) const
{
	return new Turtle(pos);
}

std::string Turtle::GetName() const
{
	return "Turtle";
}
