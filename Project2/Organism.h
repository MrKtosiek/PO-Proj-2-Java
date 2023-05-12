#pragma once
#include <iostream>
#include <string>
#include "Vector2.h"

class World;

class Organism
{
public:
	Organism(const Vector2& pos, const int priority, const int strength, const char symbol);
	virtual ~Organism();

	virtual void SetWorld(World* world);
	virtual Vector2 GetPosition() const;
	virtual void SetPosition(const Vector2& value);
	virtual int GetPriority() const;
	virtual int GetStrength() const;
	virtual char GetSymbol() const;
	virtual void SetStrength(const int value);
	virtual bool IsAlive() const;

	virtual void Action() = 0;
	virtual void Collide(Organism* other) = 0;
	virtual void Hit(Organism* attacker);
	virtual void GoBack();

	virtual void Draw(char** buffer) const;
	virtual std::string GetName() const = 0;
	virtual Organism* Clone(const Vector2& pos) const = 0;
	virtual void Die(Organism* killer);

protected:
	Vector2 pos = { 0,0 };
	Vector2 prevPos = { 0,0 };
	int priority = 0;
	int strength = 0;
	char symbol = 0;
	bool isAlive = true;
	World* world = nullptr;
};
