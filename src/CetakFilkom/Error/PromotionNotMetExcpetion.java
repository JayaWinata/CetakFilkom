package CetakFilkom.Error;

public class PromotionNotMetExcpetion extends Exception {
    public PromotionNotMetExcpetion() {
    }

    public PromotionNotMetExcpetion(String message) {
        System.err.println(message);
    }
}
