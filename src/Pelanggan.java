// public class Pengguna {
//     protected String nama;
//     protected String noTelp;

//     public Pengguna(String nama, String noTelp) {
//         this.nama = nama;
//         this.noTelp = noTelp;
//     }

//     public void tampilkanData() {
//         System.out.println("Nama\t\t\t: " + this.nama);
//         System.out.println("No. Telp\t\t: " + this.noTelp);
//     }
// }

// class Admin extends Pengguna {
//     private static String password;

//     public Admin(String nama, String noTelp) {
//         super(nama, noTelp);
//         this.nama = nama;
//         this.noTelp = noTelp;
//         Admin.password = "admin123";
//     }

//     public boolean isAdmin(String input) {
//         if (input.equals(Admin.password)) {
//             return true;
//         }
//         return false;
//     }

//     public void ubahPassword(String newPassword) {
//         Admin.password = newPassword;
//     }
// }

public abstract class Pelanggan {
    protected Lembaran lembaran;
    protected String nama;

    public String getNama() {
        return this.nama;
    }

    public void makeOrder(Lembaran lembaran) {
        this.lembaran = lembaran;
    }
}

class Guest extends Pelanggan {
    public Guest(String nama) {
        this.nama = nama;
    }
}

class Member extends Pelanggan {

    private int tanggalMember;
    private int bulanMember;
    private int tahunMember;

    public Member(String nama, int dd, int MM, int YYYY) {
        this.nama = nama;
        this.tanggalMember = dd;
        this.bulanMember = MM;
        this.tahunMember = YYYY;
    }

    public int getMemberTime(int dd, int MM, int YYYY) {
        int count;
        int tahun = YYYY - tahunMember;
        int bulan = MM - bulanMember;
        int hari = dd - tanggalMember;
        count = (tahun * 365) + (bulan * 30) + hari;
        return count;
    }
    // public void setHistori(String histori){
    // this.histori += histori + "\n";
    // }

}
