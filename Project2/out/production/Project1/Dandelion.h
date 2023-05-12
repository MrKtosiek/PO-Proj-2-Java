#pragma once
#include "Plant.h"

class Dandelion : public Plant
{
public:
	Dandelion(const Vector2& pos);
	~Dandelion();

	virtual void Action() override;
	virtual Dandelion* Clone(const Vector2& pos) const override;

	std::string GetName() const;

private:

};
