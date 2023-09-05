#include "Pamiec.h"
#include <algorithm>
#include <iostream>

Pamiec::Pamiec(int n)
{
	this->ramki = std::vector<Ramka>();
	this->ramki.reserve(n);
	for (int i=0; i < n; ++i) {
		this->ramki.push_back(Ramka());
	}
	this->memo = std::vector<SecondChance>();
}

std::vector<Ramka> Pamiec::getRamki()
{
	return ramki;
}

bool Pamiec::isFull() const
{
	bool result = true;
	for_each(ramki.begin(), ramki.end(), [&](Ramka ramka)
	{
		if (ramka.getStrona().getNumerStrony() == -1)
		{
			result = false;
		}
	});
	return result;
}

void Pamiec::dodajStroneDoPustejRamki(Proces& proces, int& bledy)
{
	for (int i=0; i < ramki.size(); ++i)
	{
		if (ramki.at(i).getStrona().getNumerStrony() == -1) {
			Strona nextPage = proces.getLastSeqPage();
			bool escape = false;
			for (int j=0; j < i; ++j) {
				if (ramki.at(j).getStrona().getNumerStrony() == nextPage.getNumerStrony()) {
					escape = true;
					break;
				}
			}
			if (escape) break;
			++bledy;
			ramki.at(i).setStrona(nextPage);
			break;		
		}
	}
}

void Pamiec::dodajStroneDoPustejRamkiMemoVer(Proces& proces, int& bledy)
{
	for (int i=0; i < ramki.size(); ++i)
	{
		if (ramki.at(i).getStrona().getNumerStrony() == -1) {
			Strona nextPage = proces.getLastSeqPage();
			bool escape = false;
			for (int j=0; j < i; ++j) {
				if (ramki.at(j).getStrona().getNumerStrony() == nextPage.getNumerStrony()) {
					escape = true;
					break;
				}
			}
			if (escape) break;
			++bledy;
			updateMemo(nextPage);
			ramki.at(i).setStrona(nextPage);
			break;		
		}
	}
}

bool Pamiec::isAlreadyInFrame(Strona strona) const
{
	for (int i=0; i < ramki.size(); ++i)
	{
		if (ramki.at(i).getStrona().getNumerStrony() == strona.getNumerStrony()) {
			return true;
		}
	}
	return false;
}

void Pamiec::setStronaAt(int n, Strona& strona)
{
	ramki.at(n).setStrona(strona);
}

std::vector<SecondChance> Pamiec::getMemo()
{
	return memo;
}

int Pamiec::get0LifeMemoIndexInFrames()
{	
	unsigned desired;

	int init_size = memo.size();
	memo.reserve(init_size*2);

	auto it = memo.begin();
	while (true)
	{
		if ((*it).life == 0) {
			desired = (*it).numer_strony;
			++it;
			break;
		}
		SecondChance temp{(*it).numer_strony, 0};
		memo.push_back(temp);
		++it;
	}

	memo.erase(memo.begin(), it);

	memo.resize(init_size-1);

	int index = 0;
	for (int j=0; j < ramki.size(); ++j) {
		if (desired == ramki.at(j).getStrona().getNumerStrony()) {
			index = j;
			break;
		}
	}
	return index;
}

void Pamiec::updateMemo(Strona strona)
{
	unsigned numer_strony = strona.getNumerStrony();
	memo.push_back(SecondChance{numer_strony});
}