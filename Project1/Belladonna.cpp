#include "Belladonna.h"
#include "World.h"
#include <stdlib.h>

Belladonna::Belladonna(const Vector2& pos) : Plant(pos, 99, 10, '?')
{
}

Belladonna::~Belladonna()
{
}

void Belladonna::Hit(Organism* attacker)
{
	Plant::Hit(attacker);
	this->Die(attacker);
}

Belladonna* Belladonna::Clone(const Vector2& pos) const
{
	return new Belladonna(pos);
}

std::string Belladonna::GetName() const
{
	return "Belladonna";
}
