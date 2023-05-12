#include "Dandelion.h"
#include "World.h"
#include <stdlib.h>

Dandelion::Dandelion(const Vector2& pos) : Plant(pos, 0, 5, 'm')
{
}

Dandelion::~Dandelion()
{
}

void Dandelion::Action()
{
	for (int i = 0; i < 3; i++)
	{
		Plant::Action();
	}
}

Dandelion* Dandelion::Clone(const Vector2& pos) const
{
	return new Dandelion(pos);
}

std::string Dandelion::GetName() const
{
	return "Dandelion";
}
