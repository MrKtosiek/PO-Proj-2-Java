#pragma once
#include "Animal.h"

class Turtle : public Animal
{
public:
	Turtle(const Vector2& pos);
	~Turtle();

	virtual void Hit(Organism* attacker) override;
	virtual void Movement() override;
	virtual Turtle* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
