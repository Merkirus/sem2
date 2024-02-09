#include "Brainfuck.hpp"
#include "Memory.hpp"
#include <iostream>

std::string Brainfuck::interpret(Code const& code, Input const& input) const {
    Memory memory;
    std::string result;
    auto input_it = input.begin();

    for(auto code_it = code.begin(); code_it != code.end(); ++code_it){
        switch(*code_it){
            case '>':
                memory.moveRight();
                break;
            case '<':
                memory.moveLeft();
                break;
            case '+':
                memory.increment();
                break;
            case '-':
                memory.decrement();
                break;
            case '.':
                result += memory.get();
                break;
            case ',':
                memory.set(*input_it);
                ++input_it;
                break;
            case '[':
                // std::cout << "IN" << '\n';
                if(memory.get() == 0){
                    int depth = 0;
                    while(1){
                        
                        if(*code_it == '[')
                            depth++;

                        if(*code_it == ']')
                            depth--;

                        if(depth == 0)
                            break;
                            
                        ++code_it;
                    }
                }
                break;
            case ']':
            // std::cout << "OUT" << '\n';
                if(memory.get() != 0){
                    int depth = 0;
                    while(1){
                        
                        if(*code_it == ']')
                            depth++;

                        if(*code_it == '[')
                            depth--;
                            
                        if(depth == 0){
                            break;
                        }

                        --code_it;
                    }
                }
                break;
        }
    }

    return result;
}
