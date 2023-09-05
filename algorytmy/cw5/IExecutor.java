package cw5;

public interface IExecutor<T,R> {
    void execute(T elem);
    R getResult();
}
