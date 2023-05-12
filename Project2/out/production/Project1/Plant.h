#pragma once
#include "Organism.h"

class Plant : public Organism
{
public:
	Plant(const Vector2& pos, const int strength, const int growChance, const char symbol);

	virtual void Action() override;
	virtual void Collide(Organism* other) override;
	virtual Plant* Clone(const Vector2& pos) const override = 0;

	virtual void Grow() const;

protected:
	int growChance;
};
