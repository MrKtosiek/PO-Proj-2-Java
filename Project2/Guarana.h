#pragma once
#include "Plant.h"

class Guarana : public Plant
{
public:
	Guarana(const Vector2& pos);
	~Guarana();

	virtual void Hit(Organism* attacker) override;
	virtual Guarana* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
