#include "Sheep.h"
#include "World.h"
#include <stdlib.h>

Sheep::Sheep(const Vector2& pos) : Animal(pos, 4, 4, 3, 'S')
{
}

Sheep::~Sheep()
{
}

Sheep* Sheep::Clone(const Vector2& pos) const
{
	return new Sheep(pos);
}

std::string Sheep::GetName() const
{
	return "Sheep";
}
