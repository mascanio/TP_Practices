package tp.pr4.mv;

@SuppressWarnings("serial")
public class CpuIsHaltedException extends Throwable {

	public CpuIsHaltedException() {
	}

	public CpuIsHaltedException(String message) {
		super(message);
	}

}
