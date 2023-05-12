#pragma once
#include <vector>
#include <algorithm>
#include <sstream>
#include "Vector2.h"
#include "Organism.h"

class World
{
public:
	World();
	World(int height, int width);
	World(const World& orig);
	~World();

	void AddOrganism(Organism* org);
	void RemoveOrganism(Organism* org);
	Organism* GetOrganism(const Vector2& pos) const;
	std::vector<Organism*>& GetOrganisms();
	const Vector2& GetSize() const;
	const size_t GetTurnNumber() const;
	void SetTurnNumber(const size_t& value);
	std::stringstream& Logs();

	bool IsPlayerAlive() const;
	void SetPlayerAlive(const bool& value);

	bool ContainsPos(Vector2 pos) const;
	Vector2 GetRandomEmptyTile() const;
	bool HasEmptyNeighbor(const Vector2 pos) const;
	Vector2 GetEmptyNeighbor(const Vector2 pos) const;

	void ExecuteTurn();
	void DrawWorld();
	void DrawLogs();
	void ClearLogs();

	World& operator=(const World& other);

private:
	Vector2 size;
	std::vector<Organism*> organisms;
	size_t turnNumber = 0;
	bool playerAlive = true;
	std::stringstream logs;
};
