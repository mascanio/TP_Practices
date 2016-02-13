package tp.pr5.mv.model;

@SuppressWarnings("serial")
public class CpuIsHaltedException extends Throwable {

	public CpuIsHaltedException() {
	}

	public CpuIsHaltedException(String message) {
		super(message);
	}

}
