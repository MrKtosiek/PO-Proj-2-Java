#include "Fox.h"
#include "World.h"
#include <stdlib.h>

Fox::Fox(const Vector2& pos) : Animal(pos, 7, 3, 2, 'F')
{
}

Fox::~Fox()
{
}

void Fox::Movement()
{
	// make 3 attempts to move to a tile that doesn't contain a stronger organism
	for (size_t i = 0; i < 3; i++)
	{
		Vector2 target = pos.GetNeighbor(rand() % 4);
		Organism* org = world->GetOrganism(target);

		if (org == nullptr || (org != nullptr && GetStrength() >= org->GetStrength()))
		{
			MoveTo(target);
			return;
		}
	}

	world->Logs() << GetName() << " didn't move\n";
}

Fox* Fox::Clone(const Vector2& pos) const
{
	return new Fox(pos);
}

std::string Fox::GetName() const
{
	return "Fox";
}
