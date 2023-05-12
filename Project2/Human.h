#pragma once
#include <string>
#include "Animal.h"

class Human : public Animal
{
public:
	Human(const Vector2& pos);
	~Human();

	bool IsAbilityActive() const;
	bool CanActivateAbility() const;
	void ActivateAbility();
	int GetAbilityDurationLeft() const;
	void SetAbilityDurationLeft(const int& value);

	virtual void Action() override;
	virtual void Movement() override;
	virtual void Hit(Organism* attacker) override;

	virtual Human* Clone(const Vector2& pos) const override;

	void SetNextAction(const char code);

	std::string GetName() const;
	virtual void Die(Organism* killer) override;

private:
	enum class HumanAction
	{
		NONE,
		UP,
		DOWN,
		LEFT,
		RIGHT,
		ABILITY
	} nextAction = HumanAction::NONE;
	int abilityDuration = 5;
	int abilityLeft = 0;
};
