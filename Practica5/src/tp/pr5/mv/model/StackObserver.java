package tp.pr5.mv.model;

public interface StackObserver<T> {
	void onPush(T value);
	void onPop(T value);
}
