#include <iostream>
#include "Prostokat.h"

using namespace std;

int main() {

	Prostokat prostokat {2,2};

	cout << "Pole prosotkąta: "  << prostokat.getArea() << " , obwód prostokąta: " << prostokat.getPerimeter() << '\n';

	return 0;
}
