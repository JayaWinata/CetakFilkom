import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Order {
    private enum Status {
        UNPAID, SUCCESSFUL, CANCELED
    }

    private double ongkir = 0.1;
    private int noPesanan;
    private Status status;
    private String pesanan = "";
    private Pelanggan pelanggan;
    private int targetHalaman;
    private int tanggal;
    private int bulan;
    private int tahun;
    private double biaya;
    private Promosi promo;
    private double tempBiaya;
    protected static HashMap<String, Lembaran> cart = new HashMap<>();
    protected static HashMap<String, Integer> cartQty = new HashMap<>();

    public Order(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
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

    public void tampilkanCart() {
        System.out.println(cart.keySet());
    }

    public void setTanggal(int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        LocalDate date = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > date.getDayOfMonth()) || (MM <= 0 || MM > date.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
        this.tanggal = dd;
        this.bulan = MM;
        this.tahun = YYYY;
    }

    // public void setHalamanBuku(int halaman) throws PageOutOfBoundsException {
    // if (halaman <= 0 || halaman > pelanggan.getLembaran().getTotalHalaman()) {
    // throw new PageOutOfBoundsException("Halaman melebihi batas!");
    // }
    // this.targetHalaman = halaman;
    // }

    private void setKuantitas(String key, int kuantitas) throws QuantityException {
        if (kuantitas < 0)
            throw new QuantityException("Nilai tidak boleh negatif!");
        cartQty.put(key, kuantitas);
    }

    public int getKuantitas(String key) {
        return cartQty.get(key);
    }

    public String TanggaltoString() {
        return String.format("%02d/%02d/%d", tanggal, bulan, tahun);
    }

    public int getNoPesanan() {
        return noPesanan;
    }

    public double getTempBiaya() {
        return tempBiaya;
    }

    public void tambahKuantitas(String key, int qty) {
        int currentQty = cartQty.get(key);
        cartQty.put(key, (qty + currentQty));
    }

    public void kurangiKuantitas(String key, int qty) {
        int currentQty = cartQty.get(key);
        cartQty.put(key, (currentQty - qty));
    }

    /**
     * method ini berfungsi sebagai setter pada atribut biaya dengan perhitungan
     * total halaman buku / lembaran x kuantitas (berapa kali pengguna ingin
     * melakukan print / fotokopi) x harga (harga print / fotokopi)
     * 
     * @throws QuantityException
     */
    // public void setBiaya(int totalHalaman, int kuantitas, int harga) throws
    // QuantityException {
    // this.setKuantitas(kuantitas);
    // this.tempBiaya = (totalHalaman * kuantitas * harga);
    // this.biaya += (totalHalaman * kuantitas * harga);
    // }

    public void applyPromo(Promosi promo) throws PromotionNotMetExcpetion {
        if (promo.isEligible(pelanggan) && promo.isOngkirEligible(hitungOngkir())
                && promo.isPriceEligible(getBiaya())) {
            this.promo = promo;
        } else
            throw new PromotionNotMetExcpetion();
    }

    public Promosi getPromo() {
        return this.promo;
    }

    public double getBiaya() {
        return this.biaya;
    }

    public double hitungOngkir() {
        return biaya * ongkir;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public double getTotalBiaya() {
        return biaya + hitungOngkir();
    }

    public double getBiayaDiskon() {
        if (promo instanceof PercentOffPromo) {
            promo = (PercentOffPromo) promo;
            return promo.hitungDiskon(getTotalBiaya());
        } else if (promo instanceof CashbackPromo) {
            promo = (CashbackPromo) promo;
            return getTotalBiaya();
        } else if (promo instanceof OngkirPromo) {
            promo = (OngkirPromo) promo;
            return getBiaya() + promo.hitungDiskonOngkir(hitungOngkir());
        }
        return 0;
    }

    /**
     * @param opsi
     *             Pilih angka berikut:
     *             1. untuk membuat pesanan print hitam- putih
     *             2. untuk membuat pesanan print berwarna
     *             3. untuk membuat pesanan fotokpoi hitam- putih
     *             4. untuk membuat pesanan fotokopi berwarna
     */
    // public void setPesanan(int opsi) {
    // switch (opsi) {
    // case 1:
    // this.pesanan += String.format("%d Print (hitam-putih)\t\t- Rp.%.0f\n\t(%d
    // halaman)\n", getKuantitas(),
    // getTempBiaya(), targetHalaman);
    // break;
    // case 2:
    // this.pesanan += String.format("%d Print (berwarna)\t\t- Rp.%.0f\n\t(%d
    // halaman)\n", getKuantitas(),
    // getTempBiaya(), targetHalaman);
    // break;
    // case 3:
    // this.pesanan += String.format("%d Fotokopi (hitam-putih)\t- Rp.%.0f\n\t(%d
    // halaman)\n",
    // getKuantitas(),
    // getTempBiaya(), targetHalaman);
    // break;
    // case 4:
    // this.pesanan += String.format("%d Fotokopi (berwarna)\t\t- Rp.%.0f\n\t(%d
    // halaman)\n", getKuantitas(),
    // getTempBiaya(), targetHalaman);
    // break;
    // default:
    // throw new InputMismatchException("Masukkan input dengan benar!");
    // }
    // }

    public void setOngkir(int PersenOngkir) {
        this.ongkir = biaya * (PersenOngkir / 100.0);
    }

    public void checkOut() {
        System.out.println("Checkout berhasil.");
        this.status = Status.UNPAID;
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

    public void printDetails() {
        if (status != null) {
            String batas = "=".repeat(50);
            System.out.println(batas + "\n");
            System.out.printf("%31s", "FILKOM CETAK\n");
            System.out.println("\n" + batas);
            System.out.println("Nama pelanggan\t\t\t: " + pelanggan.getNama());
            System.out.println("Tanggal pemesanan\t\t: " + TanggaltoString());
            if (status != Status.CANCELED)
                System.out.println("Nomor pesanan\t\t\t: " + noPesanan);
            System.out.println(batas);
            // pelanggan.lembaran.tampilkanData();

            System.out.println(batas);
            System.out.print(pesanan);
            System.out.println(batas);
            System.out.printf("Biaya\t\t\t\t- Rp.%.0f\n", getBiaya());
            System.out.printf("Ongkir\t\t\t\t- Rp.%.0f\n", hitungOngkir());
            if (this.promo != null) {
                if (this.promo instanceof PercentOffPromo) {
                    System.out.println(((PercentOffPromo) this.promo).getDiskonToString());
                } else if (this.promo instanceof CashbackPromo) {
                    String temp = ((CashbackPromo) this.promo).getCashBackToString();
                    System.out.printf("%s\t\t\t+ Rp.%.0f\n", temp, promo.hitungCashBack(getTotalBiaya()));
                } else if (this.promo instanceof OngkirPromo) {
                    System.out.println(((OngkirPromo) this.promo).getOngkirPromotoString());
                }
                System.out.println(batas);
                System.out.printf("\t\t\t\t- Rp.%.0f", getBiayaDiskon());
            } else {
                System.out.println(batas);
                System.out.printf("\t\t\t\t= Rp.%.0f", getTotalBiaya());
            }

        }
    }
}
