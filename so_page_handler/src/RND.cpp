#include "RND.h"
#include "Random.h"
#include <iostream>

RND::RND(Pamiec pamiec, Proces proces)
: pamiec{pamiec}, proces{proces} {}

void RND::run(int n)
{
	int bledy = 0;

	for (int i=0; i < n; ++i)
	{
		if (!pamiec.isFull()) {
			pamiec.dodajStroneDoPustejRamki(proces, bledy);
			continue;
		}

		Strona nextPage = proces.getLastSeqPage();
		if (pamiec.isAlreadyInFrame(nextPage)) {
			continue;
		} else {
			++bledy;
			int size = pamiec.getRamki().size();
			int index = randnum(0,size-1);
			pamiec.setStronaAt(index,nextPage);
		}
	}

	std::cout << "Błędy ramek: " << bledy << '\n';
}