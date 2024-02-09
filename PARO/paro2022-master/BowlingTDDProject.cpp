#include "catch/catch.hpp"
#include "BowlingGame.h"

TEST_CASE("2ZerosOneRoundShouldGive0") {
    BowlingGame game;

    game.roll(0);
    game.roll(0);

    REQUIRE(game.getScore() == 0);
}

TEST_CASE("") {
    BowlingGame game;

    game.roll(0);
    game.roll(0);
}