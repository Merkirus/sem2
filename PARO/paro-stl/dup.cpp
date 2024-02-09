#include <gtest/gtest.h>

#include <algorithm>
#include <vector>

using namespace std;

template <typename T>
vector<T> unique(vector<T> const& first, vector<T> const& second)
{
    // TODO:      This function should join input vectors and return only unique elements.
    // Important: Use only containers! No algorithms allowed.
    set<T> temp;

    temp.insert(first.begin(), first.end());
    temp.insert(second.begin(), second.end());

    return vector<T>{temp.begin(), temp.end()};
}

TEST(DupTest, Test)
{
    vector<double> d1 = {1.1, 2.1, 3.1, 1.0, 1.1, 0.56, 0.44, 4.445, 0.001, 9.996, 0.001};
    vector<double> d2 = {0.001, 0.002, 0.0003, 1.1, 0.44, 0.99, 0.996, 3.1, 3.12};

    vector<double> expected = {1.0, 0.002, 1.1, 0.0003, 2.1, 0.44, 0.001, 0.996, 4.445, 3.1, 9.996, 0.56, 3.12, 0.99};
    auto actual = unique(d1, d2);
    sort(begin(expected), end(expected));
    sort(begin(actual), end(actual));
    ASSERT_EQ(expected, actual);
}

TEST(DupTest, TestZeroElement)
{
    vector<double> d1;
    vector<double> d2;

    vector<double> expected;
    auto actual = unique(d1,d2);
    ASSERT_EQ(expected, actual);
}

TEST(DupTest, OneVector)
{
    vector<double> expected {1.2,4.9,5.1,8.9,10.121,33.512};
    vector<double> empty;

    auto actual = unique(expected,empty);
    ASSERT_EQ(expected, actual);
}

int main(int ac, char ** av)
{
    ::testing::InitGoogleTest(&ac, av);
    return RUN_ALL_TESTS();
}

