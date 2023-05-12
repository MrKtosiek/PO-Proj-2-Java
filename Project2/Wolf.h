#pragma once
#include "Animal.h"

class Wolf : public Animal
{
public:
	Wolf(const Vector2& pos);
	~Wolf();

	virtual Wolf* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
