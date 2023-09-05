#include "OPT.h"
#include <iostream>

OPT::OPT(Pamiec pamiec, Proces proces)
: pamiec{pamiec}, proces{proces} {}

void OPT::run(int n)
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

			/*temp vector - ostatni element do usuniecia
			w prawdziwej pamieci*/

			std::vector<int> temp{};
			for (int i=0; i < pamiec.getRamki().size(); ++i) {
				temp.push_back(pamiec.getRamki().at(i).getStrona().getNumerStrony());
			}

			std::vector<unsigned> copy_seq = proces.getSeq();

			while (temp.size() != 1) {
				if (copy_seq.empty()) {
					while (temp.size() != 1)
						temp.erase(temp.begin());
					break;
				}
				unsigned desired = copy_seq.at(0);
				copy_seq.erase(copy_seq.begin());
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
			/*koniec*/

			int index_ramki = 0;
			for (int i=0; i < pamiec.getRamki().size(); ++i) {
				if (pamiec.getRamki().at(i).getStrona().getNumerStrony() == temp.at(0)) {
					index_ramki = i;
					break;
				}
			}
			pamiec.setStronaAt(index_ramki, nextPage);
		}
	}

	std::cout << "Błędy ramek: " << bledy << '\n';
}