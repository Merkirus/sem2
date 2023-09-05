package lab4;

public class Nawiasy {

    private ArrayStack<Character> stack;

    public Nawiasy() {
        this.stack = new ArrayStack<>();
    }

    public boolean nawiasyZrownowazone(String wyrazenie) throws FullStackException, EmptyStackException {
        String temp = wyrazenie.trim();
        char[] chars = new char[temp.length()];
        temp.getChars(0, temp.length(), chars, 0);
        for (char c : chars) {
            if (nawiasOtwierajacy(c)) {
                stack.push(c);
            } else if (nawiasZamykajacy(c)) {
                char temp2 = stack.top();
                if (temp2 == odwrocNawias(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public char odwrocNawias(char c) {
        if (c == ']') return '[';
        else if (c == '}') return '{';
        else return '(';
    }

    public boolean nawiasOtwierajacy(char c) {
        return (c == '(') || (c == '{') || (c == '[');
    }

    public boolean nawiasZamykajacy(char c) {
        return (c == ')') || (c == '}') || (c == ']');
    }

}
