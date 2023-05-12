#pragma once
#include "Plant.h"

class Belladonna : public Plant
{
public:
	Belladonna(const Vector2& pos);
	~Belladonna();

	virtual void Hit(Organism* attacker) override;
	virtual Belladonna* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
