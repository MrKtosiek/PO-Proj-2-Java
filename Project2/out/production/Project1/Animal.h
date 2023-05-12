#pragma once
#include "Organism.h"

class Animal : public Organism
{
public:
	Animal(const Vector2& pos, const int priority, const int strength, const int breedingCooldown, const char symbol);

	virtual void Action() override;
	virtual void Collide(Organism* other) override;
	virtual Animal* Clone(const Vector2& pos) const override = 0;


	virtual void Movement();
	virtual void MoveTo(const Vector2& target);
	virtual void Breed(Animal* other);

protected:
	int breedingCooldown = 5;
	int currentBreedingCooldown = 0;
};
