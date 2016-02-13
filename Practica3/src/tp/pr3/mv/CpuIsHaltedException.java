package tp.pr3.mv;

@SuppressWarnings("serial")
public class CpuIsHaltedException extends Throwable {

	public CpuIsHaltedException() {
	}

	public CpuIsHaltedException(String message) {
		super(message);
	}

}
