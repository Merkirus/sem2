#ifndef OPT_H
#define OPT_H

#include "Pamiec.h"
#include "Proces.h"

class OPT
{
public:
	OPT(Pamiec pamiec, Proces proces);
	~OPT()=default;
	void run(int n);
private:
	Pamiec pamiec;
	Proces proces;
};

#endif