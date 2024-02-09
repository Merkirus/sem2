project(benchmark)
set(CMAKE_CXX_FLAGS "-std=c++2a -O0")
add_executable(bench bench.cpp)
target_link_libraries(bench tbb celero)
