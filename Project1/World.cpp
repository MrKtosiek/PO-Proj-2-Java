#include <conio.h>
#include "World.h"
#include <iostream>
#include <sstream>

World::World() : World::World(15, 15)
{
}
World::World(int height, int width)
{
	size = { height, width };
}
World::World(const World& orig)
{
	size = orig.size;
	turnNumber = orig.turnNumber;
	playerAlive = orig.playerAlive;
	organisms = std::vector<Organism*>(orig.organisms);
}
World::~World()
{
	for (size_t i = 0; i < organisms.size(); i++)
		delete organisms[i];
}

void World::AddOrganism(Organism* org)
{
	org->SetWorld(this);
	organisms.push_back(org);
}

void World::RemoveOrganism(Organism* org)
{
	for (size_t i = 0; i < organisms.size(); i++)
	{
		if (organisms[i] == org)
		{
			organisms.erase(organisms.begin() + i);
			delete org;
			break;
		}
	}
}

Organism* World::GetOrganism(const Vector2& pos) const
{
	for (size_t i = 0; i < organisms.size(); i++)
	{
		if (pos == organisms[i]->GetPosition())
			return organisms[i];
	}
	return nullptr;
}

std::vector<Organism*>& World::GetOrganisms()
{
	return organisms;
}

const Vector2& World::GetSize() const
{
	return size;
}

const size_t World::GetTurnNumber() const
{
	return turnNumber;
}

void World::SetTurnNumber(const size_t& value)
{
	turnNumber = value;
}

std::stringstream& World::Logs()
{
	return logs;
}

bool World::IsPlayerAlive() const
{
	return playerAlive;
}

void World::SetPlayerAlive(const bool& value)
{
	playerAlive = value;
}

bool World::ContainsPos(Vector2 pos) const
{
	return
		pos.x >= 0 && pos.x < size.x&&
		pos.y >= 0 && pos.y < size.y;
}

Vector2 World::GetRandomEmptyTile() const
{
	Vector2 pos = {};

	const int safetyLimit = 1000;
	for (int i = 0; i < safetyLimit; i++)
	{
		pos.x = rand() % size.x;
		pos.y = rand() % size.y;
		// if there is no organism on the chosen tile, return the position
		if (GetOrganism(pos) == nullptr)
			break;
	}

	return pos;
}

bool World::HasEmptyNeighbor(const Vector2 pos) const
{
	for (size_t i = 0; i < 4; i++)
	{
		Vector2 n = pos.GetNeighbor(i);
		if (ContainsPos(n) && GetOrganism(n) == nullptr)
			return true;
	}
	return false;
}

Vector2 World::GetEmptyNeighbor(const Vector2 pos) const
{
	std::vector<Vector2> targets;
	for (size_t i = 0; i < 4; i++)
	{
		Vector2 n = pos.GetNeighbor(i);
		if (ContainsPos(n) && GetOrganism(n) == nullptr)
			targets.push_back(n);
	}

	if (targets.size() > 0)
		return targets[rand() % targets.size()];
	else
		return { -1,-1 };
}

void World::ExecuteTurn()
{
	// order organisms by their priority
	struct ComparePriorities
	{
		bool operator() (const Organism* org1, const Organism* org2)
		{
			return (org1->GetPriority() > org2->GetPriority());
		}
	};
	std::stable_sort(organisms.begin(), organisms.end(), ComparePriorities());


	// execute actions
	size_t orgCount = organisms.size();
	for (size_t i = 0; i < orgCount; i++)
	{
		if (!organisms[i]->IsAlive())
			continue;

		//world->Logs() << i + 1 << ". ";
		organisms[i]->Action();
		
		// handle collisions
		for (size_t j = 0; j < organisms.size(); j++)
		{
			if (i != j && organisms[j]->IsAlive() && organisms[i]->GetPosition() == organisms[j]->GetPosition())
			{
				organisms[i]->Collide(organisms[j]);
			}
		}
	}

	// remove dead organisms
	for (int i = organisms.size() - 1; i >= 0; i--)
	{
		if (!organisms[i]->IsAlive())
		{
			RemoveOrganism(organisms[i]);
		}
	}


	logs << "Turn " << ++turnNumber << " finished\n";
}

void World::DrawWorld()
{
	// create a buffer for the drawing
	char** buffer = new char* [size.x];
	for (int x = 0; x < size.x; x++)
	{
		buffer[x] = new char[size.y + 1]();
		for (int y = 0; y < size.y; y++)
		{
			buffer[x][y] = ' ';
		}
	}

	// draw all organisms
	for (Organism* org : organisms)
	{
		org->Draw(buffer);
	}

	// draw the horizontal part of the frame
	_putch('#');
	for (int y = 0; y < size.y * 2 + 1; y++)
		_putch('-');
	_cputs("#\n");

	// draw the buffer on the console
	for (int x = 0; x < size.x; x++)
	{
		_putch('|');
		_putch(' ');
		for (int y = 0; y < size.y; y++)
		{
			_putch(buffer[x][y]);
			_putch(' ');
		}
		_cputs("|\n");
	}

	// draw the horizontal part of the frame
	_putch('#');
	for (int y = 0; y < size.y * 2 + 1; y++)
		_putch('-');
	_cputs("#\n");

	// deallocate the buffer
	for (int x = 0; x < size.x; x++)
	{
		delete[] buffer[x];
	}
	delete[] buffer;
}

void World::DrawLogs()
{
	std::cout << " --- Logs: ---\n\n" << logs.str();
}

void World::ClearLogs()
{
	logs.str("");
}

World& World::operator=(const World& other)
{
	World tmp(other);

	std::swap(size, tmp.size);
	std::swap(turnNumber, tmp.turnNumber);
	std::swap(playerAlive, tmp.playerAlive);
	std::swap(organisms, tmp.organisms);

	return *this;
}
