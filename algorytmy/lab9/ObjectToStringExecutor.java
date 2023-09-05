package lab9;

import cw5.IExecutor;

public class ObjectToStringExecutor implements IExecutor<String,String> {
    StringBuffer line = new StringBuffer();
    @Override
    public void execute(String elem) {
        line.append(elem).append("; ");
    }

    @Override
    public String getResult() {
        line.delete(line.length()-2, line.length());
        return line.toString();
    }
}
