#include "FIFO.h"
#include <iostream>

FIFO::FIFO(Pamiec pamiec, Proces proces)
: pamiec{pamiec}, proces{proces} {}

void FIFO::run(int n)
{
	int bledy = 0;
	int index_ramek = 0;

	for (int i=0; i < n; ++i)
	{
		if (!pamiec.isFull()) {
			pamiec.dodajStroneDoPustejRamki(proces, bledy);
			continue;
		}

		Strona nextPage = proces.getLastSeqPage();

		std::cout << "Odwołanie: " << nextPage.getNumerStrony() << "\n";

		for (int i=0; i < pamiec.getRamki().size(); ++i) {
			std::cout << "Ramka: " << i << " Odwołanie w ramce: " << pamiec.getRamki().at(i).getStrona().getNumerStrony() << "\n";
		}

		if (pamiec.isAlreadyInFrame(nextPage)) {
			continue;
		} else {
			++bledy;
			int index = index_ramek % pamiec.getRamki().size();
			pamiec.setStronaAt(index, nextPage);
			++index_ramek;
		}
	}

	std::cout << "Błędy ramek: " << bledy << "\n";
}