package tp.pr5.mv.model;

public interface MemoryObserver<T> {
	void onWrite(int index, T value);
}
