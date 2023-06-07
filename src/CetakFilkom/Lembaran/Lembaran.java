package CetakFilkom.Lembaran;

public class Lembaran {
    // protected int totalHalaman;
    private String menu;
    protected int harga;

    public Lembaran(String menu, int harga) {
        this.harga = harga;
        this.menu = menu;
    }

    public Lembaran() {
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
