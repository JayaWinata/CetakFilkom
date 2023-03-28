public class Lembaran {
    protected int totalHalaman;

    public Lembaran(int totalHalaman) {
        this.totalHalaman = totalHalaman;
    }

    public int getTotalHalaman() {
        return this.totalHalaman;
    }

    public void tampilkanData() {
        System.out.println("Total halaman\t\t\t: " + this.totalHalaman);
    }
}

class Buku extends Lembaran {
    private String judul;

    public Buku(String judul, int totalHalaman) {
        super(totalHalaman);
        this.judul = judul;
        this.totalHalaman = totalHalaman;
    }

    public String getJudul() {
        return this.judul;
    }

    @Override
    public void tampilkanData() {
        System.out.println("Judul Buku\t\t\t: " + this.judul);
        super.tampilkanData();
    }

}
