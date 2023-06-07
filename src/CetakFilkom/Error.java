package CetakFilkom;

class PromotionNotMetExcpetion extends Exception {
    public PromotionNotMetExcpetion() {
    }

    public PromotionNotMetExcpetion(String message) {
        System.err.println(message);
    }
}

class DateOutOfBoundsException extends Exception {
    public DateOutOfBoundsException(String message) {
        System.err.println(message);
    }

    public DateOutOfBoundsException() {
    }
}

class PageOutOfBoundsException extends Exception {
    public PageOutOfBoundsException(String message) {
        System.err.println(message);
    }
}

class QuantityException extends Exception {
    public QuantityException(String message) {
        System.err.println(message);
    }
}
