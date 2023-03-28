interface Applicable {
    public boolean isEligible(Pelanggan pelanggan);

    public boolean isPriceEligible(double harga);

    public boolean isOngkirEligible(double ongkir);

    public double hitungDiskon(double biaya);

    public double hitungCashBack(double biaya);

    public double hitungDiskonOngkir(double biaya);
}

public abstract class Promosi implements Applicable, Comparable<Promosi> {
    protected String promoCode;
    protected double diskon;
    protected double hargaMinimum;
    protected double ongkirMinimum;

    public int compareTo(Promosi o) {
        if (this.diskon > o.diskon)
            return 1;
        else if (this.diskon == o.diskon)
            return 0;
        else
            return -1;
    }
}

class PercentOffPromo extends Promosi {
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
        return biaya * (1 - diskon);
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

class CashbackPromo extends Promosi {
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
        return cashback;
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        return 0;
    }
}

class OngkirPromo extends Promosi {
    /**
     * @param promoPersen merupakan nilai diskon dalam satuan persen
     * @param biaya       merupakan nilai yang berfungsi sebagai harga minimum
     *                    untuk bisa menggunakan promosi ini
     * @param ongkir      merupakan nilai yang berfungsi sebagai biaya ongkir
     *                    untuk bisa menggunakan promosi ini
     */
    public OngkirPromo(int promoPersen, double biaya, double ongkir) {
        this.diskon = promoPersen / 100.0;
        this.hargaMinimum = biaya;
        this.ongkirMinimum = ongkir;
    }

    public String getOngkirPromotoString() {
        return String.format("Diskon ongkir %.0f%%", (this.diskon * 100));
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
        return 0;
    }

    @Override
    public double hitungDiskonOngkir(double biaya) {
        return biaya * (1 - diskon);
    }

}
