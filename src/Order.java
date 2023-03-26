import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {
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
        this.diskon = diskon;
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
}
