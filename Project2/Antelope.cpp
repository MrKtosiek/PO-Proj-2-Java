#include "Antelope.h"
#include "World.h"
#include <stdlib.h>

Antelope::Antelope(const Vector2& pos) : Animal(pos, 4, 4, 3, 'A')
{
}

Antelope::~Antelope()
{
}

void Antelope::Hit(Organism* attacker)
{
	if (rand() % 2 == 0 && world->HasEmptyNeighbor(pos))
	{
		MoveTo(world->GetEmptyNeighbor(pos));
		return;
	}
	Animal::Hit(attacker);
}

void Antelope::Movement()
{
	Vector2 target = pos;
	for (int i = 0; i < motionRange; i++)
	{
		target = target.GetNeighbor(rand() % 4);
	}
	MoveTo(target);
}

Antelope* Antelope::Clone(const Vector2& pos) const
{
	return new Antelope(pos);
}

std::string Antelope::GetName() const
{
	return "Antelope";
}
