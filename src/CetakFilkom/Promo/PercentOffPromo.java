package CetakFilkom.Promo;

import CetakFilkom.Pelanggan.*;

public class PercentOffPromo extends Promosi {
    /**
     * 
     * @param promoPersen merupakan nilai diskon dalam satuan persen
     * @param biaya       merupakan nilai yang berfungsi sebagai harga minimum
     *                    untuk bisa menggunakan promosi ini
     * @param ongkir      merupakan nilai yang berfungsi sebagai biaya ongkir
     *                    untuk bisa menggunakan promosi ini
     */
    public PercentOffPromo(int promoPersen, double biayaMinimum, double ongkirMinimum) {
        this.diskon = promoPersen / 100.0;
        this.hargaMinimum = biayaMinimum;
        this.ongkirMinimum = ongkirMinimum;
    }

    public String getDiskonToString() {
        return String.format("Diskon %.0f%%", (diskon * 100));
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
        if (harga >= hargaMinimum)
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
        if ((biaya * (diskon)) < this.maksPotongan) {
            return biaya * (diskon);
        }
        return this.maksPotongan;
    }

    @Override
    public double hitungCashBack(double biaya) {
        return 0;
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        return 0;
    }
}
