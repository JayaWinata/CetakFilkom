import java.time.LocalDate;
// public class Pengguna {
//     protected String nama;
//     protected String noTelp;
import java.time.temporal.ChronoUnit;

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

    public void setLembaran(Lembaran lembaran) {
        this.lembaran = lembaran;
    }

    public Lembaran getLembaran() {
        return this.lembaran;
    }

    public Order makeOrder() {
        return new Order(this);
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
    private long hariMember;

    public Member(String nama, int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        LocalDate date = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > date.getDayOfMonth()) || (MM <= 0 || MM > date.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
        this.nama = nama;
        this.tanggalMember = dd;
        this.bulanMember = MM;
        this.tahunMember = YYYY;
    }

    public void hitungMemberTime(int dd, int MM, int YYYY) {
        LocalDate date = LocalDate.of(YYYY, MM, dd);
        LocalDate memberDate = LocalDate.of(tahunMember, bulanMember, tanggalMember);
        hariMember = ChronoUnit.DAYS.between(memberDate, date);
    }

    public long getMemberTime() {
        return this.hariMember;
    }
}
