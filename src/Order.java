import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

enum Status {
    UNPAID, SUCCESSFUL, CANCELED
}

public class Order {

    private double ongkir;
    private int persenOngkir = 10;
    private int noPesanan;
    private Status status = Status.UNPAID;
    private Pelanggan pelanggan;
    private double biaya = 0;
    private Promosi promo;
    private double biayaTotal;
    private String promoCode;
    private LocalDate tanggalPembelian;
    protected static HashMap<String, Lembaran> cart = new HashMap<>();
    protected static HashMap<String, Integer> cartQty = new HashMap<>();

    public Order(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public HashMap<String, Lembaran> getCart() {
        return cart;
    }

    public HashMap<String, Integer> getCartQty() {
        return cartQty;
    }

    public void addToCart(String key, Lembaran cartItem, int qty) throws QuantityException {
        if (cart.containsKey(key)) {
            tambahKuantitas(key, qty);
            return;
        }
        setKuantitas(key, qty);
        cart.put(key, cartItem);
    }

    // mengembalikan nilai 1 jika masih terdapat
    // kuantitas yang tersisa dari hasil penghapusan menu.
    // jika saat dihapus kuantitas <= 0, maka akan mengembalikan nilai 0
    public int hapusCart(String key, int qty) {
        if ((getKuantitas(key) - qty) >= 1) {
            kurangiKuantitas(key, qty);
            return 1;
        } else {
            cart.remove(key);
            return 0;
        }
    }

    private void setKuantitas(String key, int kuantitas) throws QuantityException {
        if (kuantitas < 0)
            throw new QuantityException("Nilai tidak boleh negatif!");
        cartQty.put(key, kuantitas);
    }

    public void tambahKuantitas(String key, int qty) {
        int currentQty = cartQty.get(key);
        cartQty.put(key, (qty + currentQty));
    }

    public void kurangiKuantitas(String key, int qty) {
        int currentQty = cartQty.get(key);
        cartQty.put(key, (currentQty - qty));
    }

    public int getKuantitas(String key) {
        return cartQty.get(key);
    }

    public int getJumlahQty() {
        int jml = 0;
        for (String key : cartQty.keySet()) {
            jml += cartQty.get(key);
        }
        return jml;
    }

    public void setTanggal() {
        tanggalPembelian = LocalDate.now();
    }

    public String tanggaltoString() {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String tanggal = tanggalPembelian.format(dt);
        return tanggal;
    }

    public int getNoPesanan() {
        return noPesanan;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public Promosi getPromo() {
        return this.promo;
    }

    public Status geStatus() {
        return this.status;
    }

    public void applyPromo(String key, Promosi promo) throws PromotionNotMetExcpetion {
        setBiaya();
        setOngkir(persenOngkir);
        if (promo.isEligible(this.pelanggan) && promo.isOngkirEligible(ongkir)
                && promo.isPriceEligible(biaya)) {
            this.promo = promo;
            this.promoCode = key;
        } else
            throw new PromotionNotMetExcpetion();
    }

    public void setBiaya() {
        setOngkir(persenOngkir);
        biaya = 0;
        for (String key : cart.keySet()) {
            Lembaran temp = cart.get(key);
            biaya += (temp.getHarga() * cartQty.get(key));
        }
    }

    public double getBiaya() {
        return this.biaya;
    }

    public double getBiayaPlusOngkir() {
        return biaya + ongkir;
    }

    public double getBiayaDiskon() {
        if (promo instanceof PercentOffPromo) {
            promo = (PercentOffPromo) promo;
            return promo.hitungDiskon(getBiayaPlusOngkir());
        } else if (promo instanceof CashbackPromo) {
            promo = (CashbackPromo) promo;
            return promo.hitungCashBack(getBiayaPlusOngkir());
        } else if (promo instanceof OngkirPromo) {
            promo = (OngkirPromo) promo;
            return promo.hitungDiskonOngkir(ongkir);
        }
        return 0;
    }

    public void setOngkir(int PersenOngkir) {
        this.ongkir = biaya * (PersenOngkir / 100.0);
    }

    public double getBiayaOngkir() {
        return this.ongkir;
    }

    public double getBiayaTotal() {
        return biayaTotal;
    }

    public void checkOut() {
        biayaTotal = getBiayaPlusOngkir() - getBiayaDiskon();
        pelanggan.bayar(biayaTotal);
        this.status = Status.SUCCESSFUL;
        noPesanan++;
    }

    public void cancel() {
        System.out.println("Pesanan dibatalkan.");
        this.status = Status.CANCELED;
    }

    public void pay() {
        System.out.println("Pembayaran berhasil.");
        this.status = Status.SUCCESSFUL;
    }

    public StringBuilder print() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%3s | %-20s | %3s | %8s \n", "No", "Menu", "Qty", "Subtotal"));
        str.append("============================================\n");
        int num = 1;
        for (String key : cart.keySet()) {
            String nama = cart.get(key).getMenu();
            int qty = cartQty.get(key);
            int harga = cart.get(key).getHarga();
            str.append(String.format("%3d | %-20s |%3d |%8d\n", num, nama, qty, (qty * harga)));
            num++;
        }
        str.append("============================================\n");
        return str;
    }
}
