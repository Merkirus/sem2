#include "send_always.hpp"
#include <iostream>

#define TIME_LIMIT 1000
#define UPPER_THRESHOLD 70

send_always::send_always(class system sys) : sys{sys} {}

void send_always::start()
{
	while (sys.getZegar() != TIME_LIMIT) {

		sys.generujProcesy();

		/*WYSYLANIE WIADOMOSCI*/
		for (int i = 0; i < sys.getProcesory().size(); ++i) {
			if (sys.getProcesory().at(i).getProcesy().size() == 0)
				continue;
            for (int j = 0; j < sys.getProcesory().size(); ++j) {
                if (j == i)
                    continue;
                if (sys.getProcesory().at(j).getObciazenie() >= UPPER_THRESHOLD) {
                    sys.wyslijWiadomosc(i, i);
                    continue;
                }
                int size = sys.getProcesory().at(i).getProcesy().size();
                for (int k = 0; k < size; ++k) {
                    if (sys.getProcesory().at(j).getObciazenie() < UPPER_THRESHOLD) {
                        sys.wyslijWiadomosc(i, j);
                        sys.przekazProcesy(i);
                        sys.zerujWiadomosci();
                        size = sys.getProcesory().at(i).getProcesy().size();
                        --k;
                    }
                    if (sys.getProcesory().at(j).getObciazenie() >= UPPER_THRESHOLD) {
                        sys.wyslijWiadomosc(i, i);
                        break;
                    }
                }
            }
		}

		sys.tick();
	}

	sys.pokazStatystyki();
}