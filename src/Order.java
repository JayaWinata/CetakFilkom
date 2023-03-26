import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;

public class Order {
    enum status {
        UNPAID, SUCCESSFUL, CANCELED
    }

    private static int hargaPrint = 200;
    private static int hargaPrintWarna = 500;
    private static int hargaCopy = 300;
    private static int hargaCopyWarna = 2000;
    private String pesanan;
    private Konsumen konsumen;
    private Lembaran lembaran;
    private String tanggal;
    private int noPesanan;
    private float biaya;
    private float ongkir;
    private float diskon;

    Order(Konsumen konsumen, Lembaran lembaran) {
        this.konsumen = konsumen;
        this.lembaran = lembaran;
    }

    public void setTanggal() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        this.tanggal = d.format(c.getTime());
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setNoPesanan(int noPesanan) {
        this.noPesanan = noPesanan;
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

    public Konsumen getKonsumen() {
        return konsumen;
    }

    public Lembaran getLembaran() {
        return lembaran;
    }

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

    public status checkOut(int opsi) {
        switch (opsi) {
            case 1:
                pay();
                return status.SUCCESSFUL;
            case 2:
                return status.CANCELED;
        }
        return status.UNPAID;
    }

    public void printDetails() {
        String batas = "=".repeat(50);
        System.out.println(batas + "\n");
        System.out.printf("%31s", "FILKOM CETAK\n");
        System.out.println("\n" + batas);
        konsumen.tampilkanData();
        System.out.println(batas);
        System.out.println(pesanan);
    }

}
