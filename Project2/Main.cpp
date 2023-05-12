#include <conio.h>
#include <stdlib.h>
#include <iostream>

#include "Game.h"

int main()
{
	srand(time(nullptr));

	Vector2 worldSize;
	std::cout << "Enter world height: ";
	std::cin >> worldSize.x;
	std::cout << "Enter world width: ";
	std::cin >> worldSize.y;

	Game game(worldSize.x, worldSize.y);

	char input = 0;
	while (input != 'q')
	{
		input = _getch();

		switch (input)
		{
		case 'c':
			game.SaveWorld();
			break;
		case 'v':
			game.LoadWorld();
			break;
		case 'q':
			break;
		default:
			game.SimulateWorld(input);
			break;
		}
	}

	return 0;
}
