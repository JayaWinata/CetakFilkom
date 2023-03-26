public class Pengguna {
    protected String nama;
    protected String noTelp;

    public Pengguna(String nama, String noTelp) {
        this.nama = nama;
        this.noTelp = noTelp;
    }

    public void tampilkanData() {
        System.out.println("Nama\t\t\t: " + this.nama);
        System.out.println("No. Telp\t\t: " + this.noTelp);
    }
}

class Admin extends Pengguna {
    private static String password;
    
    public Admin(String nama, String noTelp) {
        super(nama, noTelp);
        this.nama = nama;
        this.noTelp = noTelp;
        Admin.password = "admin123";
    }

    public boolean isAdmin(String input) {
        if (input.equals(Admin.password)) {
            return true;
        }
        return false;
    }

    public void ubahPassword(String newPassword) {
        Admin.password = newPassword;
    }

    public void setDiskon(int diskon) {
        Member.diskon = diskon / 100.0;
    }

    public void setHargaPrint(int harga) {
        Lembaran.hargaCopy = harga;
    }

    public void setHargaPrintWarna(int harga) {
        Lembaran.hargaPrintWarna = harga;
    }

    public void setHargaCopy(int harga) {
        Lembaran.hargaCopy = harga;
    }

    public void setHargaCopyWarna(int harga) {
        Lembaran.hargaCopyWarna = harga;
    }
}

class Konsumen extends Pengguna{
    protected double biaya;
    protected Lembaran lembaran;

    public Konsumen(String nama, String noTelp) {
        super(nama, noTelp);
        this.nama = nama;
        this.noTelp = noTelp;
    }

    public void setBiaya(int biaya) {
        this.biaya += biaya;
    }

    public void setBerkas(Lembaran newBerkas) {
        this.lembaran = newBerkas;
    }

    public double getBiaya() {
        return this.biaya;
    }

    @Override
    public void tampilkanData(){
        super.tampilkanData();
        System.out.println("Biaya\t\t\t: Rp. " + this.biaya);
        System.out.println("Lembaran\t\t\t: " + this.lembaran.getTotalHalaman() + " halaman");
    }
}

class Member extends Konsumen{
    static double diskon = 0.1;
    // private String histori;

    public Member(String nama, String noTelp) {
        super(nama, noTelp);
    }

    @Override
    public void setBiaya(int biaya) {
        this.biaya += (biaya * (1 - Member.diskon));
    }


    // public void setHistori(String histori){
    //     this.histori += histori + "\n";
    // }

    @Override
    public void tampilkanData() {
        System.out.println("Nama\t\t\t: " + this.nama);
        System.out.println("No. Telp\t\t: " + this.noTelp);
        System.out.printf("Biaya\t\t\t: Rp. %.0f (- %.0f%%)\n", this.biaya, (Member.diskon * 100));
        System.out.println("Lembaran\t\t\t: " + this.lembaran.getTotalHalaman() + " halaman");
    }   
}
