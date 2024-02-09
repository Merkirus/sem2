#include <iostream>
#include <stdexcept>
#include <memory>

void foo()
{
    throw std::runtime_error("Error");
}

class MyPointer
{
public:
    /* W przypadku rzucenia wyjątku w konstruktorze nie zostanie zawołany
    destruktor obiektu, trzeba samemu zadbać o zwolnienie zasobów.
    Tu: zaalokowałem pamięć po potencjalnym rzuceniu wyjątkiem */
    MyPointer()
    {
        foo();
        this->pointer = new int(15);
    }

    ~MyPointer()
    {
        delete pointer;
    }
    int* pointer;
};

int main()
{
    try
    {
        MyPointer pointerTest;
        std::cout << pointerTest.pointer << std::endl;
    }
    catch(std::runtime_error const& p_err)
    {
        std::cout << "Ups: " << p_err.what() << std::endl;
    }
}
