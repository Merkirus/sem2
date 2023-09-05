#include <time.h>
#include <random>
#include <vector>
#include <iostream>
#include "Random.h"
#include "Proces.h"
#include "FIFO.h"
#include "OPT.h"
#include "LRU.h"
#include "ALRU.h"
#include "RND.h"
#include "Pamiec.h"

using namespace std;

int main(int argc, char const *argv[])
{
	srand(time(NULL));
	
	Proces p1{};

	/*Liczba stron dla procesu*/
	p1.generatePages(5);

	/*Sekwencja odwołań*/
	// p1.giveSeq(vector<unsigned>{1,2,3,4,1,2,5,1,2,3,4,5});
	p1.generateSeq(200);

	/*Liczba ramek*/
	Pamiec pam{4};

	/*Ilość analizowanych procesów*/
	int n = p1.getSeq().size();

	FIFO fifo{pam, p1};
	OPT opt{pam, p1};
	LRU lru{pam, p1};
	ALRU alru{pam, p1};
	RND rnd{pam, p1};

	cout << "FIFO: " << '\n';
	fifo.run(n);
	cout << '\n';
	cout << "OPT: " << '\n';
	opt.run(n);
	cout << '\n';
	cout << "LUR: " << '\n';
	lru.run(n);
	cout << '\n';
	cout << "ALRU: " << '\n';
	alru.run(n);
	cout << '\n';
	cout << "RAND: " << '\n';
	rnd.run(n);

	return 0;
}