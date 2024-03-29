#pragma once
#include <vector>

struct Memory final {
    using MemoryCell = unsigned char;

    MemoryCell get() const {
        return *it_;
    }

    void set(MemoryCell const value) {
        *it_ = value;
    }

    void increment() {
        ++(*it_);
    }

    void decrement() {
        --(*it_);
    }

    void moveLeft() {
        if(it_ == memory_.begin())
            it_ = memory_.end();
        --it_;
    }

    void moveRight() {
        if(it_ == memory_.end())
            it_ = memory_.begin();
        else
            ++it_;
    }

    Memory()
        : memory_(2000, 0)
        , it_(memory_.begin() + (memory_.size() / 2)) {
    }

    Memory(Memory const&) = delete;
    Memory& operator=(Memory const&) = delete;

private:
    using MemoryCells = std::vector<MemoryCell>;
    MemoryCells memory_;
    MemoryCells::iterator it_;
};
