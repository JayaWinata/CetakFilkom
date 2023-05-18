import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public abstract class Pelanggan {
    protected int saldo;
    protected Lembaran lembaran;
    protected String nama;
    protected static HashMap<String, Lembaran> cart = new HashMap<>();

    public HashMap<String, Lembaran> getCart() {
        return cart;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo += saldo;
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

    public void addToCart(String key, Lembaran cartItem, int qty) {
        if (cart.containsKey(key)) {
            cart.get(key).tambahKuantitas(qty);
            return;
        }
        cartItem.setKuantitas(qty);
        cart.put(key, cartItem);
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

    public void hitungMemberTime(int dd, int MM, int YYYY) {
        LocalDate date = LocalDate.of(YYYY, MM, dd);
        LocalDate memberDate = LocalDate.of(tahunMember, bulanMember, tanggalMember);
        hariMember = ChronoUnit.DAYS.between(memberDate, date);
    }

    public long getMemberTime() {
        return this.hariMember;
    }
}
