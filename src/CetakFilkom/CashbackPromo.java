package CetakFilkom;

public class CashbackPromo extends Promosi {
    /**
     * @param promoPersen merupakan nilai diskon dalam satuan persen
     * @param biaya       merupakan nilai yang berfungsi sebagai harga minimum
     *                    untuk bisa menggunakan promosi ini
     * @param ongkir      merupakan nilai yang berfungsi sebagai biaya ongkir
     *                    untuk bisa menggunakan promosi ini
     */
    public CashbackPromo(int promoPersen, double biaya, double ongkir) {
        this.diskon = promoPersen / 100.0;
        this.hargaMinimum = biaya;
        this.ongkirMinimum = ongkir;
    }

    public String getCashBackToString() {
        return String.format("Cashback %.0f%%", (this.diskon * 100));
    }

    @Override
    public boolean isEligible(Pelanggan pelanggan) {
        if (pelanggan instanceof Member) {
            if (((Member) pelanggan).getMemberTime() > 30) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPriceEligible(double harga) {
        if (harga > hargaMinimum)
            return true;
        return false;
    }

    @Override
    public boolean isOngkirEligible(double ongkir) {
        if (ongkir > ongkirMinimum)
            return true;
        return false;
    }

    @Override
    public double hitungDiskon(double biaya) {
        return 0;
    }

    @Override
    public double hitungCashBack(double biaya) {
        double cashback = biaya * diskon;
        if (cashback < this.maksPotongan) {
            return cashback;
        }
        return this.maksPotongan;
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        return 0;
    }
}
