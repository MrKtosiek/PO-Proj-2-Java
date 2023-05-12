#include "Heracleum.h"
#include "World.h"
#include <stdlib.h>

Heracleum::Heracleum(const Vector2& pos) : Plant(pos, 10, 10, '%')
{
}

Heracleum::~Heracleum()
{
}

void Heracleum::Action()
{
	Plant::Action();

	for (int i = 0; i < 8; i++)
	{
		Vector2 target = pos.GetNeighbor8Way(i);
		Animal* animal = dynamic_cast<Animal*>(world->GetOrganism(target));
		if (animal != nullptr)
		{
			animal->Die(this);
		}
	}
}

Heracleum* Heracleum::Clone(const Vector2& pos) const
{
	return new Heracleum(pos);
}

std::string Heracleum::GetName() const
{
	return "Heracleum";
}
