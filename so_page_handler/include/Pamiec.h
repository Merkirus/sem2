#ifndef PAMIEC_H
#define PAMIEC_H

#include <vector>
#include "Ramka.h"
#include "Proces.h"

struct SecondChance
{
	unsigned numer_strony;
	unsigned life = 1;
};

class Pamiec
{
public:
	Pamiec(int n);
	~Pamiec() = default;
	std::vector<Ramka> getRamki();
	bool isAlreadyInFrame(Strona strona) const;
	bool isFull() const;
	void dodajStroneDoPustejRamki(Proces& proces, int& bledy);
	void dodajStroneDoPustejRamkiMemoVer(Proces& proces, int& bledy);
	void setStronaAt(int n, Strona& strona);
	/*Do algorytmu drugiej szansy*/
	std::vector<SecondChance> getMemo();
	int get0LifeMemoIndexInFrames();
	void updateMemo(Strona);
private:
	std::vector<Ramka> ramki;
	std::vector<SecondChance> memo;
};

#endif /*PAMIEC_H*/