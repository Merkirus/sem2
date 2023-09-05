#ifndef ALRU_H
#define ALRU_H

#include "Pamiec.h"
#include "Proces.h"

class ALRU
{
public:
	ALRU(Pamiec pamiec, Proces proces);
	~ALRU()=default;
	void run(int n);
private:
	Pamiec pamiec;
	Proces proces;
};

#endif