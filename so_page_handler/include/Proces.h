#ifndef PROCES_H
#define PROCES_H

#include <vector>
#include "Strona.h"

class Proces
{
public:
	Proces();
	~Proces()=default;
	void generatePages(int n);
	void generatePages();
	void generateSeq(int n);
	void generateSeq();
	void giveSeq(std::vector<unsigned> v);
	unsigned getLastSeqElem();
	Strona getLastSeqPage();
	void delLastSeqElem();
	bool ownPage(Strona strona);
	std::vector<unsigned> getSeq();
	std::vector<unsigned> getPastSeq();
private:
	std::vector<unsigned> sequence;
	std::vector<Strona> strony;
	std::vector<unsigned> past_sequence;
};

#endif /*PROCES_H*/