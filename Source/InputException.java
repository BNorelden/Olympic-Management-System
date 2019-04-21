public class InputException extends Exception {

    private static final long serialVersionUID = 1L;
    private String msg;

    InputException(String msg) {

        this.msg = msg;

    }

    public String toString() {
        
        return msg;
    }

}