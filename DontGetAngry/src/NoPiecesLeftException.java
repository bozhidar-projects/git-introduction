import java.io.PrintStream;

public class NoPiecesLeftException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
	}
	
	@Override
	public String getMessage() {
		
		return "No pieces left!";
	}
}
