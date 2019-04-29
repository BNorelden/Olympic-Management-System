package OlympicManagementSystem;

public class InputException extends Exception {
	
	private String msg;
	
	
		public InputException(String msg) {
			this.msg = msg;
		}
		
		public String get() {
			return msg;
		}


}