#include "ALRU.h"
#include <iostream>

ALRU::ALRU(Pamiec pamiec, Proces proces)
: pamiec{pamiec}, proces{proces} {}

void ALRU::run(int n)
{
	int bledy = 0;

	for (int i=0; i < n; ++i)
	{
		if (!pamiec.isFull()) {
			pamiec.dodajStroneDoPustejRamkiMemoVer(proces, bledy);
			continue;
		}

		Strona nextPage = proces.getLastSeqPage();
		if (pamiec.isAlreadyInFrame(nextPage)) {
			continue;
		} else {
			++bledy;
			int index = pamiec.get0LifeMemoIndexInFrames();
			pamiec.updateMemo(nextPage);
			pamiec.setStronaAt(index, nextPage);
		}
	}

	std::cout << "Błędy ramek: " << bledy << '\n';
}