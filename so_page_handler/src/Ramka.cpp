#include "Ramka.h"
#include "BlankStrona.h"

Ramka::Ramka()
{
	obecna_strona = BlankStrona{};
}

Strona Ramka::getStrona() const
{
	return obecna_strona;
}

void Ramka::setStrona(Strona strona)
{
	this->obecna_strona = strona;
}
