public class Lembaran {
    // protected int totalHalaman;
    private String menu;
    protected int harga;
    protected int kuantitas;

    public Lembaran(String menu, int harga) {
        this.harga = harga;
        this.menu = menu;
    }

    public Lembaran() {
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }

    public void tambahKuantitas(int qty) {
        this.kuantitas += qty;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    // public int getTotalHalaman() {
    // return this.totalHalaman;
    // }

    // public void tampilkanData() {
    // System.out.println("Total halaman\t\t\t: " + this.totalHalaman);
    // }
}

class Buku extends Lembaran {
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
