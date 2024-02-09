#include <gtest/gtest.h>

#include <algorithm>
#include <iterator>
#include <vector>

using namespace std;

struct Product final
{
    float price;
    explicit Product(float price) : price(price) {}
    bool operator==(Product const& o) const { return price == o.price; }
    bool operator<(Product const& o) const { return price < o.price; }
    // Note: This class should not be touched.
};

vector<Product> find_cheapest(vector<Product> const& v, unsigned num)
{
    // TODO: return x cheapest products, where x is function param (num)
    // Note: Please pay attention to performance.
    vector<Product> temp(v);
    
    unsigned shift = num > v.size() ? v.size() : num;

    nth_element(temp.begin(), temp.begin()+shift, temp.end());

    return vector<Product>(temp.begin(), temp.begin()+shift);
}

TEST(Cheap, Test)
{
    vector<Product> products {
        Product{0.10}, Product{0.20}, Product{0.50}, Product{0.80}, Product{0.99},
        Product{1.00}, Product{1.20}, Product{1.50}, Product{1.80}, Product{1.99},
        Product{2.00}, Product{2.20}, Product{2.50}, Product{2.80}, Product{2.99}
    };

    auto const numCheapest = 5;
    vector<Product> expected;
    expected.reserve(numCheapest);
    copy(begin(products), begin(products) + numCheapest, back_inserter(expected));
    random_shuffle(products.begin(), products.end());
    auto actual = find_cheapest(products, numCheapest);
    sort(begin(actual), end(actual));

    ASSERT_EQ(expected, actual);
}

TEST(Cheap, TestCornerCase)
{
    vector<Product> products { Product{1.0} };

    auto const numCheapest = 5;
    vector<Product> expected { Product{1.0} };

    ASSERT_EQ(expected, find_cheapest(products, numCheapest));
}

TEST(Cheap, TestZeroElement)
{

    vector<Product> products {};

    auto const numCheapest = 2;

    vector<Product> expected {};

    ASSERT_EQ(expected, find_cheapest(products, numCheapest));

}

TEST(Cheap, TestNegativeNumbers)
{

    vector<Product> products {
        Product{-5.2}, Product{-2.7}, Product{-1.9},
        Product{2.1}, Product{4.6}, Product{7.9}
    };

    auto const numCheapest = 2;

    vector<Product> expected;
    expected.reserve(numCheapest);
    copy(begin(products), begin(products) + numCheapest, back_inserter(expected));
    random_shuffle(products.begin(), products.end());
    auto actual = find_cheapest(products, numCheapest);
    sort(begin(actual), end(actual));

    ASSERT_EQ(expected, actual);
}

int main(int ac, char ** av)
{
    ::testing::InitGoogleTest(&ac, av);
    return RUN_ALL_TESTS();
}

