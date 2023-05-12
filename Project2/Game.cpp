#include <fstream>
#include <sstream>
#include "Game.h"
#include "Wolf.h"
#include "Sheep.h"
#include "Fox.h"
#include "Turtle.h"
#include "Antelope.h"
#include "Grass.h"
#include "Dandelion.h"
#include "Guarana.h"
#include "Belladonna.h"
#include "Heracleum.h"

Game::Game(int height, int width)
	: world(height, width)
{
	player = new Human({ height / 2, width / 2 });
	world.AddOrganism(player);
	GenerateWorld();
	DrawMenu();
	world.DrawWorld();
}

Game::~Game()
{
}

void Game::GenerateWorld()
{
	// populate the world
	int wolfCount = 3;
	int sheepCount = 3;
	int foxCount = 3;
	int turtleCount = 3;
	int antelopeCount = 3;
	int grassCount = 3;
	int dandelionCount = 2;
	int guaranaCount = 2;
	int belladonnaCount = 1;
	int heracleumCount = 1;
	for (int i = 0; i < wolfCount; i++)
	{
		Wolf* newWolf = new Wolf(world.GetRandomEmptyTile());
		world.AddOrganism(newWolf);
	}
	for (int i = 0; i < sheepCount; i++)
	{
		Sheep* newSheep = new Sheep(world.GetRandomEmptyTile());
		world.AddOrganism(newSheep);
	}
	for (int i = 0; i < foxCount; i++)
	{
		Fox* newFox = new Fox(world.GetRandomEmptyTile());
		world.AddOrganism(newFox);
	}
	for (int i = 0; i < turtleCount; i++)
	{
		Turtle* newTurtle = new Turtle(world.GetRandomEmptyTile());
		world.AddOrganism(newTurtle);
	}
	for (int i = 0; i < antelopeCount; i++)
	{
		Antelope* newAntelope = new Antelope(world.GetRandomEmptyTile());
		world.AddOrganism(newAntelope);
	}
	for (int i = 0; i < grassCount; i++)
	{
		Grass* newGrass = new Grass(world.GetRandomEmptyTile());
		world.AddOrganism(newGrass);
	}
	for (int i = 0; i < dandelionCount; i++)
	{
		Dandelion* newDandelion = new Dandelion(world.GetRandomEmptyTile());
		world.AddOrganism(newDandelion);
	}
	for (int i = 0; i < guaranaCount; i++)
	{
		Guarana* newGuarana = new Guarana(world.GetRandomEmptyTile());
		world.AddOrganism(newGuarana);
	}
	for (int i = 0; i < belladonnaCount; i++)
	{
		Belladonna* newBelladonna = new Belladonna(world.GetRandomEmptyTile());
		world.AddOrganism(newBelladonna);
	}
	for (int i = 0; i < heracleumCount; i++)
	{
		Heracleum* newHeracleum = new Heracleum(world.GetRandomEmptyTile());
		world.AddOrganism(newHeracleum);
	}
}

void Game::SimulateWorld(char input)
{
	if (world.IsPlayerAlive())
		player->SetNextAction(input);

	system("cls"); // clear the screen

	world.ExecuteTurn();
	world.DrawLogs();
	DrawMenu();
	world.DrawWorld();
	world.ClearLogs();
}

void Game::SaveWorld()
{
	std::string filename;
	std::cout << "Enter file name: ";
	std::cin >> filename;

	std::fstream file;
	file.open(filename, std::fstream::out);

	// save world stats
	file << world.GetSize().x << " " << world.GetSize().y << " " << world.GetTurnNumber() << "\n";

	// save the organisms
	for (Organism* org : world.GetOrganisms())
	{
		file << org->GetSymbol() << " " << org->GetPosition().x << " " << org->GetPosition().y << " " << org->GetStrength();

		if (Human* h = dynamic_cast<Human*>(org))
			file << " " << h->GetAbilityDurationLeft();

		file << "\n";
	}

	file.close();

	std::cout << "World state saved successfully!\n";
	
}

void Game::LoadWorld()
{
	std::string filename;
	std::cout << "Enter file name: ";
	std::cin >> filename;

	std::fstream file;
	file.open(filename, std::fstream::in);

	Vector2 size;
	size_t turnNum;
	file >> size.x;
	file >> size.y;
	file >> turnNum;

	// create a new world
	world = World(size.x, size.y);
	world.SetTurnNumber(turnNum);

	// load the organisms
	while (!file.eof())
	{
		char symbol;
		Vector2 pos;
		int strength;

		file >> symbol;
		file >> pos.x;
		file >> pos.y;
		file >> strength;

		switch (symbol)
		{
		case 'H':
		{
			Human* newHuman = new Human(pos);
			int ability;
			file >> ability;
			newHuman->SetAbilityDurationLeft(ability);
			world.AddOrganism(newHuman);
			break;
		}
		case 'W':
		{
			Wolf* newWolf = new Wolf(pos);
			world.AddOrganism(newWolf);
			break;
		}
		case 'S':
		{
			Sheep* newSheep = new Sheep(pos);
			world.AddOrganism(newSheep);
			break;
		}
		case 'F':
		{
			Fox* newFox = new Fox(pos);
			world.AddOrganism(newFox);
			break;
		}
		case 'T':
		{
			Turtle* newTurtle = new Turtle(pos);
			world.AddOrganism(newTurtle);
			break;
		}
		case 'A':
		{
			Antelope* newAntelope = new Antelope(pos);
			world.AddOrganism(newAntelope);
			break;
		}
		case ',':
		{
			Grass* newGrass = new Grass(pos);
			world.AddOrganism(newGrass);
			break;
		}
		case 'm':
		{
			Dandelion* newDandelion = new Dandelion(pos);
			world.AddOrganism(newDandelion);
			break;
		}
		case 'g':
		{
			Guarana* newGuarana = new Guarana(pos);
			world.AddOrganism(newGuarana);
			break;
		}
		case '?':
		{
			Belladonna* newBelladonna = new Belladonna(pos);
			world.AddOrganism(newBelladonna);
			break;
		}
		case '%':
		{
			Heracleum* newHeracleum = new Heracleum(pos);
			world.AddOrganism(newHeracleum);
			break;
		}
		default:
			std::cout << "Unknown organism symbol '" << symbol << "'\n";
			break;
		}
	}

	file.close();

	std::cout << "World state loaded successfully\n";
	world.DrawWorld();
}

void Game::DrawMenu()
{
	std::cout << "\n --- Filip Jezierski 196333 ---\n\n";
	std::cout << " Controls:\n";
	std::cout << "   WASD - move the player\n";
	std::cout << "   E    - use player's special ability (Alzur's Shield)\n";
	std::cout << "   C    - save world state\n";
	std::cout << "   V    - load world state\n\n";
}
