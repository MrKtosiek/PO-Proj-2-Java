#include "Wolf.h"
#include "World.h"
#include <stdlib.h>

Wolf::Wolf(const Vector2& pos) : Animal(pos, 5, 9, 5, 'W')
{
}

Wolf::~Wolf()
{
}

Wolf* Wolf::Clone(const Vector2& pos) const
{
	return new Wolf(pos);
}

std::string Wolf::GetName() const
{
	return "Wolf";
}
