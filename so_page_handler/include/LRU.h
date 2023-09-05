#ifndef LRU_H
#define LRU_H

#include "Pamiec.h"
#include "Proces.h"

class LRU
{
public:
	LRU(Pamiec pamiec, Proces proces);
	~LRU()=default;
	void run(int n);
private:
	Pamiec pamiec;
	Proces proces;
};

#endif