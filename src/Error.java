class PromotionNotMetExcpetion extends Exception {
    PromotionNotMetExcpetion() {
        System.err.println("Syarat tidak terpenuhi");
    }

    PromotionNotMetExcpetion(String message) {
        System.err.println(message);
    }
}

class DateOutOfBoundsException extends Exception {
    DateOutOfBoundsException(String message) {
        System.err.println(message);
    }
}

class PageOutOfBoundsException extends Exception {
    PageOutOfBoundsException(String message) {
        System.err.println(message);
    }
}
