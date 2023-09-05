#ifndef RAMKA_H
#define RAMKA_H

#include <vector>
#include "Strona.h"

class Ramka
{
public:
	Ramka();
	~Ramka() = default;
	Strona getStrona() const;
	void setStrona(Strona);
private:
	Strona obecna_strona;
};

#endif /*RAMKA_H*/