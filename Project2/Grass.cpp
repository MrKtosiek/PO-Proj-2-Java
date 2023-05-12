#include "Grass.h"
#include "World.h"
#include <stdlib.h>

Grass::Grass(const Vector2& pos) : Plant(pos, 0, 30, ',')
{
}

Grass::~Grass()
{
}

Grass* Grass::Clone(const Vector2& pos) const
{
	return new Grass(pos);
}

std::string Grass::GetName() const
{
	return "Grass";
}
