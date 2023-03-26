import java.util.InputMismatchException;

public class Order {
    enum Status {
        UNPAID, SUCCESSFUL, CANCELED
    }

    private static int hargaPrint = 200;
    private static int hargaPrintWarna = 500;
    private static int hargaCopy = 300;
    private static int hargaCopyWarna = 2000;
    private int noPesanan;
    private Status status;
    private String pesanan;
    private Pelanggan pelanggan;
    // private Lembaran lembaran;
    private int tanggal;
    private int bulan;
    private int tahun;
    private float biaya;
    private float ongkir;
    private float diskon;

    Order(Pelanggan pelanggan, Lembaran lembaran) {
        this.pelanggan = pelanggan;
        // this.lembaran = lembaran;
    }

    public void setTanggal(int dd, int MM, int YYYY) {
        this.tanggal = dd;
        this.bulan = MM;
        this.tahun = YYYY;
    }

    public String getTanggal() {
        return String.format("%02d/%02d/%d", tanggal, bulan, tahun);
    }

    public int getNoPesanan() {
        return noPesanan;
    }

    public void setBiaya(float biaya) {
        this.biaya += biaya;
    }

    public float getBiaya() {
        return biaya;
    }

    public void setOngkir(float ongkir) {
        this.ongkir = ongkir;
    }

    public float getOngkir() {
        return ongkir;
    }

    public void setDiskon(float diskon) {
        this.diskon *= diskon;
    }

    public float getDiskon() {
        return diskon;
    }

    public Pelanggan getKonsumen() {
        return pelanggan;
    }

    // public Lembaran getLembaran() {
    // // return lembaran;
    // }

    public float getTotalHarga() {
        return biaya + ongkir - diskon;
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
                this.pesanan += String.format("Print (hitam- putih)\t\tRp. %d\n", getHargaPrint());
                break;
            case 2:
                this.pesanan += String.format("Print (berwarna)\t\tRp. %d\n", getHargaPrint());
                break;
            case 3:
                this.pesanan += String.format("Fotokpoi (hitam- putih)\t\tRp. %d\n", getHargaPrint());
                break;
            case 4:
                this.pesanan += String.format("Fotokopi (berwarna)\t\tRp. %d\n", getHargaPrint());
                break;
            default:
                throw new InputMismatchException("Masukkan input dengan benar!");
        }
    }

    public static void setHargaPrint(int harga) {
        hargaCopy = harga;
    }

    public static void setHargaPrintWarna(int harga) {
        hargaPrintWarna = harga;
    }

    public static void setHargaCopy(int harga) {
        hargaCopy = harga;
    }

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

    public void pay() {
        System.out.println("Melakukan pembayaran");
    }

    /**
     * @param opsi
     *             Pilih angka berikut:
     *             1. untuk melakukan pembayaran
     *             2. untuk membatalkan pembayaran
     */
    public void checkOut(int opsi) {
        switch (opsi) {
            case 1:
                pay();
                this.status = Status.SUCCESSFUL;
            case 2:
                System.out.println("Pesanan dibatalkan");
                this.status = Status.CANCELED;
            default:
                throw new InputMismatchException("Masukkan input dengan benar!");
        }
    }

    public void printDetails() {
        if (status != null) {
            String batas = "=".repeat(50);
            System.out.println(batas + "\n");
            System.out.printf("%31s", "FILKOM CETAK\n");
            System.out.println("\n" + batas);
            System.out.println("Nama pelanggan\t\t: " + pelanggan.getNama());
            System.out.println("Tanggal pemesanan\t\t: " + getTanggal());
            System.out.println("Nomor pesanan\t\t: " + noPesanan);
            System.out.println(batas);
            pelanggan.lembaran.tampilkanData();
            System.out.println(batas);
            System.out.println(pesanan);
        }
        noPesanan++;
    }

}
