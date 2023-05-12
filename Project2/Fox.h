#pragma once
#include "Animal.h"

class Fox : public Animal
{
public:
	Fox(const Vector2& pos);
	~Fox();

	virtual void Movement() override;
	virtual Fox* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
