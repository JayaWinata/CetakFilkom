package CetakFilkom;

public class PageOutOfBoundsException extends Exception {
    public PageOutOfBoundsException(String message) {
        System.err.println(message);
    }
}
