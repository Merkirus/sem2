#include <gtest/gtest.h>

#include <algorithm>
#include <vector>

using namespace std;

template <typename T>
vector<T> pow2(vector<T> const& input)
{
    // TODO:      This function should return input elements raised to the power of two.
    vector<T> temp(input);

    for_each(temp.begin(), temp.end(), [&](T& element) {element*=element;});
    
    return vector<T>{std::move(temp)};
}

TEST(Pow2Test, Test)
{
    vector<long> input =    {2, 5,  10,  15,  20,  25,  30,  45,   100};
    vector<long> expected = {4, 25, 100, 225, 400, 625, 900, 2025, 10000};
    auto actual = pow2(input);

    ASSERT_EQ(expected, actual);
}

TEST(Pow2Test, ZeroVectors)
{
    vector<int> input {0,0,0,0};
    auto actual = pow2(input);

    ASSERT_EQ(input, actual);
}

TEST(Pow2Test, EmptyVector)
{
    vector<int> input {};
    auto actual = pow2(input);

    ASSERT_EQ(input, actual);
}

TEST(Pow2Test, NegativeElementsVector)
{
    vector<long> input {-9,-4,-2,0,4,8,12,17};
    vector<long> expected {81,16,4,0,16,64,144,289};
    auto actual = pow2(input);

    ASSERT_EQ(expected, actual);
}

int main(int ac, char ** av)
{
    ::testing::InitGoogleTest(&ac, av);
    return RUN_ALL_TESTS();
}

