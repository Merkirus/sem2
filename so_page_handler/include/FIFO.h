#ifndef FIFO_H
#define FIFO_H

#include "Pamiec.h"
#include "Proces.h"

class FIFO
{
public:
	FIFO(Pamiec pamiec, Proces proces);
	~FIFO() = default;
	void run(int n);
private:
	Pamiec pamiec;
	Proces proces;
};

#endif /*FIFO_H*/