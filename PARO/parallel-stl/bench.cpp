#include <iostream>
#include <vector>
#include <random>
#include <algorithm>
#include <iterator>
#include <execution>
#include <celero/Celero.h>
#include <cmath>
#include <ranges>

template<typename T>
void fill_structure(T& structure, size_t amount)
{
    static std::random_device rd{};
    static std::mt19937 gen{ rd() };
    std::uniform_int_distribution<> dist{};
    std::generate_n(std::inserter(structure, std::begin(structure)), amount, [&]() { return dist(gen); });
}

CELERO_MAIN

constexpr auto TEST_SETS{6};
constexpr auto SAMPLES{10};
constexpr auto ITERATIONS{1000};

struct VectorTests : public ::celero::TestFixture
{
    std::vector<ExperimentValue> getExperimentValues() const override
    {
        std::vector<ExperimentValue> sizes{};
        std::ranges::transform(std::views::iota(0, TEST_SETS),
                               std::back_inserter(sizes),
                               [](const auto n) { return ExperimentValue{ int64_t(pow(2, 2 * n + 1)) * 16 }; });
        return sizes;
    }

    void setUp(const ExperimentValue& ev) override
    {
        nums.reserve(ev.Value);
        out.reserve(ev.Value);
        fill_structure(nums, ev.Value);
    }

    static int simple_op(const int in) { return 2 * in; }
    static int complex_op(const int in) { return (int)(std::sqrt(std::sin(in) * std::cos(in) + std::sqrt(2 * in))); }

    std::vector<int> nums;
    std::vector<int> out;
};

// transform x -> 2*x

BASELINE_F(SimpleTransformOp, Seq, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::seq, std::begin(nums), std::end(nums), out.begin(), simple_op);
}

BENCHMARK_F(SimpleTransformOp, Par, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::par, std::begin(nums), std::end(nums), out.begin(), simple_op);
}

BENCHMARK_F(SimpleTransformOp, Unseq, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::unseq, std::begin(nums), std::end(nums), out.begin(), simple_op);
}

BENCHMARK_F(SimpleTransformOp, ParUnseq, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::par_unseq, std::begin(nums), std::end(nums), out.begin(), simple_op);
}

// transform - more complex math ops

BASELINE_F(ComplexTransformOp, Seq, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::seq, std::begin(nums), std::end(nums), out.begin(), complex_op);
}

BENCHMARK_F(ComplexTransformOp, Par, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::par, std::begin(nums), std::end(nums), out.begin(), complex_op);
}

BENCHMARK_F(ComplexTransformOp, Unseq, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::unseq, std::begin(nums), std::end(nums), out.begin(), complex_op);
}

BENCHMARK_F(ComplexTransformOp, ParUnseq, VectorTests, SAMPLES, ITERATIONS)
{
    std::transform(std::execution::par_unseq, std::begin(nums), std::end(nums), out.begin(), complex_op);
}

// sort

BASELINE_F(Sort, Seq, VectorTests, SAMPLES, ITERATIONS)
{
    std::sort(std::execution::seq, std::begin(nums), std::end(nums));
}

BENCHMARK_F(Sort, Par, VectorTests, SAMPLES, ITERATIONS)
{
    std::sort(std::execution::par, std::begin(nums), std::end(nums));
}

BENCHMARK_F(Sort, Unseq, VectorTests, SAMPLES, ITERATIONS)
{
    std::sort(std::execution::unseq, std::begin(nums), std::end(nums));
}

BENCHMARK_F(Sort, ParUnseq, VectorTests, SAMPLES, ITERATIONS)
{
    std::sort(std::execution::par_unseq, std::begin(nums), std::end(nums));
}
