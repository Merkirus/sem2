cmake_minimum_required(VERSION 3.22.2 FATAL_ERROR)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

set(PROJECT_NAME TEST)
project(${PROJECT_NAME} LANGUAGES CXX)

#[[ Aby prawidłowo działał clang-tidy nie można przekazać listy np file(GLOB_RECURSE SRC_FILES src/*.cpp) ]]
add_executable(main src/main.cpp src/Prostokat.cpp)

target_include_directories(main PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/headers)

find_program(
    CLANG_TIDY_EXE
    NAMES clang-tidy
)
if (CLANG_TIDY_EXE)
    message(STATUS "clang-tidy found: ${CLANG_TIDY_EXE}")
    set(DO_CLANG_TIDY
        "${CLANG_TIDY_EXE}"
        "-fix-errors"
    )
    set_target_properties(main PROPERTIES CXX_CLANG_TIDY "${DO_CLANG_TIDY}")
else()
    message(STATUS "clang-tidy not found.")
endif()