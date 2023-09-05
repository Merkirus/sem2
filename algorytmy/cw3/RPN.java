package cw3;

public class RPN {

    private IQueue<Object> queue;
    private IStack<Object> stack;
    private String rpnVersion;
    private double result;

    public RPN(String wyrazenie) throws EmptyStackException, EmptyQueueException, FullStackException {
        this.queue = KonwerterRPN.analize(wyrazenie);
        this.stack = new ArrayStack<>();
        this.rpnVersion = KonwerterRPN.toRPNString(queue);
        this.result = 0;
    }

    public void setWyrazenie(String wyrazenie) {
        this.rpnVersion = wyrazenie;
    }

    public void oblicz() throws Exception {
        while (!queue.isEmpty()) {
            Object newValue = queue.dequeue();
            String stringNewValue = newValue.toString();
            if (!stringNewValue.equals("*")
                    && !stringNewValue.equals("/")
                    && !stringNewValue.equals("+")
                    && !stringNewValue.equals("-")
                    && !stringNewValue.equals("%")) {
                stack.push(newValue);
                continue;
            }
            obliczHelper(stringNewValue);
        }
    }

    private void obliczHelper(String temp) throws EmptyStackException, FullStackException {
        switch (temp) {
            case "*" -> {
                result = (double)stack.pop() * (double)stack.pop();
                stack.push(result);
            }
            case "/" -> {
                double value = (double)stack.pop();
                result = (double)stack.pop() / value;
                stack.push(result);
            }
            case "%" -> {
                double value = (double)stack.pop();
                result = (double)stack.pop() % value;
                stack.push(result);
            }
            case "+" -> {
                result = (double)stack.pop() + (double)stack.pop();
                stack.push(result);
            }
            case "-" -> {
                double value = (double)stack.pop();
                result = (double)stack.pop() - value;
                stack.push(result);
            }
        }
    }

    public String getRpnVersion() {
        return rpnVersion;
    }

    public double getResult() {
        return result;
    }

    public IQueue<Object> getQueue() {
        return queue;
    }

    public IStack<Object> getStack() {
        return stack;
    }

    public void wyswietl() throws EmptyStackException, EmptyQueueException {
        System.out.println(rpnVersion);
    }

    public void wyswietlWynik() {
        System.out.println(result);
    }

}
