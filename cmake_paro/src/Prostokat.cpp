#include "Prostokat.h"

Prostokat::Prostokat() = default;

Prostokat::Prostokat(int x, int y) : x{x}, y{y} {}

int Prostokat::getArea() {
	return x*y;
}

int Prostokat::getPerimeter() {
	return (2*x)+(2*y);
}