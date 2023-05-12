#pragma once
#include "Animal.h"

class Sheep : public Animal
{
public:
	Sheep(const Vector2& pos);
	~Sheep();

	virtual Sheep* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
