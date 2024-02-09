#pragma once

#include <vector>

class BowlingGame
{
public:
	BowlingGame() {
		for (int i=0; i < 10 ; ++i)
			rounds.push_back(Round());
	}
	void roll(int pins) {
		while (it != rounds.end())
		{
			return;
		}
		if (*it.bonus > 0)
		{
			bonus_rounds.push_back(*it);
		}
		if (*it.throws == 0)
		{
			score += *it.score;
			++it;
		}
		for (Round round : bonus_rounds)
		{

		}
		--(*it.throws);
		*it.increaseScore(pins);
		if (!bonus_rounds.empty())
		{
			
		}
	}
	int getScore() {
		return score;
	}
private:
	std::vector<Round> rounds;
	std::vector<Round> bonus_rounds;
	auto it = rounds.begin();
	int score;
};

struct Round
{
	int throws = 2;
	int score = 0;
	int bonus = 0;
	void increaseScore(int pins) {
		score += pins;
		if (throws == 1 && score == 10) {
			throws = 0;
			bonus = 2;
		}
		if (throws == 0  && score == 10) {
			bonus = 1;
		}
	}
};
