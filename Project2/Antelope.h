#pragma once
#include "Animal.h"

class Antelope : public Animal
{
public:
	Antelope(const Vector2& pos);
	~Antelope();

	virtual void Hit(Organism* attacker) override;
	virtual void Movement() override;
	virtual Antelope* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:
	const int motionRange = 2;
};
