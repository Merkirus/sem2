#include <catch.hpp>

#include "Memory.hpp"

TEST_CASE( "initially memory cells are set to 0", "[memory]" ) {
    Memory memory;
    REQUIRE( memory.get() == Memory::MemoryCell{0} );
}

TEST_CASE( "getting after setting gives the same value", "[memory]" ) {
    Memory memory;
    constexpr auto value = 42;
    memory.set(value);
    REQUIRE( memory.get() == value );
}

#if 1
TEST_CASE( "increment", "[memory]" ) {
    Memory memory;
    constexpr auto value = 42;
    memory.set(value);
    memory.increment();
    REQUIRE( memory.get() == value + 1 );
}

TEST_CASE( "decrement", "[memory]" ) {
    Memory memory;
    constexpr auto value = 42;
    memory.set(value);
    memory.decrement();
    REQUIRE( memory.get() == value - 1 );
}

TEST_CASE( "memory position can be moved around", "[memory]" ) {
    Memory memory;
    constexpr auto value1 = 3;
    memory.set(value1);
    memory.moveLeft();
    constexpr auto value2 = 2;
    memory.set(value2);
    REQUIRE( memory.get() == value2 );
    memory.moveRight();
    REQUIRE( memory.get() == value1 );
}

#endif

SCENARIO( "getting after setting gives the same value", "[memory-scen]" ) {

    GIVEN( "memory and a value" ) {
        Memory memory;
        constexpr auto value = 42;

        WHEN ("we set the value") {
            memory.set(value);

            THEN ( "getting value yields same value" ) {
                REQUIRE( memory.get() == value );
            }
        }
    }
}
