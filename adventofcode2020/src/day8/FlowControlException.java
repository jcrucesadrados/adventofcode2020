package day8;

@SuppressWarnings("serial")
public class FlowControlException extends Exception {

	private int exceptionCode;
	private String exceptionDescription;
	
	public FlowControlException(int exceptionCode, String exceptionDescription) {
		this.exceptionCode=exceptionCode;
		this.exceptionDescription=exceptionDescription;
	}
	
	public int getExceptionCode() {
		return this.exceptionCode;
	}
	
	public String getExceptionDescription() {
		return this.exceptionDescription;
	}
	
	public void showExceptionDetail() {
		System.out.println("[Flow Exception "+this.exceptionCode+"] "+this.exceptionDescription);
	}

}
