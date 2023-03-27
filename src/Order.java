import java.util.InputMismatchException;

public class Order {
    enum Status {
        UNPAID, SUCCESSFUL, CANCELED
    }

    private static int hargaPrint = 200;
    private static int hargaPrintWarna = 500;
    private static int hargaCopy = 300;
    private static int hargaCopyWarna = 2000;
    private double ongkir = getBiaya() * 0.1;
    private int noPesanan;
    private Status status;
    private String pesanan = "";
    private Pelanggan pelanggan;
    // private Lembaran lembaran;
    private int tanggal;
    private int bulan;
    private int tahun;
    private double biaya;
    private Promosi promo;

    Order(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
        // this.lembaran = lembaran;
    }

    public void setTanggal(int dd, int MM, int YYYY) {
        this.tanggal = dd;
        this.bulan = MM;
        this.tahun = YYYY;
    }

    public String TanggaltoString() {
        return String.format("%02d/%02d/%d", tanggal, bulan, tahun);
    }

    public int getNoPesanan() {
        return noPesanan;
    }

    /**
     * method ini berfungsi sebagai setter pada atribut biaya dengan perhitungan
     * total halaman buku / lembaran x kuantitas (berapa kali pengguna ingin
     * melakukan print / fotokopi) x harga (harga print / fotokopi)
     */
    public void setBiaya(int totalHalaman, int kuantitas, int harga) {
        this.biaya += (totalHalaman * kuantitas * harga);
    }

    public void applyPromo(Promosi promo) {
        this.promo = promo;
    }

    public double getBiaya() {
        return biaya;
    }

    public double getOngkir() {
        return ongkir;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    // public void setLembaran(Lembaran lembaran) {
    // this.lembaran = lembaran;
    // }

    // public Lembaran getLembaran() {
    // return lembaran;
    // }

    public double getTotalBiaya() {
        return biaya + ongkir;
    }

    public double getBiayaDiskon() {
        if (promo instanceof PercentOffPromo) {
            promo = (PercentOffPromo) promo;
            return promo.hitungDiskon(getTotalBiaya());
        } else if (promo instanceof CashbackPromo) {
            promo = (CashbackPromo) promo;
            return promo.hitungCashBack(getTotalBiaya());
        } else if (promo instanceof OngkirPromo) {
            promo = (OngkirPromo) promo;
            return biaya + promo.hitungDiskonOngkir(getOngkir());
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
    public void setPesanan(int opsi) {
        switch (opsi) {
            case 1:
                this.pesanan += String.format("Print (hitam- putih)\t\tRp. %d\n", hargaPrint);
                break;
            case 2:
                this.pesanan += String.format("Print (berwarna)\t\tRp. %d\n", hargaPrintWarna);
                break;
            case 3:
                this.pesanan += String.format("Fotokpoi (hitam- putih)\t\tRp. %d\n", hargaCopy);
                break;
            case 4:
                this.pesanan += String.format("Fotokopi (berwarna)\t\tRp. %d\n", hargaCopyWarna);
                break;
            default:
                throw new InputMismatchException("Masukkan input dengan benar!");
        }
    }

    /**
     * Method ini nantinya hanya bisa diakses oleh admin
     */
    public void setOngkir(int PersenOngkir) {
        this.ongkir = biaya * (PersenOngkir / 100.0);
    }

    /**
     * Method ini nantinya hanya bisa diakses oleh admin
     */
    public static void setHargaPrint(int harga) {
        hargaCopy = harga;
    }

    /**
     * Method ini nantinya hanya bisa diakses oleh admin
     */
    public static void setHargaPrintWarna(int harga) {
        hargaPrintWarna = harga;
    }

    /**
     * Method ini nantinya hanya bisa diakses oleh admin
     */
    public static void setHargaCopy(int harga) {
        hargaCopy = harga;
    }

    /**
     * Method ini nantinya hanya bisa diakses oleh admin
     */
    public static void setHargaCopyWarna(int harga) {
        hargaCopyWarna = harga;
    }

    public static int getHargaPrint() {
        return hargaPrint;
    }

    public static int getHargaPrintWarna() {
        return hargaPrintWarna;
    }

    public static int getHargaCopy() {
        return hargaCopy;
    }

    public static int getHargaCopyWarna() {
        return hargaCopyWarna;
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
            pelanggan.lembaran.tampilkanData();
            System.out.println(batas);
            System.out.println(pesanan);
        }
    }
}
