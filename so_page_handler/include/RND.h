#ifndef RND_H
#define RND_H

#include "Pamiec.h"
#include "Proces.h"

class RND
{
public:
	RND(Pamiec pamiec, Proces proces);
	~RND()=default;
	void run(int n);
private:
	Pamiec pamiec;
	Proces proces;
};

#endif