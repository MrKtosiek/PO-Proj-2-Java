#pragma once
#include <iostream>

struct Vector2
{
	int x, y;

	Vector2 GetNeighbor(int i) const
	{
		if (i == 0) return { x, y + 1 };
		if (i == 1) return { x + 1, y };
		if (i == 2) return { x, y - 1 };
		if (i == 3) return { x - 1, y };

		return *this;
	}

	Vector2 GetNeighbor8Way(int i) const
	{
		if (i == 0) return { x - 1, y - 1 };
		if (i == 1) return { x - 1, y     };
		if (i == 2) return { x - 1, y + 1 };
		if (i == 3) return { x    , y + 1 };
		if (i == 4) return { x + 1, y + 1 };
		if (i == 5) return { x + 1, y     };
		if (i == 6) return { x + 1, y - 1 };
		if (i == 7) return { x    , y - 1 };

		return *this;
	}
	bool operator==(const Vector2& other) const
	{
		return x == other.x && y == other.y;
	}

	friend std::ostream& operator<<(std::ostream& str, const Vector2& vector)
	{
		str << vector.x << ',' << vector.y;
		return str;
	}
};
