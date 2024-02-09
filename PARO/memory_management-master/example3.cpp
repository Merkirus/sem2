#include <iostream>
#include <stdexcept>

void validateArguments(int argc)
{
    if(argc != 2)
    {
        std::cerr << "You need to pass 1 argument" << std::endl;
        exit(-1);
    }
}

class MyException : std::logic_error {
public:
    MyException(const char* what_arg) : logic_error(what_arg) {
        this->what_arg = what_arg;
    }
    const char* what() const noexcept override {
        return what_arg;
    }
private:
    const char* what_arg;
};

class Resource
{
public:
    void use(const char* arg)
    {
        std::cout << "Using resource. Passed " << *arg << std::endl;
        if (*arg == 'd')
        {
            throw MyException("Passed d. d is prohibited.");
        }
    }
};

/* W przypadku rzucenia wyjątku należy zadbać o oddelegowanie delete
do miejsca, w którym zostanie wykonane */
int main(int argc, char* argv[])
{
    validateArguments(argc);

    const char* argument = argv[1];
    Resource* rsc = nullptr;

    try
    {
        rsc = new Resource();
        rsc->use(argument);
        delete rsc;
    }
    catch (MyException& e)
    {
        std::cout << e.what() << std::endl;
        delete rsc;
    }
    return 0;
}

