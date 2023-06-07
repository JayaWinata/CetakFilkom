package CetakFilkom;

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
        if (nama == null) {
            return "";
        }
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
