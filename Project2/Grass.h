#pragma once
#include "Plant.h"

class Grass : public Plant
{
public:
	Grass(const Vector2& pos);
	~Grass();

	virtual Grass* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
