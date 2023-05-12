#pragma once
#include "World.h"
#include "Human.h"

class Game
{
public:
	Game(int height, int width);
	~Game();

	void GenerateWorld();
	void SimulateWorld(char input);
	void SaveWorld();
	void LoadWorld();
	void DrawMenu();

private:
	World world;
	Human* player;
};
