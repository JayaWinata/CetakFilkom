package CetakFilkom.Lembaran;

public class Buku extends Lembaran {
    private String judul;

    public Buku(String judul) {
        this.judul = judul;
        super.setMenu(judul);
    }

    public String getJudul() {
        return this.judul;
    }

    // @Override
    // public void tampilkanData() {
    // System.out.println("Judul Buku\t\t\t: " + this.judul);
    // super.tampilkanData();
    // }

}
