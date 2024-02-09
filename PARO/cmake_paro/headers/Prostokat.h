#ifndef PROSTOKAT
#define PROSTOKAT

class Prostokat {
public:
	Prostokat();
	Prostokat(int x, int y);
	int getArea() const;
	int getPerimeter() const;
private:
	int x;
	int y;
};


#endif