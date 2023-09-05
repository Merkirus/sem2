#include "LRU.h"
#include <algorithm>
#include <iostream>

LRU::LRU(Pamiec pamiec, Proces proces)
: pamiec{pamiec}, proces{proces} {}

void LRU::run(int n)
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

			/*Ustalenie najstarszego elementu w ramkach*/
			std::vector<unsigned> copy_past_seq = proces.getPastSeq();
			/*Teraz od najmlodszego do najstarszego*/
			std::reverse(copy_past_seq.begin(), copy_past_seq.end());
			/*---*/

			std::vector<int> temp{};
			for (int i=0; i < pamiec.getRamki().size(); ++i) {
				temp.push_back(pamiec.getRamki().at(i).getStrona().getNumerStrony());
			}

			while (temp.size() != 1)
			{
				unsigned desired = copy_past_seq.at(0);
				copy_past_seq.erase(copy_past_seq.begin());
				auto it = temp.begin();
				for (; it != temp.end(); ++it) {
					if (desired == *it) {
						break;
					}
				}
				if (it != temp.end()) {
					temp.erase(it);
				}
			}

			int index_ramki = 0;
			for (int i=0; i < pamiec.getRamki().size(); ++i) {
				if (pamiec.getRamki().at(i).getStrona().getNumerStrony() == temp.at(0)) {
					index_ramki = i;
					break;
				}
			}

			pamiec.setStronaAt(index_ramki,nextPage);
		}
	}

	std::cout << "Błędy ramek: " << bledy << '\n';
}