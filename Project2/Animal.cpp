#include "Animal.h"
#include "World.h"
#include <cstdlib>
#include <typeinfo>

Animal::Animal(const Vector2& pos, const int priority, const int strength, const int breedingCooldown, const char symbol)
	: Organism(pos, priority, strength, symbol), breedingCooldown(breedingCooldown)
{
}

void Animal::Action()
{
	Movement();

	currentBreedingCooldown--;
}

void Animal::Collide(Organism* other)
{
	world->Logs() << "Collision on " << pos << " (" << GetName() << ", " << other->GetName() << ")\n";
	if (GetName() == other->GetName())
	{
		Breed((Animal*)other);
	}
	else
	{
		other->Hit(this);
	}
}

void Animal::Movement()
{
	MoveTo(pos.GetNeighbor(rand() % 4));
}

void Animal::MoveTo(const Vector2& target)
{
	if (world->ContainsPos(target))
	{
		prevPos = pos;
		pos = target;
		world->Logs() << GetName() << " moved to " << pos << '\n';
	}
	else
	{
		world->Logs() << GetName() << " couldn't move to " << target << '\n';
	}
}

void Animal::Breed(Animal* other)
{
	GoBack();
	if (!world->HasEmptyNeighbor(other->GetPosition()))
	{
		world->Logs() << "Breeding failed on " << pos << " (not enough space)\n";
		return;
	}
	if (currentBreedingCooldown > 0 || other->currentBreedingCooldown > 0)
	{
		world->Logs() << "Breeding failed on " << pos << " (cooldown)\n";
		return;
	}

	Vector2 childPos = world->GetEmptyNeighbor(other->GetPosition());
	Animal* child = Clone(childPos);
	world->AddOrganism(child);

	currentBreedingCooldown = breedingCooldown;
	other->currentBreedingCooldown = other->breedingCooldown;
	child->currentBreedingCooldown = child->breedingCooldown;

	world->Logs() << "Breeding on " << child->pos << "\n";
}
