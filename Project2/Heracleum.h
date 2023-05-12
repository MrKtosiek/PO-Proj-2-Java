#pragma once
#include "Plant.h"
#include "Animal.h"

class Heracleum : public Plant
{
public:
	Heracleum(const Vector2& pos);
	~Heracleum();

	virtual void Action() override;
	virtual Heracleum* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
