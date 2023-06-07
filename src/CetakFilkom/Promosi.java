package CetakFilkom;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

interface Applicable {
    public boolean isEligible(Pelanggan pelanggan);

    public boolean isPriceEligible(double harga);

    public boolean isOngkirEligible(double ongkir);

    public double hitungDiskon(double biaya);

    public double hitungCashBack(double biaya);

    public double hitungDiskonOngkir(double biaya);
}

public abstract class Promosi implements Applicable, Comparable<Promosi> {
    protected double diskon;
    protected double hargaMinimum;
    protected double ongkirMinimum;
    protected LocalDate tanggalAwal;
    protected LocalDate tanggalAkhir;
    protected double maksPotongan;

    public LocalDate getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        this.tanggalAwal = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > tanggalAwal.getDayOfMonth()) || (MM <= 0 || MM > tanggalAwal.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
    }

    public LocalDate getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(int dd, int MM, int YYYY) throws DateOutOfBoundsException {
        this.tanggalAkhir = LocalDate.of(YYYY, MM, dd);
        if ((dd <= 0 || dd > tanggalAkhir.getDayOfMonth()) || (MM <= 0 || MM > tanggalAkhir.getMonthValue())) {
            throw new DateOutOfBoundsException("Tanggal tidak sesuai!");
        }
    }

    public double getMaksPotongan() {
        return maksPotongan;
    }

    public void setMaksPotongan(double maksPotongan) {
        this.maksPotongan = maksPotongan;
    }

    public int compareTo(Promosi o) {
        if (this.diskon > o.diskon)
            return 1;
        else if (this.diskon == o.diskon)
            return 0;
        else
            return -1;
    }

    public boolean isExpired() {
        LocalDate current = LocalDate.now();
        return ChronoUnit.DAYS.between(current, tanggalAkhir) < 0;
    }

    public boolean isEligible(Pelanggan pelanggan) {
        if (pelanggan instanceof Member) {
            if (((Member) pelanggan).getMemberTime() > 30) {
                return true;
            }
        }
        return false;
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
        if ((biaya * (diskon)) < this.maksPotongan) {
            return biaya * (diskon);
        }
        return this.maksPotongan;
    }

}
