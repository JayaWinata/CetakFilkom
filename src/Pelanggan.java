import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Pelanggan {
    protected int saldo;
    protected Lembaran lembaran;
    protected String nama;

    public int getSaldo() {
        return saldo;
    }

    public void tambahSaldo(int saldo) {
        this.saldo += saldo;
    }

    public void bayar(double biaya) {
        if (saldo < biaya) {
            throw new ArithmeticException();
        }
        this.saldo -= biaya;
    }

    public boolean isBalance(double biaya) {
        if (saldo >= biaya) {
            return true;
        }
        return false;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

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

    public Guest() {
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

    public long hitungMemberTime() {
        LocalDate date = LocalDate.now();
        LocalDate memberDate = LocalDate.of(tahunMember, bulanMember, tanggalMember);
        hariMember = ChronoUnit.DAYS.between(memberDate, date);
        return hariMember;
    }

    public long getMemberTime() {
        return hitungMemberTime();
    }
}
