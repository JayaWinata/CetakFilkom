package CetakFilkom;

public class DateOutOfBoundsException extends Exception {
    public DateOutOfBoundsException(String message) {
        System.err.println(message);
    }

    public DateOutOfBoundsException() {
    }
}
